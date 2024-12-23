package modules;

import utils.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;


public class Item {
    public String name;
    public String[] keyWord;
    
    public String id;
    public State state;
    public float value;

    public LocalDate purchaseDate;

    private static final int MAX_KEYWORD = 10;
    private static final IdManager idManager = new IdManager();
    

    public Item(String name, String[] keyWord , String id, State state, float value, LocalDate purchaseDate) {
        this.name = name;
        this.keyWord = new String[MAX_KEYWORD];
        this.setKeyWord(keyWord);
        this.id = this.createId(id);
        this.state = state;
        this.value = value;
        this.purchaseDate = purchaseDate;
    }
    
    public String getName(){
        return name;
    }

    public String[] getKeyWord() {
        return keyWord;
    }
    public State getState() {
        return state;
    }

    public float getValue() {
        return value;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getId() {
        return id;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setValue(int value){
        if (value <= 0){
            throw new IllegalArgumentException("Value must be greater than 0");
        }
        this.value = value;
    }

    private String createId(String id) {
        if (Objects.equals(id, "")){
            return idManager.generateId(2);
        }
        return id;
    }

    //LOGIQUE DES KEYWORDS
    public void setKeyWord(String[] keyWord){
        if (keyWord == null){
            throw new IllegalArgumentException("Keywords cannot be null");
        }
        if (keyWord.length > MAX_KEYWORD) {
            throw new IllegalArgumentException("Too many keywords provided");
        }
        for ( int i = 0; i < keyWord.length; i++) {
            this.keyWord[i] = keyWord[i];
        }
    }

    public void addKeyword(String keyWord) {
        if (keyWord == null || keyWord.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty.");
        }
        for (int i = 0; i < MAX_KEYWORD; i++) {
            if (this.keyWord[i] == null) {
                this.keyWord[i] = keyWord;
                return;
            }
        }
        throw new IllegalStateException("No more keywords can be added.");
    }

    public void removeKeyword(String keyword) {
        for (int i = 0; i < MAX_KEYWORD; i++) {
            if (keyWord[i] != null && keyWord[i].equals(keyword)) {
                keyWord[i] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Keyword not found to be removed.");
    }

    public void setPurchaseDate(LocalDate purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    public static void main(String[] args){
        Item obj = new Item("Sample Object", new String[]{"key1", "key2"}, "", State.PERFECT, 100, LocalDate.now());
        System.out.println("Object Name: " + obj.getName());
        System.out.println("Object Id: " + obj.getId());
        System.out.println("Object keys:" + Arrays.toString(obj.getKeyWord()));
        System.out.println("Object State: " + obj.getState().getName());
        System.out.println("Object Value: " + obj.getValue());
        System.out.println("Object Purchase Date: " + obj.getPurchaseDate());
        obj.setState(State.FAULTY);
        System.out.println("Object State after change: " + obj.getState().getName());
        obj.addKeyword("newKeyword");
        obj.removeKeyword("key1");
        System.out.println("Object keys:" + Arrays.toString(obj.getKeyWord()));

        String userHomeDir = System.getProperty("user.home");
        File file = new File("/Users/acarp/gitWorkspace/stockLab/myfile.csv");
        CSVHandler.saveToCSV(obj, file);
    }
}
