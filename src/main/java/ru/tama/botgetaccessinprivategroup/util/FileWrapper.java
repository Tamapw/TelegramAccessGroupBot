package ru.tama.botgetaccessinprivategroup.util;

import java.io.*;

/**
 * Created by tama on 12.06.17.
 */
public class FileWrapper {
    public static void writeIntoFile(String filename, String message) {
        try {
            PrintWriter pw = new PrintWriter(filename);

            pw.append(message);
            pw.append("\n");

            pw.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static String readFromFile(String filename) {
        try {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        return br.readLine();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void createFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
