package com.oaktwister.models.drives;

public class DataSize {

    public static final long BYTES_IN_KILOBYTE = 1024L;
    public static final long BYTES_IN_MEGABYTE = 1024 * 1024L;
    public static final long BYTES_IN_GIGABYTE = 1024 * 1024 * 1024L;
    public static final long BYTES_IN_TERABYTE = 1024 * 1024 * 1024 * 1024L;

    public long bytes;

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long value) {
        bytes = value;
    }

    public long getKilobytes() {
        return bytes / BYTES_IN_KILOBYTE;
    }

    public void setKilobytes(long value) {
        bytes = value * BYTES_IN_KILOBYTE;
    }

    public long getMegabytes() {
        return bytes / BYTES_IN_MEGABYTE;
    }

    public void setMegabytes(long value) {
        bytes = value * BYTES_IN_MEGABYTE;
    }

    public long getGigabytes() {
        return bytes / BYTES_IN_GIGABYTE;
    }

    public void setGigabytes(long value) {
        bytes = value * BYTES_IN_GIGABYTE;
    }

    public long getTerabytes() {
        return bytes / BYTES_IN_TERABYTE;
    }

    public void setTerabytes(long value) {
        bytes = value * BYTES_IN_TERABYTE;
    }

    public DataSize(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        if(bytes > BYTES_IN_TERABYTE) {
            return getTerabytes() + " (TB)";
        } else if (bytes > BYTES_IN_GIGABYTE) {
            return getGigabytes() + " (GB)";
        } else if (bytes > BYTES_IN_MEGABYTE) {
            return getMegabytes() + " (MB)";
        } else if (bytes > BYTES_IN_KILOBYTE) {
            return getKilobytes() + " (KB)";
        } else {
            return getBytes() + " (B)";
        }
    }

}
