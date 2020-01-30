package com.vibhor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static int MOVEMENT = 0;
    public static int GRAVITY = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    public Sprite gunsprite;
    private Animation blobAnimation;


    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        Texture texture = new Texture("blobAnimation.png");
        blobAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);

    }
    public void update(float dt) {
        blobAnimation.update(dt);
        if (position.y > -76)
            velocity.add(MOVEMENT, GRAVITY, 0);

        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);


        if (position.x < 5 && position.x > -30)
            position.x = 5;
        if (position.x > 430)
            position.x = 430;
        if (position.y < 0)
            position.y = 0;
        if (position.y > 288)
            position.y = 288;

        if (position.x > 417 && position.y < 115) {

            position.x = 417;

        }

        if (position.x < 419 && position.y < 13 && position.x > 97) {

            position.y = 13;

        }

        if (position.x > 97 && position.y > 80 && position.x < 367 && position.y < 93) {

            position.y = 80;

        }

        if (position.x > 85 && position.y > 103 && position.x < 392 && position.y < 117) {

            position.y = 117;

        }
//Computer

        if((position.x > 184 && position.x < 194) && (position.y > 60 && position.y < 115)){
            position.x = 184;

        }

        if((position.x > 274 && position.x < 292) && (position.y > 60 && position.y < 115)){
            position.x = 292;

        }
        if((position.x > 184 && position.x < 292) && (position.y > 66 && position.y < 115)){
            position.y = 66;
         //   if(position.y > 46 && position.y < 66){
           //     if(Gdx.input.isKeyJustPressed(Input.Keys.E)){

            //    }


        //    }

        }

//stair rails


        if (position.x > 337 && position.y > 40 && position.x < 351 && position.y < 115) {

            position.x = 337;

        }

        if (position.x > 378 && position.y > 40 && position.x < 392 && position.y < 115) {

            position.x = 392;

        }

        if (position.x > 348 && position.y > 40 && position.x < 392 && position.y < 60) {

            position.y = 40;

        }


        if (position.x > 119 && position.y > 0 && position.x < 128 && position.y < 115) {

            position.x = 128;

        }


        if (position.x > 73 && position.y > -40 && position.x < 86 && position.y < 115) {

            position.x = 73;

        }


        velocity.scl(1 / dt);
        velocity.set(0, 0, 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return blobAnimation.getFrame();


    }

    public void jump() {
        velocity.y = 300;


    }

    public void down() {
        velocity.y = -300;


    }

    public void left() {
        velocity.x = -300;
    }


    //dashing method

    public void right() {
        velocity.x = 300;


    }

    public void Gravity() {
        GRAVITY -= 1;


    }
    public void push(){
        position.x -= 5;

    }
    public void nudge(){
        position.x += 5;

    }
    public void shove(){
        position.y -= 5;

    }

    public void AntiGravity() {
        GRAVITY += 1;


    }

    public void LPull() {
        MOVEMENT -= 1;
    }

    public void RPull() {
        MOVEMENT += 1;
    }

public void timeout(){
        position.x = -60;
        position.y = 190;
}


}




