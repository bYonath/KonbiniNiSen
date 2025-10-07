package com.bYonath.main.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.bYonath.main.utils.Constants.*;

public class Box2DUtils
{

    // Tile-map rendering boilerplate
    // this will parse through the tiled object layer
    // ill add in some special stuff to generate some other tiles
    // later on hopefully add a way to update tiles and save em!
    public static void parseTiledMapObjectLayer(World world, MapObjects objects)
    {
        for(MapObject object: objects)
        {
            Shape shape;

            if(object instanceof RectangleMapObject)
            {
                shape = createRectangle((RectangleMapObject) object);
            }
            else
            {
                continue;
            }

            Body body;

            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }

    private static PolygonShape createRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        float y_center_dist = (rectangle.y + rectangle.width * 0.5f)/PPM;
        float x_center_dist = (rectangle.x + rectangle.height * 0.5f)/PPM;
        // note: box2D generates shapes from the center
        Vector2 center = new Vector2(x_center_dist,
            y_center_dist);

        polygon.setAsBox(x_center_dist, y_center_dist, center, 0);

        return polygon;
    }


    // Box2D body generation code stuffs

    // Box type
    public static Body createBody(
        World world, boolean isStatic, boolean isFixed,
        boolean isSensor, int width, int height, Vector2 pos,
        short cBits, short mBits, short gIndx
    )
    {
        Body body;

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = isFixed;
        bodyDef.position.x = pos.x/PPM;
        bodyDef.position.y = pos.y/PPM;
        // Ngl, I love the ternary operator lol
        // :3
        bodyDef.type = isStatic ?
            BodyDef.BodyType.StaticBody :
            BodyDef.BodyType.DynamicBody;

        // Add the body into the world
        body = world.createBody(bodyDef);

        // Create the collision box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/SCALE/PPM, height/SCALE/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.maskBits = 0;
        fixtureDef.filter.categoryBits = 0;
        fixtureDef.filter.groupIndex = 0;

        body.createFixture(fixtureDef);

        return body;
    }

    // Circle type?

}
