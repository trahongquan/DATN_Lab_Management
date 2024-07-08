package LabManagement.Controller.LabController;

import LabManagement.ClassSuport.*;
import LabManagement.ClassSuport.Comparator;
import LabManagement.ClassSuport.EquidLevel.AllEquipment_ReadOnly;
import LabManagement.ClassSuport.EquidLevel.EquidAndLevel;
import LabManagement.dao.*;
import LabManagement.dto.*;
import LabManagement.dtoExport.*;
import LabManagement.entity.*;
import LabManagement.service.BookingService.BookingService;
import LabManagement.service.InventoryEquipmentService.InventoryEquipmentService;
import LabManagement.service.InventoryLabService.InventoryLabService;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.LessonService.LessonService;
import LabManagement.service.ManagingUnitService.ManagingUnitService;
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
import LabManagement.service.unitService.UnitService;
import LabManagement.service.users.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.sun.rowset.internal.Row;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                if(url.contains("?")){
                    String successParam = "&success=" + success;
                    return "redirect:/Lab/" + url + successParam;
                } else {
                    String successParam = "?success=" + success;
                    return "redirect:/Lab/" + url + successParam;
                }
            } else{
                if(url.contains("?")){
                    String successParam = "&unsuccess=" + true;
                    return "redirect:/Lab/" + url + successParam;
                } else {
                    String successParam = "?unsuccess=" + true;
                    return "redirect:/Lab/" + url + successParam;
                }
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
    private UnitService unitService;
    private InventoryLabService inventoryLabService;
    private InventoryEquipmentService inventoryEquipmentService;
    private ManagingUnitService managingUnitService;
    private LessonService lessonService;

    @Autowired
    public LabController(UserService userService, AuthorityService authorityService, BookingService bookingService, BookingRepository bookingRepository, ContentService contentService, EquipmentService equipmentService, EquipmentLabService equipmentLabService, Booking_equiService booking_equiService, PeopleService peopleService, RoleService roleService, LabService labService, PasswordEncoder passwordEncoder, ExperimentGroupService experimentGroupService, ExperimentTypeService experimentTypeService, ExperimentReportService experimentReportService, ScoreService scoreService, UnitService unitService, InventoryLabService inventoryLabService, InventoryEquipmentService inventoryEquipmentService, ManagingUnitService managingUnitService, LessonService lessonService) {
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
        this.unitService = unitService;
        this.inventoryLabService = inventoryLabService;
        this.inventoryEquipmentService = inventoryEquipmentService;
        this.managingUnitService = managingUnitService;
        this.lessonService = lessonService;
    }

    /******************************************************************************************************/
                                        /** Khu vực Chung*/
    /******************************************************************************************************/

    private LabDTO Lab2LabDTO(Lab lab){
        People people = peopleService.findByPeopleId(lab.getLabManagemetId());
        return new LabDTO(lab,people);
    }
    private List<LabDTO> Lab2LabDTO(List<Lab> labs){
        List<LabDTO> labDTOList = new ArrayList<>();
        labs.forEach(lab -> labDTOList.add(Lab2LabDTO(lab)));
        return labDTOList;
    }
    private LabDTO Lab2LabDTOandDateAndStatus(Lab lab){
        LocalDate currentDate = LocalDate.now();
        People people = peopleService.findByPeopleId(lab.getLabManagemetId());
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
            List<Booking> PassCurrentDateBookings = GetBookingFromDateAndStatus(lab, currentDate, ConfirmStatus.PENDDING);
            if(!PassCurrentDateBookings.isEmpty()){
                PassCurrentDateBookings.forEach(booking -> {
                    booking.setConfirmStatus(ConfirmStatus.APPROVE);
                    booking.setAuto(AutoComfirmStatusBooking.AutoBoking);
                    bookingService.updateBooking(booking);
                });
            }
            List<Booking> bookingApproveNotUsed = GetBookingFromDateAndStatus(lab, currentDate.minusDays(1), ConfirmStatus.APPROVE, ConfirmUsed.UNUSED);
            if(!bookingApproveNotUsed.isEmpty()){
                bookingApproveNotUsed.forEach(booking -> {
                    booking.setConfirmStatus(ConfirmStatus.CANCEL);
                    booking.setAuto(AutoComfirmStatusBooking.AutoBoking);
                    booking.setConfirmUsed(ConfirmUsed.CANCEL);
                    bookingService.updateBooking(booking);
                });
            }
        });
    }

    /** Lấy các booking còn pennding =< ngày hiện tại và theo trạng thái sử dụng */
    private List<Booking> GetBookingFromDateAndStatus(Lab lab, LocalDate currentDate, String status, String used){
        List<Booking> bookingList = GetBookingFromDateAndStatus(lab,currentDate, status)
                .stream().filter(booking ->
                booking.getConfirmUsed().equals(used)
                ).collect(Collectors.toList());
        return bookingList;
    }
    private List<Booking> GetBookingFromDateAndStatus(Lab lab, LocalDate currentDate, String status){
        List<Booking> PassCurrentDateBookings = bookingService.findAllByLab_id(lab.getId())
                .stream().filter(booking ->{
                    return (booking.getBookingDate().toLocalDate().isBefore(currentDate)
                            || booking.getBookingDate().toLocalDate().equals(currentDate))
                            && booking.getConfirmStatus().equals(status);
                })
                .collect(Collectors.toList());
        return PassCurrentDateBookings;
    }

    @GetMapping({"/",""})
    public String getLabs(Model model, Principal principal,
                          @RequestParam(value = "managingUnitId", defaultValue = "0") int managingUnitId,
                          @RequestParam(value = "RedirectLabAdmin", defaultValue = "true") boolean RedirectLabAdmin) {
        ApprovePassCurrentDateBookings(); /** Check xem các đơn đã cồn pendding mà đã đến ngày đặt thì tự động approve */
        String username = principal.getName();
        if((GetAuthorityByUsername(username).equals(RoleSystem.ROLE_ADMIN)
                ||GetAuthorityByUsername(username).equals(RoleSystem.ROLE_MANAGER))
                && RedirectLabAdmin){
            return Redirect("admin/Dashboard?username="+username,"");
        } else {
            List<Lab> labs = new ArrayList<>();
            if(managingUnitId==0){
                labs = labService.getAllLabsOnLine();
            } else {
                String managingUnitName = managingUnitService.getManagingUnitById(managingUnitId).getDepartmentName();
                labs = labService.getAllLabsOnLine().stream().filter(lab -> lab.getManagingUnit().equals(managingUnitName)).collect(Collectors.toList());
                model.addAttribute("managingUnitName", managingUnitName);
            }
            List<LabDTO> labDTOS = Labs2LabDTOsAndDateAndStatus(labs);
            List<List<DateAndStatusLab>> dateAndStatusLabsList = new ArrayList<>();
            labDTOS.forEach(labDTO -> dateAndStatusLabsList.add(labDTO.getDateAndStatusLab()));

            List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
            managingUnits.add(0, new ManagingUnit(0));

            model.addAttribute("managingUnits", managingUnits);
            model.addAttribute("dateAndStatusLabsList", dateAndStatusLabsList);
            model.addAttribute("labDTOS", labDTOS);
            model.addAttribute("currentDate", LocalDate.now());
            model.addAttribute("RedirectLabAdmin", RedirectLabAdmin);
            return "index";
        }
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
        List<People> reservationists = new ArrayList<>();
        People peoplex = GetPeopleByUsername(username);
        reservationists.add(peoplex);
        if(GetAuthorityByUsername(username).equals(RoleSystem.ROLE_ADMIN)
        || GetAuthorityByUsername(username).equals(RoleSystem.ROLE_MANAGER)){
            for (People people : GetPeoPleByRole(RoleSystem.ROLE_RESERVATIONIST)) {
                if(people != peoplex){
                    reservationists.add(people);
                }
            }
            for (People people : GetPeoPleByRole("ROLE_TEACHER")) {
                if(people != peoplex){
                    reservationists.add(people);
                }
            }
            reservationists.remove(peoplex);
        }

        List<Lesson> lessons = lessonService.findAllByLabId(id);

        model.addAttribute("lessons", lessons);
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
                                @RequestParam(value = "lesson", defaultValue = "0") int lesson,
                                @RequestParam("note") String note) {
        Lab lab = labService.findByLabId(id); LabDTO labDTO = Lab2LabDTO(lab);
        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByLabId(id);
        List<EquipmentLab> equipmentLabsCoppy = new ArrayList<>(equipmentLabs);
        Content content = contentService.saveContent(new Content(name, reservationistId, experiment_typeId, experiment_reportId, lesson, class_name, amount_of_people, "[]"));
        Booking booking = bookingService.createBooking(new Booking(id,content.getId(),date, new ConfirmStatus().PENDDING, work_time, note,0, AutoComfirmStatusBooking.NULL, ConfirmUsed.UNUSED));

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
        List<People> reservationists = new ArrayList<>();
        reservationists.add(peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid()));
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
        return Redirect("mybooking"+"?username="+username,true);
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
    private People GetPeopleByUsername(String username){
        return peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid());
    }
    @GetMapping("/mybooking")
    public String myBooking(Model model, @RequestParam("username")String username,
                            @RequestParam(value = "search", defaultValue = "false") boolean search,
                            @RequestParam(value = "AndOr", defaultValue = "false") boolean AndOr,
                            @RequestParam(value = "inputdatasearch", defaultValue = "NoSearch") String inputdatasearch,
                            @RequestParam(value = "datetimepicker", defaultValue = "1970-01-01") Date datetimepicker,
                            @RequestParam(value = "success", defaultValue = "false") boolean success,
                            @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                            ArrayList<BookingDTO> bookingDTOs ){
        People teacher = GetPeopleByUsername(username);
        List<Content> contents = contentService.findAllByReservationistId(teacher.getId());
        contents.forEach(content -> {
            Booking booking = bookingService.findByContent_id(content.getId());
            Lab lab = labService.findByLabId(booking.getLabid());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            if(search){ /** Nếu tìm kiếm */
                if(AndOr){ /** Nếu tìm kiếm và - hoặc */
                    if (booking.getBookingDate().toLocalDate().isEqual(datetimepicker.toLocalDate())
                            && lab.getLabName().toLowerCase().contains(inputdatasearch.toLowerCase())) {
                        bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));}
                } else {
                    if (booking.getBookingDate().toLocalDate().isEqual(datetimepicker.toLocalDate())
                            || lab.getLabName().toLowerCase().contains(inputdatasearch.toLowerCase())) {
                        bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));}
                }
            } else {
                bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
            }
        });
        if(datetimepicker.toLocalDate().toString().equals("1970-01-01")){
            model.addAttribute("datetimepicker", LocalDate.now());
        } else {
            model.addAttribute("datetimepicker", datetimepicker);
        }
        if(inputdatasearch.equals("NoSearch")){
            model.addAttribute("inputdatasearch", "");
        } else {
            model.addAttribute("inputdatasearch", inputdatasearch);
        }
        /** Sắp xếp lại bookingDTOs theo thứ tự ngày gần nhất đến xa nhất = Override: BookingDateComparator */
        Collections.sort(bookingDTOs, new BookingDateComparator());

        model.addAttribute("AndOr", AndOr);
        model.addAttribute("search", search);
        model.addAttribute("bookingDTOs", bookingDTOs);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return "booking/my-booking";
    }

    private ArrayList<BookingDTO> GetMyBooking(String username, boolean search, boolean AndOr, String inputdatasearch,
                                           Date datetimepicker, boolean success, boolean unsuccess){
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        ArrayList<BookingDTO> bookingDTOs = new ArrayList<>();
        myBooking(model, username, search, AndOr, inputdatasearch, datetimepicker,success, unsuccess, bookingDTOs);
        return bookingDTOs;
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
    @PostMapping({"/myAccount"})
    public String TeacherChangePass(@RequestParam("oldpass") String oldpass,
                                    @RequestParam("username") String username,
                                    @RequestParam("newpass") String newpass,
                                    @RequestParam("retypenewpass") String retypenewpass){
        Users user = userService.findByUsername(username);
        if(passwordEncoder.matches(oldpass,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newpass));
            userService.save(user);
            return Redirect("myAccount/"+username,true);
        } else {
            return "redirect:/Lab/myAccount/"+username+"?changedPassfalse=true";
        }
    }
    @GetMapping("/myBookingDetail/{id}")
    public String myBookingDetail(Model model, @PathVariable("id") int id,
                                  @RequestParam("username") String username,
                                  @RequestParam(value = "success", defaultValue = "false") boolean success,
                                  @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        ShowBookingPendding(model, id, false);
        List<People> reservationists = new ArrayList<>();
        GetPeoPleByRole("ROLE_RESERVATIONIST").forEach(people -> reservationists.add(people));
        if(GetPeopleByUsername(username) != null){
            reservationists.add(GetPeopleByUsername(username));
        }
        model.addAttribute("reservationists", reservationists);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return "booking/booking-detail-teacher";
    }

    @PostMapping({"/myBookingDetail/{id}"})
    public String myBookingDetailPost(Model model, @PathVariable("id") int id,
                                      @RequestParam("username") String username,
                                      @RequestParam("name") String name,
                                      @RequestParam("experiment_group") int experiment_groupId,
                                      @RequestParam("experiment_type") int experiment_typeId,
                                      @RequestParam("experiment_report") int experiment_reportId,
                                      @RequestParam("reservationist") int reservationistId,
                                      @RequestParam("class_name") String class_name,
                                      @RequestParam("amount_of_people") int amount_of_people,
                                      @RequestParam(value = "series", defaultValue = "") List<String> series,
                                      @RequestParam("work_time") int work_time,
                                      @RequestParam(value = "lesson", defaultValue = "0") int lesson,
                                      @RequestParam("note") String note) {
        PostBookingPendding(model, id, name, experiment_groupId, experiment_typeId, experiment_reportId, reservationistId, class_name, amount_of_people, series, work_time, lesson, note);
        return Redirect("myBookingDetail/"+id+"?username="+username,true);
    }

    @GetMapping("/myBookingDetail/Cancel")
    public String PostMappingmyBookingDetail(Model model,
                                             @RequestParam("username") String username,
                                             @RequestParam("bookingId") int bookingId,
                                             @RequestParam(value = "success", defaultValue = "false") boolean success){
        CancelBookingPendding(model, username, bookingId, success);
        return Redirect("mybooking?username=" + username, true);
    }

    @PostMapping({"/myBookingDetail/ComfirmUsed"})
    public String myBookingDetailComfirmUsed(Model model,
                                              @RequestParam("bookingId") int bookingId,
                                              @RequestParam("username") String username,
                                              @RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "success", defaultValue = "false") boolean success){
        ComfirmUsed(model, bookingId, username, file, success);
        return Redirect("mybooking?username=" + username, true);
    }

    @GetMapping({"/myBookingDetail/ComfirmUsed/download/{id}"})
    public ResponseEntity<Resource> myBookingDetailDownloadReport(@PathVariable("id") int bookingId){
        return downloadReport(bookingId);
    }

    @GetMapping({"/myBookingDetail/ComfirmUsedCancel"})
    public String myBookingDetailComfirmUsedCancel(Model model,
                                    @RequestParam("bookingId") int bookingId,
                                    @RequestParam("username") String username,
                                    @RequestParam(value = "success", defaultValue = "false") boolean success){
        ComfirmUsedCancel(model, bookingId, username, success);
        return Redirect("mybooking?username=" + username, true);
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("inputdatasearch") String inputdatasearch){
        ApprovePassCurrentDateBookings(); /** Check xem các đơn đã cồn pendding mà đã đến ngày đặt thì tự động approve */
        List<Lab> labs = labService.findAllByLabNameContainingOrLocationContainingOrAndManagingUnitContaining(inputdatasearch, inputdatasearch, inputdatasearch)
                .stream().filter(lab -> lab.getIsDelete()==0).collect(Collectors.toList());
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateAndStatus(labs);
        List<List<DateAndStatusLab>> dateAndStatusLabsList = new ArrayList<>();
        labDTOS.forEach(labDTO -> dateAndStatusLabsList.add(labDTO.getDateAndStatusLab()));
        model.addAttribute("labDTOS", labDTOS);
        model.addAttribute("dateAndStatusLabsList", dateAndStatusLabsList);
        model.addAttribute("currentDate", LocalDate.now());
        return "index";
    }
/******************************************************************************************************/
                                        /** Khu vực Admin*/
