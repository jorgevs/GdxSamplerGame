package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import common.SampleBase;
import common.SampleInfo;

public class PoolingSample extends SampleBase {
    private static final Logger LOGGER = new Logger(PoolingSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(PoolingSample.class);

    private static final float BULLET_ALIVE_TIME = 3f;
    private static final float BULLET_SPAWN_TIME = 1f;

    private Array<Bullet> bullets = new Array<Bullet>();
    private float timer;

    private final Pool<Bullet> bulletPool = Pools.get(Bullet.class, 15);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        timer += delta;

        if (timer > BULLET_SPAWN_TIME){
            timer = 0;
            Bullet bullet = bulletPool.obtain();
            bullets.add(bullet);

            LOGGER.debug("Alive bullets: " + bullets.size);
        }
        for (Bullet bullet : bullets){
            bullet.update(delta);

            if(!bullet.alive){
                bullets.removeValue(bullet, true);
                bulletPool.free(bullet);

                LOGGER.debug("After free alive bullets: " + bullets.size);
            }
        }
    }

    public static class Bullet implements Pool.Poolable {
        boolean alive = true;
        float timer;

        public Bullet(){
        }

        public void update(float delta){
            timer += delta;

            if(alive && timer > BULLET_ALIVE_TIME){
                alive = false;
            }
        }

        @Override
        public void reset() {
            alive = true;
            timer = 0;
        }
    }
}
