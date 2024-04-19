package LabManagement.Controller.LabController;

import LabManagement.ClassSuport.ToList;
import LabManagement.dao.BookingRepository;
import LabManagement.dto.EquipmentDTO;
import LabManagement.dto.EquipmentLabDTO;
import LabManagement.dto.LabDTO;
import LabManagement.entity.*;
import LabManagement.service.BookingService.BookingService;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.PeopleService.PeopleService;
import LabManagement.service.RoleService.RoleService;
import LabManagement.service.LabService.LabService;
import LabManagement.service.authority.AuthorityService;
import LabManagement.service.booking_equi.Booking_equiService;
import LabManagement.service.equipmentLab.EquipmentLabService;
import LabManagement.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SpringBootApplication
@RequestMapping("/Lab")
public class LabController {

    private UserService userService;
    private AuthorityService authorityService;
    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private EquipmentService equipmentService;
    private EquipmentLabService equipmentLabService;
    private Booking_equiService booking_equiService;
    private PeopleService peopleService;
    private RoleService roleService;
    private LabService labService;

    @Autowired
    public LabController(UserService userService, AuthorityService authorityService, BookingService bookingService, BookingRepository bookingRepository, EquipmentService equipmentService, EquipmentLabService equipmentLabService, Booking_equiService booking_equiService, PeopleService peopleService, RoleService roleService, LabService labService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.equipmentService = equipmentService;
        this.equipmentLabService = equipmentLabService;
        this.booking_equiService = booking_equiService;
        this.peopleService = peopleService;
        this.roleService = roleService;
        this.labService = labService;
    }

    /******************************************************************************************************/
                                        /** Khu vực Chung*/
    /******************************************************************************************************/

    private LabDTO Lab2LabDTO(Lab lab){
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        return new LabDTO(lab,people);
    }
    private LabDTO Lab2LabDTOandDateBookings(Lab lab){
        People people = peopleService.findByPeopleId(lab.getLab_managemet_id());
        List<Booking> bookings = bookingService.findAllByLab_id(lab.getId());
        List<Date> dateBookings = new LinkedList<>();
        if(!bookings.isEmpty()){bookings.forEach(booking -> dateBookings.add(booking.getBooking_Date()));}
        return new LabDTO(lab,dateBookings,people);
    }
    private List<LabDTO> Labs2LabDTOsAndDateBookings(List<Lab> labs){
        List<LabDTO> labDTOS = new LinkedList<>();
        labs.forEach(lab -> labDTOS.add(Lab2LabDTOandDateBookings(lab)));
        return labDTOS;
    }

    @GetMapping({"/",""})
    public String getPhones(Model model) {
        List<Lab> labs = labService.getAllLabs();
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateBookings(labs);
        model.addAttribute("labDTOS", labDTOS);
        return "index";
    }
//    @GetMapping({"/",""})
//    public String getPhones(Model model) {
//        return "index";
//    }





/******************************************************************************************************/
                                        /** Khu vực Admin*/
/******************************************************************************************************/

    @GetMapping({"/admin/Dashboard"})
    public String getAdminHomePage(Model model) {
        List<Lab> rooms = labService.getAllLabs();
        List<LabDTO> roomDTOS = Labs2LabDTOsAndDateBookings(rooms);
        model.addAttribute("roomDTOS", roomDTOS);
//        System.out.println(roomDTOS);
        return "/admin/templateAdmin";
    }
    @GetMapping("/admin/Room")
    public String RoomList(Model model/*,@RequestParam(value = "addphone", defaultValue = "false") boolean addphone*/) {
        List<Lab> labs = labService.getAllLabs();
        List<LabDTO> labDTOS = Labs2LabDTOsAndDateBookings(labs);
        model.addAttribute("labDTOS", labDTOS);
//        model.addAttribute("addphone", addphone); /** cách xử lý ở backEnd*/
        return "admin/templateAdmin";
    }
    private List<String> GetUsedSeries(){
        List<EquipmentLab> equipmentLabs = equipmentLabService.getAllEquipmentLabs();
        List<String> usedSeries = equipmentLabs.stream()
                .flatMap(equipmentLab -> Arrays.stream(equipmentLab.getEquipmentSeries()
                        .replace("[", "").replace("]", "")
                        .split(",\\s*")))
                .collect(Collectors.toList());
        return usedSeries;
    }

    private List<EquipmentDTO> GetSeriesNoUsed(List<String> usedSeries){
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
    public String AddRoom(Model model/*,@RequestParam(value = "addphone", defaultValue = "false") boolean addphone*/) {
        List<People> peoples = peopleService.getAllPeople();
        List<String> usedSeries = GetUsedSeries();
        List<EquipmentDTO> equipmentDTOS =  GetSeriesNoUsed(usedSeries);
        model.addAttribute("peoples", peoples);
        model.addAttribute("equipmentDTOS", equipmentDTOS);
        //        model.addAttribute("addphone", addphone); /** cách xử lý ở backEnd*/
        return "admin/templateAdmin";
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
                             @RequestParam("series") List<String> selectedSeries) {
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
        return "redirect:/Lab/admin/Room/add";
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


    @GetMapping("/admin/room/showFormForUpdate/{id}")
    public String RoomList(Model model, @PathVariable(value = "id") int id) {
        Lab lab = labService.findByLabId(id);
        LabDTO labDTO = Lab2LabDTO(lab);
        List<People> Managers = peopleService.getAllPeople();
        List<EquipmentLabDTO> equipmentLabDTOs = EquiLabs2EquiLabDTOs(equipmentLabService.findAllByLabId(id));

        model.addAttribute("Managers", Managers);
        model.addAttribute("labDTO", labDTO);
        model.addAttribute("equipmentLabDTOs", equipmentLabDTOs);
//        model.addAttribute("addphone", addphone); /** cách xử lý ở backEnd*/
        return "admin/templateAdmin";
    }

    @GetMapping({"/admin/Equipment"})
    public String EquipmentList(Model model/*,@RequestParam(value = "addphone", defaultValue = "false") boolean addphone*/) {
        List<Equipment> allEquipment = equipmentService.getAllEquipment();
        model.addAttribute("allEquipment", allEquipment); /** cách xử lý ở backEnd*/
        return "admin/templateAdmin";
    }
//    @GetMapping({"/admin/Approve"})
//    @GetMapping({"/admin/Pendding"})
//    @GetMapping({"/admin/CancelBoooking"})
//    @GetMapping({"/admin/Lab"})

//    @GetMapping({"/admin/ExportLabs"})
//    @GetMapping({"/admin/ExportEquipments"})

//    @GetMapping({"/admin/Role"})
//    @GetMapping({"/admin/AddManager"})
//    @GetMapping({"/admin/Manager"})
//    @GetMapping({"/admin/AddTeacher"})
//    @GetMapping({"/admin/Teacher"})
}
