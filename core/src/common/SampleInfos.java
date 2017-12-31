package common;

import com.mygdx.game.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            GdxGeneratedSample.SAMPLE_INFO,
            ApplicationListenerSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewportSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO,
            BitmapFontSample.SAMPLE_INFO,
            PoolingSample.SAMPLE_INFO,
            AssetManagerSample.SAMPLE_INFO,
            TextureAtlasSample.SAMPLE_INFO,
            CustomActorSample.SAMPLE_INFO,
            ActionsSample.SAMPLE_INFO,
            TableSample.SAMPLE_INFO,
            SkinSample.SAMPLE_INFO
    );

    private SampleInfos() {
    }

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<String>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name) {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("Name argument is required.");
        }
        SampleInfo ret = null;

        for (SampleInfo info : ALL) {
            if (info.getName().equals(name)) {
                ret = info;
                break;
            }
        }

        if (null == ret) {
            throw new IllegalArgumentException("Could not find sample with name: " + name);
        }

        return ret;
    }

}
