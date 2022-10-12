package com.oaktwister.services;

import com.oaktwister.models.Drive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DriveFactory {

    public List<Drive> getAllDrives() {
        Randomizer randomizer = new Randomizer();
        List<Drive> drives = new ArrayList<Drive>();
        for(File rootDrive : File.listRoots()) {
            Drive drive = new Drive(
                rootDrive.getName(),
                rootDrive.getPath(),
                randomizer.randomAlphabeticString(randomizer.randomInt(5, 10)),
                randomizer.randomDouble(0, 10),
                rootDrive.getFreeSpace());
            drives.add(drive);
        }
        return drives;
    }

}
