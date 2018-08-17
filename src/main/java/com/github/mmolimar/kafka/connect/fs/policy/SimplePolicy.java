package com.github.mmolimar.kafka.connect.fs.policy;

import com.github.mmolimar.kafka.connect.fs.FsSourceTaskConfig;
import org.apache.kafka.connect.source.SourceTaskContext;

import java.io.IOException;
import java.util.Map;

public class SimplePolicy extends AbstractPolicy {

    public SimplePolicy(FsSourceTaskConfig conf) throws IOException {
        super(conf);
    }

    public SimplePolicy(FsSourceTaskConfig conf, SourceTaskContext sourceTaskContext) throws IOException {
        super(conf, sourceTaskContext);
    }

    @Override
    protected void configPolicy(Map<String, Object> customConfigs) {

    }

    @Override
    protected boolean isPolicyCompleted() {
        return getExecutions() > 0;
    }

}
