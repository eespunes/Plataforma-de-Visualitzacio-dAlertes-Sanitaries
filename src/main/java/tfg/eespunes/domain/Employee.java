package tfg.eespunes.domain;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Employee {

    @NotEmpty(message = "No es pot deixar el nom d'usuari buit")
    @Size(min = 4, max = 64,message = "El nom d'usuari ha de tenir entre 4 i 64 caràcters")
    private String username;
    @NotEmpty(message = "No es pot deixar la contrasenya buida")
    @Size(min = 3, max = 32,message = "La contrasenya ha de tenir entre 3 i 32 caràcters")
    private String password;
    @NotEmpty(message = "No es pot deixar el nom buit")
    @Size(min = 1, max = 32,message = "El nom ha de tenir entre 1 i 32 caràcters")
    private String name;
    @NotEmpty(message = "No es pot deixar el cognom buit")
    @Size(min = 1, max = 64,message = "El cognom ha de tenir entre 1 i 64 caràcters")
    private String surname;
    private Role role;
    private String tempRoleName;
    private String nameError = "";

    public Employee(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
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
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTempRoleName() {
        return tempRoleName;
    }

    public void setTempRoleName(String tempRoleName) {
        this.tempRoleName = tempRoleName;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }
}
