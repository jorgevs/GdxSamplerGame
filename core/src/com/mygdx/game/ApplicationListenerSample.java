package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class ApplicationListenerSample implements ApplicationListener {
    private static final Logger LOGGER = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);

    private boolean renderInterrupted = true;

    @Override
    public void create() {
        // used to initialize game and load resources
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        LOGGER.debug("create()");
    }

    @Override
    public void resize(int width, int height) {
        // used to set a new screen size
        LOGGER.debug(String.format("resize(): width: %s, height: %s", width, height));
    }

    @Override
    public void render() {
        // used to update and render the game elements (called 60 times per second)
        if(renderInterrupted) {
            LOGGER.debug("render()");
            renderInterrupted = false;
        }
    }

    @Override
    public void pause() {
        // used to save game state when it losses focus, which does not involve the actual
        // game play being paused unless the developer wants it to pause
        LOGGER.debug("pause()");
        renderInterrupted = true;
    }

    @Override
    public void resume() {
        // used to handle the game coming back from being paused and restores game state
        LOGGER.debug("resume()");
        renderInterrupted = true;
    }

    @Override
    public void dispose() {
        // used to free resources and cleanup
        LOGGER.debug("dispose()");
    }
}
