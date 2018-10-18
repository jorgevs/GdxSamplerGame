package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

import java.util.Arrays;

public class GdxReflectionSample extends SampleBase {
    private static final Logger LOGGER = new Logger(GdxReflectionSample.class.getName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxReflectionSample.class);

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        // used to initialize game and load resources
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        debugReflection(GdxReflectionSample.class);
    }

    @Override
    public void resize(int width, int height) {
        // Nothing here
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        font.draw(batch, "Reflection sample", 20, 720 - 20);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    private static void debugReflection(Class<?> clazz) {
        Field[] fields = ClassReflection.getDeclaredFields(clazz);
        Method[] methods = ClassReflection.getDeclaredMethods(clazz);

        LOGGER.debug("=== debug reflection class: " + clazz.getName() + " ===");

        LOGGER.debug("fields-count: " + fields.length);
        for (Field field : fields) {
            LOGGER.debug("name: " + field.getName() + " type: " + field.getType());
        }

        LOGGER.debug("methods-count: " + methods.length);
        for (Method method : methods) {
            LOGGER.debug("name: " + method.getName() + " parameterTypes: " + Arrays.asList(method.getParameterTypes()));
        }
    }
}
