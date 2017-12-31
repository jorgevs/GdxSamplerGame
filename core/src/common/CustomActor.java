package common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Logger;

public class CustomActor extends Actor {
    private static final Logger LOGGER = new Logger(CustomActor.class.getName(), Logger.DEBUG);

    private final TextureRegion textureRegion;


    public CustomActor(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //LOGGER.debug("act on actor: " + this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color);

        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
