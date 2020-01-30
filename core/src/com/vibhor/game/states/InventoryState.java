package com.vibhor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vibhor.game.states.Playstate.*;

public class InventoryState extends State{
    private Texture Common;
    private Texture Uncommon;
    private Texture Rare;
    private Texture Epic;
    private Texture Legendary;
    private Texture Set;
    private Texture Mythical;
    private Texture Ancient;
    private Texture Titan;
    private Texture Forbidden;
    private Texture Cosmetic;

    public InventoryState(GameStateManager gsm) {
        super(gsm);
        //inventory
        Common = new Texture("Common.png");
        Uncommon = new Texture("Uncommon.png");
        Rare = new Texture("Rare.png");
        Epic = new Texture("Epic.png");
        Legendary = new Texture("Legendary.png");
        Set = new Texture("Set.png");
        Mythical = new Texture("Mythical.png");
        Ancient = new Texture("Ancient.png");
        Titan = new Texture("Titan.png");
        Forbidden = new Texture("Forbidden.png");
        Cosmetic = new Texture("Cosmetic.png");



    }


    @Override
    protected void HandleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            gsm.set(new Playstate(gsm));


        }

    }

    @Override
    public void update(float dt) {
        HandleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
sb.begin();

sb.end();

    }

    @Override
    public void dispose() {

    }
}