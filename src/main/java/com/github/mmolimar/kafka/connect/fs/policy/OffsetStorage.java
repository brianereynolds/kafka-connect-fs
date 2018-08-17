package com.github.mmolimar.kafka.connect.fs.policy;

public enum OffsetStorage {
    CONTEXT("context"),
    LOCAL("local");

    private String val;

    OffsetStorage(String val) {
        this.val = val;
    }

    public String getValue() {
        return this.val;
    }

    public static OffsetStorage fromString(String val) {
        if(val == null)
            return null;

        for (OffsetStorage os : OffsetStorage.values()) {
            if (os.val.equalsIgnoreCase(val)) {
                return os;
            }
        }
        return null;
    }

}
