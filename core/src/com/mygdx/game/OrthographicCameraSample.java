package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class OrthographicCameraSample extends SampleBase {
    private static final Logger LOGGER = new Logger(OrthographicCameraSample.class.getName(), Logger.DEBUG);
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(OrthographicCameraSample.class);

    private static final float WORLD_WIDTH = 10.8f; // world units : (1280 - 200) / 100 = 10.8
    private static final float WORLD_HEIGHT = 7.2f; // world units : (720) / 100 = 7.2

    private static final float CAMERA_SPEED = 2.0f; // world units
    private static final float CAMERA_ZOOM_SPEED = 2.0f; // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        queryInput();

        GdxUtils.clearScreen();

        // rendering
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void queryInput() {
        // deltaTime is the time passed between two frames
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x = camera.position.x - (CAMERA_SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x = camera.position.x + (CAMERA_SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y = camera.position.y + (CAMERA_SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y = camera.position.y - (CAMERA_SPEED * deltaTime);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)) {
            camera.zoom = camera.zoom - (CAMERA_ZOOM_SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)) {
            camera.zoom = camera.zoom + (CAMERA_ZOOM_SPEED * deltaTime);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            LOGGER.debug("camera position: " + camera.position);
            LOGGER.debug("camera zoom: " + camera.zoom);
        }

        camera.update();
    }

    private void draw() {
        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
