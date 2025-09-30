package com.bYonath.main.utils;

import com.badlogic.gdx.physics.box2d.*;

public class Box2DUtils
{

    public static Body createBody(World world)
    {
        Body body;

        BodyDef bodyDef = new BodyDef();

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(width/Constants.SCALE);

        return body;
    }

}
