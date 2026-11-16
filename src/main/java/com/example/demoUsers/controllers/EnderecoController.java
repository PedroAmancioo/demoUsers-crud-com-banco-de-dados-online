package com.example.demoUsers.controllers;

import com.example.demoUsers.models.Cliente;
import com.example.demoUsers.models.Endereco;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    @GetMapping("/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            List<Endereco> enderecos = new ArrayList<>(Endereco.findAll());
            model.addAttribute("enderecos", enderecos);
            model.addAttribute("size", Endereco.count());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/enderecos/index";
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
        return "/enderecos/create";
    }

    @PostMapping("/store")
    public String store(@RequestParam String rua,
                        @RequestParam String cidade,
                        @RequestParam String estado,
                        @RequestParam String cep,
                        @RequestParam int cliente_id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Endereco e = new Endereco();
            e.set("rua", rua, "cidade", cidade, "estado", estado, "cep", cep, "cliente_id", cliente_id);
            e.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/enderecos/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Endereco e = Endereco.findById(id);
            model.addAttribute("endereco", e);
            List<Cliente> clientes = new ArrayList<>(Cliente.findAll());
            model.addAttribute("clientes", clientes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "/enderecos/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,
                         @RequestParam String rua,
                         @RequestParam String cidade,
                         @RequestParam String estado,
                         @RequestParam String cep,
                         @RequestParam int cliente_id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Endereco e = Endereco.findById(id);
            e.set("rua", rua, "cidade", cidade, "estado", estado, "cep", cep, "cliente_id", cliente_id);
            e.saveIt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/enderecos/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            Endereco e = Endereco.findById(id);
            e.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }
        return "redirect:/enderecos/index";
    }
}
