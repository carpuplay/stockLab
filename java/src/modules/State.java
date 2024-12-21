package modules;

public enum State {
    PERFECT("Parfait","Objet en bonne état voir neuf."),
    FAULTY("Défectueux", "Objet abimé, (ex: rayure)."),
    BROKEN("Cassé", "Objet qui ne fonctionne plus."),
    UNKNOWN("Inconnu", ""),
    THROWN("Jeté", ""),
    SOLD("Vendu", ""),
    GIVEN("Donné", ""),
    BORROWED("Emprunté", "");


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
