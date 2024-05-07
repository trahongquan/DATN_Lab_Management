package LabManagement.Controller.LabController;

import LabManagement.ClassSuport.*;
import LabManagement.ClassSuport.Comparator;
import LabManagement.dao.*;
import LabManagement.dto.*;
import LabManagement.entity.*;
import LabManagement.service.BookingService.BookingService;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.PeopleService.PeopleService;
import LabManagement.service.RoleService.RoleService;
import LabManagement.service.LabService.LabService;
import LabManagement.service.authority.AuthorityService;
import LabManagement.service.booking_equi.Booking_equiService;
import LabManagement.service.content.ContentService;
import LabManagement.service.equipmentLab.EquipmentLabService;
import LabManagement.service.experimentGroupService.ExperimentGroupService;
import LabManagement.service.experimentReportService.ExperimentReportService;
import LabManagement.service.experimentTypeService.ExperimentTypeService;
import LabManagement.service.scoreService.ScoreService;
import LabManagement.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SpringBootApplication
@RequestMapping("/Lab")
public class LabController {
    String template = "admin/templateAdmin";
    public String Redirect(String url, Object success) {
        if (success instanceof Boolean) {
            if((boolean) success){
                String successParam = "?success=" + success;
                return "redirect:/Lab/" + url + successParam;
            } else{
                String successParam = "?unsuccess=" + true;
                return "redirect:/Lab/" + url + successParam;
            }
        } else {
            return "redirect:/Lab/" + url;
        }
    }

    private UserService userService;
    private AuthorityService authorityService;
    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private ContentService contentService;
    private EquipmentService equipmentService;
    private EquipmentLabService equipmentLabService;
    private Booking_equiService booking_equiService;
    private PeopleService peopleService;
    private RoleService roleService;
    private LabService labService;
    private ExperimentGroupService experimentGroupService;
    private ExperimentTypeService experimentTypeService;
    private ExperimentReportService experimentReportService;
    private ScoreService scoreService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LabController(UserService userService, AuthorityService authorityService, BookingService bookingService, BookingRepository bookingRepository, ContentService contentService, EquipmentService equipmentService, EquipmentLabService equipmentLabService, Booking_equiService booking_equiService, PeopleService peopleService, RoleService roleService, LabService labService, PasswordEncoder passwordEncoder, ExperimentGroupService experimentGroupService, ExperimentTypeService experimentTypeService, ExperimentReportService experimentReportService, ScoreService scoreService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.contentService = contentService;
        this.equipmentService = equipmentService;
        this.equipmentLabService = equipmentLabService;
        this.booking_equiService = booking_equiService;
        this.peopleService = peopleService;
        this.roleService = roleService;
        this.labService = labService;
        this.passwordEncoder = passwordEncoder;
        this.experimentTypeService = experimentTypeService;
        this.experimentGroupService = experimentGroupService;
        this.experimentReportService = experimentReportService;
        this.scoreService = scoreService;
    }

    /******************************************************************************************************/
                                        /** Khu vực Chung*/
    /******************************************************************************************************/

    private LabDTO Lab2LabDTO(Lab lab){
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        return new LabDTO(lab,people);
    }
    private List<LabDTO> Lab2LabDTO(List<Lab> labs){
        List<LabDTO> labDTOList = new ArrayList<>();
        labs.forEach(lab -> labDTOList.add(Lab2LabDTO(lab)));
        return labDTOList;
    }
    private LabDTO Lab2LabDTOandDateAndStatus(Lab lab){
        LocalDate currentDate = LocalDate.now();
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        List<Booking> bookings = bookingService.findAllByLab_id(lab.getId())
                                .stream().filter(booking -> {
                                    return booking.getBookingDate().toLocalDate().isAfter(currentDate)
                                    || booking.getBookingDate().toLocalDate().equals(currentDate);})
                                .collect(Collectors.toList());
        List<DateAndStatusLab> dateAndStatusLab = new ArrayList<>();
        if(!bookings.isEmpty()){bookings.forEach(booking -> dateAndStatusLab.add(new DateAndStatusLab(booking.getBookingDate(),booking.getConfirmStatus())));}
        return new LabDTO(lab,people,dateAndStatusLab);
    }
    private List<LabDTO> Labs2LabDTOsAndDateAndStatus(List<Lab> labs){
        List<LabDTO> labDTOS = new LinkedList<>();
        labs.forEach(lab -> labDTOS.add(Lab2LabDTOandDateAndStatus(lab)));
        return labDTOS;
    }
    private void ApprovePassCurrentDateBookings() { /** TỰ động duyệt các booking đã đến ngày*/
        LocalDate currentDate = LocalDate.now();
        List<Lab> labs = labService.getAllLabs().stream()
                .filter(lab -> (lab.getIsDelete()!=1))
                .collect(Collectors.toList());
        labs.forEach(lab -> {
            List<Booking> PassCurrentDateBookings = bookingService.findAllByLab_id(lab.getId())
                    .stream().filter(booking ->{
                                return (booking.getBookingDate().toLocalDate().isBefore(currentDate)
                                        || booking.getBookingDate().toLocalDate().equals(currentDate))
                                        && booking.getConfirmStatus().equals(ComfirmStatus.PENDDING);
                            })
                    .collect(Collectors.toList());
            if(!PassCurrentDateBookings.isEmpty()){
                PassCurrentDateBookings.forEach(booking -> {
                    booking.setConfirmStatus(ComfirmStatus.APPROVE);
                    booking.setAuto(AutoComfirmStatusBooking.AutoBoking);
                    bookingService.updateBooking(booking);
                });
            }
        });
    }

