package ru.tama.botgetaccessinprivategroup;

import ru.tama.botgetaccessinprivategroup.util.FileWrapper;

import java.io.File;

/**
 * Class {@link BuildVars} contains all constant variable for Bot.
 */
public class BuildVars {
    public static final String TOKEN_KEY = "390131614:AAGqDkn6PZtX6N_xsfujrTMz_0Z0WBchLsc";
    public static final String USER_NAME_BOT = "GroupAccessGetterBot";

    public static final String LINK_TO_DB = "jdbc:mysql://localhost:3306/bot?useUnicode=true&characterEncoding=UTF-8";
    public static final String DRIVER_DB = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME_DB = "root";
    public static final String PASSWORD_DB = "111297";
    public static final long ADMIN_ID = 391017801;

    private static String urlPrivateGroup = "";

    static {
        urlPrivateGroup = FileWrapper.readFromFile("url");
    }

    public static String getUrlPrivateGroup() {
        return urlPrivateGroup;
    }
    public static void setUrlPrivateGroup(String urlPG) {
        urlPrivateGroup = urlPG;

        FileWrapper.createFile("url");
        FileWrapper.writeIntoFile("url", urlPG);
    }
}
