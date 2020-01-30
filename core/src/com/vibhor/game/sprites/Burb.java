package com.vibhor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Burb {
    private Vector3 position;
    private Vector3 velocity;
    private Animation BurbAnimation;


    public Burb(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        Texture Birb = new Texture("Birb.png");
        BurbAnimation = new Animation(new TextureRegion(Birb), 3, 0.5f);

    }
    public void update(float dt) {
        BurbAnimation.update(dt);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);

        velocity.scl(1 / dt);
        velocity.set(0, 0, 0);

        if (position.x < 5)
            position.x = 5;
        if (position.x > 430)
            position.x = 430;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return BurbAnimation.getFrame();


    }
    public void nglitch() {
        velocity.x = -50;


    }

    public void glitch() {
        velocity.x = 50;


    }
}
