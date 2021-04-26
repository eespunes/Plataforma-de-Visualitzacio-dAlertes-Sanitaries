package tfg.eespunes.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Employee {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String roleName;
    private int roleInstitutionID;
    private String roleCountryID;

    public Employee(String username, String password, String name, String surname, String roleName, int roleInstitutionID, String roleCountryID) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleName = roleName;
        this.roleInstitutionID = roleInstitutionID;
        this.roleCountryID = roleCountryID;
    }

    public Employee() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println(password);
        this.password =  password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleInstitutionID() {
        return roleInstitutionID;
    }

    public void setRoleInstitutionID(int roleInstitutionID) {
        this.roleInstitutionID = roleInstitutionID;
    }

    public String getRoleCountryID() {
        return roleCountryID;
    }

    public void setRoleCountryID(String roleCountryID) {
        this.roleCountryID = roleCountryID;
    }

    public void setCountryIDAndHealthcareInstitutionIDFromRoleName() {
        String[] splitted = roleName.split("-");

        if (splitted.length == 3) {
            this.roleName = splitted[2];
            this.roleInstitutionID = Integer.parseInt(splitted[1]);
            this.roleCountryID = splitted[0];
        }
    }
}
