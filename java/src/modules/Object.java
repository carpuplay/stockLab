package modules;

import java.util.Arrays;

public class Object {
    public String name;
    public String[] keyWord;
    public int id;
    public State state;
    public int Value;

    private static final int MAX_KEYWORD = 10;

    public Object(String name, String[] keyWord ,int id, State state, int value) {
        this.name = name;
        this.keyWord = new String[MAX_KEYWORD];
        this.id = id;
        this.state = state;
        this.Value = value;
    }

    public String getName(){
        return name;
    }

    public State getState() {
        return state;
    }

    public int getValue(){
        return Value;
    }

    public void setState(State state) {
        //TODO
    }

    public void setName(String name){
        this.name = name;
    }

    //LOGIQUE DES KEYWORDS
    public void setKeyWord(String[] keyWord){
        if (keyWord == null){
            throw new IllegalArgumentException("Keywords cannot be null");
        }
        if (keyWord.length > MAX_KEYWORD){
            throw new IllegalArgumentException("Too many keywords provided");
        }
        this.keyWord = Arrays.copyOf(keyWord, keyWord.length);
    }

    public void addKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty.");
        }
        for (int i = 0; i < MAX_KEYWORD; i++) {
            if (keyWord[i] == null) {
                keyWord[i] = keyword;
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
}
