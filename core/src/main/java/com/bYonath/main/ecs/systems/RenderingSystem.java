package com.bYonath.main.ecs.systems;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.TextureComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static com.bYonath.main.utils.Constants.*;

public class RenderingSystem extends IteratingSystem
{

    public RenderingSystem()
    {
        super(Family.all(Box2DComponent.class, TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Box2DComponent box2DComponent = BOX2D_COMPONENT_MAPPER.get(entity);
        TextureComponent textureComponent = TEXTURE_COMPONENT_MAPPER.get(entity);

        Body body = box2DComponent.body;
        Texture texture = textureComponent.image;
        SpriteBatch batch = textureComponent.batch;

        batch.begin();

        updateTexture(batch, body, texture);

        batch.end();
    }

    public void updateTexture(SpriteBatch batch, Body body, Texture texture)
    {

        batch.draw(texture,
            body.getPosition().x * PPM - (texture.getWidth()/SCALE),
            body.getPosition().y * PPM - (texture.getHeight()/SCALE));
    }
}