    @GetMapping({"/",""})
    public String getLabs(Model model) {
        ApprovePassCurrentDateBookings(); /** Check xem các đơn đã cồn pendding mà đã đến ngày đặt thì tự động approve */
        List<Lab> labs = labService.getAllLabs().stream()
                .filter(lab -> (lab.getIsDelete()!=1))
                .collect(Collectors.toList());
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateAndStatus(labs);
        List<List<DateAndStatusLab>> dateAndStatusLabsList = new ArrayList<>();
        labDTOS.forEach(labDTO -> dateAndStatusLabsList.add(labDTO.getDateAndStatusLab()));
        model.addAttribute("dateAndStatusLabsList", dateAndStatusLabsList);
        model.addAttribute("labDTOS", labDTOS);
        model.addAttribute("currentDate", LocalDate.now());
        return "index";
    }
    @GetMapping("/LabDetail/{id}")
    public String getLabDetail(Model model, @PathVariable("id") int id,
                               @RequestParam("date") Date date,
                               @RequestParam("username") String username,
                               @RequestParam(value = "success", defaultValue = "false") boolean success) {
        Lab lab = labService.findByLabId(id);
        LabDTO labDTO = Lab2LabDTO(lab);
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabService.findAllByLabId(id));
        List<Booking_equi> bookingEquiDTOs = new ArrayList<>();
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        /*List<People> reservationists = peopleService.getAllPeople().stream()
                                            .filter(people ->
                                                people.getIsDelete()!=1 &&
                                                    (CheckRole(people, "ROLE_RESERVATIONIST")
                                                    || CheckRole(people, "ROLE_TEACHER"))
                                            ).collect(Collectors.toList());*/
        List<People> reservationists = new ArrayList<>();
        reservationists.add(FindPeopleByUsername(username));
                model.addAttribute("labDTO", labDTO);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
        model.addAttribute("bookingEquiDTOs", bookingEquiDTOs);
        model.addAttribute("experimentReports", experimentReports);
        model.addAttribute("experimentTypes", experimentTypes);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("reservationists", reservationists);
        model.addAttribute("date", date);
        model.addAttribute("success", success);
        return "booking/booking-lab";
    }

    @PostMapping("/LabDetail/{id}")
    public String PostLabDetail(Model model, @PathVariable("id") int id,
                                @RequestParam("username") String username,
                                @RequestParam("date") Date date,
                                @RequestParam("name") String name,
                                @RequestParam("experiment_group") int experiment_groupId,
                                @RequestParam("experiment_type") int experiment_typeId,
                                @RequestParam("experiment_report") int experiment_reportId,
                                @RequestParam("reservationist") int reservationistId,
                                @RequestParam("class_name") String class_name,
                                @RequestParam("amount_of_people") int amount_of_people,
                                @RequestParam(value = "series", defaultValue = "") List<String> series,
                                @RequestParam("work_time") int work_time,
                                @RequestParam("note") String note) {
        Lab lab = labService.findByLabId(id); LabDTO labDTO = Lab2LabDTO(lab);
        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByLabId(id);
        List<EquipmentLab> equipmentLabsCoppy = new ArrayList<>(equipmentLabs);
        Content content = contentService.saveContent(new Content(name, reservationistId, experiment_typeId, experiment_reportId, class_name, amount_of_people, "[]"));
        Booking booking = bookingService.createBooking(new Booking(id,content.getId(),date, new ComfirmStatus().PENDDING, work_time, note,0, AutoComfirmStatusBooking.ManualBoking));
        List<Booking_equi> booking_equis = new ArrayList<>();
        series.forEach(seri -> {
            equipmentLabsCoppy.forEach( equipmentLab -> {
                equipmentLab.getEquipmentSerieList().forEach(equiLab_seri -> {
                    if(equiLab_seri.equals(seri)){
                        Booking_equi booking_equi = booking_equiService.findByBookingIdAndAndEquipmentId(booking.getId(),equipmentLab.getEquipmentId()) != null
                                                    ? booking_equiService.findByBookingIdAndAndEquipmentId(booking.getId(),equipmentLab.getEquipmentId())
                                                    : new Booking_equi(equipmentLab.getEquipmentId(),"[]", booking.getId());
                        booking_equi.AddSeri(booking_equiService,seri);
                        equipmentLab.getEquipmentSerieList().remove(seri);
                        booking_equis.add(booking_equi);
                    }
                });
            });
        });

        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<People> reservationists = peopleService.getAllPeople().stream()
                .filter(people ->
                        people.getIsDelete()!=1 &&
                                (CheckRole(people, "ROLE_RESERVATIONIST")
                                        || CheckRole(people, "ROLE_TEACHER"))
                ).collect(Collectors.toList());

        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabsCoppy);

        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
        model.addAttribute("labDTO", labDTO);
        model.addAttribute("bookingEquiDTOs", booking_equis);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("experimentReports", experimentReports);
        model.addAttribute("experimentTypes", experimentTypes);
        model.addAttribute("reservationists", reservationists);
        model.addAttribute("date", date);

//        return "booking/booking-lab";
        return Redirect("mybooking"+"?username="+username,"");
    }

    private List<String> GetUsedSeriesOfBookingEquiment(int bookingId){
        List<Booking_equi> booking_equis = booking_equiService.findAllByBookingId(bookingId);
        List<String> usedSeries = new ArrayList<>();
        booking_equis.forEach(booking_equi -> {
            booking_equi.getEquipmentSerieList().forEach(item -> {
                usedSeries.add(item);
            });
        });
        return usedSeries;
    }
    private List<EquipmentLab> GetEquiLabs_SeriNoUsed(List<String> usedSeries, int labId){
        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByLabId(labId);
        usedSeries.forEach(seri -> {
            equipmentLabs.forEach(equipmentLab -> {
                equipmentLab.getEquipmentSerieList().forEach(seriEquiLab -> {
                    if(seri.equals(seriEquiLab)){
                        equipmentLab.DelSeri(equipmentLabService, seri);
                    }
                });
            });
        });
        return equipmentLabs;
    }
    private People FindPeopleByUsername(String username){
        return peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid());
    }
    @GetMapping("/mybooking")
    public String myBooking(Model model, @RequestParam("username")String username){
        People teacher = FindPeopleByUsername(username);
        List<Content> contents = contentService.findAllByReservationistId(teacher.getId());
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        contents.forEach(content -> {
            Booking booking = bookingService.findByContent_id(content.getId());
            Lab lab = labService.findByLabId(booking.getLabid());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
        });
        /** Sắp xếp lại bookingDTOs theo thứ tự ngày gần nhất đến xa nhất = Override: BookingDateComparator */
        Collections.sort(bookingDTOs, new BookingDateComparator());
        model.addAttribute("bookingDTOs", bookingDTOs);
        return "booking/my-booking";
    }

    @GetMapping({"/myAccount/{username}"})
    public String myAccount(Model model, @PathVariable("username") String username,
                       @RequestParam(value = "changedPassfalse", defaultValue = "false") boolean changedPassfalse,
                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        Users user = userService.findByUsername(username);
        People people = peopleService.findByPeopleId(user.getPeopleid());
        model.addAttribute("success", success);
        model.addAttribute("peopleDTO",People2PeopleDTO(people) );
        model.addAttribute("changedPassfalse", changedPassfalse);
        return "managers/account-info";
    }


/******************************************************************************************************/
                                        /** Khu vực Admin*/
