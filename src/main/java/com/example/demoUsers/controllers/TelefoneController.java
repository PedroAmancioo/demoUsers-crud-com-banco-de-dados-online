package com.example.demoUsers.controllers;

import com.example.demoUsers.models.Cliente;
import com.example.demoUsers.models.Telefone;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/telefones")
public class TelefoneController {

    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            List<Telefone> telefones = new ArrayList<>(Telefone.findAll());
            model.addAttribute("telefones", telefones);
            model.addAttribute("size", Telefone.count());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/telefones/index";
    }

    @GetMapping("/create")
    public String create(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            List<Cliente> clientes = new ArrayList<>(Cliente.findAll());
            model.addAttribute("clientes", clientes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "telefones/create";
    }

    @PostMapping("/store")
    public String store(@RequestParam String numero,
                        @RequestParam String tipo,
                        @RequestParam int cliente_id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Telefone t = new Telefone();
            t.set("numero", numero, "tipo", tipo, "cliente_id", cliente_id);
            t.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/telefones/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Telefone t = Telefone.findById(id);
            model.addAttribute("telefone", t);
            List<Cliente> clientes = new ArrayList<>(Cliente.findAll());
            model.addAttribute("clientes", clientes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/telefones/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
                         @RequestParam String numero,
                         @RequestParam String tipo,
                         @RequestParam int cliente_id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Telefone t = Telefone.findById(id);
            t.set("numero", numero, "tipo", tipo, "cliente_id", cliente_id);
            t.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/telefones/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Telefone t = Telefone.findById(id);
            t.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/telefones/index";
    }
}
