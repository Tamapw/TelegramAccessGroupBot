package ru.tama.botgetaccessinprivategroup.command;

/**
 * Created by tama on 13.06.17.
 */
public enum AccessType {
    GETTING_ACCESS_BY_ADMIN(0),
    WAITING_ACCESS_BY_ADMIN(1),
    ACCESS_ALLOWED(2),
    ACCESS_NOT_ALLOWED(3);

    private final int id;

    AccessType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AccessType getType(long id) {
        for (AccessType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new RuntimeException("There are no type for id: " + id);
    }
}
