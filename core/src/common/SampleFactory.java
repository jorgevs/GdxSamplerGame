package common;

import com.badlogic.gdx.utils.reflect.ClassReflection;

public class SampleFactory {

    public static SampleBase newSample(String name) {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("name param is required.");
        }

        SampleInfo info = SampleInfos.find(name);
        try {
            return (SampleBase) ClassReflection.newInstance(info.getClazz());
        } catch (Exception e) {
            throw new RuntimeException("Cannot create sample with name: " + name);
        }
    }

    private SampleFactory() {
    }
}
