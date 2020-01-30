package com.vibhor.game.states;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArenaState extends State{


    public ArenaState(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    protected void HandleInput() {

    }

    @Override
    public void update(float dt) {
        HandleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}

