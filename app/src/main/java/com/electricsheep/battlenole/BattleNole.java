package com.electricsheep.battlenole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/*
 * BattleNole is the main/launcher activity for Battle 'Nole.
 * It displays the title/artwork of the game and the main menu, providing the player with the
 * ability to:
 *   -Start a new game
 *   -Adjust settings
 *   -More later?
 *
 * Version 0.1.0 - July 11, 2015
 * Jason Souza
 */
public class BattleNole extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_nole);

        Intent placeShipsIntent = new Intent(this, ShipPlacement.class);
        startActivity(placeShipsIntent);

    }

}

