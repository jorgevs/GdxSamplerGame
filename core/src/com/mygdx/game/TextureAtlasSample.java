package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.SampleBase;
import common.SampleInfo;

public class TextureAtlasSample extends SampleBase {
    private static final Logger LOGGER = new Logger(TextureAtlasSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TextureAtlasSample.class);

    private static final String ATLAS = "images/atlasSample.atlas";

    // regions insdide our atlas
    private static final String BACKGROUND_BLUE = "background-blue";
    private static final String GREEN_CIRCLE = "circle-green";
    private static final String RED_CIRCLE = "circle-red";

    private static final String FONT = "fonts/oswald-32.fnt";

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private AssetManager assetManager;
    private TextureRegion backgroundBlue;
    private TextureRegion greenCircle;
    private TextureRegion redCircle;
    private BitmapFont font;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();

        // load assets
        assetManager.load(ATLAS, TextureAtlas.class);
        assetManager.load(FONT, BitmapFont.class);

        // blocks until all resources are loaded into memory
        assetManager.finishLoading();

        LOGGER.debug("diagnostics: " + assetManager.getDiagnostics());

        // get assets
        TextureAtlas atlas = assetManager.get(ATLAS);

        backgroundBlue = atlas.findRegion(BACKGROUND_BLUE);
        greenCircle = atlas.findRegion(GREEN_CIRCLE);
        redCircle = atlas.findRegion(RED_CIRCLE);
        font = assetManager.get(FONT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        batch.draw(backgroundBlue, 0, 0);
        batch.draw(greenCircle, 50, 50);
        batch.draw(redCircle, 200, 200);
        font.draw(batch, "TextureAtlasSample", 500, 50);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }
}
