package asel.java.spring.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    @NotEmpty(message = "Это поле не можеть быть пустым")
    @Size(max = 32, message = "Имя пользователя должно быть не менее 32 знаков")
    private String firstName;

    @NotEmpty(message = "Это поле не можеть быть пустым")
    @Size(max = 32, message = "Имя пользователя должно быть не менее 32 знаков")
    private String lastName;

    @NotEmpty(message = "Это поле не можеть быть пустым")
    @Email(message = "Почтовый адрес должен быть действительным")
    private String email;

    @Min(value = 1, message = "Возраст должен быть больше 0")
    private int age;

    @NotEmpty(message = "Это поле не можеть быть пустым")
    private String userPassword;

    private List<RoleDTO> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
