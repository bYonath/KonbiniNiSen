package com.bYonath.main;

import com.bYonath.main.scenes.Menu;
import com.bYonath.main.scenes.StoreGame;
import com.bYonath.main.scenes.levels.FirstLevel;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.bYonath.main.utils.Constants.GameShouldShow;

// KONBINI-NI-SEN, **WELCOME!**
// Code contributors:
// - NG
// - R

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    StoreGame game;
    Menu menu;

    FirstLevel firstLevel;

    @Override
    public void create() {
        game = new StoreGame();

        firstLevel = new FirstLevel();

        menu = new Menu();

        this.setScreen(menu);
    }

    @Override
    public void render() {

        if(GameShouldShow)
        {
            this.setScreen(game);
        }
        else
        {
            this.setScreen(menu);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.L))
        {
            this.setScreen(firstLevel);
        }

        super.render();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
