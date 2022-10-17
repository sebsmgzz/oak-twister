package com.oaktwister.models.exceptions;

public class CorruptedDataException extends Exception {

    public CorruptedDataException() {
        super("An unknown file is found to be corrupted");
    }

    public CorruptedDataException(String filePath) {
        super(String.format(
                "The file %s is found to be corrupted",
                filePath));
    }

}
