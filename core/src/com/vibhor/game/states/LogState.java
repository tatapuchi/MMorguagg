package com.vibhor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vibhor.game.MMORG;

import java.awt.Font;

public class LogState extends State{

    private BitmapFont Font;
    private Texture Bigspace;
    private Texture Backspace;


    public LogState(GameStateManager gsm) {
        super(gsm);
        Font = new BitmapFont();
        Backspace = new Texture("Backspace.png");
        Bigspace = new Texture("Bigspace.png");


    }


    @Override
    protected void HandleInput() {
if(Gdx.input.isTouched() && (Gdx.input.getX() > 600 && Gdx.input.getX() < 750) && (Gdx.input.getY() > 350 && Gdx.input.getY() < 400)){
    gsm.set(new MenuState(gsm));

}
    }

    @Override
    public void update(float dt) {
    HandleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
sb.begin();
//Devs
Font.draw(sb, "Developer:", 50, 450);
Font.draw(sb, "Phatt Guagg, kingbrad02", 50, 420);

//Beta-Testers
Font.draw(sb, "Phatt Halp:", 50, 370);
Font.draw(sb, "Raelus     TEttinger    Bamboomer Bandit     RafaSKB     Peanut Panda     Mr. Stahlfelge     Icefill", 50, 340);
Font.draw(sb, "Santorno     Cococore     Dave     Satori87     Tislom58     myke     Early Grey     d00000ge", 50, 310);
Font.draw(sb, "Yusuf Ademoglu     Ulises Perdomo Maurice Joaquin Deyes     Kyle Holst     Wener     General Starver", 50, 280);
Font.draw(sb, "Dongsheng Wang     Tony Goebel     David Iliev     David Holzwarth     Ivan Dudnyk     Koki", 50, 250);

//Update Log
Font.draw(sb, "Update Log:", 50, 200);
Font.draw(sb, "1.0.8 [11/11/2019]", 50, 170);

//Buttons
        sb.draw(Backspace,600, 50, 150, 75);
        if((Gdx.input.getX() > 600 && Gdx.input.getX() < 750) && (Gdx.input.getY() > 350 && Gdx.input.getY() < 400)){
            sb.draw(Bigspace, 580, 40, 200,100);
        }

sb.end();
    }

    @Override
    public void dispose() {

    }
}
