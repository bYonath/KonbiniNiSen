package com.bYonath.main.game_systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseMenu
{

    private Stage stage;

    private Skin skin;

    private Table table;

    private TextButton menuButton;

    private TextButton firstLevelButton;

    public PauseMenu(OrthographicCamera camera)
    {
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        table = new Table();

        table.setFillParent(true);

        menuButton = new TextButton("Menu", skin);
        firstLevelButton = new TextButton("LVL 1", skin);

        stage.setDebugAll(true);

        stage.addActor(table);

        table.add(menuButton).width(100).pad(0.5f);
        table.row();
        table.add(firstLevelButton).width(100).pad(0.5f);



    }

    public void runStage()
    {
        stage.act();
        stage.draw();
    }

    public void hideMenu()
    {
        table.setVisible(false);
    }

    public void showMenu()
    {
        table.setVisible(true);
    }

}
