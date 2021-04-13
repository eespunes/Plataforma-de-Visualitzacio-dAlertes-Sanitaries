package tfg.eespunes.webControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tfg.eespunes.domain.HealthcareInstitution;

@Controller
public class WebGetController {


    public WebGetController() {
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/institution/create")
    public String createInstitution(Model model) {
        model.addAttribute("createHealthcareInstitution", new HealthcareInstitution());
        return "healthcareInstitution/createHealthcareInstitution";
    }
}
