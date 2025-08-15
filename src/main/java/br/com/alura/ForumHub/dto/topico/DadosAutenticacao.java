package br.com.alura.ForumHub.dto.topico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@jakarta.validation.Valid
public record DadosAutenticacao(
        @NotBlank @Email
        String login,

        @NotBlank
        String senha
) {
}
