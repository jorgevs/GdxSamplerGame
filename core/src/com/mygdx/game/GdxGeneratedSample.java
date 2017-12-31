package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import common.SampleBase;
import common.SampleInfo;
import utils.GdxUtils;

public class GdxGeneratedSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxGeneratedSample.class);

    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
