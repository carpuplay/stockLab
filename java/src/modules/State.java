package modules;

public enum State {
    PERFECT("Parfait",""),
    FAULTY("Defectueux", ""),
    BROKEN("Cassé", ""),
    UNKNOWN("Inconnu", ""),
    THROWN("Jeté", ""),
    SOLD("Vendu", ""),
    GIVEN("Donné", "");


    private final String name;
    private String description;

    State(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public boolean isAvailable() {
        return this == PERFECT || this == FAULTY || this == UNKNOWN;
    }

}
