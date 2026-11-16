package com.example.demoUsers.models;

import org.javalite.activejdbc.Model;

public class Cliente extends Model {
    //Getters
    public String getNome(){
        return getString("nome");
    }

    public String getCpf(){
        return getString("cpf");
    }

    public String getEmail(){
        return getString("email");
    }

    //Setters
    public void setNome(String nome){
        set("nome", nome);
    }

    public void setCpf(String cpf){
        set("cpf", cpf);
    }

    public void setEmail(String email){
        set("email", email);
    }
}
