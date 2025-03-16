package com.blackboxtools.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaExceptionSiteAdder {

    private static final String EXCEPTION_SITES_FILE = System.getProperty("user.home") +
            "\\AppData\\LocalLow\\Sun\\Java\\Deployment\\security\\exception.sites";

    public static void addExceptionSite(String url) {
        try {
            // Crear el archivo si no existe
            Files.createDirectories(Paths.get(EXCEPTION_SITES_FILE).getParent());
            Files.createFile(Paths.get(EXCEPTION_SITES_FILE));

            // Escribir la URL en el archivo
            try (FileWriter writer = new FileWriter(EXCEPTION_SITES_FILE, true)) {
                writer.write(url + "\n");
                System.out.println("✅ Sitio agregado exitosamente: " + url);
            }

        } catch (IOException e) {
            System.err.println("❌ Error al agregar la excepción de Java: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        addExceptionSite("https://ejemplo.com");
    }
}

