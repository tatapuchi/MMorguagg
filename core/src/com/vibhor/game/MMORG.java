package com.vibhor.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vibhor.game.states.GameStateManager;
import com.vibhor.game.states.MenuState;

public class MMORG extends ApplicationAdapter {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    public static final String TITLE = "Bedlam";
    private GameStateManager gsm;
    private SpriteBatch batch;


    //private Music gamemusic;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        gsm.push(new MenuState(gsm));


        //gamemusic = Gdx.audio.newMusic(Gdx.files.internal("Megalovania.mp3"));

        //gamemusic.setLooping(true);
    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);


    }


}

