package com.github.mmolimar.kafka.connect.fs.util;

import com.github.mmolimar.kafka.connect.fs.FsSourceTaskConfig;
import com.github.mmolimar.kafka.connect.fs.file.reader.FileReader;
import com.github.mmolimar.kafka.connect.fs.policy.Policy;
import org.apache.commons.lang.reflect.ConstructorUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.kafka.connect.source.SourceTaskContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

public class ReflectionUtils {

    public static FileReader makeReader(Class<? extends FileReader> clazz, FileSystem fs,
                                        Path path, Map<String, Object> config) throws Throwable {
        return make(clazz, fs, path, config);
    }

    public static Policy makePolicy(Class<? extends Policy> clazz, FsSourceTaskConfig conf) throws Throwable {
        return make(clazz, conf);
    }

    public static Policy makePolicy(Class<? extends Policy> clazz, FsSourceTaskConfig conf, SourceTaskContext sourceTaskContext) throws Throwable {
        return make(clazz, conf, sourceTaskContext);
    }

    private static <T> T make(Class<T> clazz, Object... args) throws Throwable {
        try {
            Class[] constClasses = new Class[args.length];
            int index = 0;
            for(Object arg : args){
                if(arg == null)
                    continue;
                Class theClazz = arg.getClass();
                String strVal = arg.toString();
                if(strVal.startsWith("EasyMock for")) {
                    String clazzName = arg.toString().substring(strVal.lastIndexOf(' ')+1, strVal.length());
                    theClazz = Class.forName(clazzName);
                }
                constClasses[index++] = theClazz;
            }

            Constructor constructor = ConstructorUtils.getMatchingAccessibleConstructor(clazz, constClasses);
            return (T) constructor.newInstance(args);
        } catch (IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
