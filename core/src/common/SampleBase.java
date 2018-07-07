package common;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;

public class SampleBase implements ApplicationListener, InputProcessor {

    @Override
    public void create() {
        // used to initialize game and load resources
    }

    @Override
    public void resize(int i, int i1) {
        // used to set a new screen size
    }

    @Override
    public void render() {
        // used to update and render the game elements (called 60 times per second)
    }

    @Override
    public void pause() {
        // used to save game state when it losses focus, which does not involve the actual
        // game play being paused unless the developer wants it to pause
    }

    @Override
    public void resume() {
        // used to handle the game coming back from being paused and restores game state
    }

    @Override
    public void dispose() {
        // used to free resources and cleanup
    }

    @Override
    public boolean keyDown(int i) {
        // Called when a key was pressed
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        // Called when a key was released
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        // Called when the screen was touched or a mouse button was pressed
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        // Called when the screen was touched or a mouse button was pressed
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        // Called when a finger was lifted or a mouse button was released
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        // Called when a finger or the mouse was dragged
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        // Called when the mouse was moved without any buttons being pressed
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        // Called when the mouse wheel was scrolled
        return false;
    }
}
