package com.oaktwister.services.repos;

import com.oaktwister.models.drives.Drive;
import com.oaktwister.models.drives.DriveMetaData;
import com.oaktwister.models.drives.Version;
import com.oaktwister.services.configs.Install;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriveRepo {

    private final static String DRIVE_PROPS_FILE_NAME = ".oak";
    private final static String DRIVE_PROP_REGEX = "(?<key>[^=]+?)=(?<value>.+)";
    private final static String DRIVE_PROP_KEY_CGROUP = "key";
    private final static String DRIVE_PROP_VALUE_CGROUP = "value";
    private final static String ID_PROP_KEY = "ID";
    private final static String VERSION_PROP_KEY = "VERSION";
    private final static String WINDOWS_OS_DRIVE_PATH = "C:\\";
    private final static String UNIX_OS_DRIVE_PATH = "\\";
    private final static String JAVA_CLASS_PATH_KEY = "java.class.path";

    private final Install install;

    public DriveRepo(Install install) {
        this.install = install;
    }

    public List<Drive> getAllDrives() {

        // Load connected drives as well as mock data if development env
        ArrayList<Drive> drives = new ArrayList<Drive>();
        ArrayList<File> driveRoots = new ArrayList<>(Arrays.asList(File.listRoots()));
        if(install.isDevelopmentEnv()) {
            Path pathToExecutingProgram = Path.of(System.getProperty(JAVA_CLASS_PATH_KEY));
            Path workingDir = pathToExecutingProgram.getParent().getParent().getParent();
            Path pathToMockData = Paths.get(workingDir.toString(), "data", "mock");
            File f = new File(pathToMockData.toString());
            driveRoots.add(f);
        }

        // Add the drive model for each drive loaded
        for(File rootDrive : driveRoots) {
            String path = rootDrive.getPath();
            if(WINDOWS_OS_DRIVE_PATH.equals(path) || UNIX_OS_DRIVE_PATH.equals(path)) {
                continue;
            }
            Drive drive = new Drive(
                path,
                rootDrive.getTotalSpace(),
                rootDrive.getFreeSpace(),
                getDriveProps(rootDrive));
            drives.add(drive);
        }
        return drives;

    }

    private DriveMetaData getDriveProps(File driveFile) {
        try {
            File oakFile = new File(driveFile, DRIVE_PROPS_FILE_NAME);
            Scanner scanner = new Scanner(oakFile);
            Hashtable<String, String> oakProps = new Hashtable<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile(DRIVE_PROP_REGEX);
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    oakProps.put(
                        matcher.group(DRIVE_PROP_KEY_CGROUP),
                        matcher.group(DRIVE_PROP_VALUE_CGROUP));
                }
            }
            scanner.close();
            return new DriveMetaData(
                UUID.fromString(oakProps.get(ID_PROP_KEY)),
                new Version(oakProps.get(VERSION_PROP_KEY)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
