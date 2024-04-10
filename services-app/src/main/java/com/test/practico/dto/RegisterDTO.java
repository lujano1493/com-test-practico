package com.test.practico.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.test.practico.enums.UserRole;

import lombok.Data;

@Data
public class RegisterDTO {
	@NotEmpty(message="ingresa nombre")
    String name;
	@NotEmpty(message="ingresa apellido")
    String lastName;
	@NotEmpty(message="ingresa correo")
	@Email(message = "ingresa un correo valido")
    String email;
	@NotEmpty(message="ingresa contraseña")
    String password;
	@NotNull(message="ingresa perfil")
    UserRole role;
}