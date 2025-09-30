package com.bYonath.main.scenes;

import static com.bYonath.main.utils.Constants.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Game implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    public Game()
    {
        world = new World(new Vector2(0,0), false);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth()/ SCALE,
            Gdx.graphics.getHeight()/ SCALE);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        debugRenderer.render(world, camera.combined.scl(PPM));
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,Gdx.graphics.getWidth()/SCALE, Gdx.graphics.getHeight()/SCALE);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
