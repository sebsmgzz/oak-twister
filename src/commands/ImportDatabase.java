package commands;

import models.BaseManager;
import models.ManagerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ImportDatabase {

    private final static String CSV_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\csv";
    private final static String SQLITE_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\db.sqlite";

    private final HashMap<String, BaseManager> managersMap;

    public ImportDatabase(ManagerFactory managerFactory) {
        this.managersMap = new HashMap<>();
        this.managersMap.put("accounts", managerFactory.getAccountManager());
        this.managersMap.put("identities", managerFactory.getIdentityManager());
        this.managersMap.put("passwords", managerFactory.getPasswordManager());
        this.managersMap.put("platforms", managerFactory.getPlatformManager());
    }

    public void execute() {
        dropFile();
        createTables();
    }

    public void dropFile() {
        File file = new File(SQLITE_PATH);
        if(file.delete()) {
            System.out.println(file.getName() + " deleted");
        }
        else {
            System.out.println("Failed to delete at " + SQLITE_PATH);
        }
    }

    public void createTables() {
        for(String key : managersMap.keySet()) {
            System.out.print("Creating " + key + "...");
            boolean created = managersMap.get(key).createTable();
            if(created) {
                System.out.println("success");
                fillTable(key);
            } else {
                System.out.println("failed");
            }
        }
    }

    public void fillTable(String key) {
        BaseManager manager = managersMap.get(key);
        System.out.print("Filling " + key + "...");
        try {
            getValues(key);
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("failed");
        }
    }

    public HashMap<String, String> getValues(String key) throws FileNotFoundException {
        File file = new File(CSV_PATH + "\\" + key + ".csv");
        Scanner myReader = new Scanner(file);
        if (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        return null;
    }

}
