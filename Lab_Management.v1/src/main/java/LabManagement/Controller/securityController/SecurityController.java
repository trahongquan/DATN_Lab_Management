package LabManagement.Controller.securityController;

import LabManagement.dao.BookingRepository;
import LabManagement.entity.Authority;
import LabManagement.entity.People;
import LabManagement.entity.Users;
import LabManagement.service.BookingService.BookingService;
import LabManagement.service.EquipmentService.EquipmentService;
import LabManagement.service.LabService.LabService;
import LabManagement.service.PeopleService.PeopleService;
import LabManagement.service.RoleService.RoleService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class SecurityController {

    String template = "admin/templateAdmin";
    public String Redirect(String url, Object success) {
        if (success instanceof Boolean) {
            if((boolean) success){
                if(url.contains("?")){
                    String successParam = "&success=" + success;
                    return "redirect:/" + url + successParam;
                } else {
                    String successParam = "?success=" + success;
                    return "redirect:/" + url + successParam;
                }
            } else{
                if(url.contains("?")){
                    String successParam = "&unsuccess=" + true;
                    return "redirect:/" + url + successParam;
                } else {
                    String successParam = "?unsuccess=" + true;
                    return "redirect:/" + url + successParam;
                }
            }
        } else {
            return "redirect:/" + url;
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
    public SecurityController(UserService userService, AuthorityService authorityService, BookingService bookingService, BookingRepository bookingRepository, ContentService contentService, EquipmentService equipmentService, EquipmentLabService equipmentLabService, Booking_equiService booking_equiService, PeopleService peopleService, RoleService roleService, LabService labService, PasswordEncoder passwordEncoder, ExperimentGroupService experimentGroupService, ExperimentTypeService experimentTypeService, ExperimentReportService experimentReportService, ScoreService scoreService) {
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

    @GetMapping("/login")
    public String Login(Model model) {
        List<String> userDB = new ArrayList<>();
        userService.getAllUsers().forEach(user -> {
            userDB.add(user.getUsername());
        });
        model.addAttribute("userDB", userDB);
        return "security/login";
    }
    @GetMapping("/access-denied")
    public String Accessdenied() {
        return "security/access-denied";
    }
    @PostMapping("/register")
    public String Register(@RequestParam("name") String name,
                           @RequestParam("rank") String rank,
                           @RequestParam("unit") String unit,
                           @RequestParam("militaryNumber") long militaryNumber,
                           @RequestParam("contact") String contact,
                           @RequestParam("usernameReg") String usernameReg,
                           @RequestParam("password") String password,
                           @RequestParam("confirmPassword") String confirmPassword){
        People people = new People(name, rank, unit, militaryNumber, contact, 1);
        peopleService.createPeople(people);
        Authority authority = new Authority(usernameReg, "ROLE_TEACHERWAIT");
        authorityService.createAuthority(authority);
        Users user = new Users(usernameReg, passwordEncoder.encode(password), people.getId(), 0);
        userService.createUser(user);
        return Redirect("login",true);
    }

}

