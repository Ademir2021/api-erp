package br.com.centroinfo.api.dtos.userDTO;

import java.util.List;

// public record LoginResponseDTO(
//     String token,
//     String role
//     ) {
// }

public class LoginResponseDTO {

    private Long id;
    private String token;
    private String login;
    private List<String> roles;

    public LoginResponseDTO(Long id, String token, String login, List<String> roles) {
        this.id = id;
        this.token = token;
        this.login = login;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;

    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
