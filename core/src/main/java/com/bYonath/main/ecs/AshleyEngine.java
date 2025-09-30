package com.bYonath.main.ecs;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public class AshleyEngine extends PooledEngine
{
    World world;
    OrthographicCamera camera;

    public AshleyEngine(World world, OrthographicCamera camera)
    {
        super();
        this.world = world;
    }

    public void createPlayer()
    {
        // Create the entity for use in the ECS
        // Since this class extends Engine, we can call
        // .createEntity here
        Entity player = this.createEntity();

        // Begin adding stuff to the entity in this case
        // components
        // shorthand
        //player.add(this.createComponent(PlayerComponent.class));

        PlayerComponent playerComponent = this.createComponent(PlayerComponent.class);
        // add a box2d component
        Box2DComponent box2DComponent = this.createComponent(Box2DComponent.class);
        // set the stuff for the things
        box2DComponent.body = null;


    }
}
