package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class BitmapFontSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(BitmapFontSample.class);

    private static final float WIDTH = 1080f;
    private static final float HEIGHT = 720f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont effectFont;
    private BitmapFont wrappedFont;
    private BitmapFont uiFont;
    private BitmapFont markupFont;
    private GlyphLayout glyphLayout;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        effectFont = new BitmapFont(Gdx.files.internal("fonts/effect_font_32.fnt"));
        wrappedFont = new BitmapFont(Gdx.files.internal("fonts/effect_font_32.fnt"));
        uiFont = new BitmapFont(Gdx.files.internal("fonts/ui_font_32.fnt"));
        markupFont = new BitmapFont(Gdx.files.internal("fonts/ui_font_32.fnt"));
        markupFont.getData().markupEnabled = true;
        glyphLayout = new GlyphLayout();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        // rendering
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    public void draw() {
        String text1 = "USING BITMAP FONT";
        effectFont.draw(batch, text1, 20, HEIGHT);

        String text2 = "USING BITMAP WRAPPED FONT";
        effectFont.draw(batch, text2, 20, HEIGHT-200, 100, 0, true);

        String text3 = "BITMAP FONTS ARE COOL!";
        glyphLayout.setText(uiFont, text3);
        uiFont.draw(batch, text3, (WIDTH - glyphLayout.width) / 2f, (HEIGHT - glyphLayout.height) / 2f);

        String text4 = "[#FF0000]USING [GREEN]BITMAP [YELLOW]WRAPPED [BLUE]FONT";
        markupFont.draw(batch, text4, 100, HEIGHT-600);
    }

    @Override
    public void dispose() {
        batch.dispose();
        effectFont.dispose();
        uiFont.dispose();
    }

}
