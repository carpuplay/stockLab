package modules;

import utils.ArrayManager;
import utils.IdManager;

import java.time.LocalDate;
import java.util.Arrays;


public class Box {
    private String boxId;
    public Item[] items;
    private static final int MAX_ITEMS = 30;

    private static final IdManager idManager = new IdManager();
    private static final ArrayManager arrayManager = new ArrayManager();
    private static final int TYPE = 1;

    public Box(String boxId) {
        this.boxId = idManager.createId(boxId, TYPE);
        this.items = new Item[MAX_ITEMS];
    }

    public String getBoxId() {
        return boxId;
    }

    public Item[] getItems() {
        return items;
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

    public void addItem(Item item) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        throw new IllegalArgumentException("Box is full");
    }

    public void removeItem(String id) {
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (items[i] != null) {
                String itemId = items[i].getId();
                if (itemId.equals(id)) {
                    items[i] = null;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Item not found in box");
    }

    public static void main(String[] args){
        Box test = new Box("");
        Item item = new Item("item", new String[]{"key"}, "", State.PERFECT, 10, LocalDate.of(2020,10,21));


        System.out.println("BoxId: " + test.getBoxId());
        test.addItem(item);
        System.out.println("Items in box (by id search): " + test.getItemById(item.getId()).getName());
        System.out.println("Items in box (by get)" + Arrays.toString(test.getItems()));



    }

}