package modules;

import utils.ArrayManager;
import utils.IdManager;

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
                if (item.getId().equals(id)) {
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

    public static void main(String[] args) {
        Rack test = new Rack("");
        Item item = new Item("test", new String[]{"test"}, "",State.PERFECT, 0, null);
        Item item2 = new Item("test2", new String[]{"test2"}, "", State.PERFECT, 0, null);
        Item item3 = new Item("test3", new String[]{"test3"}, "",State.PERFECT, 0, null);
        System.out.println("Rack ID: " + test.getRackId());
        test.addItem(item);
        test.addItem(item2);
        test.addItem(item3);

        System.out.println("Item ID (by id search): " + test.getItemById(item.getId()).getId());
        test.removeItemFromRack(item2);
        System.out.println("Item ID (by id search): " + test.getItemById(item2.getId()).getId());


    }
}
