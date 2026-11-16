package com.example.demoUsers.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

@Table("telefones")
@BelongsTo(parent = Cliente.class, foreignKeyName = "cliente_id")
public class Telefone extends Model {
    //Getters
    public String getNumero(){
        return getString("numero");
    }

    public String getTipo(){
        return getString("tipo");
    }

    public Integer getClienteId(){
        return getInteger("cliente_id");
    }

    //Setters
    public void setNumero(String numero){
        set("numero", numero);
    }

    public void setTipo(String tipo){
        set("tipo", tipo);
    }

    public void setClienteId(Integer clienteId){
        set("cliente_id", clienteId);
    }
}
