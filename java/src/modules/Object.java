package modules;

public class Object {
    public String name;
    public String[] keyWord;
    public int id;
    public State state;
    public int Value;

    private static final int MAX_KEYWORD = 10;

    public Object(String name, int id, State state, int value) {
        this.name = name;
        this.keyWord = new String[MAX_KEYWORD];
        this.id = id;
        this.state = state;
        this.Value = value;
    }


}
