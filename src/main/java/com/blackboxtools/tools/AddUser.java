package com.blackboxtools.tools;

import java.io.IOException;
import java.util.Scanner;

public class AddUser {

    public AddUser() {
        try (Scanner enterData = new Scanner(System.in)) {

            System.out.print("Name of new user administrator: ");
            String user = enterData.nextLine().trim();  // Eliminamos espacios extra

            System.out.print("Password for new user administrator: ");
            String pass = enterData.nextLine().trim();

            // Validación para evitar errores con datos vacíos
            if (user.isEmpty() || pass.isEmpty()) {
                System.out.println("Error: The username and password cannot be empty.");
                return; // Salimos del constructor sin ejecutar comandos
            }

            // Envolver en comillas dobles para evitar problemas con espacios en blanco
            String comandoCrearUsuario = "net user \"" + user + "\" \"" + pass + "\" /add";
            String comandoAgregarAdmin = "net localgroup Administradores \"" + user + "\" /add";

            // Ejecutar los comandos
            ejecutarComando(comandoCrearUsuario);
            ejecutarComando(comandoAgregarAdmin);

            System.out.println("✅ Administrator user '" + user + "' created successfully.");

        } catch (IOException e) {
            System.err.println("❌ Error while creating the user: " + e.getMessage());
        }
    }

    public static void ejecutarComando(String comando) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
        builder.redirectErrorStream(true);
        Process proceso = builder.start();
        try {
            proceso.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restaurar la interrupción
            System.err.println("❌ Command execution was interrupted.");
        }
    }
}
