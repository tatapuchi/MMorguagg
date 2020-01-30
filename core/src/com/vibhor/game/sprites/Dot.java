package com.vibhor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Dot {
    private Animation ExclamationAnimation;
    private Texture Exclamation;


    public Dot() {
        Exclamation = new Texture("Exclamation.png");
        ExclamationAnimation = new Animation(new TextureRegion(Exclamation), 8, 0.6f);

    }

    public void update(float dt) {
ExclamationAnimation.update(dt);
    }


    public TextureRegion getTexture() {
        return ExclamationAnimation.getFrame();


    }
}
