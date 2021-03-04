package utilities;

import models.Model;
import models.Wrapper;
import models.WrapperFactory;
import models.account.Account;
import models.account.AccountManager;
import models.identity.Identity;
import models.identity.IdentityManager;
import models.password.Password;
import models.password.PasswordManager;
import models.platform.Platform;
import models.platform.PlatformManager;
import org.sqlite.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImportDatabase {

    private final static String CSV_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\csv";
    private final static String SQLITE_PATH = "C:\\Users\\SMaronas\\Repos\\OakTwister\\data\\db.sqlite";

    private final HashMap<String, Wrapper> wrappers;

    public ImportDatabase() {
        this.wrappers = new HashMap<>();
        WrapperFactory wrapperFactory = new WrapperFactory();
        wrappers.put("accounts", wrapperFactory.getAccount());
        wrappers.put("identities", wrapperFactory.getIdentity());
        wrappers.put("passwords", wrapperFactory.getPassword());
        wrappers.put("platforms", wrapperFactory.getPlatform());
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
        for(String key : wrappers.keySet()) {
            System.out.println("------------------------------------");
            System.out.print("Creating " + key + "...");
            Wrapper wrapper = wrappers.get(key);
            boolean created = wrapper.getManager().createTable();
            if(created) {
                System.out.println("success");
                fillTable(key, wrapper);
            } else {
                System.out.println("failed");
            }
        }
    }

    public void fillTable(String tableName, Wrapper wrapper) {
        System.out.print("Filling " + tableName + " ");
        try {
            List<List<String>> rows = getCsvRows(tableName);
            List<String> headers = rows.get(0);
            for (int i = 1; i < rows.size(); i++) {
                String message = i + "/" + (rows.size() - 1);
                int length = message.length();
                System.out.print(message);
                List<String> values = rows.get(i);
                HashMap<String, String> map = new HashMap<>();
                for(int j = 0; j < values.size(); j++) {
                    map.put(headers.get(j), values.get(j));
                }
                Model model = wrapper.getSerializer().serialize(map);
                if(post(tableName, model, wrapper)) {
                    if(i + 1 != rows.size()) {
                        System.out.print("\b".repeat(length));
                    }
                } else {
                    System.out.println("Error at " + i);
                    return;
                }
            }
            System.out.println(" success");
        } catch (Exception e) {
            System.out.println(" failed");
            System.out.println(e);
        }
    }

    public List<List<String>> getCsvRows(String key) throws FileNotFoundException {
        List<List<String>> values = new ArrayList<>();
        File file = new File(CSV_PATH + "\\" + key + ".csv");
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String row = myReader.nextLine();
            List<String> csvRow = csvSplit(row);
            values.add(csvRow);
        }
        return values;
    }

    public List<String> csvSplit(String data) {
        List<String> csv = new ArrayList<>();
        String[] quoteSeparatedData = data.split("\"");
        boolean isQuotedSurrounded = data.startsWith("\"");
        for(String quotedData : quoteSeparatedData) {
            int commasCount = 0;
            for(int i = 0; i < quotedData.length(); i++) {
                if(quotedData.charAt(i) == ',') {
                    commasCount++;
                }
            }
            if(isQuotedSurrounded) {
                csv.add(quotedData);
            } else {
                String[] innerCsv = quotedData.split(",");
                csv.addAll(Arrays.asList(innerCsv));
            }
            isQuotedSurrounded = !isQuotedSurrounded;
        }
        return csv;
    }

    public boolean post(String tableName, Model model, Wrapper wrapper) {
        switch (tableName) {
            case "accounts":
                AccountManager accountManager = (AccountManager) wrapper.getManager();
                return accountManager.insert((Account) model);
            case "identities":
                IdentityManager identityManager = (IdentityManager) wrapper.getManager();
                return identityManager.insert((Identity) model);
            case "passwords":
                PasswordManager passwordManager = (PasswordManager) wrapper.getManager();
                return passwordManager.insert((Password) model);
            case "platforms":
                PlatformManager platformManager = (PlatformManager) wrapper.getManager();
                return platformManager.insert((Platform) model);
        }
        return false;
    }

}
