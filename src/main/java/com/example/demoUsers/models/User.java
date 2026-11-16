package com.example.demoUsers.models;

import org.javalite.activejdbc.Model;

public class User extends Model {
    //Getters
    public String GetEmail(){
        return getString("email");
    }

    public String GetFirstName(){
        return getString("first_name");
    }

    public String GetLastName(){
        return getString("last_name");
    }

    //Setters
    public void setEmail(String email){
        set("email", email);
    }

    public void setFirstName(String firstName){
        set("first_name", firstName);
    }

    public void setLastName(String lastName){
        set("last_name", lastName);
    }
}
