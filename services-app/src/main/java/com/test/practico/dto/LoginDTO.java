package com.test.practico.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * DTO (Data Transfer Object) para almacenar las credenciales de inicio de sesión.
 */
@Data
public class LoginDTO {
	@NotEmpty(message = "ingresa email")
	@Email(message = "ingresa email valido")
    String email;
	@NotEmpty(message = "ingresa password")
    String password;
}