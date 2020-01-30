package com.vibhor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Blob {
    private Vector3 position;
    private Vector3 velocity;
    private Animation BotAnimation;
  //  CollisionRect rect;


    public Blob(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        Texture bot = new Texture("botAnimation.png");
        BotAnimation = new Animation(new TextureRegion(bot), 3, 0.5f);
       // this.rect = new CollisionRect(x,y,14, 28);

    }
    public void update(float dt) {
        BotAnimation.update(dt);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);

        velocity.scl(1 / dt);
        velocity.set(0, 0, 0);

       // rect.move(getPosition().x,getPosition().y);
        if (position.x < 5)
            position.x = 5;
        if (position.x > 430)
            position.x = 430;
        }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return BotAnimation.getFrame();


    }
    public void nglitch() {
        velocity.x = -50;


    }

    public void glitch() {
        velocity.x = 50;


    }


   // public CollisionRect getCollsionRect(){
    //    return rect;


//    }

}
