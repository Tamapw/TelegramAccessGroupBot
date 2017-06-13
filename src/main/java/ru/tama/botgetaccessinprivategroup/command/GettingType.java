package ru.tama.botgetaccessinprivategroup.command;

/**
 * Created by tama on 13.06.17.
 */
public enum GettingType {
    GETTING_PHONE(2),
    GETTING_YEAR_BIRTHDAY(3),
    ALL_DATA_GET(4);

    private final int id;

    GettingType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GettingType getType(long id) {
        for (GettingType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new RuntimeException("There are no type for id: " + id);
    }
}
