package com.bYonath.main.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class PlayerComponent implements Component, Pool.Poolable
{

    // I'll add the camera here
    public OrthographicCamera camera;
    public float speed;
    public Vector2 velocity = new Vector2(0,0);

    @Override
    public void reset() {

    }
}
