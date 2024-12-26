package modules;

import utils.IdManager;

import java.util.Objects;


public class Box {
    private String id;
    private String itemId;
    private static final IdManager idManager = new IdManager();
    private static final int TYPE = 1;

    public Box(String id, String itemId) {
        this.id = idManager.createId(id, TYPE);
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }
    public void addItemId(String item) {
        // add logic to add small item in smallItems array
    }



}