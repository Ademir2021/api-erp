package br.com.centroinfo.api.dtos.userDTO;

import br.com.centroinfo.api.entities.users.UserRole;;

public record RegisterDTO(Long id, String login, String password, UserRole role) {}
