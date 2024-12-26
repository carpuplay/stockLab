package modules;

import utils.ArrayManager;
import utils.IdManager;

public class Rack {

    public String id;

    public Item[] items;

    public Box[] boxes;

    private static final int MAX_ITEMS = 100;
    private static final int MAX_BOXES = 100;
    private static final int TYPE = 1;

    private static final IdManager idManager = new IdManager();
    private static final ArrayManager arrayManager = new ArrayManager();

    public Rack(String id) {
        this.items = new Item[MAX_ITEMS];
        this.boxes = new Box[MAX_BOXES];
        this.id = idManager.createId(id, TYPE);
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
            String itemId = items[i].getId();
            if (itemId.equals(id)) {
                return items[i];
            }
        }
        return null;
    }
}
