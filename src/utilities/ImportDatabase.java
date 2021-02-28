package utilities;

import models.Wrapper;
import models.WrapperFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ImportDatabase {

    private final static String CSV_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\csv";
    private final static String SQLITE_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\db.sqlite";

    private final List<Wrapper> wrappers;

    public ImportDatabase() {
        this.wrappers = new ArrayList<>();
        WrapperFactory wrapperFactory = new WrapperFactory();
        wrappers.add(wrapperFactory.getAccount());
        wrappers.add(wrapperFactory.getIdentity());
        wrappers.add(wrapperFactory.getPassword());
        wrappers.add(wrapperFactory.getPlatform());
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
        for(Wrapper wrapper : wrappers) {
            String tableName = wrapper.getMetaModel().getTableName();
            System.out.print("Creating " + tableName + "...");
            boolean created = wrapper.getManager().createTable();
            if(created) {
                System.out.println("success");
                fillTable(tableName, wrapper);
            } else {
                System.out.println("failed");
            }
        }
    }

    public void fillTable(String tableName, Wrapper wrapper) {
        System.out.print("Filling " + tableName + "...");
        try {
            getValues(tableName);
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
