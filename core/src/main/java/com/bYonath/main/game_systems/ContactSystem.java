package com.bYonath.main.game_systems;

import com.badlogic.gdx.physics.box2d.*;
import static com.bYonath.main.utils.Constants.*;

public class ContactSystem implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        System.out.println("Contact A " + fixtureA);
        System.out.println("Contact B " + fixtureB);


        if(fixtureA.getBody().getUserData() != null && fixtureB.getBody().getUserData() != null) {
            door_contact = fixtureA.getBody().getUserData().equals("DOOR_AREA") ||
                fixtureB.getBody().getUserData().equals("DOOR_AREA");

            shop_contact = fixtureA.getBody().getUserData().equals("SHOP_AREA") ||
                fixtureB.getBody().getUserData().equals("SHOP_AREA");
        }
    }

    @Override
    public void endContact(Contact contact) {
        door_contact = false;
        shop_contact = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
