package com.bYonath.main.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.bYonath.main.utils.Constants.*;

public class Menu implements Screen {

    // Finish this by 12/17/2025
    Skin skin;

    Stage stage;
    Table table;

    Widget buttonWidget;

    TextButton startButton;
    TextButton creditsButton;
    TextButton settingsButton;

    Image background;

    public Menu()
    {
        background = new Image(
            new Texture(Gdx.files.internal("GameBackground.png")));

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        stage = new Stage();

        stage.setDebugAll(true);

        Gdx.input.setInputProcessor(stage);

        table = new Table();

        startButton = new TextButton("Start", skin);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Start Button Clicked");
                GameShouldShow = true;
            }
        });

        creditsButton = new TextButton("Credits", skin);

        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Credits Button Clicked");
            }
        });

        settingsButton = new TextButton("Settings", skin);

        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Settings Button Pressed");
            }
        });

        table.setFillParent(true);

        stage.addActor(table);
        table.addActor(background);

        table.add(startButton)
            .width(100f)
            .pad(0.5f);
        table.row();

        table.add(settingsButton)
            .width(100f)
            .pad(0.5f);
        table.row();

        table.add(creditsButton)
            .width(100f)
            .pad(0.5f);
        table.row();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
