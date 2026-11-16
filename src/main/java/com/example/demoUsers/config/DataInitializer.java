package com.example.demoUsers.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 1. Abre a ligação no início da aplicação
        System.out.println("Ligar à base de dados SQLite...");
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:meu_app.db", "", "");
            // 2. Garante que as tabelas existem (Útil porque o ficheiro .db pode estar vazio)
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name VARCHAR(56), " +
                    "last_name VARCHAR(56), " +
                    "email VARCHAR(100))";
            Base.exec(sqlUsers);

            String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100), " +
                    "cpf VARCHAR(14), " +
                    "email VARCHAR(100))";
            Base.exec(sqlClientes);

            String sqlEnderecos = "CREATE TABLE IF NOT EXISTS enderecos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "rua VARCHAR(200), " +
                    "cidade VARCHAR(100), " +
                    "estado VARCHAR(50), " +
                    "cep VARCHAR(10), " +
                    "cliente_id INTEGER, " +
                    "FOREIGN KEY (cliente_id) REFERENCES clientes(id))";
            Base.exec(sqlEnderecos);

            String sqlTelefones = "CREATE TABLE IF NOT EXISTS telefones (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "numero VARCHAR(20), " +
                    "tipo VARCHAR(20), " +
                    "cliente_id INTEGER, " +
                    "FOREIGN KEY (cliente_id) REFERENCES clientes(id))";
            Base.exec(sqlTelefones);

            // A PARTIR DAQUI A LIGAÇÃO ESTÁ ABERTA E AS TABELAS EXISTEM

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            // 4. É fundamental SEMPRE fechar a ligação no final
            // O bloco finally garante que fechará mesmo se der erro no meio do caminho
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}
