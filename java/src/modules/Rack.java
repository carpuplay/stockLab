package modules;

import utils.ArrayManager;
import utils.IdManager;

import java.time.LocalDate;

public class Rack {

    public String rackId;

    public Item[] items;

    public Box[] boxes;

    private static final int MAX_ITEMS = 100;
    private static final int MAX_BOXES = 100;
    private static final int TYPE = 1;

    private static final IdManager idManager = new IdManager();
    private static final ArrayManager arrayManager = new ArrayManager();

    public Rack(String rackId) {
        this.items = new Item[MAX_ITEMS];
        this.boxes = new Box[MAX_BOXES];
        this.rackId = idManager.createId( rackId, TYPE);
    }

    public String getRackId() {
        return rackId;
    }
    public Box[] getBoxes() {
        return boxes;
    }

    public Item[] getItems() {
        return items;
    }

    public Box getBoxById(String id) {
        int boxIndex = 1;
        return null;
    }

    public Item getItemById(String id) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] != null) {
                String itemId = items[i].getId();
                if (itemId.equals(id)) {
                    return items[i];
                }
            }
        }
        System.out.println("Item not found in rack");
        for (int i = 0; i < MAX_BOXES; i++) {
            if (boxes[i] != null) {
                Item item = boxes[i].getItemById(id);
                if (item != null) {
                    return items[i];
                }
            }
        }
        System.out.println("Item not found in boxes");
        return null;
    }

    public void addItem(Item item) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        throw new IllegalArgumentException("Rack is full");
    }

    public void removeItemFromRack(Item item) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == item) {
                    items[i] = null;
                    return;
            }
        }
        throw new IllegalArgumentException("Item not found in rack");
    }

    public void addBox(Box box) {
        for (int i = 0; i < MAX_BOXES; i++) {
            if (boxes[i] == null) {
                boxes[i] = box;
                return;
            }
        }
        throw new IllegalArgumentException("Rack is full");
    }

    public int emptySlots() {
        int count = 0;
        for (Item item : items) {
            if (item == null) {
                count++;
            }
        }
        return count;
    }

    public String toCSV() {
        return rackId + "," + rackId;
    }

    public static void main(String[] args) {

        Rack rack = new Rack("rackTest");
        Box box1 = new Box("");

        Item item1 = new Item("Item1", new String[]{"key1"}, "", State.PERFECT, 10, LocalDate.now());
        Item item2 = new Item("Item2", new String[]{"key2"}, "", State.DAMAGED, 15, LocalDate.now());
        Item item3 = new Item("Item3", new String[]{"key3"}, "", State.PERFECT, 20, LocalDate.now());

        rack.addItem(item1);
        rack.addItem(item2);
        rack.addItem(item3);
        Item[] items = rack.getItems();

        // Verif elmt rack
        System.out.println("Items in Rack:");
        for (Item item : items) {
            if (item != null) {
                System.out.println("- " + item.getName() + " | ID: " + item.getId());
            }
        }

        System.out.println("Empty slots in rack: " + rack.emptySlots());

        rack.addBox(box1);

        System.out.println("Boxes in Rack:" );
        for (Box box : rack.getBoxes()) {
            if (box != null) {
                System.out.println("- " + box.getBoxId());
            }
        }

    }
}
