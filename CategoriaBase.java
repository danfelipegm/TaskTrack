package co.edu.upb.categorias;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class CategoriaBase {
    private String categoria;
    private HashMap<String, Integer> pines = new HashMap<>();
    private static final String PINES_FILE = "pines.txt";

    public CategoriaBase(String categoria) {
        this.categoria = categoria;
        cargarPinesDesdeArchivo();
    }

    public void establecerPin(int nuevoPin) {
        pines.put(categoria, nuevoPin);
        guardarPinesEnArchivo();
    }

    public boolean acceder(int pinIngresado) {
        return pines.containsKey(categoria) && pinIngresado == pines.get(categoria);
    }

    private void cargarPinesDesdeArchivo() {
        try {
            File file = new File(PINES_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String cat = parts[0];
                        int pin = Integer.parseInt(parts[1]);
                        pines.put(cat, pin);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarPinesEnArchivo() {
        try (FileWriter writer = new FileWriter(PINES_FILE)) {
            for (String cat : pines.keySet()) {
                int pin = pines.get(cat);
                writer.write(cat + ":" + pin + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}