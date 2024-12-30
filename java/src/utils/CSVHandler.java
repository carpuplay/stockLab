package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import modules.*;

public class CSVHandler {

    private CSVHandler() {
        // Private constructor to prevent instantiation
    }

    public static void saveToCSV(Item obj, File file) {
        if (obj == null || file == null) {
            throw new IllegalArgumentException("Item or file cannot be null.");
        }

        try {
            // Ensure the file exists
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Failed to create the file at " + file.getAbsolutePath());
                return;
            }

            // Write data to the file
            try (FileWriter fileWriter = new FileWriter(file, true); // Append mode
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {

                String csvLine = formatItemAsCSV(obj);
                printWriter.println(csvLine);
            }

        } catch (IOException e) {
            System.err.println("An error occurred while saving to CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String formatItemAsCSV(Item obj) {
        String keywords = String.join("|", obj.getKeyWord());
        return String.format("%s,%s,%s,%.2f,%s,%s",
                obj.getName(),
                obj.getId(),
                obj.getState().getName(),
                obj.getValue(),
                obj.getPurchaseDate(),
                keywords);
    }

    // Main test function
    public static void main(String[] args) {
        // Create an Item object for testing
        Item testItem = new Item("Item1", new String[]{"key1", "key2"}, "", State.PERFECT, 45, LocalDate.now());

        // Create a File object
        File testFile = new File("test.csv");

        // Call the saveToCSV method
        saveToCSV(testItem, testFile);

        System.out.println("Data saved to " + testFile.getAbsolutePath());
    }
}
