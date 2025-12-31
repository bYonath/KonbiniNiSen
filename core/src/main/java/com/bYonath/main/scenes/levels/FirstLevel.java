package com.bYonath.main.scenes.levels;

import com.bYonath.main.ecs.AshleyEngine;
import com.bYonath.main.game_systems.ContactSystem;
import com.bYonath.main.game_systems.GameInteractions;
import com.bYonath.main.game_systems.PauseMenu;
import com.bYonath.main.game_systems.TimeSystem;
import com.bYonath.main.scenes.StoreGame;
import com.bYonath.main.utils.Box2DUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import static com.bYonath.main.scenes.StoreGame.camera;
import static com.bYonath.main.scenes.StoreGame.mapRenderer;
import static com.bYonath.main.utils.Constants.*;
import static com.bYonath.main.utils.Constants.SCALE;

public class FirstLevel implements Screen
{
    private World world;
    private Box2DDebugRenderer debugRenderer;
    // ECS Engine
    private AshleyEngine engine;
    // Player interactions
    private GameInteractions interactions;
    // Pause Menu
    private PauseMenu pauseMenu;
    // Libgdx stuff
    private SpriteBatch batch;
    private Timer timer;
    private TimeSystem timeSystem;
    // Tileset Stuff
    private TiledMap Level_1;

    public FirstLevel()
    {
        world = new World(new Vector2(0,0), false);
        // forgor to set the contact listener
        world.setContactListener(new ContactSystem());

        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        // This map is moving with the player
        Level_1 = new TmxMapLoader().load("tilesets/LevelLoadTest.tmx");
        mapRenderer.setMap(Level_1);

        Box2DUtils.parseTiledMapObjectLayer(world,
            Level_1.getLayers().get("CollisionLayer").getObjects());

        // Timer creation
        timer = new Timer();
        timeSystem = new TimeSystem(timer);
        timeSystem.start();

        timeSystem.scheduleRepeatTask(timeSystem.testTask ,0,5);

        //mapRenderer.setView(camera);

        engine = new AshleyEngine(world, camera, batch);

        engine.createPlayer();

        interactions = new GameInteractions();
        pauseMenu = new PauseMenu(camera);

    }

    @Override
    public void show() {
        mapRenderer.setMap(Level_1);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.15f,0.15f,0.15f,0.15f);

//        mapRenderer.render();
//        mapRenderer.setView(camera);

        world.step(1/60f, 6,2);



        engine.update(delta);

        //System.out.println(playerBdRepr.getPosition().x);

        interactions.processInteractions();

        if(MenuShouldShow)
        {
            pauseMenu.showMenu();
        }
        else
        {
            pauseMenu.hideMenu();
        }

        pauseMenu.runStage();

        debugRenderer.render(world, camera.combined.scl(PPM));

        //System.out.println(door_contact);
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
