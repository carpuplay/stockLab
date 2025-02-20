package utils;

import modules.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVHandler {

    private static String generateFileName(String prefix, String extension) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return prefix + "_" + timestamp + extension;
    }

    public static void saveBoxesToCSV(Box[] boxes, String filePath, String rackId) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            filePath = generateFileName("boxes", ".csv");
        }

        boolean fileExists = new File(filePath).exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (!fileExists) {
                writer.write("ID,RackID\n"); // Asegurar que el encabezado incluye RackID
            }

            for (Box box : boxes) {
                if (box == null) continue;
                writer.write(box.getBoxId() + "," + rackId + "\n"); // Garantizar que RackID se escribe correctamente

                // Guardar los items de cada caja
                String itemsFilePath = filePath.replace("boxes", "items");
                saveItemsToCSV(box.getItems(), itemsFilePath, box.getBoxId(), rackId);
            }
        }
    }

    public static void saveItemsToCSV(Item[] items, String filePath, String boxId, String rackId) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            filePath = generateFileName("items", ".csv");
        }

        boolean fileExists = new File(filePath).exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (!fileExists) {
                writer.write("ID,Name,KeyWord0,KeyWord1,KeyWord2,KeyWord3,KeyWord4,KeyWord5,KeyWord6,KeyWord7,KeyWord8,KeyWord9,State,Value,PurchaseDate,BoxID,RackID\n");
            }

            for (Item item : items) {
                if (item == null) continue;
                writer.write(item.toCSV() + "," + (boxId.isEmpty() ? "NULL" : boxId) + "," + rackId + "\n");
            }
        }
    }

    public static void saveRacksToCSV(Rack[] racks, String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            filePath = generateFileName("racks", ".csv");
        }

        boolean fileExists = new File(filePath).exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (!fileExists) {
                writer.write("ID\n"); // Verificar que el header se escribe correctamente
            }

            for (Rack rack : racks) {
                if (rack == null) continue;
                writer.write(rack.getRackId() + "\n"); // Asegurar que el ID del rack se guarda correctamente

                // Guardar cajas y sus items
                String boxFilePath = filePath.replace("racks", "boxes");
                saveBoxesToCSV(rack.getBoxes(), boxFilePath, rack.getRackId());

                // Guardar los items que están directamente en el rack
                String itemFilePath = filePath.replace("racks", "items");
                saveItemsToCSV(rack.getItems(), itemFilePath, "", rack.getRackId());
            }
        }
    }
}
