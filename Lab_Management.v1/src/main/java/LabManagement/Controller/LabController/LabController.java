package LabManagement.Controller.LabController;

import LabManagement.ClassSuport.*;
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
import LabManagement.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SpringBootApplication
@RequestMapping("/Lab")
public class LabController {
    String template = "admin/templateAdmin";
    public String Redirect(String url, Object success) {
        if (success instanceof Boolean) {
            String successParam = "?success=" + success;
            return "redirect:/Lab/" + url + successParam;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LabController(UserService userService, AuthorityService authorityService, BookingService bookingService, BookingRepository bookingRepository, ContentService contentService, EquipmentService equipmentService, EquipmentLabService equipmentLabService, Booking_equiService booking_equiService, PeopleService peopleService, RoleService roleService, LabService labService, PasswordEncoder passwordEncoder, ExperimentGroupService experimentGroupService, ExperimentTypeService experimentTypeService, ExperimentReportService experimentReportService) {
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
    }

    /******************************************************************************************************/
                                        /** Khu vực Chung*/
    /******************************************************************************************************/

    private LabDTO Lab2LabDTO(Lab lab){
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        return new LabDTO(lab,people);
    }
    private LabDTO Lab2LabDTOandDateAndStatus(Lab lab){
        LocalDate currentDate = LocalDate.now();
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        List<Booking> bookings = bookingService.findAllByLab_id(lab.getId())
                                .stream().filter(booking -> {
                                    return booking.getBooking_Date().toLocalDate().isAfter(currentDate)
                                    || booking.getBooking_Date().toLocalDate().equals(currentDate);})
                                .collect(Collectors.toList());
        List<DateAndStatusLab> dateAndStatusLab = new ArrayList<>();
        if(!bookings.isEmpty()){bookings.forEach(booking -> dateAndStatusLab.add(new DateAndStatusLab(booking.getBooking_Date(),booking.getConfirm_Status())));}
        return new LabDTO(lab,people,dateAndStatusLab);
    }
    private List<LabDTO> Labs2LabDTOsAndDateAndStatus(List<Lab> labs){
        List<LabDTO> labDTOS = new LinkedList<>();
        labs.forEach(lab -> labDTOS.add(Lab2LabDTOandDateAndStatus(lab)));
        return labDTOS;
    }
    private void ApprovePassCurrentDateBookings() {
        LocalDate currentDate = LocalDate.now();
        List<Lab> labs = labService.getAllLabs().stream()
                .filter(lab -> (lab.getIsDelete()!=1))
                .collect(Collectors.toList());
        labs.forEach(lab -> {
            List<Booking> PassCurrentDateBookings = bookingService.findAllByLab_id(lab.getId())
                    .stream().filter(booking ->{
                                return (booking.getBooking_Date().toLocalDate().isBefore(currentDate)
                                        || booking.getBooking_Date().toLocalDate().equals(currentDate))
                                        && booking.getConfirm_Status().equals(ComfirmStatus.PENDDING);
                            })
                    .collect(Collectors.toList());
            if(!PassCurrentDateBookings.isEmpty()){
                PassCurrentDateBookings.forEach(booking -> {
                    booking.setConfirm_Status(ComfirmStatus.APPROVE);
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
        List<Booking_equi> booking_equis = booking_equiService.findByBookingId(bookingId);
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
        List<Lab> rooms = labService.getAllLabs();
        List<LabDTO> roomDTOS = Labs2LabDTOsAndDateAndStatus(rooms);
        model.addAttribute("roomDTOS", roomDTOS);
//        System.out.println(roomDTOS);
        return template;
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
        List<People> peoples = peopleService.getAllPeople();
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);
        model.addAttribute("peoples", peoples);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("success", success);
        //        model.addAttribute("addphone", addphone); /** cách xử lý ở backEnd*/
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
        List<People> Managers = peopleService.getAllPeople();
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabService.findAllByLabId(id));
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);

        model.addAttribute("Managers", Managers);
        model.addAttribute("labDTO", labDTO);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("success", success);
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
        return template;
    }

    @GetMapping("/admin/Equipment/add")
    public String AddEquipment(){
        return template;
    }
    @PostMapping("/admin/Equipment/add")
    public String AddEquipment(/*,@RequestParam(value = "addphone", defaultValue = "false") boolean addphone*/
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("series") List<String> series) {
        Equipment equipment = equipmentService.createEquipment(new Equipment(name, series.toString(), "[]", description, series.size(),0));

        return Redirect("admin/Equipment",true);
    }

    @GetMapping("/admin/Equipment/showFormForUpdate/{id}")
    public String EquipmentDetail(Model model, @PathVariable(value = "id") int id) {
        Equipment equipment = equipmentService.findByEquipmentId(id);
        model.addAttribute("equipment", equipment);
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
        return template;
    }

    @GetMapping("/admin/Manager/add")
    public String AddManager(Model model){
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
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
        return template;
    }

    @GetMapping("/admin/Teacher/add")
    public String AddTeacher(Model model){
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
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
        List<Booking> bookings = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirm_Status().equals(status)).collect(Collectors.toList());
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
        return template;
    }
    @GetMapping({"/admin/LabBookingManagement/Approve"})
    public String ApproveBooking(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ComfirmStatus.APPROVE);
        model.addAttribute("success", success);
        return template;
    }
    @GetMapping({"/admin/LabBookingManagement/Cancel"})
    public String CancelBooking(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ComfirmStatus.CANCEL);
        model.addAttribute("success", success);
        return template;
    }

//    @GetMapping({"/admin/Approve"})
//    @GetMapping({"/admin/Pendding"})
//    @GetMapping({"/admin/CancelBoooking"})
//    @GetMapping({"/admin/Lab"})

//    @GetMapping({"/admin/ExportLabs"})
//    @GetMapping({"/admin/ExportEquipments"})

//    @GetMapping({"/admin/Roles"})
//    @GetMapping({"/admin/AddManager"})
//    @GetMapping({"/admin/AddTeacher"})
//    @GetMapping({"/admin/Teacher"})
}
