package tfg.eespunes.domain;

public class Employee {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Role role;

    public Employee(int id, String username, String password, String name, String surname, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
