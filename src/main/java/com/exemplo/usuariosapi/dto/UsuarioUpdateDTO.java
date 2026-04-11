package com.exemplo.usuariosapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "Email invalido")
    private String email;
    @NotBlank(message = "Senha não pode ser vazio")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    //Getters e Setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
}
