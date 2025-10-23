package com.bYonath.main.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {

    public Texture image;
    public SpriteBatch batch;

    @Override
    public void reset() {

    }
}
