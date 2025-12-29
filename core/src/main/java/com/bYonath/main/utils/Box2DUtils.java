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

    public static Vector2 playerSpawn = new Vector2();
    // Tile-map rendering boilerplate
    // this will parse through the tiled object layer
    // ill add in some special stuff to generate some other tiles
    // later on hopefully add a way to update tiles and save em!
    public static void parseTiledMapObjectLayer(World world, MapObjects objects)
    {
        boolean isSensor = false;
        boolean isPlayerSpawnPoint = false;

        for(MapObject object: objects)
        {
            Shape shape;

            if(object.getName() != null && object.getName().equals("player_spawn_tile_obj"))
            {
                System.out.println("player spawn object found");

                // May wanna use the shape generator to create the body
                // for the player lol
                //shape = createRectangle((RectangleMapObject) object, true);

                isPlayerSpawnPoint = true;

                loadPlayer(world, (RectangleMapObject) object);

                continue;
            }
            // Shop event area code
            else if(object.getName() != null && object.getName().equals("shop_event_area"))
            {
                System.out.println("Shop event area found");

                shape = createRectangle((RectangleMapObject) object, false);

                isSensor = true;
            }
            // Door event area code
            else if(object.getName() != null && object.getName().equals("door_event_area"))
            {
                System.out.println("Door event area found");

                shape = createRectangle((RectangleMapObject) object, false);

                isSensor = true;
            }

            // For basically everything else
            else if(object instanceof RectangleMapObject)
            {
                shape = createRectangle((RectangleMapObject) object, false);

                isSensor = false;
            }
            else
            {
                continue;
            }

            Body body;

            // Create the bodydef
            BodyDef bdef = new BodyDef();

            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);

            // MORE CONFIGURATIONS!!!
            if(object.getName() != null) {
                switch (object.getName()) {
                    case "door_event_area":
                        body.setUserData("DOOR_AREA");
                        break;
                    case "shop_event_area":
                        body.setUserData("SHOP_AREA");
                        break;
                    default:
                        System.out.println("Placeholder for something else");
                        break;
                }
            }

            // Fixtures and collisions here
            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.density = 1.0f;
            fdef.filter.categoryBits = (short) 4;
            fdef.filter.groupIndex = 0;
            fdef.filter.maskBits = PLAYER_CBIT;
            fdef.isSensor = isSensor;

            body.createFixture(fdef);
            shape.dispose();

//            if(!isPlayerSpawnPoint) {
//                bdef.type = BodyDef.BodyType.StaticBody;
//                body = world.createBody(bdef);
//                // Fixtures and collisions here
//                FixtureDef fdef = new FixtureDef();
//                fdef.shape = shape;
//                fdef.density = 1.0f;
//                fdef.filter.categoryBits = (short) 4;
//                fdef.filter.groupIndex = 0;
//                fdef.filter.maskBits = PLAYER_CBIT;
//                fdef.isSensor = isSensor;
//                body.createFixture(fdef);
//                shape.dispose();
//            }
//            else
//            {
//                bdef.type = BodyDef.BodyType.DynamicBody;
//                bdef.position.set(playerSpawn);
//
//                body = world.createBody(bdef);
//                // Fixtures and collisions here
//                FixtureDef fdef = new FixtureDef();
//                fdef.shape = shape;
//                fdef.density = 1.0f;
//                fdef.filter.categoryBits = PLAYER_CBIT;
//                fdef.filter.groupIndex = 0;
//                fdef.filter.maskBits = (short) 4;
//                body.createFixture(fdef);
//                shape.dispose();
//
//                isPlayerSpawnPoint = false;
//                playerBdRepr = body;
//            }
        }
    }

    private static PolygonShape createRectangle(RectangleMapObject rectangleObject, boolean isPlayer) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        float y_center_dist = (rectangle.y + rectangle.height * 0.5f)/PPM;
        float x_center_dist = (rectangle.x + rectangle.width * 0.5f)/PPM;
        // note: box2D generates shapes from the center
        Vector2 center = new Vector2(x_center_dist,
            y_center_dist);

        float hx = rectangle.width * 0.5f / PPM;
        float hy = rectangle.height * 0.5f / PPM;

        if(isPlayer)
        {
            playerSpawn.set(hx,hy);
        }

        polygon.setAsBox(hx, hy, center, 0);

//        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / Constants.PPM,
//            (rectangle.y + rectangle.height * 0.5f ) / Constants.PPM);
//        polygon.setAsBox(rectangle.width * 0.5f /Constants.PPM,
//            rectangle.height * 0.5f / Constants.PPM,
//            size,
//            0.0f);

        return polygon;
    }


    // Box2D body generation code stuffs

    // Box type
    public static Body createBody(
        World world, boolean isStatic, boolean isFixed,
        boolean isSensor, int width, int height, Vector2 pos,
        short cBits, short mBits, short gIndx, String tag
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

        body.setUserData(tag);

        // Create the collision box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/SCALE/PPM, height/SCALE/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits; // is a
        fixtureDef.filter.maskBits = mBits; // collides with
        fixtureDef.filter.groupIndex = gIndx;

        body.createFixture(fixtureDef);

        return body;
    }

    // Circle type?

    // Special TMR Methods
    public static void loadPlayer(World world, RectangleMapObject startingLocation)
    {
        Rectangle startingRect = startingLocation.getRectangle();

        float startX = (startingRect.x + startingRect.width * 0.5f);
        float startY = (startingRect.y + startingRect.height * 0.5f);

        Vector2 startPos = new Vector2(startX,startY);

        playerBdRepr = createBody(world, false,
            true, false, PLAYER_WIDTH,PLAYER_HEIGHT,
            startPos, PLAYER_CBIT, (short)4, PLAYER_GINDX, PLAYER_TAG);
    }

}
