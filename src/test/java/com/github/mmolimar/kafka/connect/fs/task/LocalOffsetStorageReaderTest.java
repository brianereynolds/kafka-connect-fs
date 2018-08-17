package com.github.mmolimar.kafka.connect.fs.task;

import com.github.mmolimar.kafka.connect.fs.LocalOffsetStorageReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LocalOffsetStorageReaderTest {
    @Test
    public void basicTest() {
        String path = "/thepath";
        Long offset = 32L;
        LocalOffsetStorageReader localOffsetStorageReader = new LocalOffsetStorageReader();
        localOffsetStorageReader.setOffset(path, offset);

        Map<String, Object> partition = new HashMap<String, Object>() {{
            put("path", path);
        }};
        Map<String, Object> expected = new HashMap<String, Object>() {{
            put("offset", offset);
        }};

        Assert.assertEquals(expected, localOffsetStorageReader.offset(partition));
    }
}
