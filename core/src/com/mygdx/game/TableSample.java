package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.CustomActor;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class TableSample extends SampleBase {
    private static final Logger LOGGER = new Logger(TableSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TableSample.class);

    private static final float WORLD_WIDTH = 1080f;   // World units
    private static final float WORLD_HEIGHT = 720f;   // World units


    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        viewport = new FitViewport(1080, 720);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        initUI();
    }

    private void initUI() {
        Table table = new Table();
        table.defaults().space(20);

        for (int i = 0; i < 6; i++) {
            CustomActor customActor = new CustomActor(new TextureRegion(texture));
            // need to set size, default width/height is 0
            customActor.setSize(180, 60);
            table.add(customActor);
            table.row();
        }

        table.center();
        table.setFillParent(true);
        table.pack();
        stage.addActor(table);
        stage.setDebugAll(true);
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