/******************************************************************************************************/

    @GetMapping({"/admin/Dashboard"})
    public String getAdminHomePage(Model model, @RequestParam("username") String username) {

//        ApprovePassCurrentDateBookings(); /** Check xem các đơn đã cồn pendding mà đã đến ngày đặt thì tự động approve */

        /** Dashboard Booking */

        List<BookingDTO> bookingAPPROVE = GetBookingByStatus(model, ConfirmStatus.APPROVE, username);
        List<BookingDTO> bookingCANCEL = GetBookingByStatus(model, ConfirmStatus.CANCEL, username);
        List<BookingDTO> bookingPENDDING = GetBookingByStatus(model, ConfirmStatus.PENDDING, username);
        model.addAttribute("bookingAPPROVE", bookingAPPROVE.size());
        model.addAttribute("bookingCANCEL", bookingCANCEL.size());
        model.addAttribute("bookingPENDDING", bookingPENDDING.size());

        /** Dashboard Lab - So sánh số lượng đặt hàng ngày */
        List<Booking> bookingsToday = bookingService.getAllBookings()
                .stream().filter(booking -> booking.getBookingDate().toLocalDate().isEqual(LocalDate.now())
                                            && !booking.getConfirmStatus().equals(ConfirmStatus.CANCEL))
                .collect(Collectors.toList());
        List<Booking> bookingsYesterday = bookingService.getAllBookings()
                .stream().filter(booking -> booking.getBookingDate().toLocalDate().isEqual(LocalDate.now().minusDays(1))
                                            && !booking.getConfirmStatus().equals(ConfirmStatus.CANCEL))
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
        List<People> teachers = GetPeoPleByRole(RoleSystem.ROLE_TEACHER);
        model.addAttribute("teachers", teachers.size());
        List<People> reservationist = GetPeoPleByRole(RoleSystem.ROLE_RESERVATIONIST);
        model.addAttribute("reservationist", reservationist.size());
        List<People> teacherwaits = GetPeoPleWaitByRole(RoleSystem.ROLE_TEACHERWAIT);
        model.addAttribute("teacherwaits", teacherwaits.size());
        List<People> admins = GetPeoPleByRole(RoleSystem.ROLE_ADMIN);
        model.addAttribute("admins", admins.size());
        List<People> managers = GetPeoPleByRole(RoleSystem.ROLE_MANAGER);
        model.addAttribute("managers", managers.size());

        /** Dashboard Lab */

        model.addAttribute("Labs",labService.getAllLabsOnLine());

        /** Dashboard Lab - Score*/
        model.addAttribute("labsOnLineAndScores", LabsOnLineAndScore(365, 0, 0).subList(0,3));
        model.addAttribute("title", "Tổng quan");
        return template;
    }
    private List<LabsOnLineAndScore> LabsOnLineAndScore(int day, int experiment_type, int experiment_report) {
        LocalDate defaultDate1, defaultDate2;
        switch (day) {
            case 7:
                defaultDate1 = LocalDate.now().minusWeeks(1);
                defaultDate2 = LocalDate.now();
            case 30:
                defaultDate1 = LocalDate.now().minusMonths(1);
                defaultDate2 = LocalDate.now();
            case 90:
                defaultDate1 = LocalDate.now().minusMonths(3);
                defaultDate2 = LocalDate.now();
            case 180:
                defaultDate1 = LocalDate.now().minusMonths(6);
                defaultDate2 = LocalDate.now();
            case 365:
                defaultDate1 = LocalDate.now().minusYears(1);
                defaultDate2 = LocalDate.now();
            default:
                // Handle the default case: August of the previous year to now
                LocalDate previousAugust = LocalDate.of(LocalDate.now().getYear() - 1, 8, 1);
                defaultDate1 = previousAugust;
                defaultDate2 = LocalDate.now();
        }
        return LabsOnLineAndScore(defaultDate1, defaultDate2, experiment_type, experiment_report);
    }
    private List<LabsOnLineAndScore> LabsOnLineAndScore(LocalDate date1, LocalDate date2, int experiment_type, int experiment_report){
        List<Score> scores = scoreService.getAllScore();
        List<LabsOnLineAndScore> labsOnLineAndScores = new ArrayList<>();
        List<Lab> labsOnLine = labService.getAllLabsOnLine();
        /** Lặp qua từng lab */
        for (Lab lab : labsOnLine) {
            LabsOnLineAndScore labsOnLineAndScore = new LabsOnLineAndScore();
            /** set lab */
            labsOnLineAndScore.setLab(lab);
            /** Lấy các đơn đặt phòng đã được DUYỆT và trong 1 khoảng thời gian nào đó */
            List<Booking> bookings = bookingService.findAllByLab_id(lab.getId())
                    .stream().filter(booking -> {
                        return booking.getConfirmStatus().equals(ConfirmStatus.APPROVE)
                                && booking.getConfirmUsed().equals(ConfirmUsed.USED)
                                && booking.getBookingDate().toLocalDate().isAfter(date1)
                                && booking.getBookingDate().toLocalDate().isBefore(date2);
                    })
                    .collect(Collectors.toList());
            /** Lặp qua các đơn đặt */
            for (Booking booking : bookings) {
                /** Tìm content tương ứng với booking */
                Content content = contentService.getContentById(booking.getContentid());
                /** Kiểm tra xem người dùng muốn xem tất cả hay xem theo một loại */
                if(experiment_type != 0 && experiment_report != 0)
                /** Nếu lọc kết quả theo experiment_report experiment_type, nếu không thỏa mãn thì bỏ qua*/
                    if(content.getExperimentType()!=experiment_type || content.getExperimentReport()!=experiment_report)
                        continue;
                /** Lặp trong ds bảng điểm*/
                scores.forEach(score -> {
                    /** Kiểm tra thuộc dòng nào trong bảng điểm*/
                    if(score.getExperimentTypeId()==content.getExperimentType() && score.getExperimentReportId()==content.getExperimentReport()){
                        /** Mỗi lần tìm thấy thì cộng vào tổng điểm score */
                        if(experimentReportService.getExperimentReportById(score.getExperimentReportId()).getReportType().equals("Giờ khai thác")){
                            if(content.getLesson()!=0) {
                                String lessonName = lessonService.getLessonById(content.getLesson()).getName();
                                if(!labsOnLineAndScore.getLessonName().contains(lessonName)) labsOnLineAndScore.addLessonName(lessonName);
                            }
                            labsOnLineAndScore.setScore(labsOnLineAndScore.getScore() + (double) booking.getWork_times()/15);
                            labsOnLineAndScore.setHour(labsOnLineAndScore.getHour() +  booking.getWork_times());
                        } else {labsOnLineAndScore.setScore(labsOnLineAndScore.getScore() + score.getScore());}
                        String typename = experimentReportService.getExperimentReportById(score.getExperimentReportId()).getReportType()
                                            +" - (" + experimentTypeService.getExperimentTypeById(score.getExperimentTypeId()).getTypeName() +")";
                        /** Kiểm tra xem đã thêm vào tên vào trong list typename chưa? */
                        if(labsOnLineAndScore.getTypeName().size()> 0){
                            /** Nếu list typename đã được tạo thì...*/
                            boolean found = false;
                                /** Tìm trong list typename */
                            for (int i = 0; i < labsOnLineAndScore.getTypeName().size(); i++) {
                                if(labsOnLineAndScore.getTypeName().get(i).equals(typename)){
                                    /** Nếu có thì set true và tăng số lượng và break */
                                    found = true;
                                    labsOnLineAndScore.increaseQuantityAtIndex(i);
                                    continue;
                                }
                            }
                            /** Nếu không tìm thấy trong list typename thì thêm mới */
                            if(!found){
                                double doubleScore = score.getScore();
                                int quantity = 1;
                                labsOnLineAndScore.addDetailScore(typename, quantity, doubleScore);
                            }
                        } else {
                            /** Nếu list typename chưa được tạo thì thêm mới*/
                            double doubleScore = score.getScore();
                            int quantity = 1;
                            labsOnLineAndScore.addDetailScore(typename, quantity, doubleScore);
                        }
                    }
                });
            };
            labsOnLineAndScores.add(labsOnLineAndScore);
        };
        Collections.sort(labsOnLineAndScores, new LabsOnLineAndScoreComparator());
        return labsOnLineAndScores;
    }
    private List<People> GetPeoPleWaitByRole(String role){
        List<People> PeoPleByRole = peopleService.getAllPeople().stream()
                .filter(people -> people.getIsDelete()==1 && CheckRole(people, role)
                ).collect(Collectors.toList());
        return PeoPleByRole;
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
    private int GetPeopleIdByUsername(String username){
        return userService.findByUsername(username).getPeopleid();
    }
    private String GetAuthorityByUsername(String username){
        return authorityService.findAuthorityByUsername(username).getAuthority();
    }

    @GetMapping("/admin/Room")
    public String RoomList(Model model,
                           @RequestParam("username") String username,
                           @RequestParam(value = "success", defaultValue = "false") boolean success) {
        List<Lab> labs = labService.getAllLabs();
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateAndStatus(labs).stream().filter(labDTO -> labDTO.getIsDeleted()==0).collect(Collectors.toList());
        if(GetAuthorityByUsername(username).equals("ROLE_ADMIN")){
            model.addAttribute("labDTOS", labDTOS);
        } else {
            model.addAttribute("labDTOS", labDTOS.stream().filter(labDTO -> labDTO.getLab_managemet_id()==GetPeopleIdByUsername(username)).collect(Collectors.toList()));
        }
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
        List<People> peoples = peopleService.getAllPeople().stream().filter(people -> CheckRole(people,RoleSystem.ROLE_MANAGER)/*||CheckRole(people,"ROLE_ADMIN")*/).collect(Collectors.toList());;
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);
        List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
        model.addAttribute("peoples", peoples);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("managingUnits", managingUnits);
        model.addAttribute("success", success);
        model.addAttribute("title", "Thêm mới phòng thí nghiệm");
        return template;
    }

    private int DelSeriInEquiBySeri(EquidAndLevel equidAndLevel, String seri) {
        List<Equipment> equipments = equipmentService.getAllEquipment();
        for (Equipment equipmentCopy : equipments) {
            List<String> seriesList = equipmentCopy.getSeriesAsList();
            for (int i = 0; i < seriesList.size(); i++) {
                if (seriesList.get(i).equals(seri)) {
                    Equipment equipment = equipmentService.findByEquipmentId(equipmentCopy.getId());
                    equipment.getSeriesAsList().remove(i);
                    equipment.setSeries(equipment.getSeriesAsList().toString());
                    equipmentService.updateEquipment(equipment);
                    String level = equipment.getLevelList().get(i);
                    String usingdatte = equipment.getUsingList().get(i);
                    equidAndLevel.setEquidid(equipmentCopy.getId());
                    equidAndLevel.setLevel(level);
                    equidAndLevel.setUsingdate(usingdatte);
                    return equipmentCopy.getId();
                }
            }
        }
        return -1;
    }

    @PostMapping("/admin/Room/add")
    public String submitForm(@RequestParam("username") String username,
                             @RequestParam("LabName") String LabName,
                             @RequestParam("LabCapacity") int LabCapacity,
                             @RequestParam("LabLocation") String LabLocation,
                             @RequestParam("managingUnit") String managingUnit,
                             @RequestParam("Management") int Managementid,
                             @RequestParam(value = "series", defaultValue = "") List<String> selectedSeries) {
        Lab lab = labService.createLab(new Lab(LabName, LabCapacity, LabLocation, managingUnit, Managementid, 0));
        for (String seri : selectedSeries) {
            EquidAndLevel equidAndLevel = new EquidAndLevel();
            int equi_id = DelSeriInEquiBySeri(equidAndLevel, seri);
            EquipmentLab equipmentLab = equipmentLabService.findByLabIdAndEquipmentId(lab.getId(), equi_id);
            if (equipmentLab.getId() == 0){
                equipmentLabService.saveEquipmentLab(new EquipmentLab(lab.getId(), equi_id, new ToList().StringToList(seri),
                                                    new ToList().StringToList(equidAndLevel.getLevel()),
                                                    new ToList().StringToList(equidAndLevel.getUsingdate())));
            } else {
                equipmentLab.AddSeri(equipmentLabService, seri);
            }
        }
        return Redirect("admin/Room?username="+username,true);
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
        for (int i = 0; i < equipmentLabRemove.getEquipmentSerieList().size(); i++) {
            equipment.AddSeri(equipmentService, equipmentLabRemove.getEquipmentSerieList().get(i));
            equipment.AddLevel(equipmentService, equipmentLabRemove.getLevelList().get(i));
            equipment.AddUsing(equipmentService, equipmentLabRemove.getUsingList().get(i));
        }
        equipmentLabService.deleteEquipmentLab(removeEquiLab);
        return Redirect("admin/room/showFormForUpdate/"+LabId,true);
    }

    @GetMapping("/admin/room/showFormForUpdate/{id}")
    public String RoomDetail(Model model, @PathVariable(value = "id") int labId,
                             @RequestParam(value = "experiment_group", defaultValue = "0") int experiment_group,
                             @RequestParam(value = "experiment_type", defaultValue = "0") int experiment_type,
                             @RequestParam(value = "experiment_report", defaultValue = "0") int experiment_report,
                             @RequestParam(value = "experiment_YesNo", defaultValue = "false") boolean experiment_YesNo,
                             @RequestParam(value = "start_date", defaultValue = "") String startDate,
                             @RequestParam(value = "end_date", defaultValue = "") String endDate,
                             @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                             @RequestParam(value = "success", defaultValue = "false") boolean success) {
        Lab lab = labService.findByLabId(labId);
        LabDTO labDTO = Lab2LabDTO(lab);
        List<People> Managers = peopleService.getAllPeople().stream().filter(people -> CheckRole(people,RoleSystem.ROLE_MANAGER)/*||CheckRole(people,"ROLE_ADMIN")*/).collect(Collectors.toList());
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabService.findAllByLabId(labId));
        List<String> usedSeries = GetUsedSeriesOfEquipmentLabs();
        List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
        List<EquipmentDTO> equipmentDTOS =  GetEquisDTO_SeriNoUsed(usedSeries);

        model.addAttribute("managingUnits", managingUnits);
        model.addAttribute("Managers", Managers);
        model.addAttribute("labDTO", labDTO);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        model.addAttribute("success", success);
        model.addAttribute("title", "Chi tiết phòng thí nghiệm");


        List<LabsOnLineAndScore> labsOnLineAndScoresX = new ArrayList<>();
        List<LabsOnLineAndScore> labsOnLineAndScores = GetLabsOnLineAndScore(labsOnLineAndScoresX, model, experiment_group, experiment_type, experiment_report,
                                experiment_YesNo, startDate.split("T")[0], endDate.split("T")[0], success, unsuccess);
        for (LabsOnLineAndScore labsOnLineAndScore : labsOnLineAndScores) {
            if(labsOnLineAndScore.getLab().getId()==labId){
                model.addAttribute("labsOnLineAndScore", labsOnLineAndScore);
            }
        }
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
                        int position = equipment.DelSeri(equipmentService, seriEqui);
                        String level = equipment.getLevelList().get(position);
                        equipment.DelLevel(equipmentService, level);
                        String usingdate = equipment.getUsingList().get(position);
                        equipment.DelUsing(equipmentService, usingdate);
                        if(position>0){
                            /** Add seri vào trong EquiLab*/
                            EquipmentLab equipmentLab = equipmentLabService.findByLabIdAndEquipmentId(lab.getId(),equipment.getId());
                            if(equipmentLab.getId() != 0){
                                equipmentLab.AddSeri(equipmentLabService, seri);
                                equipmentLab.AddLevel(equipmentLabService, level);
                                equipmentLab.AddUsing(equipmentLabService, usingdate);
                            } else {
                                EquipmentLab equipmentLabNew = new EquipmentLab(lab.getId(),equipment.getId(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                                equipmentLabNew.AddSeri(equipmentLabService, seri);
                                equipmentLabNew.AddSeri(equipmentLabService, level);
                                equipmentLabNew.AddUsing(equipmentLabService, usingdate);
                            }
                        } else {
                            throw new RuntimeException("Không tìm thấy seri: " + seri);
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
                                 @RequestParam("managingUnit") String managingUnit,
                                 @RequestParam("location") String location,
                                 @RequestParam(value = "lab_managemet_id", defaultValue = "0") int lab_managemet_id,
                                 @RequestParam(value = "series", defaultValue = "[]") List<String> series) {
        Lab lab = labService.findByLabId(id);
        lab.setLabName(labName);
        lab.setCapacity(capacity);
        lab.setLocation(location);
        lab.setManagingUnit(managingUnit);
        if(lab_managemet_id != 0) lab.setLabManagemetId(lab_managemet_id);
        labService.updateLab(lab);
        RemoveFromEquiSeriAndAddSeri2EquiLab(equipmentService, equipmentLabService, series, lab);
        return Redirect("admin/room/showFormForUpdate/"+id,true);
    }

    @PostMapping("/admin/room/delete/{id}")
    public String DelLab(@PathVariable("id") int id,
                         @RequestParam("username") String username){
        labService.deleteLab(id);
        return Redirect("admin/Room?username="+username,true);
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

    private List<Equipment> GetAllEquipmentHasAllSeri(String username){
        People people = peopleService.findByPeopleId(GetPeopleIdByUsername(username));
        List<EquipmentLab> allEquipmentLab = equipmentLabService.getAllEquipmentLabs();

        if(CheckRole(people, RoleSystem.ROLE_ADMIN)){
            List<Equipment> allEquipment = equipmentService.getAllEquipment().stream().filter(equipment -> equipment.getIsDeleted()==0).collect(Collectors.toList());
            for (EquipmentLab equipmentLab : allEquipmentLab) {
                for (Equipment equipment : allEquipment) {
                    if (equipmentLab.getEquipmentId() == equipment.getId()) {
                        List<String> equipListSeri = equipment.getSeriesAsList();
                        for (String seriLab : equipmentLab.getEquipmentSerieList()) equipListSeri.add(seriLab);
                        equipment.setEquipmentSeries(equipListSeri.toString());

                        List<String> equipListLevel = equipment.getLevelList();
                        for (String levelLab : equipmentLab.getLevelList()) equipListLevel.add(levelLab);
                        equipment.setLevelList(equipListLevel.toString());

                        List<String> equipListUsing = equipment.getUsingList();
                        for (String usingLab : equipmentLab.getUsingList()) equipListUsing.add(usingLab);
                        equipment.setUsing(equipListUsing.toString());
                    }
                }
            }
            return allEquipment;
        } else if (CheckRole(people, RoleSystem.ROLE_MANAGER)){
            List<Lab> labs = labService.getAllLabsOnLine()
                                        .stream().filter(lab ->lab.getLabManagemetId()==people.getId())
                                        .collect(Collectors.toList());
            /** Dùng Set đề lưu phần tử chỉ tồn tại duy nhất */
            List<EquipmentLab> allEquipmentLabByManager = new ArrayList<>();
            Set<Integer> uniqueEquipmentIds = new HashSet<>();

            for (Lab lab : labs) {
                for (EquipmentLab equipmentLab : allEquipmentLab) {
                    if (equipmentLab.getLabId() == lab.getId()) {
                        allEquipmentLabByManager.add(equipmentLab);
                        uniqueEquipmentIds.add(equipmentLab.getEquipmentId());
                    }
                }
            }

            List<Equipment> allEquipmentByManager = new ArrayList<>();
            uniqueEquipmentIds.forEach(id ->{
                Equipment equipment = equipmentService.findByEquipmentId(id);
                equipment.setSeries("[]");
                allEquipmentByManager.add(equipment);
            });

            for (EquipmentLab equipmentLab : allEquipmentLabByManager) {
                Equipment equipmentFindByEquiLab = equipmentService.findByEquipmentId(equipmentLab.getEquipmentId());
                for (Equipment equipment : allEquipmentByManager) {
                    if(equipment.getId() == equipmentFindByEquiLab.getId()){
                        List<String> seriesEquipment = equipment.getSeriesAsList();
                        List<String> seriesEquipmentLab = equipmentLab.getEquipmentSerieList();
                        for (String seri : seriesEquipmentLab) seriesEquipment.add(seri);
                        equipment.setSeries(seriesEquipment.toString());
                    }
                }
            }
            return allEquipmentByManager;
        } else {return new ArrayList<>();}
    }

    @GetMapping({"/admin/AllEquipment"})
    public String AllEquipmentList(Model model,
                                   @RequestParam("username") String username,
                                   @RequestParam(value = "success", defaultValue = "false") boolean success) {
        model.addAttribute("allEquipment", GetAllEquipmentHasAllSeri(username));
        model.addAttribute("success",success);
        model.addAttribute("title", "QLDS thiết bị");
        return template;
    }

    @GetMapping("/admin/Equipment/add")
    public String AddEquipment(Model model){
        List<Unit> units = unitService.getAllUnits();
        model.addAttribute("title", "Thêm mới Thiết bị");
        model.addAttribute("units", units);
        return template;
    }
    @PostMapping("/admin/Equipment/add")
    public String AddEquipment(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("origin") String origin,
                               @RequestParam("unit") String unit,
                               @RequestParam("levels") List<String> levels,
                               @RequestParam("using") List<String> using,
                               @RequestParam("series") List<String> series) {
        Equipment equipment = equipmentService.createEquipment(new Equipment(name, series.toString(), "[]", description, series.size(),0, origin, levels.toString(), using.toString(), unit));
        return Redirect("admin/Equipment",true);
    }

    @GetMapping("/admin/Equipment/showFormForUpdate/{id}")
    public String EquipmentDetail(Model model, @PathVariable(value = "id") int id) {
        Equipment equipment = equipmentService.findByEquipmentId(id);
        List<Unit> units = unitService.getAllUnits();
        model.addAttribute("equipment", equipment);
        model.addAttribute("units", units);
        model.addAttribute("title", "Thông tin chi tiết thiết bị");
        return template;
    }

    @GetMapping("/admin/AllEquipment/showAll/{id}")
    public String AllEquipmentDetail(Model model, @PathVariable(value = "id") int id,
                                     @RequestParam("username") String username) {
        AllEquipment_ReadOnly allEquipment_readOnly = GetAllEquipment_ReadOnly(id, username);

        List<Unit> units = unitService.getAllUnits();

        model.addAttribute("units", units);
        model.addAttribute("equipment", allEquipment_readOnly.getEquipment());
        model.addAttribute("LabIds", allEquipment_readOnly.getLabId());
        model.addAttribute("LabNames", allEquipment_readOnly.getLabNames());
        model.addAttribute("title", "Thông tin chi tiết thiết bị");
        return template;
    }
    private AllEquipment_ReadOnly GetAllEquipment_ReadOnly(int equipId, String username){
        People people = GetPeopleByUsername(username);
        boolean isAdmin = CheckRole(people, RoleSystem.ROLE_ADMIN);
        Equipment equipment = equipmentService.findByEquipmentId(equipId);
        List<String> series = new ArrayList<>();
        List<String> levels = new ArrayList<>();
        List<String> usings = new ArrayList<>();

        List<String> LabNames = new ArrayList<>();
        List<Integer> LabId = new ArrayList<>();
        if(isAdmin){
            series = equipment.getSeriesAsList();
            levels = equipment.getLevelList();
            usings = equipment.getUsingList();
            for (String seri : series) {
                LabNames.add(""); LabId.add(0);
            }
        }

        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByEquipmentId(equipId);
        for (EquipmentLab equipmentLab : equipmentLabs) {
            for (int i = 0; i < equipmentLab.getLevelList().size(); i++) {
                series.add(equipmentLab.getEquipmentSerieList().get(i));
                Lab lab = labService.findByLabId(equipmentLab.getLabId());
                LabNames.add(lab.getLabName());
                LabId.add(lab.getId());
                levels.add(equipmentLab.getLevelList().get(i));
                usings.add(equipmentLab.getUsingList().get(i));
            }
        }
        equipment.setSeries(series.toString());
        equipment.setLevelList(levels.toString());
        equipment.setUsingList(usings.toString());

        return new AllEquipment_ReadOnly(equipment, LabNames, LabId);
    }

    @GetMapping("/admin/AllEquipment/showForManager/{id}")
    public String AllEquipmentshowForManagerDetail(Model model, @PathVariable(value = "id") int id,
                                                   @RequestParam("username") String username) {
        AllEquipment_ReadOnly allEquipment_readOnly = GetAllEquipment_ReadOnly(id, username);

        List<Unit> units = unitService.getAllUnits();

        model.addAttribute("equipment", allEquipment_readOnly.getEquipment());
        model.addAttribute("units", units);
        model.addAttribute("LabNames", allEquipment_readOnly.getLabNames());
        model.addAttribute("LabIds", allEquipment_readOnly.getLabId());
        model.addAttribute("title", "Thông tin chi tiết thiết bị");
        return template;
    }


    @PostMapping("/admin/Equipment/showFormForUpdate/{id}")
    public String EquipmentDetailPost(@PathVariable(value = "id") int id,
                                      @RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("unit") String unit,
                                      @RequestParam("origin") String origin,
                                      @RequestParam(value = "series", defaultValue = "[]") List<String> series,
                                      @RequestParam(value = "levels", defaultValue = "[]") List<String> levels,
                                      @RequestParam(value = "using", defaultValue = "[]") List<String> using,
                                      @RequestParam(value = "seriesfixed", defaultValue = "[]") List<String> seriesfixed) {
        Equipment equipment = equipmentService.findByEquipmentId(id);
        equipment.setName(name);
        equipment.setDescription(description);
        equipment.setOrigin(origin);
        equipment.setUnit(unit);
        equipment.setSeries(series.toString());
        equipment.setLevelList(levels.toString());
        for (String usingdate : using) {
            usingdate = usingdate.split("T")[0];
        }
        equipment.setUsingList(using.toString());
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
                    return people.getIsDelete()==0 && (CheckRole(people, RoleSystem.ROLE_ADMIN) || CheckRole(people, RoleSystem.ROLE_MANAGER));
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
        List<Lab> labs = labService.findAllByLabManagemetId(id).stream().filter(l -> l.getIsDelete()==0).collect(Collectors.toList());
        model.addAttribute("labs", labs);

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

        Authority authority = authorityService.findAuthorityByUsername(username);
        Users user = userService.findByUsername(username);
        if(role.equals(RoleSystem.ROLE_TEACHERWAIT)){
            people.setIsDelete(1);
            user.setEnabled(0);
        }
        peopleService.updatePeople(people);
        authority.setAuthority(role);
        authorityService.createAuthority(authority);

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
                    return people.getIsDelete()==0 && CheckRole(people,RoleSystem.ROLE_TEACHER);
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
        List<Roles> roles = GetRolesNomal();
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
    private List<Roles> GetRolesNomal(){
        List<Roles> roles = roleService.getAllRoles().stream().filter(role ->
                !(role.getRole().equals(RoleSystem.ROLE_MANAGER) || role.getRole().equals(RoleSystem.ROLE_MANAGER))
        ).collect(Collectors.toList());
        return roles;
    }

    @GetMapping("/admin/Teacher/showFormForUpdate/{id}")
    public String TeacherDetail(Model model, @PathVariable(value = "id") int id) {
        ManagerDetail(model,id);
        List<Roles> roles = GetRolesNomal();
        model.addAttribute("roles", roles);

        List<Content> contents = contentService.findAllByReservationistId(id);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        contents.forEach(content -> {
            Booking booking = bookingService.findByContent_id(content.getId());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            Lab lab = labService.findByLabId(booking.getLabid());
            bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
        });
        model.addAttribute("bookingDTOs", bookingDTOs);

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

                        /*****************************************************/
                                            /** TEACHER WAIT */
                        /*****************************************************/

    @GetMapping("/admin/UserWait")
    public String UserWait(Model model, @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<People> teacher = peopleService.getAllPeople()
                .stream()
                .filter(people -> {
                    return people.getIsDelete()==1 && CheckRole(people,RoleSystem.ROLE_TEACHERWAIT);
                })
                .collect(Collectors.toList());
        List<String> roles = new ArrayList<>();
        List<PeopleDTO> teacherDTOS = Peoples2PeopleDTOs(teacher);
        model.addAttribute("teacherDTOS", teacherDTOS);
        model.addAttribute("success",success);
        model.addAttribute("title", "QLDS Giáo viên đăng kí tài khoản");
        return template;
    }
    @GetMapping("/admin/UserWaitCancel")
    public String UserWait(Model model,
                           @RequestParam("id") int id){
        Authority authority = authorityService.findAuthorityByUsername(userService.findByPeopleId(id).getUsername());
        authority.setAuthority("ROLE_TAECHERWAITCANCEL");
        authorityService.createAuthority(authority);
        return Redirect("admin/UserWait", true);
    }

    @PostMapping("/admin/UserWait")
    public String UserWait(@RequestParam("teacherid") int teacherid,
                           @RequestParam("roleselected") String roleselected){
        People people = peopleService.findByPeopleId(teacherid);
        people.setIsDelete(0);
        peopleService.createPeople(people);
        Users user = userService.findByPeopleId(teacherid);
        user.setEnabled(1);
        Authority authority = authorityService.findAuthorityByUsername(user.getUsername());
        authority.setAuthority(roleselected);
        authorityService.createAuthority(authority);
        return Redirect("admin/UserWait", true);
    }


    /******************************************************************************************/
                                            /** Role */
    /******************************************************************************************/

    @GetMapping({"/admin/Role"})
    public String Role(Model model,
                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<Roles> roles = roleService.getAllRoles();
        List<Roles> rolesNotRoleSystem = roleService.getAllRolesNotRoleSystem();
        model.addAttribute("rolesNotRoleSystem", rolesNotRoleSystem);
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
                                            /** Managing Unit */
    /******************************************************************************************/

    @GetMapping({"/admin/ManagingUnit"})
    public String ManagingUnit(Model model,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
        List<ManagingUnit> AllManagingUnits = managingUnitService.getAllManagingUnits();
        List<Lab> labs = labService.getAllLabs().stream().filter(lab -> (lab.getManagingUnit()!=null || lab.getManagingUnit().equals(""))).collect(Collectors.toList());

        // Tạo một danh sách chứa các departmentName từ labs
        List<String> existingDepartmentNames = new ArrayList<>();
        for (Lab lab : labs) {
            existingDepartmentNames.add(lab.getManagingUnit().trim());
        }
        // Loại bỏ các departmentName đã có trong labs khỏi managingUnits
        if(existingDepartmentNames.size()!=0) managingUnits.removeIf(managingUnit -> {
            return existingDepartmentNames.contains(managingUnit.getDepartmentName().trim());
        });
        model.addAttribute("managingUnits", managingUnits);
        model.addAttribute("AllManagingUnits", AllManagingUnits);
        model.addAttribute("success", success);
        model.addAttribute("title", "Quản đơn vị chủ quản của PTN");
        return template;
    }

    @PostMapping("/admin/ManagingUnit/add")
    public String AddManagingUnit(Model model, @RequestParam("managingunit") String managingunit) {
        managingUnitService.createManagingUnit(new ManagingUnit(managingunit));
        return Redirect("admin/ManagingUnit", true);
    }

    @PostMapping("/admin/ManagingUnit/delete")
    public String DelManagingUnit(Model model, @RequestParam("managingunitId") int managingunitId) {
        try {
            managingUnitService.deleteManagingUnit(managingunitId);
            return Redirect("admin/ManagingUnit",true);
        } catch (Exception e){
            return Redirect("admin/ManagingUnit",false);
        }
    }

    /******************************************************************************************/
                                            /** myAccount */
    /******************************************************************************************/


    @GetMapping({"/admin/myAccount/{username}"})
    public String Role(Model model, @PathVariable("username") String username,
                       @RequestParam(value = "changedPassfalse", defaultValue = "false") boolean changedPassfalse,
                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<Lab> labs = labService.findAllByLabManagemetId(GetPeopleIdByUsername(username)).stream().filter(l -> l.getIsDelete()==0).collect(Collectors.toList());
        model.addAttribute("labs", labs);

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
                                  @RequestParam("newpass") String newpass,
                                  @RequestParam("retypenewpass") String retypenewpass){
        TeacherChangePass(oldpass, username, newpass, retypenewpass);
        Users user = userService.findByUsername(username);
        if(passwordEncoder.matches(oldpass,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newpass));
            userService.save(user);
            return Redirect("admin/myAccount/"+username,true);
        } else {
            return "redirect:/Lab/admin/myAccount/"+username+"?changedPassfalse=true";
        }
    }



    /******************************************************************************************/
                                /** Laboratory Booking Management */
    /******************************************************************************************/

    /** Cán bộ phụ trách chỉ quản lý được phòng do mình phụ trách.
     *  Lấy booking theo phân cấp quyền, người quản lý phòng và trạng thái đơn
     * */
    private List<BookingDTO> GetBookingByStatus(Model model, String status, String username){
        int idManager = userService.findByUsername(username).getPeopleid();
        String role = authorityService.findAuthorityByUsername(username).getAuthority();
        List<Booking> bookings = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(status)).collect(Collectors.toList());
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        bookings.forEach(booking -> {
            Content content = contentService.getContentById(booking.getContentid());
            Lab lab = labService.findByLabId(booking.getLabid());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            if(role.equals("ROLE_ADMIN")){
                bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
            } else {
                if(lab.getLabManagemetId()==idManager){
                    bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
                }
            }
        });
        /** Sắp xếp lại bookingDTOs theo thứ tự ngày gần nhất đến xa nhất = Override: BookingDateComparator */
        Collections.sort(bookingDTOs, new BookingDateComparator());
        model.addAttribute("bookingDTOs", bookingDTOs);
        return bookingDTOs;
    }

    @GetMapping({"/admin/LabBookingManagement/Pendding"})
    public String PeddingBooking(Model model,
                               @RequestParam("username") String username,
                               @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ConfirmStatus.PENDDING, username);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL đơn chờ duyệt");
        return template;
    }
    private ContentDTO Content2ContentDTO(Content content) {
        Lesson lesson = lessonService.getLessonById(content.getLesson());
        ExperimentType experimentType = experimentTypeService.getExperimentTypeById(content.getExperimentType());
        ExperimentGroup experimentGroup = experimentGroupService.getExperimentGroupById(experimentType.getExperimentGroupId());
        ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
        People reservationist = peopleService.findByPeopleId(content.getReservationistId());
        return new ContentDTO(content, reservationist, experimentGroup, experimentType, experimentReport, lesson);
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
            return people.getIsDelete()!=1 && (CheckRole(people,RoleSystem.ROLE_TEACHER) || CheckRole(people,RoleSystem.ROLE_RESERVATIONIST));
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
        List<Lesson> lessons = lessonService.findAllByLabId(labDTO.getId());

        model.addAttribute("lessons", lessons);
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

    @PostMapping({"/admin/ShowBookingPenddind/{id}"})
    public String PostBookingPendding(Model model, @PathVariable("id") int id,
                                      @RequestParam("name") String name,
                                      @RequestParam("experiment_group") int experiment_groupId,
                                      @RequestParam("experiment_type") int experiment_typeId,
                                      @RequestParam("experiment_report") int experiment_reportId,
                                      @RequestParam("reservationist") int reservationistId,
                                      @RequestParam("class_name") String class_name,
                                      @RequestParam("amount_of_people") int amount_of_people,
                                      @RequestParam(value = "series", defaultValue = "") List<String> series,
                                      @RequestParam("work_time") int work_time,
                                      @RequestParam(value = "lesson", defaultValue = "0") int lesson,
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
        content.setLesson(lesson);
        contentService.saveContent(content);

        return Redirect("admin/ShowBookingPendding/"+id,true);
    }
    @GetMapping({"/admin/ShowBookingPendding/Approve"})
    public String ApproveBookingPendding(Model model,
                                         @RequestParam("username") String username,
                                         @RequestParam("bookingId") int bookingId,
                                         @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setAuto(AutoComfirmStatusBooking.ManualBoking);
        booking.setConfirmStatus(ConfirmStatus.APPROVE);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Pendding?username="+username, true);
    }

    @GetMapping({"/admin/ShowBookingPendding/Cancel"})
    public String CancelBookingPendding(Model model,
                                        @RequestParam("username") String username,
                                        @RequestParam("bookingId") int bookingId,
                                        @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setConfirmStatus(ConfirmStatus.CANCEL);
        booking.setAuto(AutoComfirmStatusBooking.ManualBoking);
        booking.setConfirmUsed(ConfirmUsed.CANCEL);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Pendding?username="+username, true);
    }

    @GetMapping({"/admin/LabBookingManagement/Approve"})
    public String ApproveBooking(Model model,
                                 @RequestParam("username") String username,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ConfirmStatus.APPROVE,username);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL đơn đã duyệt");
        return template;
    }

    @GetMapping({"/admin/LabBookingManagement/WaitComfirmUsed"})
    public String WaitComfirmUsed(Model model,
                                 @RequestParam("username") String username,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ConfirmStatus.APPROVE,username);
        model.addAttribute("success", success);
        model.addAttribute("title", "QL YC chờ giáo viên xác nhận sử dụng phòng");
        return template;
    }

    @PostMapping({"/admin/LabBookingManagement/ComfirmUsed"})
    public String ComfirmUsed(Model model,
                              @RequestParam("bookingId") int bookingId,
                              @RequestParam("username") String username,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setConfirmUsed(ConfirmUsed.USED);
        bookingService.updateBooking(booking);
        Content content = contentService.getContentById(booking.getContentid());
        model.addAttribute("success", success);
        model.addAttribute("title", "QL YC chờ giáo viên xác nhận sử dụng phòng");
        if (!file.isEmpty()) {
            try {
                // Lưu ảnh vào thư mục static/images
                String fileName = file.getOriginalFilename();
                String filePath = "file/" + fileName;
                file.transferTo(new File(filePath));
                content.setFileName(filePath);
                contentService.saveContent(content);
                return Redirect("admin/LabBookingManagement/WaitComfirmUsed?username="+username,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Redirect("admin/LabBookingManagement/WaitComfirmUsed?username="+username,false);
    }

    @GetMapping("/admin/LabBookingManagement/ComfirmUsed/download/{id}")
    public ResponseEntity<Resource> downloadReport(@PathVariable("id") int bookingId) {
        String fileName = contentService.getContentById(
                            bookingService.findByBookingId(bookingId).getContentid()
                        ).getFileName();
        Resource resource = new ClassPathResource("static/"+fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName.split("/")[1])
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping({"/admin/LabBookingManagement/ComfirmUsedCancel"})
    public String ComfirmUsedCancel(Model model,
                                    @RequestParam("bookingId") int bookingId,
                                    @RequestParam("username") String username,
                                    @RequestParam(value = "success", defaultValue = "false") boolean success){
        CancelBookingPendding(model, username, bookingId, success);
        model.addAttribute("title", "QL YC chờ giáo viên xác nhận sử dụng phòng");
        return Redirect("admin/LabBookingManagement/WaitComfirmUsed?username="+username,true);
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
                                        @RequestParam("username") String username,
                                        @RequestParam("bookingId") int bookingId,
                                        @RequestParam(value = "success", defaultValue = "false") boolean success){
        Booking booking = bookingService.findByBookingId(bookingId);
        booking.setAuto(AutoComfirmStatusBooking.NULL);
        booking.setConfirmStatus(ConfirmStatus.PENDDING);
        booking.setConfirmUsed(ConfirmUsed.UNUSED);
        bookingService.updateBooking(booking);
        model.addAttribute("success",success);
        return Redirect("admin/LabBookingManagement/Approve?username="+username,true);
    }

    @GetMapping({"/admin/LabBookingManagement/Cancel"})
    public String CancelBooking(Model model,
                                @RequestParam("username") String username,
                                @RequestParam(value = "success", defaultValue = "false") boolean success){
        GetBookingByStatus(model, ConfirmStatus.CANCEL,username);
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
                                              @RequestParam("username") String username,
                                              @RequestParam("bookingId") int bookingId,
                                              @RequestParam(value = "success", defaultValue = "false") boolean success){
        UnApproveShowBookingApprove(model,username, bookingId, success);
        return Redirect("admin/LabBookingManagement/Cancel?username="+username,true);
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
                if (score.getExperimentReportId() == experimentReport.getId()) {
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
                if (content.getExperimentReport() == experimentReport.getId()) {
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
    public String LabRankingsFromDate(Model model,
                              @RequestParam(value = "experiment_group", defaultValue = "0") int experiment_group,
                              @RequestParam(value = "experiment_type", defaultValue = "0") int experiment_type,
                              @RequestParam(value = "experiment_report", defaultValue = "0") int experiment_report,
                              @RequestParam(value = "experiment_YesNo", defaultValue = "false") boolean experiment_YesNo,
                              @RequestParam(value = "start_date", defaultValue = "") String startDate,
                              @RequestParam(value = "end_date", defaultValue = "") String endDate,
                              @RequestParam(value = "success", defaultValue = "false") boolean success,
                              @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        List<LabsOnLineAndScore> labsOnLineAndScores = new ArrayList<>();
        GetLabsOnLineAndScore(labsOnLineAndScores, model, experiment_group, experiment_type, experiment_report,
                experiment_YesNo, startDate.split("T")[0], endDate.split("T")[0], success, unsuccess);
        return template;
    }

    private List<LabsOnLineAndScore> GetLabsOnLineAndScore(List<LabsOnLineAndScore> labsOnLineAndScores, Model model,
                                                           int experiment_group, int experiment_type, int experiment_report,
                                                           boolean experiment_YesNo, String startDate, String endDate,
                                                           boolean success, boolean unsuccess){
        if(!startDate.equals("") && !endDate.equals("")){
            LocalDateTime x1 = LocalDateTime.parse(startDate+"T12:00:00");
            LocalDateTime x2 = LocalDateTime.parse(endDate+"T12:00:00");
            model.addAttribute("start_date",x1);
            model.addAttribute("end_date",x2);
        }
        if(!startDate.equals("") || !endDate.equals("")){
            labsOnLineAndScores = LabsOnLineAndScore(LocalDate.parse(startDate), LocalDate.parse(endDate), experiment_type, experiment_report);
        }else{
            labsOnLineAndScores = LabsOnLineAndScore(1, experiment_type, experiment_report);
            LocalDate previousAugust = LocalDate.of(LocalDate.now().getYear() - 1, 8, 1);
            LocalDateTime defaultDate1 = LocalDateTime.parse(previousAugust.toString()+"T12:00:00");
            LocalDateTime defaultDate2 = LocalDateTime.now();
            model.addAttribute("start_date",defaultDate1);
            model.addAttribute("end_date",defaultDate2);
        }
        model.addAttribute("labsOnLineAndScores",labsOnLineAndScores);
        model.addAttribute("title","BXH chấm điểm PTN");

        List<ExperimentGroup> experimentGroups = experimentGroupService.getAllExperimentGroups();
        List<ExperimentReport> experimentReports = experimentReportService.getAllExperimentReports();
        List<ExperimentType> experimentTypes = experimentTypeService.getAllExperimentTypes();
        model.addAttribute("experimentTypes", experimentTypes);
        model.addAttribute("experimentReports", experimentReports);
        model.addAttribute("experimentGroups", experimentGroups);
        model.addAttribute("experimentGroupId", experiment_group);
        model.addAttribute("experimentTypeId", experiment_type);
        model.addAttribute("experimentReportId", experiment_report);
        model.addAttribute("experiment_YesNo", experiment_YesNo);
        return labsOnLineAndScores;
    }

    @PostMapping({"/admin/LabRankings"})
    public String LabRankingsFromDatePost(Model model,
                                          @RequestParam(value = "experiment_group", defaultValue = "0") int experiment_group,
                                          @RequestParam(value = "experiment_type", defaultValue = "0") int experiment_type,
                                          @RequestParam(value = "experiment_report", defaultValue = "0") int experiment_report,
                                          @RequestParam(value = "experiment_YesNo", defaultValue = "false") boolean experiment_YesNo,
                                          @RequestParam(value = "start_date", defaultValue = "") String startDate,
                                          @RequestParam(value = "end_date", defaultValue = "") String endDate,
                                          @RequestParam(value = "success", defaultValue = "false") boolean success,
                                          @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        LabRankingsFromDate(model, experiment_group, experiment_type, experiment_report, experiment_YesNo,
                            startDate.split("T")[0], endDate.split("T")[0], success, unsuccess);
        return template;
    }


                                    /******************************************************/
                                                /** Kiểm kê theo phòng - Inventory */
                                    /*****************************************************/

    @GetMapping({"/admin/Showinventory/Lab"})
    public String Showinventory(Model model,
                                @RequestParam("username") String username,
                                @RequestParam(value = "success", defaultValue = "false") boolean success,
                                @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        RoomList(model, username, success);
        model.addAttribute("title", "Kiểm kê phòng thí nghiệm");
    return template;
    }

    private List<EquipmentLabDTO> GetListEquipmentLabDTO_FromInventoryLab(InventoryLab inventoryLab){
        List<EquipmentLabDTO> equipmentLabDTOS = new ArrayList<>();
        Gson gson = new Gson();
        // Chuyển đổi chuỗi JSON thành danh sách đối tượng
        Type type = new TypeToken<List<EquipmentLabDTO>>() {}.getType();
        equipmentLabDTOS = gson.fromJson(inventoryLab.getEquipmentLabData(), type);
        return equipmentLabDTOS;
    }
    private List<EquipmentLabDTO> GetListEquipmentLabDTO_FromInventoryEquip(InventoryEquipment inventoryEquip, String username){
        List<EquipmentLabDTO> equipmentLabDTOS = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<EquipmentLabDTO>>() {}.getType();
        if(CheckRole(
                peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid()),
                RoleSystem.ROLE_ADMIN
        )) {
            equipmentLabDTOS = gson.fromJson(inventoryEquip.getEquipmentData(), type);
            return equipmentLabDTOS;
        } else {
            List<Integer> listEquipIdFromUsername = new ArrayList<>();
            List<EquipmentLab> equipmentLabs = new ArrayList<>();
            int managerLabId = userService.findByUsername(username).getPeopleid();
            List<Lab> labs = labService.findAllByLabManagemetId(managerLabId).stream().filter(lab -> lab.getIsDelete()==0).collect(Collectors.toList());
            for (Lab lab : labs) {
                equipmentLabService.findAllByLabId(lab.getId()).forEach(e -> equipmentLabs.add(e));
            }
            equipmentLabs.forEach(e -> listEquipIdFromUsername.add(e.getEquipmentId()));
            Set<Integer> listEquipIdUniqueFromUsername = new HashSet<>(listEquipIdFromUsername);
            List<EquipmentLabDTO> equipmentLabDTOSFromJson = gson.fromJson(inventoryEquip.getEquipmentData(), type);
            equipmentLabDTOS  = equipmentLabDTOSFromJson.stream()
                                .filter(e -> listEquipIdUniqueFromUsername.contains(e.getEquipmentId())).collect(Collectors.toList());
            return equipmentLabDTOS;
        }

    }
    private List<EquipmentLabDTO> GetListEquipmentLabDTO_FromLabId(int LabId){
        List<EquipmentLabDTO> equipmentLabDTOs = new ArrayList<>();
        equipmentLabService.findAllByLabId(LabId).forEach(equipmentLab -> {
            EquipmentLabDTO equipmentLabDTO = new EquipmentLabDTO(equipmentLab, equipmentService.findByEquipmentId(equipmentLab.getEquipmentId()),
                    equipmentLab.getEquipmentSerieList(), equipmentLab.getLevelList(), equipmentLab.getUsingList());
            equipmentLabDTOs.add(equipmentLabDTO);
        });
        return equipmentLabDTOs;
    }

    private void SaveEquipmentLabDTOsToDataBase(int LabId, int year, List<EquipmentLabDTO> equipmentLabDTOs){
        // Chuyển đổi danh sách thành chuỗi JSON
        Gson gson = new Gson();
        String serializedData = gson.toJson(equipmentLabDTOs);
        if(LabId==0){
            inventoryEquipmentService.saveInventoryEquipment(new InventoryEquipment(year, serializedData));
        } else {
            inventoryLabService.saveEquipmentLab(new InventoryLab(LabId, year, serializedData));
        }
    }

    @GetMapping("/admin/room/showinventory/{id}")
    public String showinventory(Model model,
                                @RequestParam("username") String username,
                                @PathVariable(value = "id") int LabId,
                                @RequestParam(value = "success", defaultValue = "false") boolean success) {

        int year = LocalDate.now().getYear();
        InventoryResult inventoryResult = ShowInventoryForLabId(LabId, year);

        List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();

        model.addAttribute("inventoryLab_DataForYear", inventoryResult.getInventoryLab_DataForYear());
        model.addAttribute("equipmentLabDTOs", inventoryResult.getEquipmentLabDTOs());
        model.addAttribute("inventoryCompares", inventoryResult.getInventoryCompares());
        model.addAttribute("labDTO", inventoryResult.getLabDTO());
        model.addAttribute("year", year);
        model.addAttribute("success", success);
        model.addAttribute("managingUnits", managingUnits);
        model.addAttribute("title", "Chi tiết Kiểm kê PTN năm " + year);
        return template;
    }

    private InventoryResult ShowInventoryForLabId(int LabId, int year){
        InventoryResult inventoryResult = new InventoryResult();
        inventoryResult.setLabDTO(Lab2LabDTO(labService.findByLabId(LabId)));
        inventoryResult.setInventoryLab_DataForYear(
                inventoryLabService.getAllEquipmentLabs()
                .stream().filter(e -> e.getLabId()==LabId)
                .collect(Collectors.toList())
        );

        List<EquipmentLabDTO> equipmentLabDTOs_1 = new ArrayList<>();

        boolean found = false;
        for (InventoryLab e : inventoryResult.getInventoryLab_DataForYear()) {
            if(e.getYear()==year){
                equipmentLabDTOs_1 = GetListEquipmentLabDTO_FromInventoryLab(e);
                found = true;
                break;
            }
        }

        /*Lấy ra list years của các năm (kể cả nhưng năm mà phòng ko có danh sách)*/
        List<Integer> years = new ArrayList<>();
        inventoryResult.getInventoryLab_DataForYear().forEach(e -> years.add(e.getYear()));
        Set<Integer> uniqueYears = new HashSet<>(years);
        List<Integer> sortedUniqueYears = new ArrayList<>(uniqueYears);
        Collections.sort(sortedUniqueYears);
        boolean yearPresent = sortedUniqueYears.contains((int) LocalDate.now().getYear());

        if(!found){
            InventoryLab equipmentLabDtoCheckYear = inventoryLabService.findByLabIdAndAndYear(LabId, year);
            if(equipmentLabDtoCheckYear.getId()!=0){
                equipmentLabDTOs_1 = GetListEquipmentLabDTO_FromLabId(LabId);
            } else {
                equipmentLabDTOs_1 = new ArrayList<>();
                if(!yearPresent){
                    List<EquipmentLab> equipmentLabList = equipmentLabService.findAllByLabId(LabId);
                    for (EquipmentLab equipmentLab : equipmentLabList) {
                        equipmentLabDTOs_1.add(
                                new EquipmentLabDTO(equipmentLab, equipmentService.findByEquipmentId(equipmentLab.getEquipmentId()),
                                                    equipmentLab.getEquipmentSerieList(),
                                                    equipmentLab.getLevelList(),
                                                    equipmentLab.getUsingList())
                        );
                    }
                    SaveEquipmentLabDTOsToDataBase(LabId, LocalDate.now().getYear(), equipmentLabDTOs_1);
                }
            }
        }
        List<EquipmentLabDTO> equipmentLabDTOs_2 = new ArrayList<>();
        InventoryLab equipmentLabDto2 = inventoryLabService.findByLabIdAndAndYear(LabId, year-1);

        if(equipmentLabDto2.getId()!=0) {
            equipmentLabDTOs_2 = GetListEquipmentLabDTO_FromInventoryLab(equipmentLabDto2);
            inventoryResult.setEquipmentLabDTOs(
                    CompareInventory(equipmentLabDTOs_1, equipmentLabDTOs_2, inventoryResult.getInventoryCompares())
            );
        } else {
            inventoryResult.setEquipmentLabDTOs(equipmentLabDTOs_1);
        }
        /* Lấy lại lần nữa để cập nhật năm danh sách kiểm kê*/
        inventoryResult.setInventoryLab_DataForYear(
                inventoryLabService.getAllEquipmentLabs()
                .stream().filter(e -> e.getLabId()==LabId)
                .collect(Collectors.toList())
        );
        return inventoryResult;
    }

    @GetMapping("/admin/room/showinventoryForyear/{id}")
    public String showinventory(Model model,
                                @RequestParam("username") String username,
                                @PathVariable(value = "id") int labid,
                                @RequestParam("inventoryLab") int inventoryId,
                                @RequestParam(value = "success", defaultValue = "false") boolean success) {
        LabDTO labDTO = Lab2LabDTO(labService.findByLabId(labid));
        InventoryLab equipmentLabDto = inventoryLabService.getEquipmentLabById(inventoryId);
        InventoryResult inventoryResult = ShowInventoryForLabId(labid, equipmentLabDto.getYear());
        List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();

        model.addAttribute("inventoryLab_DataForYear", inventoryResult.getInventoryLab_DataForYear());
        model.addAttribute("labDTO", inventoryResult.getLabDTO());
        model.addAttribute("year", equipmentLabDto.getYear());
        model.addAttribute("equipmentLabDTOs", inventoryResult.getEquipmentLabDTOs());
        model.addAttribute("inventoryCompares", inventoryResult.getInventoryCompares());
        model.addAttribute("success", success);
        model.addAttribute("managingUnits", managingUnits);
        model.addAttribute("title", "Chi tiết kiểm kê PTN năm "+ equipmentLabDto.getYear());

        return template;
    }

    private List<EquipmentLabDTO> CompareInventory(List<EquipmentLabDTO> equipmentLabDTOS_Year,
                                                   List<EquipmentLabDTO> equipmentLabDTOS_MinusYear,
                                                   List<InventoryCompare> inventoryChanges) {
        List<EquipmentLabDTO> equipmentLabDTOS = new ArrayList<>();
        Map<Integer, EquipmentLabDTO> dtoMap1 = new HashMap<>();
        Map<Integer, EquipmentLabDTO> dtoMap2 = new HashMap<>();

        for (EquipmentLabDTO dto2 : equipmentLabDTOS_MinusYear) {
            dtoMap2.put(dto2.getEquipmentId(), dto2);
        }
        for (EquipmentLabDTO dto1 : equipmentLabDTOS_Year) {
            dtoMap1.put(dto1.getEquipmentId(), dto1);
        }

        /** Sau for sẽ add các phần tử có trong List 1*/
        for (EquipmentLabDTO dto1 : equipmentLabDTOS_Year) {
            EquipmentLabDTO matchingDTO2 = dtoMap2.get(dto1.getEquipmentId());
            if (matchingDTO2 == null) {
                equipmentLabDTOS.add(dto1);

                InventoryCompare inventoryCompare = new InventoryCompare();
                inventoryCompare.setThisYear(dto1.getEquipmentSerieList().size());
                inventoryCompare.setLastYear(0);
                int increase = inventoryCompare.getThisYear() - inventoryCompare.getLastYear();
                inventoryCompare.setIncrease(increase);
                inventoryCompare.setReduce(0);
                inventoryChanges.add(inventoryCompare);
            } else {
                equipmentLabDTOS.add(dto1);

                InventoryCompare inventoryCompare = new InventoryCompare();
                inventoryCompare.setThisYear(dto1.getEquipmentSerieList().size());
                inventoryCompare.setLastYear(matchingDTO2.getEquipmentSerieList().size());
                int increase = inventoryCompare.getThisYear() - inventoryCompare.getLastYear();
                inventoryCompare.setIncrease(Math.max(increase,0));
                inventoryCompare.setReduce(Math.max(-increase,0));
                inventoryChanges.add(inventoryCompare);
            }
        }
        /** Sau for sẽ add nốt những phần tử có trong List 2 không có trong List 1*/
        for (EquipmentLabDTO dto2 : equipmentLabDTOS_MinusYear) {
            EquipmentLabDTO matchingDTO1 = dtoMap1.get(dto2.getEquipmentId());
            if (matchingDTO1 == null) {
                equipmentLabDTOS.add(dto2);

                InventoryCompare inventoryCompare = new InventoryCompare();
                inventoryCompare.setLastYear(dto2.getEquipmentSerieList().size());
                inventoryCompare.setThisYear(0);
                int increase = inventoryCompare.getThisYear() - inventoryCompare.getLastYear();
                inventoryCompare.setReduce(-increase);
                inventoryCompare.setIncrease(0);
                inventoryChanges.add(inventoryCompare);
            }
        }
        return equipmentLabDTOS;
    }

    private People GetPeopleFromUsername(String username){
        return peopleService.findByPeopleId(
                userService.findByUsername(username).getPeopleid()
        );
    }

    @GetMapping({"/admin/Showinventory/AllshowinventoryLab"})
    public String ShowinventoryAllshowinventoryLab(Model model,
                                                    @RequestParam(value = "managingUnitsCheck", defaultValue = "false") boolean managingUnitsCheck,
                                                    @RequestParam(value = "yearSelected", defaultValue = "0") int yearSelected,
                                                    @RequestParam(value = "managingUnitId", defaultValue = "0") int managingUnitId,
                                                    @RequestParam("username") String username,
                                                    @RequestParam(value = "success", defaultValue = "false") boolean success,
                                                    ArrayList<InventoryResult> inventoryResults) {
        int idFromUsername = GetPeopleFromUsername(username).getId();

        int year = LocalDate.now().getYear();
        if(yearSelected!=0) year = yearSelected;
        /*Lấy ra list years của các năm (kể cả nhưng năm mà phòng ko có danh sách)*/
        List<Integer> sortedUniqueYears = GetSortedUniqueYears_InventoryLab();

        List<Lab> labs = new ArrayList<>();
//        List<InventoryResult> inventoryResults = new ArrayList<>();
        String departmentName = "";

        if (managingUnitsCheck) managingUnitId = 0;
        if (managingUnitId == 0) {
            if(CheckRole(GetPeopleFromUsername(username),RoleSystem.ROLE_ADMIN)){
                labs = labService.getAllLabsOnLine();
            } else {
                labs = labService.getAllLabsOnLine()
                        .stream().filter(lab -> lab.getLabManagemetId()==idFromUsername)
                        .collect(Collectors.toList());
            }
            model.addAttribute("title", "Chi tiết kiểm kê PTN năm "+ year);
        } else {
            String departmentNameX = managingUnitService.getManagingUnitById(managingUnitId).getDepartmentName();
            if(CheckRole(GetPeopleFromUsername(username),RoleSystem.ROLE_ADMIN)) {
                labs = labService.getAllLabsOnLine().stream().filter(lab -> {
                        return lab.getManagingUnit().trim().equals(departmentNameX);
                    }).collect(Collectors.toList());
            } else {
                /** Nếu ko phải admin thì lọc theo manager*/
                labs = labService.getAllLabsOnLine().stream().filter(lab -> {
                    return lab.getManagingUnit().trim().equals(departmentNameX)
                            && lab.getLabManagemetId() == idFromUsername;
                    }).collect(Collectors.toList());
            }
            departmentName = departmentNameX;
            model.addAttribute("title", "Kiểm kê "+departmentName+" năm "+ year);
        }


        for (Lab lab : labs) {
            InventoryResult inventoryResult = ShowInventoryForLabId(lab.getId(), year);
            inventoryResults.add(inventoryResult);
        }

        GetManagingUnitsFromUsername(model, username);

        model.addAttribute("inventoryResults", inventoryResults);
        model.addAttribute("sortedUniqueYears", sortedUniqueYears);
        model.addAttribute("year", year);
        model.addAttribute("departmentName", departmentName);
        model.addAttribute("managingUnitId", managingUnitId);
        model.addAttribute("success", success);
        model.addAttribute("managingUnitsCheck", managingUnitsCheck);

        return template;
    }

    private ArrayList<InventoryResult> ShowinventoryAllshowinventoryLab(boolean managingUnitsCheck, int yearSelected, int managingUnitId, String username, boolean success, ArrayList<InventoryResult> inventoryResults){
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        ShowinventoryAllshowinventoryLab(model, managingUnitsCheck, yearSelected, managingUnitId, username, success, inventoryResults);
        return inventoryResults;
    }

    private List<Integer> GetSortedUniqueYears_InventoryLab() {
        List<Integer> years = new ArrayList<>();
        inventoryLabService.getAllEquipmentLabs().forEach(e -> years.add(e.getYear()));
        Set<Integer> uniqueYears = new HashSet<>(years);
        List<Integer> sortedUniqueYears = new ArrayList<>(uniqueYears);
        Collections.sort(sortedUniqueYears);
        return sortedUniqueYears;
    }
    private List<Integer> GetSortedUniqueYears_InventoryEquipment() {
        List<Integer> years = new ArrayList<>();
        inventoryEquipmentService.getAllInventoryEquipment().forEach(e -> years.add(e.getYear()));
        Set<Integer> uniqueYears = new HashSet<>(years);
        List<Integer> sortedUniqueYears = new ArrayList<>(uniqueYears);
        Collections.sort(sortedUniqueYears);
        return sortedUniqueYears;
    }

    private List<ManagingUnit> GetManagingUnitsFromUsername(Model model, String username){
        /** lấy ra list managingUnits theo phân quyền */
        if(CheckRole(GetPeopleFromUsername(username),RoleSystem.ROLE_ADMIN)){
            List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
            model.addAttribute("managingUnits", managingUnits);
            return managingUnits;
        } else {
            List<Lab> labsFromPeopleId = labService.findAllByLabManagemetId(peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid()).getId());
            List<String> managingUnitsName = new ArrayList<>();
            labsFromPeopleId.forEach(l -> managingUnitsName.add(l.getManagingUnit()));
            List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits().stream()
                    .filter(m -> managingUnitsName.contains(m.getDepartmentName()))
                    .collect(Collectors.toList());
            model.addAttribute("managingUnits", managingUnits);
            return managingUnits;
        }
    }

                                /******************************************************/
                                        /** Kiểm kê theo thiết bị - Inventory */
                                /*****************************************************/


    @GetMapping({"/admin/Showinventory/Equipment"})
    public String ShowinventoryEquipment(Model model,
                                         @RequestParam(value = "managingUnitsCheck", defaultValue = "false") boolean managingUnitsCheck,
                                         @RequestParam(value = "yearSelected", defaultValue = "0") int yearSelected,
                                         @RequestParam(value = "managingUnitId", defaultValue = "0") int managingUnitId,
                                         @RequestParam("username") String username,
                                         @RequestParam(value = "success", defaultValue = "false") boolean success,
                                         @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                                         InventoryResult inventoryResult){

        /** Giống phần kiểm kê all lab */
        /*Lấy ra list years của các năm (kể cả nhưng năm mà phòng ko có danh sách)*/
        List<Integer> sortedUniqueYears = GetSortedUniqueYears_InventoryEquipment();
        model.addAttribute("sortedUniqueYears", sortedUniqueYears);

        int year = LocalDate.now().getYear();
        if(yearSelected!=0) year = yearSelected;
        model.addAttribute("year", year);

        model.addAttribute("success", success);
        model.addAttribute("managingUnitsCheck", managingUnitsCheck);

        GetManagingUnitsFromUsername(model, username);

        /** Phần khác của kiểm kê all equip */

        /** Nếu xem theo bộ môn (không check ô xem tất cả) */
        if(managingUnitId!=0 && !managingUnitsCheck){
            ArrayList<InventoryResult> inventoryResults_Year = new ArrayList<>();
            ShowinventoryAllshowinventoryLab(managingUnitsCheck, year, managingUnitId, username, success, inventoryResults_Year);
            List<EquipmentLabDTO> equipmentLabDTOsUnique_Year = GetEquipmentLabDTOsUnique_Year(inventoryResults_Year);

            ArrayList<InventoryResult> inventoryResults_MinusYear = new ArrayList<>();
            ShowinventoryAllshowinventoryLab(managingUnitsCheck, year-1, managingUnitId, username, success, inventoryResults_MinusYear);
            List<EquipmentLabDTO> equipmentLabDTOsUnique_MinusYear = GetEquipmentLabDTOsUnique_Year(inventoryResults_MinusYear);

            InventoryResult inventoryResultInventoryCompares = new InventoryResult();
            List<EquipmentLabDTO> equipmentLabDTOsUnique = CompareInventory(equipmentLabDTOsUnique_Year, equipmentLabDTOsUnique_MinusYear, inventoryResultInventoryCompares.getInventoryCompares());
            inventoryResult.setInventoryCompares(inventoryResultInventoryCompares.getInventoryCompares());
            inventoryResult.setEquipmentLabDTOs(equipmentLabDTOsUnique);

            model.addAttribute("inventoryResult", inventoryResult);
            model.addAttribute("managingUnitName", managingUnitService.getManagingUnitById(managingUnitId).getDepartmentName());
        }

        /** Nếu xem tất cả) */
        if(managingUnitsCheck || (managingUnitId==0 && !managingUnitsCheck)){
            InventoryEquipment inventoryEquipment_Year =  inventoryEquipmentService.findByYear(year);
            List<EquipmentLabDTO> equipmentLabDTOsUnique_Year;
            if(inventoryEquipment_Year==null && !sortedUniqueYears.contains(LocalDate.now().getYear())){
                ArrayList<InventoryResult> inventoryResults_Year = new ArrayList<>();
                if(!CheckRole(peopleService.findByPeopleId(userService.findByUsername(username).getPeopleid()),RoleSystem.ROLE_ADMIN)){
                    ShowinventoryAllshowinventoryLab(managingUnitsCheck, year, managingUnitId, "admin", success, inventoryResults_Year);
                    equipmentLabDTOsUnique_Year = GetEquipmentLabDTOsUnique_Year(inventoryResults_Year);
                    SaveEquipmentLabDTOsToDataBase(0, LocalDate.now().getYear(), equipmentLabDTOsUnique_Year);
                }
                ShowinventoryAllshowinventoryLab(managingUnitsCheck, year, managingUnitId, username, success, inventoryResults_Year);
                equipmentLabDTOsUnique_Year = GetEquipmentLabDTOsUnique_Year(inventoryResults_Year);
            } else {
                equipmentLabDTOsUnique_Year = GetListEquipmentLabDTO_FromInventoryEquip(inventoryEquipment_Year, username);
            }
            InventoryEquipment inventoryEquipment_MinusYear =  inventoryEquipmentService.findByYear(year-1);
            List<EquipmentLabDTO> equipmentLabDTOsUnique_MinusYear;
            if(inventoryEquipment_MinusYear!=null){
                equipmentLabDTOsUnique_MinusYear = GetListEquipmentLabDTO_FromInventoryEquip(inventoryEquipment_MinusYear, username);
            } else {
                equipmentLabDTOsUnique_MinusYear = new ArrayList<>();
            }
            InventoryResult inventoryResultCompare = new InventoryResult();
            List<EquipmentLabDTO> equipmentLabDTOS = CompareInventory(equipmentLabDTOsUnique_Year, equipmentLabDTOsUnique_MinusYear, inventoryResultCompare.getInventoryCompares());

//            inventoryResult = new InventoryResult();
            inventoryResult.setEquipmentLabDTOs(equipmentLabDTOS);
            inventoryResult.setInventoryCompares(inventoryResultCompare.getInventoryCompares());
            model.addAttribute("inventoryResult", inventoryResult);
        }

        model.addAttribute("managingUnitId", managingUnitId);
        model.addAttribute("title", "Kiểm kê trang thiết bị năm " + year);

        return template;
    }

    private InventoryResult ShowinventoryEquipment(boolean managingUnitsCheck,int yearSelected,int managingUnitId,
                                                   String username,boolean success,boolean unsuccess){
        InventoryResult inventoryResult = new InventoryResult();
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        ShowinventoryEquipment(model, managingUnitsCheck, yearSelected, managingUnitId, username, success, unsuccess, inventoryResult);
        return inventoryResult;
    }


    private List<EquipmentLabDTO> GetEquipmentLabDTOsUnique_Year(List<InventoryResult> inventoryResults_Year){
        /** Test lấy ds Equip */
        List<EquipmentLabDTO> allEquipmentLabDTOs = new ArrayList<>();
        List<EquipmentLabDTO> equipmentLabDTOsUnique_Year = new ArrayList<>();
        inventoryResults_Year.forEach(i -> {
            i.getEquipmentLabDTOs().forEach(e -> {
                allEquipmentLabDTOs.add(e);
            });
        });

        List<Integer> listEquipIdUnique = new ArrayList<>();
        for (EquipmentLabDTO equipLabDTO_all : allEquipmentLabDTOs) {
            if(!listEquipIdUnique.contains(equipLabDTO_all.getEquipmentId())){
                /** Nếu listEquipIdUniqueCoppy chưa có EquipmentId này */
                equipmentLabDTOsUnique_Year.add(equipLabDTO_all);
                listEquipIdUnique.add(equipLabDTO_all.getEquipmentId());
            } else {
                for (EquipmentLabDTO equipmentLabDTO_Unique : equipmentLabDTOsUnique_Year) {
                    if(equipmentLabDTO_Unique.getEquipmentId()==equipLabDTO_all.getEquipmentId()){
                        /** Tìm equipmentLabDTO_Unique chứa Equipment trong equipmentLabDTOsUnique để thêm các thông số */
                        List<String> series_Unique = equipmentLabDTO_Unique.getEquipmentSerieList();
                        List<String> levels_Unique = equipmentLabDTO_Unique.getLevels();
                        List<String> usingdates_Unique = equipmentLabDTO_Unique.getUsingdates();
                        for (int i = 0; i < equipLabDTO_all.getEquipmentSerieList().size(); i++) {
                            series_Unique.add(equipLabDTO_all.getEquipmentSerieList().get(i));
                            levels_Unique.add(equipLabDTO_all.getLevels().get(i));
                            usingdates_Unique.add(equipLabDTO_all.getUsingdates().get(i));
                        }
                        equipmentLabDTO_Unique.setEquipmentSerieList(series_Unique);
                        equipmentLabDTO_Unique.setEquipmentSeries(series_Unique.toString());
                        equipmentLabDTO_Unique.setLevels(levels_Unique);
                        equipmentLabDTO_Unique.setUsingdates(usingdates_Unique);
                    }
                }
            }
        }
        return equipmentLabDTOsUnique_Year;
        /** Kết thúc test */
    }


                                        /******************************************************/
                                                /** Quản lý bài thí nghiệm - Lesson */
                                        /*****************************************************/

    @GetMapping({"/admin/Lesson"})
    public String Lesson(Model model,
                         @RequestParam("username") String username,
                         @RequestParam(value = "success", defaultValue = "false") boolean success,
                         @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        People people = GetPeopleByUsername(username);
        boolean isAdmin = CheckRole(people,RoleSystem.ROLE_ADMIN);
        List<LessonDTO> lessonDTOs = new ArrayList<>();
        List<Lab> labs = new ArrayList<>();
        if(isAdmin){
            labs = labService.getAllLabsOnLine();
        }  else {
            labs = labService.getAllLabsOnLine().stream().filter(lab -> lab.getLabManagemetId()==people.getId()).collect(Collectors.toList());
        }

        lessonService.getAllLessons().forEach(lesson -> {
            Lab lab = labService.findByLabId(lesson.getLabId());
            if(isAdmin){
                lessonDTOs.add(new LessonDTO(lesson, lab));
            } else {
                if(lab.getLabManagemetId()==people.getId()){
                    lessonDTOs.add(new LessonDTO(lesson, lab));
                }
            }
        });

        model.addAttribute("labs", labs);
        model.addAttribute("lessonDTOs", lessonDTOs);
        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        model.addAttribute("title", "Danh mục bài thí nghiệm");

        return template;
    }

    @GetMapping("/admin/Lesson/delete")
    public String DeleteLesson(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("lessonId") int lessonId,
                               @RequestParam(value = "success", defaultValue = "false") boolean success,
                               @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        try {
            lessonService.deleteLesson(lessonId);
            return Redirect("admin/Lesson?username="+username, true);
        } catch (Exception e){
            e.printStackTrace();
            return Redirect("admin/Lesson?username="+username, false);
        }
    }
    @PostMapping("/admin/Lesson/add")
    public String AddLesson(@ModelAttribute Lesson lesson,
                            @RequestParam("username") String username,
                            @RequestParam(value = "success", defaultValue = "false") boolean success,
                            @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        try {
            if(lesson.getId()!=0){
                lessonService.updateLesson(lesson.getId(),lesson);
            } else {
                lessonService.createLesson(lesson);
            }
            return Redirect("admin/Lesson?username="+username, true);
        } catch (Exception e){
            e.printStackTrace();
            return Redirect("admin/Lesson?username="+username, false);
        }
    }

                                /******************************************************/
                                            /** Quản lý Export Report Excel */
                                /*****************************************************/

    private List<BookingDtoExport> GetBookingDtoExportByStatus(String status, String username){
        People people = GetPeopleByUsername(username);
        boolean isAdmin = CheckRole(people, RoleSystem.ROLE_ADMIN);
        List<Booking> bookings = bookingService.getAllBookings().stream().filter(booking -> booking.getConfirmStatus().equals(status)).collect(Collectors.toList());
        List<BookingDtoExport> bookingDtoExports = new ArrayList<>();
        bookings.forEach(booking -> {
            Content content = contentService.getContentById(booking.getContentid());
            People reservationist = peopleService.findByPeopleId(content.getReservationistId());
            ExperimentType experimentType = experimentTypeService.getExperimentTypeById(content.getExperimentType());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            Lab lab = labService.findByLabId(booking.getLabid());
            if(isAdmin){
                bookingDtoExports.add(new BookingDtoExport(booking,lab.getLabName(), content, experimentType.getTypeName(), experimentReport.getReportType(),reservationist.getRank() + " " + reservationist.getName()));
            } else {
                if(lab.getLabManagemetId()==people.getId()){
                    bookingDtoExports.add(new BookingDtoExport(booking,lab.getLabName(), content, experimentType.getTypeName(), experimentReport.getReportType(),reservationist.getRank() + " " + reservationist.getName()));
                }
            }
        });
        return bookingDtoExports;
    }

    private Workbook createSheet(Workbook workbook, List<?> dataList, String[] headers, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        Row headerRow = sheet.createRow(0);
        fillDataToSheet(sheet, headers, dataList, 0);
        return workbook;
    }
    private Workbook createSheet(Workbook workbook, List<?> dataList, String[] headers, String sheetName, boolean inventoryExport) {
        Sheet sheet = workbook.createSheet(sheetName);
        Row headerRow = sheet.createRow(0);
        fillDataToSheetInventoryExport(sheet, headers, dataList, 0);
        return workbook;
    }

    private ResponseEntity<Resource> exportData(Workbook workbook, String fileName) {
        return ResponseExcel(workbook, fileName);
    }

    private String getFileName(List<?> dataList) {
        String status = "Trống";
        if (!dataList.isEmpty()) {
            Object firstData = dataList.get(0);
            if (firstData instanceof BookingDtoExport) {
                status = ((BookingDtoExport) firstData).getComfirmUsed();
            } else if (firstData instanceof LabDtoExport) {
                status = "Phòng thí nghiệm";
            } else if (firstData instanceof ScoresLabExport) {
                status = "Danh sách điểm";
            }
        }
        return "Danh sách " + status;
    }

    private ResponseEntity<Resource> ResponseExcel(Workbook workbook, String excelName) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + excelName + ".xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void fillDataToSheet(Sheet sheet, String[] headers, List<?> dataList, int startCellIndex) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i + startCellIndex);
            cell.setCellValue(headers[i]);
        }

        int rowIndex = 1;
        for (Object data : dataList) {
            Row row = sheet.createRow(rowIndex++);
            Field[] fields = data.getClass().getDeclaredFields();
            int cellIndex = startCellIndex;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Cell cell = row.createCell(cellIndex++);
                    Object value = field.get(data);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fillDataToSheetInventoryExport(Sheet sheet, String[] headers, List<?> dataList, int startCellIndex) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i + startCellIndex);
            cell.setCellValue(headers[i]);
        }

        int rowIndex = 1;
        for (Object data : dataList) {
            Field[] fields = data.getClass().getDeclaredFields();
            int cellIndex = startCellIndex;

            /** Java áp đặt các ràng buộc truy cập để bảo vệ tính đóng gói của đối tượng. Bằng cách sử dụng phương thức setAccessible(true),
             chúng ta có thể bỏ qua ràng buộc này và truy cập vào các trường không công khai.*/
            int mergeRowCount = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(List.class)) {
                /** Kiểm tra kiểu dữ liệu của trường có phải là List không*/
                    try {
                        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                        /** Ép kiểu trường thành List String và lấy giá trị của trường từ đối tượng data*/
                        Object Series_value = field.get(data);
                        List<String> listSeries_Value = (List<String>) Series_value;
                        /** Lấy số lượng phần tử trong danh sách*/
                        mergeRowCount = listSeries_Value.size();
                        break;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            List<Row> rows = new ArrayList<>();
            for (int i = 0; i < mergeRowCount; i++) {
                Row row = sheet.createRow(rowIndex++);
                rows.add(row);
            }
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(data);
                    Cell cell = rows.get(0).createCell(cellIndex++);
                    if (value instanceof List) {
                        List<String> listValue = (List<String>) value;
                        for (int i = 0; i < listValue.size(); i++) {
                            if(i==0) cell.setCellValue(listValue.get(i));
                            for (int i1 = 1; i1 < rows.size(); i1++) {
                                if(i == i1) rows.get(i1).createCell(cellIndex-1).setCellValue(listValue.get(i));
                            }
                        }
                    } else {
                        if (value != null) {
                            if(mergeRowCount>1) sheet.addMergedRegion(new CellRangeAddress(rows.get(0).getRowNum(), rows.get(0).getRowNum() + mergeRowCount - 1, cell.getColumnIndex(), cell.getColumnIndex()));
                            cell.setCellValue(value.toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/admin/Export/LabBookingPendding")
    public ResponseEntity<Resource> ExportLabBookingPendding(@RequestParam("username") String username) {
        List<BookingDtoExport> bookingDtoExports = GetBookingDtoExportByStatus(ConfirmStatus.PENDDING, username);
        return ExportLabBooking(bookingDtoExports, "Danh sách đơn chờ duyệt");
    }
    @GetMapping("/admin/Export/LabBookingApprove")
    public ResponseEntity<Resource> ExportLabBookingApprove(@RequestParam("username") String username) {
        List<BookingDtoExport> bookingDtoExports = GetBookingDtoExportByStatus(ConfirmStatus.APPROVE, username);
        return ExportLabBooking(bookingDtoExports, "Danh sách đơn đã chấp nhận");
    }
    @GetMapping("/admin/Export/LabBookingWaitComfirmUsed")
    public ResponseEntity<Resource> ExportLabBookingWaitComfirmUsed(@RequestParam("username") String username) {
        List<BookingDtoExport> bookingDtoExports = GetBookingDtoExportByStatus(ConfirmStatus.APPROVE, username)
                                                    .stream().filter(b -> b.getComfirmUsed().equals(ConfirmUsed.USED))
                                                    .collect(Collectors.toList());
        return ExportLabBooking(bookingDtoExports, "Danh sách đơn chờ người dùng xác nhận");
    }
    @GetMapping("/admin/Export/LabBookingCancel")
    public ResponseEntity<Resource> ExportLabBookingCancel(@RequestParam("username") String username) {
        List<BookingDtoExport> bookingDtoExports = GetBookingDtoExportByStatus(ConfirmStatus.CANCEL, username);
        return ExportLabBooking(bookingDtoExports, "Danh sách đơn đã hủy");
    }

    private ResponseEntity<Resource> ExportLabBooking(List<BookingDtoExport> bookingDtoExports, String sheetName){
        String[] headers = {"ID", "Tên PTN", "Tên bài", "Tên lớp", "Người đặt", "Số giờ", "Số người", "Ngày đặt", "Ghi chú", "Loại hình TN", "Loại BC", "Trạng thái quản lý", "Trạng thái người dùng", "Thao tác"};
        Workbook workbook = new XSSFWorkbook();
        createSheet(workbook, bookingDtoExports, headers,   sheetName);
        return exportData(workbook, sheetName);
    }

            /*********************************/
                    /** Export Lab */
            /********************************/

    private List<LabDtoExport> GetLabDtoExport(String username){
        List<LabDtoExport> labDtoExports = new ArrayList<>();
            People people = GetPeopleByUsername(username);
            boolean isAdmin = CheckRole(people, RoleSystem.ROLE_ADMIN);
            labService.getAllLabsOnLine().forEach(lab -> {
                People labManager = peopleService.findByPeopleId(lab.getLabManagemetId());
                if(isAdmin){
                    labDtoExports.add(new LabDtoExport(lab, labManager.getRank()+ " " +labManager.getName()));
                }else {
                    if (lab.getLabManagemetId()==people.getId()){
                        labDtoExports.add(new LabDtoExport(lab, people.getRank()+ " " +people.getName()));
                    }
                }
            });
        return labDtoExports;
    }
    @GetMapping("/admin/Export/Labs")
    public ResponseEntity<Resource> ExportLabs(@RequestParam("username") String username) {
        List<LabDtoExport> labDtoExports = GetLabDtoExport(username);
        String[] headers = {"ID", "Tên PTN", "Người quản lý", "Đơn vị chủ quản", "Sức chứa (người)", "Vị trí"};
        Workbook workbook = new XSSFWorkbook();
        createSheet(workbook, labDtoExports, headers,"Danh sách phòng thí nghiệm");
        return exportData(workbook, "Danh sách phòng thí nghiệm");
    }

    @GetMapping("/admin/Export/LabAndScores/{labId}")
    public ResponseEntity<Resource> ExportLabAndScores(@PathVariable("labId") int labId, Model model,
                                                       @RequestParam("username") String username,
                                                       @RequestParam(value = "experiment_group", defaultValue = "0") int experiment_group,
                                                       @RequestParam(value = "experiment_type", defaultValue = "0") int experiment_type,
                                                       @RequestParam(value = "experiment_report", defaultValue = "0") int experiment_report,
                                                       @RequestParam(value = "experiment_YesNo", defaultValue = "false") boolean experiment_YesNo,
                                                       @RequestParam(value = "start_date", defaultValue = "") String startDate,
                                                       @RequestParam(value = "end_date", defaultValue = "") String endDate,
                                                       @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess,
                                                       @RequestParam(value = "success", defaultValue = "false") boolean success){
        /** Lấy thông tin lab */
        List<LabDtoExport> labDtoExports = GetLabDtoExport(username).stream().filter(labDtoExport -> labDtoExport.getId()==labId).collect(Collectors.toList());
        Workbook workbook = new XSSFWorkbook();
        String[] headersLab = {"ID", "Tên PTN", "Người quản lý", "Đơn vị chủ quản", "Sức chứa (người)", "Vị trí"};
        createSheet(workbook, labDtoExports, headersLab,"Thông tin PTN");


        /** Lấy thông tin chấm điểm lab*/
        List<LabsOnLineAndScore> labsOnLineAndScoresX = new ArrayList<>();
        List<LabsOnLineAndScore> labsOnLineAndScores = GetLabsOnLineAndScore(labsOnLineAndScoresX, model, experiment_group, experiment_type, experiment_report,
                experiment_YesNo, startDate.split("T")[0], endDate.split("T")[0], success, unsuccess);
        LabsOnLineAndScore labsOnLineAndScoreData = new LabsOnLineAndScore();
        for (LabsOnLineAndScore labsOnLineAndScore : labsOnLineAndScores) {
            if(labsOnLineAndScore.getLab().getId()==labId){
                labsOnLineAndScoreData = labsOnLineAndScore;
            }
        }
        List<ScoresLabExport> scoresLabExports = new ArrayList<>();
        for (int i = 0; i < labsOnLineAndScoreData.getTypeName().size(); i++) {
            String typeName = labsOnLineAndScoreData.getTypeName().get(i);
            if(typeName.contains("Giờ khai thác")){
                ScoresLabExport scoresLabExport = new ScoresLabExport(i+1, labsOnLineAndScoreData.getTypeName().get(i),labsOnLineAndScoreData.getQuantity().get(i), labsOnLineAndScoreData.getHour(), labsOnLineAndScoreData.getScores().get(i), labsOnLineAndScoreData.getScores().get(i)*labsOnLineAndScoreData.getHour());
                scoresLabExports.add(scoresLabExport);
            } else {
                ScoresLabExport scoresLabExport = new ScoresLabExport(i+1, labsOnLineAndScoreData.getTypeName().get(i),labsOnLineAndScoreData.getQuantity().get(i), 0, labsOnLineAndScoreData.getScores().get(i), labsOnLineAndScoreData.getScores().get(i)*labsOnLineAndScoreData.getQuantity().get(i));
                scoresLabExports.add(scoresLabExport);
            }
        }
        String[] headersScore = {"STT", "Phân loại", "Số lượng (Bài TN hoặc)", "Tổng giờ", "Điểm", "Tổng điểm"};
        createSheet(workbook, scoresLabExports, headersScore,"Chi tiết điểm");

        /** Lấy thông tin chấm điểm: các bài thí nghiệm đã được khai thác */
        String[] headersLesson = {"ID", "ID PTN", "Mã bài TN", "Tên bài", "Thời lượng (giờ)", "Loại", "Trạng thái"};
        List<Lesson> lessonsUsed = new ArrayList<>();
        List<Lesson> lessons = lessonService.findAllByLabId(labId);
        for (String lessonName : labsOnLineAndScoreData.getLessonName()) {
            Lesson lesson = lessonService.findByName(lessonName);
            lessonsUsed.add(lesson);
        }
        createSheet(workbook, lessonsUsed, headersLesson,"Danh sách bài thí nghiệm đã thực hiện");

        /** Tất cả các bài thí nghiệm của PTN */
        createSheet(workbook, lessons, headersLesson,"Danh sách bài thí nghiệm của PTN");

        /** Các yêu cầu đặt phòng của phòng */
        String[] headers = {"ID", "Tên PTN", "Tên bài", "Tên lớp", "Người đặt", "Số giờ", "Số người", "Ngày đặt", "Ghi chú", "Loại hình TN", "Loại BC", "Trạng thái quản lý", "Trạng thái người dùng", "Thao tác"};
        List<BookingDtoExport> bookingDtoExports_PENDDING = GetBookingDtoExportByStatus(ConfirmStatus.PENDDING, username)
                                                            .stream().filter(b -> b.getLabName().equals(labDtoExports.get(0).getLabName())).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_PENDDING, headers,"Danh sách đơn chờ xử lý");

        List<BookingDtoExport> bookingDtoExports_APPROVE = GetBookingDtoExportByStatus(ConfirmStatus.APPROVE, username)
                                                            .stream().filter(b -> b.getLabName().equals(labDtoExports.get(0).getLabName())).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_APPROVE, headers,"Danh sách đơn đã xác nhận");

        List<BookingDtoExport> bookingDtoExports_APPROVE_USED = GetBookingDtoExportByStatus(ConfirmStatus.APPROVE, username)
                                                            .stream().filter(b -> b.getComfirmUsed().equals(ConfirmUsed.USED)
                                                                            && b.getLabName().equals(labDtoExports.get(0).getLabName()))
                                                                            .collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_APPROVE_USED, headers,"Danh sách đơn người dùng đã xác nhận");

        List<BookingDtoExport> bookingDtoExports_CANCEL = GetBookingDtoExportByStatus(ConfirmStatus.CANCEL, username)
                                                            .stream().filter(b -> b.getLabName().equals(labDtoExports.get(0).getLabName())).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_CANCEL, headers,"Danh sách đơn đã hủy");

        /** Danh sách thiết bị của phòng */
        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByLabId(labId);
        List<EquipmentDtoExport> equipmentDtoExports = new ArrayList<>();
        for (EquipmentLab equipmentLab : equipmentLabs) {
            Equipment equipment = equipmentService.findByEquipmentId(equipmentLab.getEquipmentId());
            for (int i = 0; i < equipmentLab.getEquipmentSerieList().size(); i++) {
                EquipmentDtoExport equipmentDtoExport = new EquipmentDtoExport(equipment.getId(),equipment.getName(), equipmentLab.getEquipmentSerieList().get(i),
                                                                                equipment.getUnit(), equipmentLab.getLevelList().get(i), equipmentLab.getUsingList().get(i), equipment.getOrigin(),equipment.getDescription());

                equipmentDtoExports.add(equipmentDtoExport);
            }
        }

        String[] headersEquip = {"ID", "Tên PTN", "Seri/Mã số", "Đơn vị tính", "Xuất sứ", "Phân cấp TB", "Ngày bắt đầu sử dụng", "Mô tả"};
        createSheet(workbook, equipmentDtoExports, headersEquip,"Danh sách TTB thuộc PTN");

        return exportData(workbook, "Chi tiết chấm điểm của " + labDtoExports.get(0).getLabName());
    }

                /*********************************/
                    /** Export Managing Unit */
                /********************************/

    @GetMapping("/admin/Export/ManagingUnits")
    public ResponseEntity<Resource> ExportManagingUnits() {
                    List<ManagingUnit> managingUnits = managingUnitService.getAllManagingUnits();
                    String[] headers = {"ID", "Tên đơn vị chủ quản"};
                    Workbook workbook = new XSSFWorkbook();
                    createSheet(workbook, managingUnits, headers,"Đơn vị chủ quản");
                    return exportData(workbook, "Danh sách Đơn vị chủ quản PTN");
        }

                /*********************************/
                    /** Export Lesson */
                /********************************/

    @GetMapping("/admin/Export/Lesson")
    public ResponseEntity<Resource> ExportManagingUnits(@RequestParam("username") String username) {
        People people = GetPeopleByUsername(username);
        boolean isAdmin = CheckRole(people,RoleSystem.ROLE_ADMIN);
        List<LessonDtoExport> lessonDtoExports = new ArrayList<>();
        lessonService.getAllLessons().forEach(lesson -> {
            Lab lab = labService.findByLabId(lesson.getLabId());
            if(isAdmin){
                lessonDtoExports.add(new LessonDtoExport(lesson.getId(), lab.getLabName(), lesson.getNoLesson(), lesson.getName(), lesson.getModule(), lesson.getLevel(), lesson.getWorkTime(), lesson.getType(), lesson.getNote()));
            } else {
                if(lab.getLabManagemetId()==people.getId()){
                    lessonDtoExports.add(new LessonDtoExport(lesson.getId(), lab.getLabName(), lesson.getNoLesson(), lesson.getName(), lesson.getModule(), lesson.getLevel(), lesson.getWorkTime(), lesson.getType(), lesson.getNote()));
                }
            }
        });
        String[] headers = {"ID", "Tên PTN", "Mã số", "Tên bài TN", "Học phần môn học", "Cấp học", "Thời lượng (giờ)", "Phân loại", "Ghi chú"};
        Workbook workbook = new XSSFWorkbook();
        createSheet(workbook, lessonDtoExports, headers,"Danh sách bài thí nghiệm");
        return exportData(workbook, "Danh sách bài thí nghiệm");
    }

                /*********************************/
                    /** Export Equipment */
                /********************************/

    @GetMapping("/admin/Export/EquipmentAll")
    public ResponseEntity<Resource> ExportEquipmentAll(@RequestParam("username") String username) {
            List<EquipmentDtoExportWithLabName> equipmentDtoExportWithLabNames = new ArrayList<>();
            People people = peopleService.findByPeopleId(GetPeopleIdByUsername(username));
            List<EquipmentLab> allEquipmentLab = equipmentLabService.getAllEquipmentLabs();
            List<Equipment> allEquipment = new ArrayList<>();

            if (CheckRole(people, RoleSystem.ROLE_ADMIN)) {
                allEquipment = equipmentService.getAllEquipment().stream()
                                                .filter(equipment -> equipment.getIsDeleted() == 0)
                                                .collect(Collectors.toList());
                for (Equipment equipment : allEquipment) {
                    List<String> labNames = new ArrayList<>();
                    List<Integer> labIds = new ArrayList<>();
                    for (int i = 0; i < equipment.getSeriesAsList().size(); i++) {
                        labNames.add("");
                        labIds.add(0);
                    }
                    for (EquipmentLab equipmentLab : allEquipmentLab) {
                        if (equipmentLab.getEquipmentId() == equipment.getId()) {
                            Lab lab = labService.findByLabId(equipmentLab.getLabId());
                            for (String s : equipmentLab.getEquipmentSerieList()) {
                                labNames.add(lab.getLabName());
                                labIds.add(lab.getId());
                            }
                            SetSeriLevelUsing_For_EquipFromEquiLab(equipmentLab, equipment);
                        }
                    }
                    CreateEquipDtoExportWithLabName(equipmentDtoExportWithLabNames, equipment, labNames);
                }
            } else {
                List<EquipmentLab> allEquipmentLab_AfterFilter = new ArrayList<>();
                /** Lấy ra Lab do người đó phụ trách */
                List<Lab> labs = labService.getAllLabsOnLine().stream()
                        .filter(lab -> lab.getLabManagemetId() == people.getId())
                        .collect(Collectors.toList());
                /** Lấy ra DS equipment ID chứa những ID duy nhất */
                Set<Integer> uniqueEquipmentIds = new HashSet<>();
                for (Lab lab : labs) {
                    for (EquipmentLab equipmentLab : allEquipmentLab) {
                        if (equipmentLab.getLabId() == lab.getId()) {
                            uniqueEquipmentIds.add(equipmentLab.getEquipmentId());
                            allEquipmentLab_AfterFilter.add(equipmentLab);
                        }
                    }
                }
                /** Tạo DS allEquipment chứa những equipment duy nhất và set trống seri, level, using */
                for (Integer id : uniqueEquipmentIds) {
                    Equipment equipment = equipmentService.findByEquipmentId(id);
                    equipment.setSeries("[]");
                    equipment.setLevelList("[]");
                    equipment.setUsingList("[]");
                    allEquipment.add(equipment);
                }
                /** trả về DS equipmentDtoExportWithLabNames để xuất */
                for (Equipment equipment : allEquipment) {
                    List<String> labNames = new ArrayList<>();
                    List<Integer> labIds = new ArrayList<>();

                    for (EquipmentLab equipmentLab : allEquipmentLab_AfterFilter) {
                        /** Lọc những equip trong equip Lab */
                        if (equipment.getId() == equipmentLab.getEquipmentId()) {
                            Lab lab = labService.findByLabId(equipmentLab.getLabId());
                            /** Bỏ vào vòng lặp vì có bn seri thì thêm từng đó lần tên
                             *  do SetSeriLevelUsing_For_EquipFromEquiLab sử dụng addAll*/
                            for (String s : equipmentLab.getEquipmentSerieList()) {
                                labIds.add(lab.getId());
                                labNames.add(lab.getLabName());
                            }
                            SetSeriLevelUsing_For_EquipFromEquiLab(equipmentLab, equipment);
                        }
                    }
                CreateEquipDtoExportWithLabName(equipmentDtoExportWithLabNames, equipment, labNames);
                }
            }

            String[] headers = {"ID", "Tên TB", "Seri/Mã số", "Đơn vị tính", "Phân cấp TB", "Ngày bắt đầu sử dụng", "Xuất sứ", "Tên PTN", "Mô tả"};
            Workbook workbook = new XSSFWorkbook();
            Set<String> uniqueLabNames_Set = new HashSet<>(); // Tạo một set để lưu trữ các tên labName duy nhất
            for (EquipmentDtoExportWithLabName equipment : equipmentDtoExportWithLabNames) {
                uniqueLabNames_Set.add(equipment.getLabName()); // Thêm tên labName vào set
            }
            List<String> uniqueLabNames = new ArrayList<>(uniqueLabNames_Set);
            int z = 0;
            if(uniqueLabNames.contains("")) {
                Sheet sheet = workbook.createSheet(0 + ". DSTB Chưa được biên chế");
                Row headerRow = sheet.createRow(0);
                int rowIndex = 1;
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                for (EquipmentDtoExportWithLabName equipment : equipmentDtoExportWithLabNames) {
                    if (equipment.getLabName().equals("")) {
                        rowIndex = CreatedRowFromIndex(sheet, rowIndex, equipment);
                    }
                }
                uniqueLabNames.remove("");
            }
            for (String labName : uniqueLabNames) {
                Sheet sheet = workbook.createSheet(++z + ". Danh sách thiết bị " + labName);

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                int rowIndex = 1;
                for (EquipmentDtoExportWithLabName equipment : equipmentDtoExportWithLabNames) {
                    if (equipment.getLabName().equals(labName)) {
                        rowIndex = CreatedRowFromIndex(sheet, rowIndex, equipment);
                    }
                }
            }
            return ResponseExcel(workbook, "Danh sách tất cả các thiết bị thuộc phòng quản lý");
        }

    private int CreatedRowFromIndex(Sheet sheet, int rowIndex, EquipmentDtoExportWithLabName equipment) {
        Row dataRow = sheet.createRow(rowIndex);
        dataRow.createCell(0).setCellValue(equipment.getId());
        dataRow.createCell(1).setCellValue(equipment.getName());
        dataRow.createCell(2).setCellValue(equipment.getSerie());
        dataRow.createCell(3).setCellValue(equipment.getUnit());
        dataRow.createCell(4).setCellValue(equipment.getLevel());
        dataRow.createCell(5).setCellValue(equipment.getUsing());
        dataRow.createCell(6).setCellValue(equipment.getOrigin());
        dataRow.createCell(7).setCellValue(equipment.getLabName());
        dataRow.createCell(8).setCellValue(equipment.getDescription());

        rowIndex++;
        return rowIndex;
    }

    private List<EquipmentDtoExportWithLabName> CreateEquipDtoExportWithLabName(List<EquipmentDtoExportWithLabName> equipmentDtoExportWithLabNames, Equipment equipment, List<String> labNames) {
        for (int i = 0; i < equipment.getSeriesAsList().size(); i++) {
            equipmentDtoExportWithLabNames.add(new EquipmentDtoExportWithLabName(
                    equipment.getId(),
                    equipment.getName(),
                    equipment.getSeriesAsList().get(i),
                    equipment.getUnit(),
                    equipment.getLevelList().get(i),
                    equipment.getUsingList().get(i),
                    equipment.getOrigin(),
                    labNames.get(i),
                    equipment.getDescription()
            ));
        }
        return equipmentDtoExportWithLabNames;
    }

    private void SetSeriLevelUsing_For_EquipFromEquiLab(EquipmentLab equipmentLab, Equipment equipment) {
        List<String> series = equipment.getSeriesAsList();
        series.addAll(equipmentLab.getEquipmentSerieList());
        equipment.setSeries(series.toString());
        List<String> leveles = equipment.getLevelList();
        leveles.addAll(equipmentLab.getLevelList());
        equipment.setLevelList(leveles.toString());
        List<String> usinges = equipment.getUsingList();
        usinges.addAll(equipmentLab.getUsingList());
        equipment.setUsingList(usinges.toString());
    }

    @GetMapping("/admin/Export/Equipment")
    public ResponseEntity<Resource> ExportEquipment(@RequestParam("username") String username,
                                                    @RequestParam("equipmentId") int equipmentId) {

        Equipment equipment = equipmentService.findByEquipmentId(equipmentId);
        List<EquipmentLab> equipmentLabs = equipmentLabService.findAllByEquipmentId(equipmentId);
        List<EquipmentDtoExportWithLabName> equipmentDtoExportWithLabNames = new ArrayList<>();
        List<String> labNames = new ArrayList<>();
        List<Integer> labIds = new ArrayList<>();
        for (int i = 0; i < equipment.getSeriesAsList().size(); i++) {
            labIds.add(0);
            labNames.add("");
        }
        for (EquipmentLab equipmentLab : equipmentLabs) {
            Lab lab = labService.findByLabId(equipmentLab.getLabId());
            equipmentLab.getEquipmentSerieList().forEach(e -> {
                labNames.add(lab.getLabName());
                labIds.add(lab.getId());
            });
            SetSeriLevelUsing_For_EquipFromEquiLab(equipmentLab, equipment);
        }
        CreateEquipDtoExportWithLabName(equipmentDtoExportWithLabNames, equipment, labNames);

        String[] headers = {"ID", "Tên TB", "Seri/Mã số", "Đơn vị tính", "Phân cấp TB", "Ngày bắt đầu sử dụng", "Xuất sứ", "Tên PTN", "Mô tả"};
        Workbook workbook = new XSSFWorkbook();
        createSheet(workbook, equipmentDtoExportWithLabNames, headers,"DS TB " + equipment.getName());
        return exportData(workbook, "DS TB "  + equipment.getName());
    }



                /*********************************/
                        /** Export Roles */
                /********************************/

    @GetMapping("/admin/Export/LabRankings")
    public ResponseEntity<Resource> ExportLabRankings(Model model,
                                                      @RequestParam(value = "experiment_group", defaultValue = "0") int experiment_group,
                                                      @RequestParam(value = "experiment_type", defaultValue = "0") int experiment_type,
                                                      @RequestParam(value = "experiment_report", defaultValue = "0") int experiment_report,
                                                      @RequestParam(value = "experiment_YesNo", defaultValue = "false") boolean experiment_YesNo,
                                                      @RequestParam(value = "start_date", defaultValue = "") String startDate,
                                                      @RequestParam(value = "end_date", defaultValue = "") String endDate,
                                                      @RequestParam(value = "success", defaultValue = "false") boolean success,
                                                      @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        Workbook workbook = new XSSFWorkbook();

        /** Lấy thông tin chấm điểm lab*/
        List<LabsOnLineAndScore> labsOnLineAndScoresX = new ArrayList<>();
        List<LabsOnLineAndScore> labsOnLineAndScores = GetLabsOnLineAndScore(labsOnLineAndScoresX, model, experiment_group, experiment_type, experiment_report,
                experiment_YesNo, startDate.split("T")[0], endDate.split("T")[0], success, unsuccess);
        List<ScoresLabRankingsExport> scoresLabRankingsExports = new ArrayList<>();
        for (int i = 0; i < labsOnLineAndScores.size(); i++) {
            int STT = i +1;
            scoresLabRankingsExports.add(new ScoresLabRankingsExport(
                    STT, "Hạng " +STT, labsOnLineAndScores.get(i).getLab().getLabName(), labsOnLineAndScores.get(i).getScore()
            ));
        }

        List<List<ScoresLabExport>> scoresLabExports_List = new ArrayList<>();

        for (LabsOnLineAndScore labsOnLineAndScore : labsOnLineAndScores) {
            List<ScoresLabExport> scoresLabExports = new ArrayList<>();
            for (int i = 0; i < labsOnLineAndScore.getTypeName().size(); i++) {
                String typeName = labsOnLineAndScore.getTypeName().get(i);
                if(!typeName.contains("Giờ khai thác")){
                    scoresLabExports.add(new ScoresLabExport(i+1, labsOnLineAndScore.getTypeName().get(i),labsOnLineAndScore.getQuantity().get(i), 0, labsOnLineAndScore.getScores().get(i), labsOnLineAndScore.getScores().get(i)*labsOnLineAndScore.getQuantity().get(i)));
                } else {
                    scoresLabExports.add(new ScoresLabExport(i+1, labsOnLineAndScore.getTypeName().get(i),labsOnLineAndScore.getQuantity().get(i), labsOnLineAndScore.getHour(), labsOnLineAndScore.getScores().get(i), labsOnLineAndScore.getScores().get(i)*labsOnLineAndScore.getHour()));
                }
            }
            scoresLabExports_List.add(scoresLabExports);
        }
        String[] headersRanking = {"STT", "Hạng", "Tên PTN", "Tổng điểm"};
        createSheet(workbook, scoresLabRankingsExports, headersRanking,"Bảng xếp hạng");
        String[] headersScore = {"STT", "Phân loại", "Số lượng", "Tổng giờ", "Điểm", "Tổng điểm"};
        for (int i = 0; i < scoresLabExports_List.size(); i++) {
            createSheet(workbook, scoresLabExports_List.get(i), headersScore,i+1 + ". " + scoresLabRankingsExports.get(i).getLabName());
        }
        return exportData(workbook, "Danh điểm các phòng thí nghiệm");
    }


                    /*********************************/
                        /** Export Inventory */
                    /********************************/

    @GetMapping("/admin/Export/AllshowinventoryLab")
    public ResponseEntity<Resource> ExportInventoryAllLab(@RequestParam(value = "managingUnitsCheck", defaultValue = "false") boolean managingUnitsCheck,
                                                          @RequestParam(value = "yearSelected", defaultValue = "0") int yearSelected,
                                                          @RequestParam(value = "managingUnitId", defaultValue = "0") int managingUnitId,
                                                          @RequestParam(value = "labId", defaultValue = "0") int labId,
                                                          @RequestParam("username") String username,
                                                          @RequestParam(value = "success", defaultValue = "false") boolean success){
        List<InventoryResult> inventoryResults = ShowinventoryAllshowinventoryLab(managingUnitsCheck, yearSelected, managingUnitId, username, success, new ArrayList<InventoryResult>());
        if (labId!=0) inventoryResults.removeIf(inventoryResult -> inventoryResult.getLabDTO().getId() != labId);
        int j = 0;
        for (InventoryResult inventoryResult : inventoryResults) {
            int z = 0;
            System.out.println((j+1) + ": " + inventoryResult.getLabDTO().getLabName());
            for (EquipmentLabDTO equipmentLabDTO : inventoryResult.getEquipmentLabDTOs()) {
                System.out.print("_" + (z+1) + ". "+ equipmentLabDTO.getEquipment().getName() + ": ");
                for (int i = 0; i < equipmentLabDTO.getEquipmentSerieList().size(); i++) {
                    System.out.print(equipmentLabDTO.getEquipmentSerieList().get(i)+" /// ");
                }
                if(inventoryResult.getInventoryCompares().size()>0){
                    System.out.print(" LastYear: " + inventoryResult.getInventoryCompares().get(z).getLastYear());
                    System.out.print(" ThisYear: " + inventoryResult.getInventoryCompares().get(z).getThisYear());
                    System.out.print(" Increase: " + inventoryResult.getInventoryCompares().get(z).getIncrease());
                    System.out.print(" Reduce: " + inventoryResult.getInventoryCompares().get(z).getReduce() +"\n");
                } else {
                    System.out.print(" LastYear: " + 0);
                    System.out.print(" ThisYear: " + equipmentLabDTO.getEquipmentSerieList().size());
                    System.out.print(" Increase: " + 0);
                    System.out.print(" Reduce: " + 0 +"\n");

                }
                z++;
            }
            j++;
            System.out.println("===============================");
        }

        int year = LocalDate.now().getYear();
        if(yearSelected!=0) year = yearSelected;
        System.out.println("year = " + year);
        System.out.println("managingUnitsCheck = " + managingUnitsCheck);
        System.out.println("managingUnitId = " + managingUnitId);


        List<InventoryResultExport> inventoryResultExports = new ArrayList<>();
        List<InventoryResultExport> inventoryResultExports_NotEquipment = new ArrayList<>();
        for (InventoryResult inventoryResult : inventoryResults) {
            if(inventoryResult.getEquipmentLabDTOs().size()==0)
                inventoryResultExports_NotEquipment.add(
                    new InventoryResultExport(
                            "","",
                            new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),
                            0,0,0,0,
                            inventoryResult.getLabDTO().getLabName()
                    )
                );
            for (int i = 0; i < inventoryResult.getEquipmentLabDTOs().size(); i++) {
                EquipmentLabDTO equipmentLabDTOS = inventoryResult.getEquipmentLabDTOs().get(i);
                InventoryResultExport inventoryResultExport = new InventoryResultExport(
                        equipmentLabDTOS.getEquipment().getName(),
                        equipmentLabDTOS.getEquipment().getUnit(),
                        equipmentLabDTOS.getEquipmentSerieList(),
                        equipmentLabDTOS.getLevels(),
                        equipmentLabDTOS.getUsingdates(),
                        inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getLastYear() : 0,
                        inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getThisYear() : equipmentLabDTOS.getEquipmentSerieList().size(),
                        inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getIncrease() : 0,
                        inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getReduce() : 0,
                        inventoryResult.getLabDTO().getLabName()
                );
                inventoryResultExports.add(inventoryResultExport);
            }
        }

        Set<String> labNameUnique_Set = new HashSet<>();
        for (InventoryResultExport inventoryResultExport : inventoryResultExports) {
            labNameUnique_Set.add(inventoryResultExport.getLabName());
        }
        List<String> labNameUnique = new ArrayList<>(labNameUnique_Set);

        /** Xuất Exel */
        int year_MinusYear = year -1;
        String[] headers = {"Tên trang bị", "Đơn vị tính", "Seri/mã", "Phân cấp", "Năm sử dụng", "Thực lực 0 giờ ngày 01/01/" + year_MinusYear, "Thực lực 0 giờ ngày 01/01/" + year, "Tăng", "Giảm", "Tên PTN"};
        Workbook workbook = new XSSFWorkbook();
        if(managingUnitId==0 && labId==0)
            createSheet(workbook, inventoryResultExports_NotEquipment, headers,0+". Phòng trống hoặc không có T.tin K.kê");

        List<InventoryResultExport> inventoryResultExport_SameLabName = new ArrayList<>();
        for (int i = 0; i < labNameUnique.size(); i++) {
            for (InventoryResultExport inventoryResultExport : inventoryResultExports) {
                if(inventoryResultExport.getLabName().equals(labNameUnique.get(i)))
                    inventoryResultExport_SameLabName.add(inventoryResultExport);
            }
            createSheet(workbook, inventoryResultExport_SameLabName, headers,(i+1)+". "+ labNameUnique.get(i), true);
            inventoryResultExport_SameLabName.clear();
        }

        return exportData(workbook, "Danh sách kiểm kê");
    }

    @GetMapping({"/admin/Export/AllshowinventoryEquipment"})
    public ResponseEntity<Resource> ExportInventoryAllEquipment(@RequestParam(value = "managingUnitsCheck", defaultValue = "false") boolean managingUnitsCheck,
                                                                @RequestParam(value = "yearSelected", defaultValue = "0") int yearSelected,
                                                                @RequestParam(value = "managingUnitId", defaultValue = "0") int managingUnitId,
                                                                @RequestParam("username") String username,
                                                                @RequestParam(value = "success", defaultValue = "false") boolean success,
                                                                @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        InventoryResult inventoryResult = ShowinventoryEquipment(managingUnitsCheck, yearSelected, managingUnitId, username, success, unsuccess);
        int year = LocalDate.now().getYear();
        if(yearSelected!=0) year = yearSelected;
        int year_MinusYear = year -1;

        String managingUnitName = "";
        if(managingUnitId != 0) managingUnitName = managingUnitService.getManagingUnitById(managingUnitId).getDepartmentName();

        List<InventoryResultExport> inventoryResultExports = new ArrayList<>();
        for (int i = 0; i < inventoryResult.getEquipmentLabDTOs().size(); i++) {
            EquipmentLabDTO equipmentLabDTOS = inventoryResult.getEquipmentLabDTOs().get(i);
            InventoryResultExport inventoryResultExport = new InventoryResultExport(
                    equipmentLabDTOS.getEquipment().getName(),
                    equipmentLabDTOS.getEquipment().getUnit(),
                    equipmentLabDTOS.getEquipmentSerieList(),
                    equipmentLabDTOS.getLevels(),
                    equipmentLabDTOS.getUsingdates(),
                    inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getLastYear() : 0,
                    inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getThisYear() : equipmentLabDTOS.getEquipmentSerieList().size(),
                    inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getIncrease() : 0,
                    inventoryResult.getInventoryCompares().size() > 0 ? inventoryResult.getInventoryCompares().get(i).getReduce() : 0,
                    (managingUnitId != 0) && !managingUnitsCheck ? managingUnitName : ""
            );
            inventoryResultExports.add(inventoryResultExport);
        }

        Workbook workbook = new XSSFWorkbook();
        String[] headers = {"Tên trang bị", "Đơn vị tính", "Seri/mã", "Phân cấp", "Năm sử dụng", "Thực lực 0 giờ ngày 01/01/" + year_MinusYear, "Thực lực 0 giờ ngày 01/01/" + year, "Tăng", "Giảm", "Tên Bộ môn"};
        String sheetName = "Kiểm kê trang bị năm " + year;
        if ((managingUnitId != 0 && !managingUnitsCheck)){
            sheetName = managingUnitName + " năm " + year;
        } else {
            headers = Arrays.copyOfRange(headers, 0, headers.length - 1);
        }
        createSheet(workbook, inventoryResultExports, headers, sheetName, true);
        return exportData(workbook, "Danh sách kiểm kê trang bị " + managingUnitName + " " + year);
    }
                        /*********************************/
                            /**    Export Manager   */
                        /********************************/

    @GetMapping({"/admin/Export/Managers"})
    public ResponseEntity<Resource> ExportManagers(){
        List<People> managers = peopleService.getAllPeople()
                                .stream().filter(p -> {
                                    return (CheckRole(p, RoleSystem.ROLE_ADMIN) || CheckRole(p, RoleSystem.ROLE_MANAGER)) && p.getIsDelete()==0;
                                }).collect(Collectors.toList());

        List<PeoplesExport> managersExports = CreatePeopleExport(managers);
        Workbook workbook = new XSSFWorkbook();
        String[] headers = {"ID", "Họ và tên", "Cấp bậc", "Đơn vị", "Số hiệu QN", "Liên lạc", "Tên đăng nhập", "Mật khẩu (đã mã hóa)", "Quyền truy cập"};
        String sheetName = "Danh sách tài khoản quản lý";
        createSheet(workbook, managersExports, headers, sheetName);
        return exportData(workbook, sheetName);
    }

    @GetMapping({"/admin/Export/Managers/{id}"})
    public ResponseEntity<Resource> ExportManagers(@PathVariable("id") int managerId){
        List<People> managers = new ArrayList<>();
        People people = peopleService.findByPeopleId(managerId);
        managers.add(people);
        List<Lab> labs = labService.findAllByLabManagemetId(managerId);
        labs.removeIf(lab -> lab.getIsDelete()!=0);
        List<LabDtoExport> labDtoExports = new ArrayList<>();
        for (Lab lab : labs) {
            labDtoExports.add(new LabDtoExport(lab, true));
        }
        List<PeoplesExport> managersExports = CreatePeopleExport(managers);
        Workbook workbook = new XSSFWorkbook();
        String[] headers = {"ID", "Họ và tên", "Cấp bậc", "Đơn vị", "Số hiệu QN", "Liên lạc", "Tên đăng nhập", "Mật khẩu (đã mã hóa)", "Quyền truy cập"};
        String[] headersLabs = {"ID", "Tên PTN", "Người quản lý", "Đơn vị chủ quản", "Sức chứa (người)", "Vị trí"};
        String sheetManager = "TT TK " + people.getRank() + " " + people.getName();
        String sheetLabs = "DS PTN đang quản lý";
        createSheet(workbook, managersExports, headers, sheetManager);
        createSheet(workbook, labDtoExports, headersLabs, sheetLabs);
        return exportData(workbook, sheetManager);
    }


                        /*********************************/
                            /**    Export Teacher   */
                        /********************************/

    private List<PeoplesExport> CreatePeopleExport(List<People> teachers) {
        List<PeoplesExport> teachersExports = new ArrayList<>();
        for (People teacher : teachers) {
            Users user = userService.findByPeopleId(teacher.getId());
            teachersExports.add(new PeoplesExport(
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getRank(),
                    teacher.getUnit(),
                    teacher.getMilitaryNumber(),
                    teacher.getContact(),
                    user.getUsername(),
                    user.getPassword(),
                    GetAuthorityByUsername(user.getUsername())
            ));
        }
        return teachersExports;
    }

    @GetMapping({"/admin/Export/Teachers"})
    public ResponseEntity<Resource> ExportTeachers(){
        List<People> teachers = peopleService.getAllPeople()
                                .stream().filter(p -> {
                                    return !(CheckRole(p, RoleSystem.ROLE_ADMIN) || CheckRole(p, RoleSystem.ROLE_MANAGER)) && p.getIsDelete()==0;
                                }).collect(Collectors.toList());

        List<PeoplesExport> teachersExports = CreatePeopleExport(teachers);
        Workbook workbook = new XSSFWorkbook();
        String sheetName = "Danh sách tài khoản giáo viên và người phụ trách";
        String[] headers = {"ID", "Họ và tên", "Cấp bậc", "Đơn vị", "Số hiệu QN", "Liên lạc", "Tên đăng nhập", "Mật khẩu (đã mã hóa)", "Quyền truy cập"};
        createSheet(workbook, teachersExports, headers, sheetName);
        return exportData(workbook, sheetName);
    }

    @GetMapping({"/admin/Export/Teachers/{id}"})
    public ResponseEntity<Resource> ExportTeacher(@PathVariable("id") int teacherId){
        List<People> teachers = new ArrayList<>();
        People people = peopleService.findByPeopleId(teacherId);
        teachers.add(people);
        List<PeoplesExport> teachersExports = CreatePeopleExport(teachers);

        List<Content> contents = contentService.findAllByReservationistId(teacherId);
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        List<BookingDtoExport> bookingDtoExports = new ArrayList<>();
        contents.forEach(content -> {
            Booking booking = bookingService.findByContent_id(content.getId());
            Lab lab = labService.findByLabId(booking.getLabid());
            ExperimentReport experimentReport = experimentReportService.getExperimentReportById(content.getExperimentReport());
            bookingDTOs.add(new BookingDTO(booking,content,lab,experimentReport));
        });
        Collections.sort(bookingDTOs, new BookingDateComparator());
        bookingDTOs.forEach(bookingDTO -> {
            Booking booking = bookingService.findByBookingId(bookingDTO.getId());
            People reservationist = peopleService.findByPeopleId(bookingDTO.getContent().getReservationistId());
            ExperimentType experimentType = experimentTypeService.getExperimentTypeById(bookingDTO.getContent().getExperimentType());
            bookingDtoExports.add(new BookingDtoExport(booking,bookingDTO.getLab().getLabName(), bookingDTO.getContent(), experimentType.getTypeName(), bookingDTO.getExperimentReport().getReportType(),reservationist.getRank() + " " + reservationist.getName()));
        });

        String[] headers = {"ID", "Họ và tên", "Cấp bậc", "Đơn vị", "Số hiệu QN", "Liên lạc", "Tên đăng nhập", "Mật khẩu (đã mã hóa)", "Quyền truy cập"};
        Workbook workbook = new XSSFWorkbook();
        String sheetName = "TT TK GV " + people.getName();
        createSheet(workbook, teachersExports, headers, sheetName);

        String[] headersBooking = {"ID", "Tên PTN", "Tên bài", "Tên lớp", "Người đặt", "Số giờ", "Số người", "Ngày đặt", "Ghi chú", "Loại hình TN", "Loại BC", "Trạng thái quản lý", "Trạng thái người dùng", "Thao tác"};
        List<BookingDtoExport> bookingDtoExports_Pendding = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.PENDDING)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Pendding, headersBooking, ConfirmStatus.PENDDING);
        List<BookingDtoExport> bookingDtoExports_Approve = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.APPROVE)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Approve, headersBooking, ConfirmStatus.APPROVE);
        List<BookingDtoExport> bookingDtoExports_UnUsed = bookingDtoExports.stream()
                                                            .filter(b -> {
                                                                return b.getComfirmUsed().equals(ConfirmUsed.UNUSED);
                                                            }).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_UnUsed, headersBooking, "Chờ GV xác nhận sử dụng");
        List<BookingDtoExport> bookingDtoExports_Cancel = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.CANCEL)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Cancel, headersBooking, ConfirmStatus.CANCEL);

        return exportData(workbook, sheetName);
    }

    @GetMapping({"/Export/Teachers/myBooking"})
    public ResponseEntity<Resource> ExportTeacherMyBooking(@RequestParam("username")String username,
                                                           @RequestParam(value = "search", defaultValue = "false") boolean search,
                                                           @RequestParam(value = "AndOr", defaultValue = "false") boolean AndOr,
                                                           @RequestParam(value = "inputdatasearch", defaultValue = "NoSearch") String inputdatasearch,
                                                           @RequestParam(value = "datetimepicker", defaultValue = "1970-01-01") Date datetimepicker,
                                                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                                                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess){
        List<BookingDTO> bookingDTOs = GetMyBooking(username, search, AndOr, inputdatasearch, datetimepicker, success, unsuccess);
        System.out.println("bookingDTOs size = " + bookingDTOs.size());
        System.out.println("username = " + username);
        System.out.println("search = " + search);
        System.out.println("AndOr = " + AndOr);
        System.out.println("inputdatasearch = " + inputdatasearch);
        System.out.println("datetimepicker = " + datetimepicker);

        List<BookingDtoExport> bookingDtoExports = new ArrayList<>();
        bookingDTOs.forEach(bookingDTO -> {
            Booking booking = bookingService.findByBookingId(bookingDTO.getId());
            People reservationist = peopleService.findByPeopleId(bookingDTO.getContent().getReservationistId());
            ExperimentType experimentType = experimentTypeService.getExperimentTypeById(bookingDTO.getContent().getExperimentType());
            BookingDtoExport bookingDtoExport = new BookingDtoExport(booking,bookingDTO.getLab().getLabName(),
                                                                    bookingDTO.getContent(),
                                                                    experimentType.getTypeName(),
                                                                    bookingDTO.getExperimentReport().getReportType(),
                                                                    reservationist.getRank() + " " + reservationist.getName());
            bookingDtoExports.add(bookingDtoExport);
        });
        Workbook workbook = new XSSFWorkbook();
        String[] headersBooking = {"ID", "Tên PTN", "Tên bài", "Tên lớp", "Người đặt", "Số giờ", "Số người", "Ngày đặt", "Ghi chú", "Loại hình TN", "Loại BC", "Trạng thái quản lý", "Trạng thái người dùng", "Thao tác"};
        List<BookingDtoExport> bookingDtoExports_Pendding = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.PENDDING)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Pendding, headersBooking, ConfirmStatus.PENDDING);
        List<BookingDtoExport> bookingDtoExports_Approve = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.APPROVE)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Approve, headersBooking, ConfirmStatus.APPROVE);
        List<BookingDtoExport> bookingDtoExports_UnUsed = bookingDtoExports.stream()
                                                            .filter(b -> {
                                                                return b.getComfirmUsed().equals(ConfirmUsed.UNUSED);
                                                            }).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_UnUsed, headersBooking, "Chờ GV xác nhận sử dụng");
        List<BookingDtoExport> bookingDtoExports_Cancel = bookingDtoExports.stream()
                                                            .filter(b -> b.getConfirm_Status().equals(ConfirmStatus.CANCEL)).collect(Collectors.toList());
        createSheet(workbook, bookingDtoExports_Cancel, headersBooking, ConfirmStatus.CANCEL);

        return exportData(workbook, "Lịch sử đặt PTN");
    }
}
