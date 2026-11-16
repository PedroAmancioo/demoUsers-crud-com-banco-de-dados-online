package com.example.demoUsers.controllers;

import com.example.demoUsers.models.User;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    @GetMapping("/index") // lista de usuários
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");

            // Cria uma usuário aleatóiro para cadastar no banco
            User u = new User();
            u.set("first_name", "João"+User.count(), "last_name", "Silva"+User.count(), "email", "joao"+User.count()+"@email.com");
            u.saveIt(); // Guarda na base de dados

            List<User> users = new ArrayList<>(User.findAll()); //cria uma lista com todos os usuários do banco
            model.addAttribute("users", users);
            model.addAttribute("size", User.count());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            Base.close();
        }
        return "/users/index";
    }
}