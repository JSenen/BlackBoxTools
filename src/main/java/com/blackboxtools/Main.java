package com.blackboxtools;

import com.blackboxtools.tools.AddUser;
import com.blackboxtools.tools.JavaExceptionSiteAdder;
import com.blackboxtools.tools.CrackPassword;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner selection = new Scanner(System.in);
        clearScreen();

        while (true) { //Mantener el menú en un bucle infinito hasta que el usuario decida salir
            System.out.println("================================================================");
            System.out.println(" ____  _            _    ____            _____           _     \n" +
                    "| __ )| | __ _  ___| | _| __ )  _____  _|_   _|__   ___ | |___ \n" +
                    "|  _ \\| |/ _` |/ __| |/ /  _ \\ / _ \\ \\/ / | |/ _ \\ / _ \\| / __|\n" +
                    "| |_) | | (_| | (__|   <| |_) | (_) >  <  | | (_) | (_) | \\__ \\\n" +
                    "|____/|_|\\__,_|\\___|_|\\_\\____/ \\___/_/\\_\\ |_|\\___/ \\___/|_|___/");
            System.out.println("\n================================================================");
            System.out.println("***************             MENU               *****************\n");
            System.out.println("(1) Add user to Windows\n");
            System.out.println("(2) Add URL to Java Exception Sites\n");
            System.out.println("(3) Crack PBKDF2 password\n");
            System.out.println("(q) Exit");
            System.out.print("Enter an option (or 'q' to exit): ");

            String menu = selection.nextLine();

            switch (menu) {
                case "q":
                    System.out.println("Thank you, bye bye !!");
                    selection.close();
                    return;  // 🔹 En lugar de `System.exit(0)`, usamos `return` para salir del `while`
                case "1":
                    System.out.println("\nOption 1 selected - Adding new user...");
                    new AddUser();  // Se ejecuta la clase y vuelve al menú
                    break;
                case "2":
                    System.out.println("\nOption 2 selected - Adding URL to Java Exception Sites");
                    System.out.print("Enter URL exception > ");
                    String url = selection.nextLine();
                    JavaExceptionSiteAdder.addExceptionSite(url);
                    break;
                case "3":
                    System.out.println("\nOption 3 selected - PBKDF2 hash cracking...");
                    CrackPassword.main(new String[]{}); // Llamar al `main` para ejecutar CrackPassword
                    break;
                default:
                    System.out.println("Option not valid, try again.");
            }
        }
    }

    // Clean terminal (MAC and Windows)
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error clearing console: " + e.getMessage());
        }
    }
}
