package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.CustomActor;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class CustomActorSample extends SampleBase {
    private static final Logger LOGGER = new Logger(CustomActorSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(CustomActorSample.class);

    private static final float WORLD_WIDTH = 1080f;  // World units
    private static final float WORLD_HEIGHT = 720f;  // World units


    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        CustomActor customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160, 80);
        customActor.setPosition((WORLD_WIDTH - customActor.getWidth()) / 2, (WORLD_HEIGHT - customActor.getHeight()) / 2);

        customActor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LOGGER.debug("CustomActor clicked event: " + event + " x: " + x + " y: " + y);
            }
        });

        stage.addActor(customActor);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        // no need to call setProjectionMatrix, begin/end. Everything is handled internally.
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
