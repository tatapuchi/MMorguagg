package com.vibhor.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vibhor.game.MMORG;

public class MenuState extends State {
    private Texture background;
    private Texture Onlinebutton;
    private Texture Online;
    private Texture Offlinebutton;
    private Texture Offline;
    private Texture Logbutton;
    private Texture Upog;
    private Texture Youbutton;
    private Texture Youtube;

    private Music gamemusic;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("pixil-frame-0.png");
           Online = new Texture("Online2.png");
            Onlinebutton = new Texture("Online1.png");
            Offline = new Texture("Offline2.png");
           Offlinebutton = new Texture("Offline1.png");
            Upog = new Texture("LOG2.png");
             Logbutton = new Texture("LOG1.png");
           Youtube = new Texture("Youtube2.png");
        Youbutton = new Texture("Youtube1.png");
    }

    @Override
    public void HandleInput() {
 if (Gdx.input.justTouched() && (Gdx.input.getX() > 270 && Gdx.input.getX() < 370) && (Gdx.input.getY() > 380 && Gdx.input.getY() < MMORG.HEIGHT)){
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
sb.draw(background, 0, 0, MMORG.WIDTH, MMORG.HEIGHT);

// BUTTONS
         sb.draw(Onlinebutton, 100, 10, 160, 96);
          if((Gdx.input.getX() > 120 && Gdx.input.getX() < 210) && (Gdx.input.getY() > 370 && Gdx.input.getY() < MMORG.HEIGHT)){
          sb.draw(Online, 80, -2, 200, 120);
         }

         sb.draw(Offlinebutton, 250, 10, 160, 96);
          if((Gdx.input.getX() > 270 && Gdx.input.getX() < 370) && (Gdx.input.getY() > 380 && Gdx.input.getY() < MMORG.HEIGHT)){
             sb.draw(Offline, 230, -2, 200, 120);
          }

         sb.draw(Youbutton, 400, 20, 160, 86);
         if((Gdx.input.getX() > 420 && Gdx.input.getX() < 530) && (Gdx.input.getY() > 370 && Gdx.input.getY() < MMORG.HEIGHT)){
            sb.draw(Youtube, 380, 8, 200, 110);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                Gdx.net.openURI("https://www.youtube.com/channel/UCx_58jndYID4xS09MR28C8g?sub_confirmation=1");
            }
          }

          sb.draw(Logbutton, 550, 20, 160, 86);
         if((Gdx.input.getX() > 570 && Gdx.input.getX() < 760) && (Gdx.input.getY() > 380 && Gdx.input.getY() < MMORG.HEIGHT)){
              sb.draw(Upog, 530, 8, 200, 110);
             if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                 Gdx.net.openURI("https://www.patreon.com/join/Guagg/checkout");

             // gsm.set(new LogState(gsm));
             }
          }

sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        Logbutton.dispose();
        Upog.dispose();
        Youtube.dispose();
        Youbutton.dispose();
        Onlinebutton.dispose();
        Online.dispose();
        Offline.dispose();
        Offlinebutton.dispose();
    }
}