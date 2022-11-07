package com.oaktwister.app.services.drives;

import com.oaktwister.domain.models.drives.Drive;
import com.oaktwister.domain.models.drives.DriveMeta;
import com.oaktwister.domain.models.drives.Version;
import com.oaktwister.app.services.configs.Environment;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriveLoader {

    public final static String DRIVE_META_FILE_NAME = ".oak";
    public final static String DRIVE_META_REGEX = "(?<key>[^=]+?)=(?<value>.+)";
    public final static String DRIVE_META_KEY_GROUP = "key";
    public final static String DRIVE_META_VALUE_GROUP = "value";

    public final static String META_VERSION_KEY = "VERSION";
    public final static String META_USER_ID_KEY = "USER_ID";
    public final static String META_USERNAME_KEY = "USERNAME";

    public final static String WINDOWS_OS_DRIVE_PATH = "C:\\";
    public final static String UNIX_OS_DRIVE_PATH = "\\";
    public final static String JAVA_CLASS_PATH_KEY = "java.class.path";

    private final Environment environment;

    public DriveLoader(Environment environment) {
        this.environment = environment;
    }

    public DriveMeta loadMeta(Drive drive) {
        try {

            // Get path to metadata
            String drivePath = drive.getPath();
            File metaPath = new File(drivePath, DRIVE_META_FILE_NAME);

            // Read the metadata as a hashtable
            Hashtable<String, String> meta = new Hashtable<>();
            Scanner scanner = new Scanner(metaPath);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile(DRIVE_META_REGEX);
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    String key = matcher.group(DRIVE_META_KEY_GROUP);
                    String value = matcher.group(DRIVE_META_VALUE_GROUP);
                    meta.put(key, value);
                } else {
                    // TODO: Log warning - Unknown metadata property found
                }
            }
            scanner.close();

            // Parse the metadata to corresponding values
            Version version = Version.parse(meta.get(META_VERSION_KEY));
            String username = meta.get(META_USERNAME_KEY);
            UUID userId = UUID.fromString(meta.get(META_USER_ID_KEY));
            return new DriveMeta(version, username, userId);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public List<Drive> listAllDrives() {

        // Get a list of the paths to the connected drives
        ArrayList<Drive> drives = new ArrayList<Drive>();
        File[] fsRootPaths = File.listRoots();
        List<File> fsRootPathsList = Arrays.asList(fsRootPaths);
        ArrayList<File> driveRoots = new ArrayList<>(fsRootPathsList);

        // Load mock data in dev env
        if(environment.isDevelopment()) {
            Path exePath = Path.of(System.getProperty(JAVA_CLASS_PATH_KEY));
            Path workDirPath = exePath.getParent().getParent().getParent();
            Path mockDataPath = Paths.get(workDirPath.toString(), "data", "mock");
            driveRoots.add(new File(mockDataPath.toString()));
        }

        // For each drive, determine if it has metadata
        for(File driveRoot : driveRoots) {
            String drivePath = driveRoot.getPath();
            File driveMetaPath = new File(driveRoot, DRIVE_META_FILE_NAME);
            boolean hasMeta = driveMetaPath.exists();
            Drive drive = new Drive(drivePath, hasMeta);
            drives.add(drive);
        }
        return drives;

    }

}
