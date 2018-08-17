package com.github.mmolimar.kafka.connect.fs;

import org.apache.kafka.connect.storage.OffsetStorageReader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LocalOffsetStorageReader implements OffsetStorageReader {

    private Map<String, Map<String, Object>> storageMap = new HashMap<>();

    public void setOffset(String path, Long offset) {
        Map<String, Object> offsetMap = new HashMap<String, Object>() {{
            put("offset", offset);
        }};
        storageMap.put(path, offsetMap);
    }

    @Override
    public <T> Map<String, Object> offset(Map<String, T> map) {
        return storageMap.get(map.get("path"));
    }

    @Override
    public <T> Map<Map<String, T>, Map<String, Object>> offsets(Collection<Map<String, T>> collection) {
        return null;
    }
}
