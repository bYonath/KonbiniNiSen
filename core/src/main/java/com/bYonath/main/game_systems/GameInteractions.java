package com.bYonath.main.game_systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.bYonath.main.utils.Constants.*;

public class GameInteractions
{

    public void processInteractions()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E))
        {
            if(door_contact)
            {
                System.out.println("door menu activated");
                MenuShouldShow = !MenuShouldShow;
            }
            if(shop_contact)
            {
                System.out.println("shop in session");
                MenuShouldShow = !MenuShouldShow;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            System.out.println("Menu Activated");
            // Toggle boolean lol
            MenuShouldShow = !MenuShouldShow;
            GameShouldShow = !GameShouldShow;
//            System.out.println(MenuShouldShow);
        }
    }

}
