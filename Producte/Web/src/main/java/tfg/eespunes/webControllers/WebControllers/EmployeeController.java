package tfg.eespunes.webControllers.WebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tfg.eespunes.domain.Employee;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.persistance.DatabaseController;

import javax.validation.Valid;

@Controller
public class EmployeeController {
    private final DatabaseController databaseController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    //CREAR
    @GetMapping("/employee/create")
    public String createEmployee(Employee employee,Model model) {
        model.addAttribute("roles", databaseController.getAllRoles());
        return "employee/createEmployee";
    }

    @PostMapping("/employee/create")
    public String createEmployeePOST(@Valid Employee employee, BindingResult bindingResult,Model model,  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return createEmployee(employee,model);
        }

        String[] role = employee.getTempRoleName().split(" - ");
        employee.setRole(databaseController.getRole(role[2], Integer.parseInt(role[1]), role[0]));

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        databaseController.addEmployee(employee);

        redirectAttributes.addAttribute("username", employee.getUsername());
        return "redirect:/employee/{username}";
    }

    //GET ALL
    @GetMapping("/employee/all")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", databaseController.getAllEmployees());
        return "employee/showEmployees";
    }

    //GET ONE

    @GetMapping("/employee/{username}")
    public String getEmployee(@PathVariable String username, Model model) {
        model.addAttribute("employee", databaseController.getEmployee(username));
        return "employee/showEmployee";
    }

    //EDIT
    @GetMapping("/employee/edit/{username}")
    public String editEmployee(@PathVariable String username, Model model) {
        model.addAttribute("employee", databaseController.getEmployee(username));
        return "employee/editEmployee";
    }

    @PostMapping("/employee/edit/{username}")
    public String editEmployeePOST(Employee employee,RedirectAttributes redirectAttributes) {
        databaseController.editEmployee(employee);

        redirectAttributes.addAttribute("username", employee.getUsername());
        return "redirect:/employee/{username}";
    }

    //DELETE
    @GetMapping("/employee/delete/{username}")
    public String deleteEmployee(@PathVariable String username, Model model) {
        databaseController.deleteEmployee(username);

        return getAllEmployees(model);
    }
}