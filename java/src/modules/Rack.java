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
        this.rackId = idManager.createId(rackId, TYPE);
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
        for (int i = 0; i < MAX_BOXES; i++) {
            if (boxes[i] != null) {
                Item item = boxes[i].getItemById(id);
                if (item.getId().equals(id)) {
                    return items[i];
                }
            }
        }
        return null;
    }

    public void addItem(Item item) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
        throw new IllegalArgumentException("Rack is full");
    }

    public void removeIdFromRack(String id) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] != null) {
                if (items[i].getId().equals(id)) {
                    items[i] = null;
                }
            }
        }
    }

    public void addBox(Box box) {
        for (int i = 0; i < MAX_BOXES; i++) {
            if (boxes[i] != null) {
                throw new IllegalArgumentException("Rack is full");
            } else {
                boxes[i] = box;
            }
        }
    }



}
