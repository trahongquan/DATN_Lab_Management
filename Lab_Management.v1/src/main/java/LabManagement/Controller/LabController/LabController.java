package LabManagement.Controller.LabController;

import LabManagement.dao.BookingRepository;
import LabManagement.dto.RoomDTO;
import LabManagement.entity.Booking;
import LabManagement.entity.People;
import LabManagement.entity.Room;
import LabManagement.service.BookingService.BookingService;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.PeopleService.PeopleService;
import LabManagement.service.RoleService.RoleService;
import LabManagement.service.RoomService.RoomService;
import LabManagement.service.authority.AuthorityService;
import LabManagement.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Controller
@SpringBootApplication
@RequestMapping("/Lab")
public class LabController {

    private UserService userService;
    private AuthorityService authorityService;
    private BookingService bookingService;
    private BookingRepository bookingRepository;
    private EquipmentService equipmentService;
    private PeopleService peopleService;
    private RoleService roleService;
    private RoomService roomService;

    @Autowired
    public LabController(UserService userService, AuthorityService authorityService, BookingService bookingService, EquipmentService equipmentService, PeopleService peopleService, RoleService roleService, RoomService roomService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.bookingService = bookingService;
        this.equipmentService = equipmentService;
        this.peopleService = peopleService;
        this.roleService = roleService;
        this.roomService = roomService;
    }

    /******************************************************************************************************/
                                        /** Khu vực Chung*/
    /******************************************************************************************************/

    private RoomDTO Room2RoomDTO(Room room){
        People people = peopleService.findByPeopleId(room.getLeaderId());
        return new RoomDTO(room,people);
    }
    private RoomDTO Room2RoomDTOandDateBookings(Room room){
        People people = peopleService.findByPeopleId(room.getLeaderId());
        List<Booking> bookings = bookingService.findAllByRoomId(room.getId());
        List<Date> dateBookings = new LinkedList<>();
        if(!bookings.isEmpty()){bookings.forEach(booking -> dateBookings.add(booking.getBookingDate()));}
        return new RoomDTO(room,dateBookings,people);
    }
    private List<RoomDTO> Rooms2RoomDTOsAndDateBookings(List<Room> rooms){
        List<RoomDTO> roomDTOs = new LinkedList<>();
        rooms.forEach(room -> roomDTOs.add(Room2RoomDTOandDateBookings(room)));
        return roomDTOs;
    }

    @GetMapping({"/",""})
    public String getPhones(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomDTO> roomDTOS = Rooms2RoomDTOsAndDateBookings(rooms);
        model.addAttribute("roomDTOS", roomDTOS);
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
        List<Room> rooms = roomService.getAllRooms();
        List<RoomDTO> roomDTOS = Rooms2RoomDTOsAndDateBookings(rooms);
        model.addAttribute("roomDTOS", roomDTOS);
        System.out.println(roomDTOS);
        return "/admin/templateAdmin";
    }
    @GetMapping("/admin/Room")
    public String redirectToAdminHandshopListAdmin(Model model,@RequestParam(value = "addphone", defaultValue = "false") boolean addphone) {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomDTO> roomDTOS = Rooms2RoomDTOsAndDateBookings(rooms);
        model.addAttribute("roomDTOS", roomDTOS); /** cách xử lý ở backEnd*/
        model.addAttribute("addphone", addphone); /** cách xử lý ở backEnd*/
//        return "admin/list-phones";
        return "admin/templateAdmin";
    }

//    @GetMapping({"/admin/Approve"})
//    @GetMapping({"/admin/Pendding"})
//    @GetMapping({"/admin/CancelBoooking"})
//    @GetMapping({"/admin/Room"})
//    @GetMapping({"/admin/Equipment"})
//    @GetMapping({"/admin/Role"})
//    @GetMapping({"/admin/AddManager"})
//    @GetMapping({"/admin/Manager"})
//    @GetMapping({"/admin/AddTeacher"})
//    @GetMapping({"/admin/Teacher"})
}
