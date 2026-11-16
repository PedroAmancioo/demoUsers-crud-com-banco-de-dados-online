package com.example.demoUsers.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

@Table("enderecos")
@BelongsTo(parent = Cliente.class, foreignKeyName = "cliente_id")
public class Endereco extends Model {
    //Getters
    public String getRua(){
        return getString("rua");
    }

    public String getCidade(){
        return getString("cidade");
    }

    public String getEstado(){
        return getString("estado");
    }

    public String getCep(){
        return getString("cep");
    }

    public Integer getClienteId(){
        return getInteger("cliente_id");
    }

    //Setters
    public void setRua(String rua){
        set("rua", rua);
    }

    public void setCidade(String cidade){
        set("cidade", cidade);
    }

    public void setEstado(String estado){
        set("estado", estado);
    }

    public void setCep(String cep){
        set("cep", cep);
    }

    public void setClienteId(Integer clienteId){
        set("cliente_id", clienteId);
    }
}
