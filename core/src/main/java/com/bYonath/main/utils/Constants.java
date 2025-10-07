package com.bYonath.main.utils;

import com.bYonath.main.ecs.components.Box2DComponent;
import com.bYonath.main.ecs.components.PlayerComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class Constants
{
    // Scale Factor stuff
    public static final float SCALE = 2f;
    public static final float PPM = 32f;

    // Player info
    public static final float PLAYER_SPEED = 5f;
    public static final String PLAYER_TAG = "PLAYER";
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 32;
    // Collision info
    public static final short PLAYER_CBIT = 0;
    public static final short PLAYER_MBIT = 0;
    public static final short PLAYER_GINDX = 0;

    // Component mappers
    public static final ComponentMapper<Box2DComponent> BOX2D_COMPONENT_MAPPER
        = ComponentMapper.getFor(Box2DComponent.class);
    public static final ComponentMapper<PlayerComponent> PLAYER_COMPONENT_MAPPER
        = ComponentMapper.getFor(PlayerComponent.class);
}
