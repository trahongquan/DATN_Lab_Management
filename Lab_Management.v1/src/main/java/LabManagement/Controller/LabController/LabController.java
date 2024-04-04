package LabManagement.Controller.LabController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@RequestMapping("/Lab")
public class LabController {

    @GetMapping({"/",""})
    public String getPhones(Model model) {
        return "index";
    }
}
