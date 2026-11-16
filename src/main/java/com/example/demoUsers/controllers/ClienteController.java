package com.example.demoUsers.controllers;

import com.example.demoUsers.models.Cliente;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            List<Cliente> clientes = new ArrayList<>(Cliente.findAll());
            model.addAttribute("clientes", clientes);
            model.addAttribute("size", Cliente.count());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/clientes/index";
    }

    @GetMapping("/create")
    public String create() {
        return "/clientes/create";
    }

    @PostMapping("/store")
    public String store(@RequestParam String nome,
                        @RequestParam String cpf,
                        @RequestParam String email) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Cliente c = new Cliente();
            c.set("nome", nome, "cpf", cpf, "email", email);
            c.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/clientes/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Cliente c = Cliente.findById(id);
            model.addAttribute("cliente", c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/clientes/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
                         @RequestParam String nome,
                         @RequestParam String cpf,
                         @RequestParam String email) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Cliente c = Cliente.findById(id);
            c.set("nome", nome, "cpf", cpf, "email", email);
            c.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/clientes/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Cliente c = Cliente.findById(id);
            c.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/clientes/index";
    }
}
