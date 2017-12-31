package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.CustomActor;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class SkinSample extends SampleBase {
    private static final Logger LOGGER = new Logger(SkinSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SkinSample.class);

    private static final String UI_SKIN = "ui/uiskin.json";

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;


    private Viewport viewport;
    private Stage stage;
    private AssetManager assetManager;
    private Skin skin;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        assetManager.load(UI_SKIN, Skin.class);
        assetManager.finishLoading();

        viewport = new FitViewport(1080, 720);
        stage = new Stage(viewport);

        skin = assetManager.get(UI_SKIN);

        Gdx.input.setInputProcessor(stage);

        initUI();
    }

    private void initUI(){
        Table table = new Table();
        table.defaults().pad(20);

        for (int i = 0; i < 4; i++) {
            TextButton textButton = new TextButton("Button " + i, skin);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    LOGGER.debug("event: " + changeEvent + " actor: " + actor);
                }
            });
            table.add(textButton);
        }

        table.row();

        for (int i = 0; i < 2; i++) {
            CheckBox checkBox = new CheckBox("Checkbox " + i, skin, "custom");
            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    LOGGER.debug("event: " + changeEvent + " actor: " + actor);
                }
            });
            table.add(checkBox);
        }

        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
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