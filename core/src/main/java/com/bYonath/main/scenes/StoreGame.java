package com.bYonath.main.scenes;

import static com.bYonath.main.utils.Constants.*;

import com.bYonath.main.ecs.AshleyEngine;
import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.game_systems.ContactSystem;
import com.bYonath.main.game_systems.TimeSystem;
import com.bYonath.main.utils.Box2DUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class StoreGame implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    public static OrthographicCamera camera;
    // ECS Engine
    private AshleyEngine engine;
    // Libgdx stuff
    private SpriteBatch batch;
    private Timer timer;
    private TimeSystem timeSystem;
    // Tileset Stuff
    private TiledMap BaseMap;
    // This is not truly the best way to implement this type of thing
    // lol
    public static OrthogonalTiledMapRenderer mapRenderer;

    public StoreGame()
    {
        world = new World(new Vector2(0,0), false);
        // forgor to set the contact listener
        world.setContactListener(new ContactSystem());

        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        // This map is moving with the player
        BaseMap = new TmxMapLoader().load("tilesets/MainLobbyWorldV1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(BaseMap);

        Box2DUtils.parseTiledMapObjectLayer(world,
            BaseMap.getLayers().get("CollisionLayer").getObjects());

        // Timer creation
        timer = new Timer();
        timeSystem = new TimeSystem(timer);
        timeSystem.start();

        timeSystem.scheduleRepeatTask(timeSystem.testTask ,0,5);


        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth()/SCALE,
            Gdx.graphics.getHeight()/SCALE);

        //mapRenderer.setView(camera);

        engine = new AshleyEngine(world, camera, batch);

        engine.createPlayer();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.15f,0.15f,0.15f,0.15f);

//        mapRenderer.render();
//        mapRenderer.setView(camera);

        world.step(1/60f, 6,2);

        engine.update(delta);

        //System.out.println(playerBdRepr.getPosition().x);

        debugRenderer.render(world, camera.combined.scl(PPM));
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,
            Gdx.graphics.getWidth()/SCALE,
            Gdx.graphics.getHeight()/SCALE);
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