/******************************************************************************************************/

    @GetMapping({"/admin/Dashboard"})
    public String getAdminHomePage(Model model) {

        /** Dashboard Booking */

        List<Booking> bookingAPPROVE = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(ComfirmStatus.APPROVE)).collect(Collectors.toList());
        List<Booking> bookingCANCEL = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(ComfirmStatus.CANCEL)).collect(Collectors.toList());
        List<Booking> bookingPENDDING = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(ComfirmStatus.PENDDING)).collect(Collectors.toList());
        model.addAttribute("bookingAPPROVE", bookingAPPROVE.size());
        model.addAttribute("bookingCANCEL", bookingCANCEL.size());
        model.addAttribute("bookingPENDDING", bookingPENDDING.size());

        /** Dashboard Lab - So sánh số lượng đặt hàng ngày */
        List<Booking> bookingsToday = bookingService.getAllBookings()
                .stream().filter(booking -> booking.getBookingDate().toLocalDate().isEqual(LocalDate.now())
                                            && !booking.getConfirmStatus().equals(ComfirmStatus.CANCEL))
                .collect(Collectors.toList());
        List<Booking> bookingsYesterday = bookingService.getAllBookings()
                .stream().filter(booking -> booking.getBookingDate().toLocalDate().isEqual(LocalDate.now().minusDays(1))
                                            && !booking.getConfirmStatus().equals(ComfirmStatus.CANCEL))
                .collect(Collectors.toList());
        int comparatorLab = bookingsToday.size() - bookingsYesterday.size();
        Comparator comparatorLabComponet = CretedComparatorComponet(comparatorLab);
        model.addAttribute("comparatorLabComponet", comparatorLabComponet);

        List<Lab> labsBooking = new ArrayList<>();
        bookingsToday.forEach(booking -> labsBooking.add(labService.findByLabId(booking.getLabid())));
        model.addAttribute("labsBooking", labsBooking);

        /** Dashboard Equipment */
        List<Equipment> equipments = equipmentService.getAllEquipmentOnLine();
        List<String> seriEqui = new ArrayList<>();
        List<String> seriEquiFixed = new ArrayList<>();
        equipments.forEach(equipment -> {
            equipment.getSeriesAsList().forEach(seri -> seriEqui.add(seri));
            equipment.getSeriesFixedAsList().forEach(seri -> seriEquiFixed.add(seri));
        });
        List<EquipmentLab> equipmentLabs = equipmentLabService.getAllEquipmentLabs();
        List<String> seriEquiLab = new ArrayList<>();
        equipmentLabs.forEach(equipmentLab -> equipmentLab.getEquipmentSerieList().forEach(seri -> seriEquiLab.add(seri)));
        int quantityEqui = seriEqui.size() + seriEquiLab.size();
        int quantityEquiFixed = seriEquiFixed.size();
        model.addAttribute("quantityEqui", quantityEqui);
        model.addAttribute("quantityEquiFixed", quantityEquiFixed);

        /** Dashboard People */
        List<People> teachers = GetPeoPleByRole("ROLE_TEACHER");
        model.addAttribute("teachers", teachers.size());
        List<People> reservationist = GetPeoPleByRole("ROLE_RESERVATIONIST");
        model.addAttribute("reservationist", reservationist.size());
        List<People> admins = GetPeoPleByRole("ROLE_ADMIN");
        model.addAttribute("admins", admins.size());
        List<People> managers = GetPeoPleByRole("ROLE_MANAGER");
        model.addAttribute("managers", managers.size());

        /** Dashboard Lab - Score*/
        model.addAttribute("labsOnLineAndScores", LabsOnLineAndScore());
        model.addAttribute("title", "Tổng quan");
        return template;
    }
    private List<LabsOnLineAndScore> LabsOnLineAndScore(){
        List<Score> scores = scoreService.getAllScore();
        List<LabsOnLineAndScore> labsOnLineAndScores = new ArrayList<>();
        List<Lab> labsOnLine = labService.getAllLabsOnLine();
        labsOnLine.forEach(lab -> {
            LabsOnLineAndScore labsOnLineAndScore = new LabsOnLineAndScore();
            labsOnLineAndScore.setLab(lab);
            List<Booking> bookings = bookingService.findAllByLab_id(lab.getId());
            bookings.forEach(booking -> {
                Content content = contentService.getContentById(booking.getContentid());
                scores.forEach(score -> {
                    if(score.getExperimentTypeId()==content.getExperimentType() && score.getExperimentReportId()==content.getExperimentReport()){
                        labsOnLineAndScore.setScore(labsOnLineAndScore.getScore() + score.getScore());
                    }
                });
            });
            labsOnLineAndScores.add(labsOnLineAndScore);
        });
        Collections.sort(labsOnLineAndScores, new LabsOnLineAndScoreComparator());
        return labsOnLineAndScores;
    }
    private List<People> GetPeoPleByRole(String role){
        List<People> PeoPleByRole = peopleService.getAllPeople().stream()
                .filter(people -> people.getIsDelete()==0 && CheckRole(people, role)
                ).collect(Collectors.toList());
        return PeoPleByRole;
    }
    private List<People> GetPeoPleByRoles(String role1, String role2){
        List<People> PeoPleByRole = peopleService.getAllPeople().stream()
                .filter(people -> people.getIsDelete()==0 &&
                        (CheckRole(people, role1) || CheckRole(people, role2))
                ).collect(Collectors.toList());
        return PeoPleByRole;
    }
    private Comparator CretedComparatorComponet(int comparatorInt){
        Comparator comparatorComponet = new Comparator();
        if(comparatorInt < 0 ) {
            comparatorComponet.setQuantity(comparatorInt*-1);
            comparatorComponet.setCompared(Compared.DECREASE);
        } else if (comparatorInt == 0) {
            comparatorComponet.setQuantity(comparatorInt);
            comparatorComponet.setCompared(Compared.EQUAL);
        } else {
            comparatorComponet.setQuantity(comparatorInt);
            comparatorComponet.setCompared(Compared.INCREASE);
        }
        return comparatorComponet;
    }

    /******************************************************************************************/
                                        /** Room Lab*/
    /******************************************************************************************/
    @GetMapping("/admin/Room")
    public String RoomList(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success) {
        List<Lab> labs = labService.getAllLabs();
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateAndStatus(labs);
        model.addAttribute("labDTOS", labDTOS.stream().filter(labDTO -> labDTO.getIsDeleted()==0).collect(Collectors.toList()));
        model.addAttribute("success", success);
        model.addAttribute("title", "QLDS phòng thí nghiệm");
        return template;
    }
    private List<String> GetUsedSeriesOfEquipmentLabs(){
        List<EquipmentLab> equipmentLabs = equipmentLabService.getAllEquipmentLabs();
        List<String> usedSeries = equipmentLabs.stream()
                .flatMap(equipmentLab -> Arrays.stream(equipmentLab.getEquipmentSeries()
                        .replace("[", "").replace("]", "")
                        .split(",\\s*")))
                .collect(Collectors.toList());
        return usedSeries;
    }
    private List<EquipmentDTO> GetEquisDTO_SeriNoUsed(List<String> usedSeries){
        List<EquipmentDTO> equipmentDTOS = Equis2EquiDTOS(
                equipmentService.getAllEquipment()
        );
        usedSeries.forEach(seri -> {
            for (int i = 0; i < equipmentDTOS.size(); i++) {
                for (int j = 0; j < equipmentDTOS.get(i).getSerieList().size() ; j++) {
                    if(seri.equals(equipmentDTOS.get(i).getSerieList().get(j))){
                        equipmentDTOS.get(i).getSerieList().remove(j);
                        break;
                    }
                }
            }
        });
        return equipmentDTOS;
    }

    @GetMapping("/admin/Room/add")
    public String AddRoom(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success) {
        List<People> peoples = peopleService.getAllPeople().stream().filter(people -> CheckRole(people,"ROLE_MANAGER")||CheckRole(people,"ROLE_ADMIN")).collect(Collectors.toList());;
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);
        model.addAttribute("peoples", peoples);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("success", success);
        model.addAttribute("title", "Thêm mới phòng thí nghiệm");
        return template;
    }

    private int DelSeriInEquiBySeri(String seri) {
        List<Equipment> equipments = equipmentService.getAllEquipment();
        for (Equipment equipmentCopy : equipments) {
            List<String> seriesList = equipmentCopy.getSeriesAsList();
            for (String seriCopy : seriesList) {
                if (seriCopy.equals(seri)) {
                    Equipment equipment = equipmentService.findByEquipmentId(equipmentCopy.getId());
                    equipment.getSeriesAsList().remove(seri);
                    equipment.setSeries(equipment.getSeriesAsList().toString());
                    equipmentService.updateEquipment(equipment);
                    return equipmentCopy.getId();
                }
            }
        }
        return -1;
    }
    @PostMapping("/admin/Room/add")
    public String submitForm(@RequestParam("LabName") String LabName,
                             @RequestParam("LabCapacity") int LabCapacity,
                             @RequestParam("LabLocation") String LabLocation,
                             @RequestParam("Management") int Managementid,
                             @RequestParam(value = "series", defaultValue = "") List<String> selectedSeries) {
        Lab lab = labService.createLab(new Lab(LabName, LabCapacity, LabLocation, Managementid, 0));
        for (String seri : selectedSeries) {
            int equi_id = DelSeriInEquiBySeri(seri);
            EquipmentLab equipmentLab = equipmentLabService.findByLabIdAndEquipmentId(lab.getId(), equi_id);
            if (equipmentLab.getId() == 0){
                equipmentLabService.saveEquipmentLab(new EquipmentLab(lab.getId(), equi_id, new ToList().StringToList(seri)));
            } else {
                equipmentLab.AddSeri(equipmentLabService, seri);
            }
        }
        return Redirect("admin/Room",true);
    }
    private List<EquipmentDTO> Equis2EquiDTOS(List<Equipment> equipments){
        List<EquipmentDTO> equipmentDTOS = new ArrayList<>();
        equipments.forEach(equipment -> equipmentDTOS.add(new EquipmentDTO(equipment)));
        return equipmentDTOS;
    }
    private EquipmentLabDTO EquiLab2EquiLabDTO(EquipmentLab equipmentLab){
        EquipmentLabDTO equipmentLabDTO = new EquipmentLabDTO(equipmentLab, equipmentLab.getEquipmentSerieList());
        equipmentLabDTO.setName(equipmentService.findByEquipmentId(equipmentLab.getEquipmentId()).getName());
        return equipmentLabDTO;
    }
    private List<EquipmentLabDTO> EquiLabs2EquiLabDTOs(List<EquipmentLab> equipmentLabs){
        List<EquipmentLabDTO> equipmentLabDTOS = new ArrayList<>();
        equipmentLabs.forEach(equipmentLab -> {
            equipmentLabDTOS.add(EquiLab2EquiLabDTO(equipmentLab));
        });
        return equipmentLabDTOS;
    }

    @PostMapping("/admin/room/removeEquiLab")
    public String removeEquiLab(@RequestParam("LabId") int LabId,
                                @RequestParam("removeEquiLab") int removeEquiLab){
        EquipmentLab equipmentLabRemove = equipmentLabService.getEquipmentLabById(removeEquiLab);
        Equipment equipment = equipmentService.findByEquipmentId(equipmentLabRemove.getEquipmentId());
        equipmentLabRemove.getEquipmentSerieList().forEach(item -> {
            equipment.AddSeri(equipmentService, item);
        });
        equipmentLabService.deleteEquipmentLab(removeEquiLab);
        return Redirect("admin/room/showFormForUpdate/"+LabId,true);
    }

    @GetMapping("/admin/room/showFormForUpdate/{id}")
    public String RoomDetail(Model model, @PathVariable(value = "id") int id,
                            @RequestParam(value = "success", defaultValue = "false") boolean success) {
        Lab lab = labService.findByLabId(id);
        LabDTO labDTO = Lab2LabDTO(lab);
        List<People> Managers = peopleService.getAllPeople().stream().filter(people -> CheckRole(people,"ROLE_MANAGER")||CheckRole(people,"ROLE_ADMIN")).collect(Collectors.toList());
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabService.findAllByLabId(id));
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);

        model.addAttribute("Managers", Managers);
        model.addAttribute("labDTO", labDTO);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("success", success);
        model.addAttribute("title", "Chi tiết phòng thí nghiệm");
        return template;
    }

    private void RemoveFromEquiSeriAndAddSeri2EquiLab(EquipmentService equipmentService,
                                                      EquipmentLabService equipmentLabService,
                                                      List<String> series,
                                                      Lab lab){
        List<Equipment> equipments = equipmentService.getAllEquipment();
        series.forEach(seri -> {
            for (Equipment equipment : equipments) {
                for (String seriEqui: equipment.getEquipmentSerieList()) {
                    if(seriEqui.equals(seri)){
                        /** Remove seri trong Equi*/
                        equipment.DelSeri(equipmentService, seriEqui);
                        /** Add seri vào trong EquiLab*/
                        EquipmentLab equipmentLab = equipmentLabService.findByLabIdAndEquipmentId(lab.getId(),equipment.getId());
                        if(equipmentLab.getId() != 0){
                            equipmentLab.AddSeri(equipmentLabService, seri);
                        } else {
                            EquipmentLab equipmentLabNew = new EquipmentLab(lab.getId(),equipment.getId(),new ArrayList<>());
                            equipmentLabNew.AddSeri(equipmentLabService, seri);
                        }
                    }
                }
            }
        });
    }

    @PostMapping("/admin/room/showFormForUpdate/{id}")
    public String RoomDetailPost(Model model, @PathVariable(value = "id") int id,
                                 @RequestParam("labName") String labName,
                                 @RequestParam("capacity") int capacity,
                                 @RequestParam("location") String location,
                                 @RequestParam("lab_managemet_id") int lab_managemet_id,
                                 @RequestParam(value = "series", defaultValue = "[]") List<String> series) {
        Lab lab = labService.findByLabId(id);
        lab.setLabName(labName);
        lab.setCapacity(capacity);
        lab.setLocation(location);
        lab.setLab_managemet_id(lab_managemet_id);
        labService.updateLab(lab);
        RemoveFromEquiSeriAndAddSeri2EquiLab(equipmentService, equipmentLabService, series, lab);
        return Redirect("admin/room/showFormForUpdate/"+id,true);
    }

    @PostMapping("/admin/room/delete/{id}")
    public String DelLab(@PathVariable("id") int id ){
        labService.deleteLab(id);
        return Redirect("admin/Room",true);
    }

    /******************************************************************************************/
                                            /** Equipment */
    /******************************************************************************************/

    @GetMapping({"/admin/Equipment"})
    public String EquipmentList(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success) {
        List<Equipment> allEquipment = equipmentService.getAllEquipment();
        model.addAttribute("allEquipment", allEquipment.stream().filter(equipment -> equipment.getIsDeleted()==0).collect(Collectors.toList()));
        model.addAttribute("success",success);
        model.addAttribute("title", "QLDS thiết bị");
        return template;
    }

    @GetMapping("/admin/Equipment/add")
    public String AddEquipment(Model model){
        model.addAttribute("title", "Thêm mới Thiết bị");
        return template;
    }
    @PostMapping("/admin/Equipment/add")
    public String AddEquipment(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("series") List<String> series) {
        Equipment equipment = equipmentService.createEquipment(new Equipment(name, series.toString(), "[]", description, series.size(),0));

        return Redirect("admin/Equipment",true);
    }

    @GetMapping("/admin/Equipment/showFormForUpdate/{id}")
    public String EquipmentDetail(Model model, @PathVariable(value = "id") int id) {
        Equipment equipment = equipmentService.findByEquipmentId(id);
        model.addAttribute("equipment", equipment);
        model.addAttribute("title", "Thông tin chi tiết thiết bị");
        return template;
    }

    @PostMapping("/admin/Equipment/showFormForUpdate/{id}")
    public String EquipmentDetailPost(@PathVariable(value = "id") int id,
                                      @RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam(value = "series", defaultValue = "[]") List<String> series,
                                      @RequestParam(value = "seriesfixed", defaultValue = "[]") List<String> seriesfixed) {
        Equipment equipment = equipmentService.findByEquipmentId(id);
        equipment.setName(name);
        equipment.setDescription(description);
        equipment.setSeries(series.toString());
        equipment.setSeriesFixed(seriesfixed.toString());
        equipment.setQuantity(series.size());
        equipmentService.updateEquipment(equipment);
        return Redirect("admin/Equipment",true);
    }

    @PostMapping("/admin/Equipment/delete/{id}")
    public String DelEquipment(@PathVariable("id") int id ){
        equipmentService.deleteEquipment(id);
        return Redirect("admin/Equipment",true);
    }

    /******************************************************************************************/
                                        /** Manager */
    /******************************************************************************************/

    private PeopleDTO People2PeopleDTO(People people){
        Users user = userService.findByPeopleId(people.getId());
        Authority authority = authorityService.findAuthorityByUsername(user.getUsername());
        return new PeopleDTO(people, user, authority);
    }
    private List<PeopleDTO> Peoples2PeopleDTOs(List<People> peoples){
        List<PeopleDTO> peopleDTOS = new ArrayList<>();
        peoples.forEach(people -> peopleDTOS.add(People2PeopleDTO(people)));
        return peopleDTOS;
    }

    @GetMapping({"/admin/Manager"})
    public String Manager(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<People> managers = peopleService.getAllPeople()
                .stream()
                .filter(people -> {
                    return people.getIsDelete()==0 && !CheckRole(people, "ROLE_TEACHER");
                })
                .collect(Collectors.toList());
        List<PeopleDTO> managerDTOS = Peoples2PeopleDTOs(managers);
        model.addAttribute("managerDTOS", managerDTOS);
        model.addAttribute("success",success);
        model.addAttribute("title", "DS admin và người quản lý");
        return template;
    }

    @GetMapping("/admin/Manager/add")
    public String AddManager(Model model){
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("title", "Thêm mới người quản lý");
        return template;
    }
    @PostMapping("/admin/Manager/add")
    public String AddManager(@RequestParam("name") String name,
                            @RequestParam("rank") String rank,
                            @RequestParam("unit") String unit,
                            @RequestParam("militaryNumber") long militaryNumber,
                            @RequestParam("contact") String contact,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("authority") String role) {
        People people = new People(name, rank, unit, militaryNumber, contact, 0);
        peopleService.createPeople(people);
        Authority authority = new Authority(username, role);
        authorityService.createAuthority(authority);
        Users user = new Users(username, passwordEncoder.encode(password), people.getId(), 1);
        userService.createUser(user);
        return Redirect("admin/Manager",true);
    }

    @GetMapping("/admin/Manager/showFormForUpdate/{id}")
    public String ManagerDetail(Model model, @PathVariable(value = "id") int id) {
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        People manager = peopleService.findByPeopleId(id);
        PeopleDTO managerDTO = People2PeopleDTO(manager);
        model.addAttribute("manager", managerDTO);
        model.addAttribute("title", "Thông tin chi tiết tài khoản");
        return template;
    }

    @PostMapping("/admin/Manager/showFormForUpdate/{id}")
    public String ManagerDetailPost(@PathVariable(value = "id") int id,
                                    @RequestParam("name") String name,
                                    @RequestParam("rank") String rank,
                                    @RequestParam("unit") String unit,
                                    @RequestParam("militaryNumber") long militaryNumber,
                                    @RequestParam("contact") String contact,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("authority") String role) {

        People people = peopleService.findByPeopleId(id);
        people.setName(name);
        people.setRank(rank);
        people.setUnit(unit);
        people.setMilitaryNumber(militaryNumber);
        people.setContact(contact);
        peopleService.updatePeople(people);

        Authority authority = authorityService.findAuthorityByUsername(username);
        authority.setAuthority(role);
        authorityService.createAuthority(authority);

        Users user = userService.findByUsername(username);
        if(!password.equals("Không có mk đâu")) user.setPassword(passwordEncoder.encode(password));
        userService.save(user);

        return Redirect("admin/Manager",true);
    }

    @PostMapping("/admin/Manager/delete/{id}")
    public String DelManager(@PathVariable("id") int id ){
        peopleService.deletePeople(id);
        return Redirect("admin/Manager",true);
    }


    /******************************************************************************************/
                                            /** Teacher */
    /******************************************************************************************/

    private boolean CheckRole(People people, String role){
        return authorityService.findAuthorityByUsername(
                        userService
                                .findByPeopleId(people.getId())
                                .getUsername()
                ).getAuthority()
                        .equals(role);
    }

    @GetMapping({"/admin/Teacher"})
    public String Teacher(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<People> teacher = peopleService.getAllPeople()
                .stream()
                .filter(people -> {
                    return people.getIsDelete()==0 && CheckRole(people,"ROLE_TEACHER");
                })
                .collect(Collectors.toList());
        List<PeopleDTO> teacherDTOS = Peoples2PeopleDTOs(teacher);
        model.addAttribute("teacherDTOS", teacherDTOS);
        model.addAttribute("success",success);
        model.addAttribute("title", "QLDS Giáo viên");
        return template;
    }

    @GetMapping("/admin/Teacher/add")
    public String AddTeacher(Model model){
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("title", "Thêm mới giáo viên");
        return template;
    }
    @PostMapping("/admin/Teacher/add")
    public String AddTeacher(@RequestParam("name") String name,
                            @RequestParam("rank") String rank,
                            @RequestParam("unit") String unit,
                            @RequestParam("militaryNumber") long militaryNumber,
                            @RequestParam("contact") String contact,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("authority") String role) {
        AddManager(name, rank, unit, militaryNumber, contact, username, password, role);
        return Redirect("admin/Teacher",true);
    }

    @GetMapping("/admin/Teacher/showFormForUpdate/{id}")
    public String TeacherDetail(Model model, @PathVariable(value = "id") int id) {
        ManagerDetail(model,id);
        return template;
    }

    @PostMapping("/admin/Teacher/showFormForUpdate/{id}")
    public String TeacherDetailPost(@PathVariable(value = "id") int id,
                                    @RequestParam("name") String name,
                                    @RequestParam("rank") String rank,
                                    @RequestParam("unit") String unit,
                                    @RequestParam("militaryNumber") long militaryNumber,
                                    @RequestParam("contact") String contact,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("authority") String role) {
        ManagerDetailPost(id, name, rank, unit, militaryNumber, contact, username, password, role);
        return Redirect("admin/Teacher",true);
    }

    @PostMapping("/admin/Teacher/delete/{id}")
    public String DelTeacher(@PathVariable("id") int id ){
        peopleService.deletePeople(id);
        return Redirect("admin/Teacher",true);
    }

    /******************************************************************************************/
                                            /** Role */
    /******************************************************************************************/

    @GetMapping({"/admin/Role"})
    public String Role(Model model,
                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("success", success);
        model.addAttribute("title", "Quản lý Quyền");
        return template;
    }

    @PostMapping("/admin/Role/add")
    public String AddRole(Model model, @RequestParam("role") String roleSt) {
        try {
            Roles role = new Roles(roleSt);
            roleService.createRole(role);
            return Redirect("admin/Role",true);
        } catch (Exception e){
            return Redirect("admin/Role",false);
        }
    }

    @PostMapping("/admin/Role/delete")
    public String DelRole(Model model, @RequestParam("roleid") int roleid) {
        try {
            roleService.deleteRole(roleid);
            return Redirect("admin/Role",true);
        } catch (Exception e){
            return Redirect("admin/Role",false);
        }
    }

    /******************************************************************************************/
                                            /** myAccount */
    /******************************************************************************************/


    @GetMapping({"/admin/myAccount/{username}"})
    public String Role(Model model, @PathVariable("username") String username,
                       @RequestParam(value = "changedPassfalse", defaultValue = "false") boolean changedPassfalse,
                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        Users user = userService.findByUsername(username);
        People people = peopleService.findByPeopleId(user.getPeopleid());
        model.addAttribute("peopleDTO",People2PeopleDTO(people) );
        model.addAttribute("success", success);
        model.addAttribute("changedPassfalse", changedPassfalse);
        model.addAttribute("title", "Thông tin tài khoản");
        return template;
    }

    @PostMapping("/admin/myAccount")
    public String AdminChangePass(@RequestParam("oldpass") String oldpass,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password){
        Users user = userService.findByUsername(username);
        if(passwordEncoder.matches(oldpass,user.getPassword())){
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
            return Redirect("admin/myAccount/"+username,true);
        } else {
            return "redirect:/Lab/admin/myAccount/"+username+"?changedPassfalse=true";
        }
    }



    /******************************************************************************************/
                                /** Laboratory Booking Management */
    /******************************************************************************************/

    private void GetBookingByStatus(Model model, String status){
        List<Booking> bookings = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(status)).collect(Collectors.toList());
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        bookings.forEach(booking -> {
            Content content = contentService.getContentById(booking.getContentid());
            Lab lab = labService.findByLabId(booking.getLabid());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
        });
        /** Sắp xếp lại bookingDTOs theo thứ tự ngày gần nhất đến xa nhất = Override: BookingDateComparator */
        Collections.sort(bookingDTOs, new BookingDateComparator());
        model.addAttribute("bookingDTOs", bookingDTOs);
    }

    @GetMapping({"/admin/LabBookingManagement/Pendding"})
    public String PeddingBooking(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ComfirmStatus.PENDDING);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL đơn chờ duyệt");
        return template;
    }
    private ContentDTO Content2ContentDTO(Content content) {
        ExperimentType experimentType = experimentTypeService.getExperimentTypeById(content.getExperimentType());
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(experimentType.getExperimentGroupId());
        ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
        People reservationist = peopleService.findByPeopleId(content.getReservationistId());
        return new ContentDTO(content, reservationist, experimentGroup, experimentType, experimentReport);
    }

    @GetMapping({"/admin/ShowBookingPendding/{id}"})
    public String ShowBookingPendding(Model model, @PathVariable("id") int id,
                                      @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(id);
        ContentDTO contentDTO = Content2ContentDTO(contentService.getContentById(booking.getContentid()));
        LabDTO labDTO = Lab2LabDTO(labService.findByLabId(booking.getLabid()));
        List<Booking_equi> booking_equis = booking_equiService.findAllByBookingId(booking.getId());
        BookingDTO bookingDTO = new BookingDTO(booking,contentDTO,labDTO,booking_equis);
        model.addAttribute("bookingDTO", bookingDTO);
        model.addAttribute("date", booking.getBookingDate());

        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        model.addAttribute("experimentReports", experimentReports);
        model.addAttribute("experimentTypes", experimentTypes);
        model.addAttribute("experimentGroups", experimentGroups);

        List<People> reservationists = peopleService.getAllPeople().stream().filter(people -> {
            return people.getIsDelete()!=1 && (CheckRole(people,"ROLE_TEACHER") || CheckRole(people,"ROLE_RESERVATIONIST"));
        }).collect(Collectors.toList());
        model.addAttribute("reservationists", reservationists);

        List<Booking_EquiDTO> bookingEquiDTOs = new ArrayList<>();
        bookingDTO.getBooking_equis().forEach(booking_equi -> {
            Equipment equipment = equipmentService.findByEquipmentId(booking_equi.getEquipmentId());
            bookingEquiDTOs.add(new Booking_EquiDTO(booking_equi, equipment));
        });
        model.addAttribute("bookingEquiDTOs", bookingEquiDTOs);

        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByLabId(booking.getLabid());

        /** Loại bỏ các seri Equi đã được booking trong Equi Lab - Còn lại các seri chưa được book*/
        for (EquipmentLab equipmentLab : equipmentLabs) {
            for (Booking_equi booking_equi : bookingDTO.getBooking_equis()) {
                if (equipmentLab.getEquipmentId() == booking_equi.getEquipmentId()) {
                    List<String> equipmentSerieList = equipmentLab.getEquipmentSerieList();
                    List<String> bookingSerieList = booking_equi.getEquipmentSerieList();
                    for (String seriLab : new ArrayList<>(equipmentSerieList)) {
                        for (String seriBooking : new ArrayList<>(bookingSerieList)) {
                            if (seriLab.equals(seriBooking)) {
                                equipmentSerieList.remove(seriLab);
                                equipmentLab.setEquipmentSeries(equipmentSerieList.toString());
                            }
                        }
                    }
                }
            }
        }
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabs);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs); /** Đã trừ những Equi đã được đặt*/
        model.addAttribute("success", success);
        model.addAttribute("title", "Chi tiết đơn chờ duyệt");
        return template;
    }

    @PostMapping("/admin/ShowBookingPendding/removeBookingEqui")
    public String removeBookingEqui(@RequestParam("bookingId") int bookingId,
                                @RequestParam("removeBookingEqui") int removeBookingEqui){
        booking_equiService.deleteBookingEquipment(removeBookingEqui);
        return Redirect("admin/ShowBookingPendding/"+bookingId,true);
    }

    @PostMapping("/admin/ShowBookingPendding/{id}")
    public String PostBookingPendding(Model model, @PathVariable("id") int id,
//                                      @RequestParam("selectlabid") int selectlabid,
                                      @RequestParam("name") String name,
                                      @RequestParam("experiment_group") int experiment_groupId,
                                      @RequestParam("experiment_type") int experiment_typeId,
                                      @RequestParam("experiment_report") int experiment_reportId,
                                      @RequestParam("reservationist") int reservationistId,
                                      @RequestParam("class_name") String class_name,
                                      @RequestParam("amount_of_people") int amount_of_people,
                                      @RequestParam(value = "series", defaultValue = "") List<String> series,
                                      @RequestParam("work_time") int work_time,
                                      @RequestParam("note") String note) {
        Booking booking = bookingService.findByBookingId(id);
//        booking.setLabid(selectlabid);
        booking.setWork_times(work_time);
        booking.setNote(note);
        bookingService.updateBooking(booking);

        Content content = contentService.getContentById(booking.getContentid());
        content.setName(name);
        content.setClassName(class_name);
        content.setReservationistId(reservationistId);
        content.setExperimentType(experiment_typeId);
        content.setExperimentReport(experiment_reportId);
        content.setAmountOfPeople(amount_of_people);

        contentService.saveContent(content);

        return Redirect("admin/ShowBookingPendding/"+id,true);
    }
    @GetMapping({"/admin/ShowBookingPendding/Approve"})
    public String ApproveBookingPendding(Model model,
                                 @RequestParam("bookingId") int bookingId,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setAuto(AutoComfirmStatusBooking.ManualBoking);
        booking.setConfirmStatus(ComfirmStatus.APPROVE);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Pendding", true);
    }

    @GetMapping({"/admin/ShowBookingPendding/Cancel"})
    public String CancelBookingPendding(Model model,
                                         @RequestParam("bookingId") int bookingId,
                                         @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setConfirmStatus(ComfirmStatus.CANCEL);
        booking.setAuto(AutoComfirmStatusBooking.ManualBoking);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Pendding", true);
    }

    @GetMapping({"/admin/LabBookingManagement/Approve"})
    public String ApproveBooking(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ComfirmStatus.APPROVE);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL đơn đã duyệt");
        return template;
    }

    @GetMapping({"/admin/ShowBookingApprove/{id}"})
    public String ShowBookingApprove(Model model, @PathVariable("id") int id,
                                      @RequestParam(value = "success", defaultValue = "false") boolean success){
        ShowBookingPendding(model, id, success);
        model.addAttribute("title", "Chi tiết đơn đã duyệt");
        return template;
    }
    @GetMapping({"/admin/LabBookingManagement/UnApprove"})
    public String UnApproveShowBookingApprove(Model model,
                                        @RequestParam("bookingId") int bookingId,
                                        @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setAuto(AutoComfirmStatusBooking.NULL);
        booking.setConfirmStatus(ComfirmStatus.PENDDING);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Approve",true);
    }

    @GetMapping({"/admin/LabBookingManagement/Cancel"})
    public String CancelBooking(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ComfirmStatus.CANCEL);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL đơn đã hủy");
        return template;
    }
    @GetMapping({"/admin/ShowBookingCancel/{id}"})
    public String ShowBookingCancel(Model model, @PathVariable("id") int id,
                                     @RequestParam(value = "success", defaultValue = "false") boolean success){
        ShowBookingPendding(model, id, success);
        model.addAttribute("title", "Chi tiết đơn đã hủy");
        return template;
    }
    @GetMapping({"/admin/LabBookingManagement/UnCancel"})
    public String UnCancelShowBookingApprove(Model model,
                                              @RequestParam("bookingId") int bookingId,
                                              @RequestParam(value = "success", defaultValue = "false") boolean success){
        UnApproveShowBookingApprove(model, bookingId, success);
        return Redirect("admin/LabBookingManagement/Cancel",true);
    }

    /******************************************************************************************/
                                    /** Experiment management & Score */
    /******************************************************************************************/


                        /******************************************************/
                                        /** Experiment Group */
                        /*****************************************************/

    private List<ExperimentGroup> getUnusedExperimentGroups(List<ExperimentGroup> experimentGroups,
                                                            List<ExperimentType> experimentTypes,
                                                          List<Boolean> booleans){
        List<ExperimentGroup> unusedExperimentGroups = new ArrayList<>();
        for (ExperimentGroup experimentGroup : experimentGroups) {
            boolean found = false;
            for (ExperimentType experimentType : experimentTypes) {
                if (experimentType.getExperimentGroupId() == experimentGroup.getId()) {
                    found = true;
                    booleans.add(found);
                    break;
                }
            }
            if (!found) {
                unusedExperimentGroups.add(experimentGroup);
                booleans.add(found);
            }
        }
        return unusedExperimentGroups;
    }

    @GetMapping({"/admin/ExperimentManagement/ExperimentGroup"})
    public String ExperimentGroup(Model model,
                                  @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                                  @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<Boolean> booleans = new ArrayList<>();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentGroup> unusedExperimentGroups = getUnusedExperimentGroups(experimentGroups, experimentTypes, booleans);
        model.addAttribute("unusedExperimentGroups", unusedExperimentGroups);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("booleans", booleans);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        model.addAttribute("title", "QL nhóm loại hình thí nghiệm");
        return template;
    }
    @PostMapping({"/admin/ExperimentManagement/ExperimentGroup/del"})
    public String ExperimentGroupDel(Model model,
                                  @RequestParam("experiment") int experimentid){
        experimentGroupService.deleteExperimentGroup(experimentid);
        return Redirect("admin/ExperimentManagement/ExperimentGroup", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentGroup/add"})
    public String ExperimentGroupAdd(Model model,
                                  @RequestParam("experiment") String experiment){
        experimentGroupService.saveExperimentGroup(new ExperimentGroup(experiment));
        return Redirect("admin/ExperimentManagement/ExperimentGroup", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentGroup/edit"})
    public String ExperimentGroupEdit(Model model,
                                     @RequestParam("groupid") int groupid,
                                     @RequestParam("groupName") String groupName){
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(groupid);
        experimentGroup.setGroupName(groupName);
        experimentGroupService.saveExperimentGroup(experimentGroup);
        return Redirect("admin/ExperimentManagement/ExperimentGroup", true);
    }

                        /******************************************************/
                                         /** Experiment Type */
                        /*****************************************************/

    private List<ExperimentType> getUnusedExperimentTypes(List<ExperimentType> experimentTypes,
                                                          List<Score> scores, List<Content> contents,
                                                          List<CheckExperiment> checkExperiments){
        List<ExperimentType> unusedExperimentTypes = new ArrayList<>();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        for (ExperimentType experimentType : experimentTypes) {
            boolean found = false;
            for (Score score : scores) {
                if (score.getExperimentTypeId() == experimentType.getId()) {
                    found = true;
                    checkExperiments.add(new CheckExperiment(experimentType.getId(), found));
                    break;
                }
            }
            if (!found) {
                unusedExperimentTypes.add(experimentType);
                checkExperiments.add(new CheckExperiment(experimentType.getId(), found));
            }
        }
        List<ExperimentType> unusedExperimentTypesCopy = new ArrayList<>(unusedExperimentTypes);
        for (ExperimentType experimentType : unusedExperimentTypesCopy) {
            for (Content content : contents) {
                if (content.getExperimentType() == experimentType.getId()) {
                    checkExperiments.forEach(checkExperiment -> {
                        if(checkExperiment.getId() == experimentType.getId()){
                            checkExperiment.setaBoolean(true);
                            unusedExperimentTypes.remove(experimentType);
                        }
                    });
                }
            }
            for (ExperimentReport experimentReport : experimentReports) {
                experimentReport.getExpTypeIdList().forEach(typeId -> {
                    if (typeId == experimentType.getId()){
                        System.out.println(true);
                        checkExperiments.forEach(checkExperiment -> {
                            if(checkExperiment.getId() == experimentType.getId()){
                                checkExperiment.setaBoolean(true);
                                unusedExperimentTypes.remove(experimentType);
                            }
                        });
                    }
                });
            }
        }
        return unusedExperimentTypes;
    }

    @GetMapping({"/admin/ExperimentManagement/ExperimentType"})
    public String ExperimentType(Model model,
                                 @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<CheckExperiment> checkExperiments = new ArrayList<>();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<Score> scores = scoreService.getAllScore();
        List<Content> contents = contentService.getAllContents();
        List<ExperimentType> unusedExperimentTypes = getUnusedExperimentTypes(experimentTypes, scores, contents, checkExperiments);
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        model.addAttribute("unusedExperimentTypes", unusedExperimentTypes);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("experimentTypes", ExpTypes2ExpTypeDTOs(experimentTypes));
        model.addAttribute("checkExperiments", checkExperiments);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        model.addAttribute("title", "QL loại hình thí nghiệm");
        return template;
    }

    private ExperimentTypeDTO ExpType2ExpTypeDTO(ExperimentType experimentType){
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(experimentType.getExperimentGroupId());
        return new ExperimentTypeDTO(experimentType, experimentGroup);
    }

    private List<ExperimentTypeDTO> ExpTypes2ExpTypeDTOs(List<ExperimentType> experimentTypes){
        List<ExperimentTypeDTO> experimentTypeDTOS = new ArrayList<>();
        experimentTypes.forEach(experimentType -> experimentTypeDTOS.add(ExpType2ExpTypeDTO(experimentType)));
        return experimentTypeDTOS;
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentType/del"})
    public String ExperimentTypeDel(Model model,
                                     @RequestParam("experiment") int experimentid){
        experimentTypeService.deleteExperimentType(experimentid);
        return Redirect("admin/ExperimentManagement/ExperimentType", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentType/add"})
    public String ExperimentTypeAdd(Model model,
                                     @RequestParam("experimentGroupId") int experimentGroupId,
                                     @RequestParam("experimentType") String experimentType){
        experimentTypeService.saveExperimentType(new ExperimentType(experimentType,experimentGroupId));
        return Redirect("admin/ExperimentManagement/ExperimentType", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentType/edit"})
    public String ExperimentTypeEdit(Model model,
//                                      @RequestParam("experimentGroupId") int experimentGroupId,
                                      @RequestParam("typeid") int typeid,
                                      @RequestParam("typeName") String typeName){
        ExperimentType experimentType = experimentTypeService.getExperimentTypeById(typeid);
        experimentType.setTypeName(typeName);
//        experimentType.setExperimentGroupId(experimentGroupId);
        experimentTypeService.saveExperimentType(experimentType);
        return Redirect("admin/ExperimentManagement/ExperimentType", true);
    }
//    @PostMapping({"/admin/ExperimentManagement/ExperimentType/UnlinkGroup"})
//    public String UnlinkGroup(Model model,
//                              @RequestParam("experimentTypeId") int experimentTypeId){
//        ExperimentType experimentType = experimentTypeService.getExperimentTypeById(experimentTypeId);
//        experimentType.setExperimentGroupId();
//        experimentTypeService.saveExperimentType(experimentType);
//        return Redirect("admin/ExperimentManagement/ExperimentType", true);
//    }


                            /******************************************************/
                                            /** Experiment Report */
                            /*****************************************************/

    private List<ExperimentReport> getUnusedExperimentReports(List<ExperimentReport> experimentReports,
                                                              List<Score> scores, List<Content> contents,
                                                              List<CheckExperiment> checkExperiments){
        List<ExperimentReport> unusedExperimentReports = new ArrayList<>();
        for (ExperimentReport experimentReport : experimentReports) {
            boolean found = false;
            for (Score score : scores) {
                if (score.getExperimentTypeId() == experimentReport.getId()) {
                    found = true;
                    checkExperiments.add(new CheckExperiment(experimentReport.getId(), found));
                    break;
                }
            }
            if (!found) {
                unusedExperimentReports.add(experimentReport);
                checkExperiments.add(new CheckExperiment(experimentReport.getId(), found));
            }
        }
        List<ExperimentReport> unusedExperimentTypesCopy = new ArrayList<>(unusedExperimentReports);
        for (ExperimentReport experimentReport : unusedExperimentTypesCopy) {
            for (Content content : contents) {
                if (content.getExperimentType() == experimentReport.getId()) {
                    checkExperiments.forEach(checkExperiment -> {
                        if(checkExperiment.getId() == experimentReport.getId()){
                            checkExperiment.setaBoolean(true);
                            unusedExperimentReports.remove(experimentReport);
                        }
                    });
                    break;
                }
            }
        }
        return unusedExperimentReports;
    }

    @GetMapping({"/admin/ExperimentManagement/ExperimentReport"})
    public String ExperimentReport(Model model,
                                 @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        List<CheckExperiment> checkExperiments = new ArrayList<>();
        List<Score> scores = scoreService.getAllScore();
        List<Content> contents = contentService.getAllContents();
        List<ExperimentReport> unusedExperimentReports = getUnusedExperimentReports(experimentReports, scores, contents, checkExperiments);

        model.addAttribute("unusedExperimentReports", unusedExperimentReports);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("experimentTypes", experimentTypes);
        model.addAttribute("experimentReports", ExpReports2ExpReportDTOs(experimentReports));
        model.addAttribute("checkExperiments", checkExperiments);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        model.addAttribute("title", "QL loại báo cáo thí nghiệm");
        return template;
    }

    private ExperimentReportDTO ExpReport2ExpReportDTO(ExperimentReport experimentReport){
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(experimentReport.getExperimentGroupId());
        List<ExperimentType> experimentTypes = new ArrayList<>();
        experimentReport.getExpTypeIdList().forEach(typeId -> {
            ExperimentType experimentType = experimentTypeService.getExperimentTypeById(typeId);
            experimentTypes.add(experimentType);
        });
        return new ExperimentReportDTO(experimentReport, experimentGroup, experimentTypes);
    }

    private List<ExperimentReportDTO> ExpReports2ExpReportDTOs(List<ExperimentReport> experimentReports){
        List<ExperimentReportDTO> experimentReportDTOS = new ArrayList<>();
        experimentReports.forEach(experimentReport -> experimentReportDTOS.add(ExpReport2ExpReportDTO(experimentReport)));
        return experimentReportDTOS;
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentReport/del"})
    public String ExperimentReportDel(Model model,
                                    @RequestParam("experiment") int experimentid){
        experimentReportService.deleteExperimentReport(experimentid);
        return Redirect("admin/ExperimentManagement/ExperimentType", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentReport/add"})
    public String ExperimentReportAdd(Model model,
                                    @RequestParam("experimentGroupId") int experimentGroupId,
                                    @RequestParam(value = "experimentTypeId", defaultValue = "") List<Integer> experimentTypeId,
                                    @RequestParam("experimentReport") String experimentReport){
        experimentReportService.saveExperimentReport(new ExperimentReport(experimentReport,experimentGroupId, experimentTypeId.toString()));
        return Redirect("admin/ExperimentManagement/ExperimentReport", true);
    }

    @PostMapping({"/admin/ExperimentManagement/ExperimentReport/edit"})
    public String ExperimentReportEdit(Model model,
                                     @RequestParam("experimentReportId") int experimentReportId,
                                     /*@RequestParam("experimentGroupId") int experimentGroupId,
                                     @RequestParam(value = "experimentTypeId", defaultValue = "") List<Integer> experimentTypeId,*/
                                     @RequestParam("ReportType") String ReportType){
        ExperimentReport experimentReport = experimentReportService.getExperimentReportById(experimentReportId);
        experimentReport.setReportType(ReportType);
//        experimentReport.setExperimentGroupId(experimentGroupId);
//        experimentReport.setExperimentTypeId(experimentTypeId.toString());
        experimentReportService.saveExperimentReport(experimentReport);
        return Redirect("admin/ExperimentManagement/ExperimentReport", true);
    }


                            /******************************************************/
                                            /** Score */
                            /*****************************************************/

    @GetMapping({"/admin/ExperimentManagement/Score"})
    public String Score(Model model,
                        @RequestParam(value = "success", defaultValue = "false") boolean success,
                        @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        List<CheckExperiment> checkScores = new ArrayList<>();
        List<CheckExperiment> checkExpReport = new ArrayList<>();
        List<CheckExperiment> checkExpType = new ArrayList<>();
        List<Boolean> checkExpGroup = new ArrayList<>();

        List<Score> scores = scoreService.getAllScore();
        List<Content> contents = contentService.getAllContents();

        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();

        List<ExperimentReport> unusedExperimentReports = getUnusedExperimentReports(experimentReports, scores, contents, checkExpReport);
        List<ExperimentType> unusedExperimentTypes = getUnusedExperimentTypes(experimentTypes, scores, contents, checkExpType);
        List<ExperimentGroup> unusedExperimentGroups = getUnusedExperimentGroups(experimentGroups, experimentTypes, checkExpGroup);

        model.addAttribute("unusedExperimentReports", experimentReports);
        model.addAttribute("unusedExperimentTypes", experimentTypes);
        model.addAttribute("unusedExperimentGroups", experimentGroups);
//        model.addAttribute("unusedExperimentReports", unusedExperimentReports);
//        model.addAttribute("unusedExperimentTypes", unusedExperimentTypes);
//        model.addAttribute("unusedExperimentGroups", unusedExperimentGroups);
//        model.addAttribute("experimentReports", ExpReports2ExpReportDTOs(experimentReports));
        model.addAttribute("checkScores", checkScores);
        model.addAttribute("scores", Scores2ScoreDTOS(scores));
        model.addAttribute("scoresList", scores);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        model.addAttribute("title", "QL điểm chấm phòng thí nghiệm");
        return template;
    }

    private ScoreDTO Score2ScoreDTO(Score score){
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(score.getExperimentGroupId());
        ExperimentType experimentType = experimentTypeService.getExperimentTypeById(score.getExperimentTypeId());
        ExperimentReport experimentReport = experimentReportService.getExperimentReportById(score.getExperimentReportId());
        return new ScoreDTO(score, experimentGroup, experimentType, experimentReport);
    }

    private List<ScoreDTO> Scores2ScoreDTOS(List<Score> scores){
        List<ScoreDTO> scoreDTOS = new ArrayList<>();
        scores.forEach(score -> scoreDTOS.add(Score2ScoreDTO(score)));
        return scoreDTOS;
    }

    @PostMapping({"/admin/ExperimentManagement/Score/add"})
    public String ExperimentReportAdd(Model model,
                                      @RequestParam("experiment_group") int experimentGroupId,
                                      @RequestParam("experiment_type") int experimentTypeId,
                                      @RequestParam("experiment_report") int experimentReportId,
                                      @RequestParam("score") double score){

        Score scoreExit = scoreService.FindByExperimentGroupIdAndExperimentTypeIdAndExperimentReportId(experimentGroupId, experimentTypeId, experimentReportId);
        if(scoreExit != null){
//            return "redirect:/Lab/admin/ExperimentManagement/Score?unsuccess=true";
            return Redirect("admin/ExperimentManagement/Score", false);
        } else {
            scoreService.saveScore(new Score(experimentGroupId, experimentTypeId, experimentReportId, score));
            return Redirect("admin/ExperimentManagement/Score", true);
        }
    }

    @PostMapping({"/admin/ExperimentManagement/Score/del"})
    public String ScoreDel(Model model,
                            @RequestParam("delScoreid") int scoreid){
        scoreService.delScoreById(scoreid);
        return Redirect("admin/ExperimentManagement/Score", true);
    }

    @PostMapping({"/admin/ExperimentManagement/Score/edit"})
    public String ScoreEdit(Model model,
                            @RequestParam("scoreid") int scoreid,
                            @RequestParam("score") double DbScore){
        Score score = scoreService.getScoreById(scoreid);
        score.setScore(DbScore);
        scoreService.saveScore(score);
        return Redirect("admin/ExperimentManagement/Score", true);
    }

    @GetMapping({"/admin/LabRankings"})
    public String LabRankings(Model model,
                              @RequestParam(value = "success", defaultValue = "false") boolean success,
                              @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        model.addAttribute("labsOnLineAndScores",LabsOnLineAndScore());
        return template;
    }

//    @GetMapping({"/admin/ExperimentManagement/ExperimentType"})
//    @GetMapping({"/admin/ExperimentManagement/ExperimentReport"})
//    @GetMapping({"/admin/ExperimentManagement/Score"})

}
