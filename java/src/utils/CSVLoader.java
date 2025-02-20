package utils;

import modules.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CSVLoader {

    public static List<Rack> loadRacksFromCSV(String filePath) throws IOException {
        List<Rack> racks = new ArrayList<>();
        Map<String, Rack> rackMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Saltar la cabecera

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 1) continue;

                String rackId = parts[0].trim();
                Rack rack = new Rack(rackId);
                racks.add(rack);
                rackMap.put(rackId, rack);
            }
        }

        // Cargar las cajas y los items
        loadBoxesFromCSV(rackMap, filePath.replace("racks", "boxes"));
        loadItemsFromCSV(rackMap, filePath.replace("racks", "items"));

        return racks;
    }

    public static void loadBoxesFromCSV(Map<String, Rack> rackMap, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Saltar la cabecera

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String boxId = parts[0].trim();
                String rackId = parts[1].trim();

                Rack rack = rackMap.get(rackId);
                if (rack != null) {
                    Box box = new Box(boxId);
                    rack.addBox(box);
                }
            }
        }
    }

    public static void loadItemsFromCSV(Map<String, Rack> rackMap, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Saltar la cabecera

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 17) continue;

                String itemId = parts[0].trim();
                String name = parts[1].trim();
                String[] keywords = Arrays.copyOfRange(parts, 2, 12); // Extraer keywords
                State state = State.valueOf(parts[12].trim());
                float value = Float.parseFloat(parts[13].trim());
                LocalDate purchaseDate = LocalDate.parse(parts[14].trim());
                String boxId = parts[15].trim();
                String rackId = parts[16].trim();

                Item item = new Item(name, keywords, itemId, state, value, purchaseDate);


                Rack rack = rackMap.get(rackId);
                if (rack != null) {
                    if (!boxId.equals("NULL")) {
                        Box box = rack.getBoxById(boxId);
                        if (box != null) {
                            box.addItem(item);
                        }
                    } else {
                        rack.addItem(item);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {

            // 2️⃣ Cargar racks desde CSV
            List<Rack> racks = loadRacksFromCSV("racks_20250220_213303.csv");

            // 3️⃣ Verificar la carga de datos
            System.out.println("✅ Racks cargados:");
            for (Rack rack : racks) {
                System.out.println("Rack ID: " + rack.getRackId());
                System.out.println("  Cajas:");
                for (Box box : rack.getBoxes()) {
                    System.out.println("    Box ID: " + box.getBoxId());
                    System.out.println("      Ítems:");
                    for (Item item : box.getItems()) {
                        System.out.println("        Item ID: " + item.getId() + ", Nombre: " + item.getName());
                    }
                }
                System.out.println("  Ítems sueltos en el rack:");
                for (Item item : rack.getItems()) {
                    System.out.println("    Item ID: " + item.getId() + ", Nombre: " + item.getName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
