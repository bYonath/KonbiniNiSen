package com.bYonath.main.utils;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.PlayerComponent;
import com.bYonath.main.ecs.components.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Constants
{
    // Scale Factor stuff
    public static final float SCALE = 2f;
    public static final float PPM = 16f;

    // Player info
    public static final float PLAYER_SPEED = 5f;
    public static final String PLAYER_TAG = "PLAYER";
    public static final int PLAYER_WIDTH = 16;
    public static final int PLAYER_HEIGHT = 16;

    // Collision info
    public static final short PLAYER_CBIT = 2;
    public static final short PLAYER_MBIT = 2;
    public static final short PLAYER_GINDX = 2;

    // Update Intervals
    public static final int UPDATE_CYCLE_MINUTES = 10;
    public static final int SECONDS_IN_MINUTES = 60;
    public static final float UPDATE_CYCLE_SECONDS = UPDATE_CYCLE_MINUTES * SECONDS_IN_MINUTES;

    // Component mappers
    public static final ComponentMapper<Box2DComponent> BOX2D_COMPONENT_MAPPER
        = ComponentMapper.getFor(Box2DComponent.class);
    public static final ComponentMapper<PlayerComponent> PLAYER_COMPONENT_MAPPER
        = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE_COMPONENT_MAPPER
        = ComponentMapper.getFor(TextureComponent.class);

    public static Body playerBdRepr = null;

    public static boolean door_contact = false;
    public static boolean shop_contact = false;

    public static boolean MenuShouldShow = false;
    public static boolean GameShouldShow = false;
}
