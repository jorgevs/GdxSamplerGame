package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;

public class GdxReflectionSample extends ApplicationAdapter {
	private static final Logger LOGGER = new Logger(GdxReflectionSample.class.getName(), Logger.DEBUG);

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;
	
	@Override
	public void create () {
		// used to initialize game and load resources
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 720, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

		debugReflection(GdxReflectionSample.class);
	}

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0, 0, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		draw();
		batch.end();
	}

	private void draw(){
        // mouse touch x/y
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

        font.draw(batch, "x: " + mouseX + " y: " + mouseY, 20, 720-20);
        font.draw(batch, leftPressed ? "Left button pressed" : "Left button not pressed", 20, 720-50);
        font.draw(batch, rightPressed ? "Right button pressed" : "Right button not pressed", 20, 720-80);

        // keyboard keys
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        font.draw(batch, wPressed ? "W is pressed" : "W is not pressed", 20, 720-110);
        font.draw(batch, sPressed ? "S is pressed" : "S is not pressed", 20, 720-140);

    }

	@Override
	public void dispose () {
		batch.dispose();
        font.dispose();
	}

	private static void debugReflection(Class<?> clazz){
		Field[] fields = ClassReflection.getDeclaredFields(clazz);
		Method[] methods = ClassReflection.getDeclaredMethods(clazz);

		LOGGER.debug("=== debug reflection class: " + clazz.getName() + " ===");

		LOGGER.debug("fields-count: " + fields.length);
		for (Field field : fields){
			LOGGER.debug("name: " + field.getName() + " type: " + field.getType());
		}

		LOGGER.debug("methods-count: " + methods.length);
		for (Method method : methods){
			LOGGER.debug("name: " + method.getName() + " parameterTypes: " + Arrays.asList(method.getParameterTypes()));
		}
	}
}
