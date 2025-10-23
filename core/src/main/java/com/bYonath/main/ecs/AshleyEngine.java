package com.bYonath.main.ecs;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.PlayerComponent;
import com.bYonath.main.ecs.components.TextureComponent;
import com.bYonath.main.ecs.systems.PlayerMovementSystem;
import com.bYonath.main.ecs.systems.RenderingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.bYonath.main.utils.Constants.*;
import static com.bYonath.main.utils.Box2DUtils.*;

public class AshleyEngine extends PooledEngine
{
    World world;
    OrthographicCamera camera;
    SpriteBatch batch;

    Texture texture;

    public AshleyEngine(World world, OrthographicCamera camera, SpriteBatch batch)
    {
        super();
        this.world = world;
        this.camera = camera;
        this.batch = batch;

        // Create player asset stuff
        texture = new Texture(Gdx.files.internal("PlayerPlaceholderSpriteKBNS.png"));

        this.addSystem(new PlayerMovementSystem());
        this.addSystem(new RenderingSystem());
        createPlayer();
    }

    public void createPlayer()
    {
        //System.out.println("Player Created");

        // Create the entity for use in the ECS
        // Since this class extends Engine, we can call
        // .createEntity here
        Entity player = this.createEntity();

        // Begin adding stuff to the entity in this case
        // components
        // shorthand
        //player.add(this.createComponent(PlayerComponent.class));

        PlayerComponent playerComponent = this.createComponent(PlayerComponent.class);
        // need to add a speed and cam to the player component
        playerComponent.camera = camera;
        playerComponent.speed = PLAYER_SPEED;
        // add a box2d component
        Box2DComponent box2DComponent = this.createComponent(Box2DComponent.class);

        TextureComponent textureComponent = this.createComponent(TextureComponent.class);
        textureComponent.image = texture;
        textureComponent.batch = batch;

        // set the stuff for the things
        Body playerBody = box2DComponent.body = createBody(world, false,
            true, false, PLAYER_WIDTH,PLAYER_HEIGHT,
            new Vector2(0,0), PLAYER_CBIT,PLAYER_MBIT,PLAYER_GINDX);

        // set starting camera position
        playerComponent.camera.position.set(
            playerBody.getPosition().x*PPM,
            playerBody.getPosition().y*PPM, 0);
        // update camera
        playerComponent.camera.update();

        // add stuff to the entity
        player.add(playerComponent);
        player.add(box2DComponent);
        player.add(textureComponent);

        this.addEntity(player);

    }
}
