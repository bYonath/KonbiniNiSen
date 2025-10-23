package com.bYonath.main.ecs.systems;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.PlayerComponent;
import com.bYonath.main.ecs.components.TextureComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

import static com.bYonath.main.utils.Constants. *;

public class PlayerMovementSystem extends IteratingSystem {

    public PlayerMovementSystem()
    {
        // System will apply only if all families are included
        super(Family.all(PlayerComponent.class, Box2DComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Could use entity.getComponent(component);
        // But its slow and im lazy, too much work ;-;
        PlayerComponent playerComponent = PLAYER_COMPONENT_MAPPER.get(entity);
        Box2DComponent box2DComponent = BOX2D_COMPONENT_MAPPER.get(entity);
        TextureComponent textureComponent = TEXTURE_COMPONENT_MAPPER.get(entity);

        OrthographicCamera camera = playerComponent.camera;
        Vector2 velocity = playerComponent.velocity;
        Body player = box2DComponent.body;
        Texture texture = textureComponent.image;
        SpriteBatch batch = textureComponent.batch;

        updatePos(velocity, camera, player, batch);
    }

    public void updatePos(Vector2 velocity, OrthographicCamera camera, Body player, SpriteBatch batch)
    {
        Vector3 cameraPos = camera.position;
        cameraPos.x = player.getPosition().x*PPM;
        cameraPos.y = player.getPosition().y*PPM;
        camera.position.set(cameraPos);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        velocity.x = 0;
        velocity.y = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            velocity.y = -PLAYER_SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            velocity.y = PLAYER_SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            velocity.x = PLAYER_SPEED;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            velocity.x = -PLAYER_SPEED;
        }

        player.setLinearVelocity(velocity);
    }


}
