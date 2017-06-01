package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ApplicationListenerSample;

public class DesktopLauncherApplicationListener {

    public static void main(String[] argv) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "ApplicationListenerSample";
        config.useGL30 = false;
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new ApplicationListenerSample(), config);
    }
}
