package utils;

import modules.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVHandler {
    public static void saveItemsToCSV(Item[] items, String filePath, String boxId, String rackId) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(" ID, Name, KeyWord0 , KeyWord1, KeyWord2, KeyWord3, KeyWord3, KeyWord4, KeyWord5, KeyWord6, KeyWord7, KeyWord8, KeyWord9 , State, Value, PurchaseDate , BoxID, RackID\n");
            for (Item item : items) {
                if (item == null) {
                    continue;
                }
                writer.write(item.toCSV() + "," + boxId + rackId + "\n");
            }
        }
    }

    public static void saveBoxesToCSV(Box[] boxes, String filePath, String rackId) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,RackID\n");
            for (Box box : boxes) {
                if (box == null) {
                    continue;
                }
                writer.write(box.toCSV() + "," + rackId + "\n");
                saveItemsToCSV(box.getItems(), filePath, box.getBoxId(), rackId);
            }
        }
    }

    public static void saveRacksToCSV(Rack[] racks, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID\n");
            for (Rack rack : racks) {
                if (rack == null) {
                    continue;
                }
                writer.write(rack.toCSV() + "\n");
                saveBoxesToCSV(rack.getBoxes() , filePath, rack.getRackId());
                saveItemsToCSV(rack.getItems(), filePath, "",rack.getRackId());
            }
        }
    }
}
