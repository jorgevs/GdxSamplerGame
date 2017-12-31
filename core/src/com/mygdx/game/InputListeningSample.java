package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

import java.util.ArrayList;
import java.util.List;

public class InputListeningSample extends SampleBase {
    private static final Logger LOGGER = new Logger(InputListeningSample.class.getName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputListeningSample.class);

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;

    private final int MAX_MESSAGE_COUNT = 15;
    private final List<String> messages = new ArrayList<String>();


    private void addMessage(String message) {
        messages.add(message);

        if (messages.size() > MAX_MESSAGE_COUNT) {
            messages.remove(0);
        }
    }

    @Override
    public boolean keyDown(int i) {
        String message = "Keydown keycode: " + i;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        String message = "keyUp keycode: " + i;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        String message = "keyTyped keycode: " + c;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        String message = "touchDown screenX: " + i + " screenY" + i1;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        String message = "touchUp screenX: " + i + " screenY" + i1;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        String message = "touchDragged screenX: " + i + " screenY" + i1;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        String message = "mouseMoved screenX: " + i + " screenY" + i1;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        String message = "scrolled amount: " + i;
        LOGGER.debug(message);
        addMessage(message);
        return true;
    }

    @Override
    public void create() {
        // used to initialize game and load resources
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        for (int i = 0; i < messages.size(); i++) {
            font.draw(batch, messages.get(i), 20, 720 - 40 * (i + 1));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
