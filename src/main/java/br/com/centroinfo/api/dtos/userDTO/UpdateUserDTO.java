package br.com.centroinfo.api.dtos.userDTO;

import br.com.centroinfo.api.entities.users.UserRole;

public class UpdateUserDTO {
private Long id;
private String login;
private String password;
private UserRole role;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


}
