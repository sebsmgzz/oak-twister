package com.oaktwister.services;

import com.oaktwister.models.Drive;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriveFactory {

    public List<Drive> getAllDrives() {
        List<Drive> drives = new ArrayList<Drive>();
        for(File rootDrive : File.listRoots()) {
            Drive drive = new Drive(
                findDriveId(rootDrive),
                rootDrive.getPath(),
                rootDrive.getTotalSpace(),
                rootDrive.getFreeSpace());
            drives.add(drive);
        }
        return drives;
    }

    private UUID findDriveId(File drive) {
        try {
            File oakFile = new File(drive, ".oak");
            Scanner scanner = new Scanner(oakFile);
            Hashtable<String, String> oakProps = new Hashtable<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile("(?<key>[^=]+?)=(?<value>.+)");
                Matcher matcher = pattern.matcher(line);
                oakProps.put(
                        matcher.group("key"),
                        matcher.group("value"));
            }
            scanner.close();
            return UUID.fromString(oakProps.get("ID"));
        } catch (Exception e) {
            return null;
        }
    }

}
