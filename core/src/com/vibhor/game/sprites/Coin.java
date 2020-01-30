package com.vibhor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Coin {

        private Vector3 position;

        private Animation CoinAnimation;


        public Coin(int x, int y) {
            position = new Vector3(x, y, 0);
            Texture bot = new Texture("CoinAnimation.png");
            CoinAnimation = new Animation(new TextureRegion(bot), 5, 0.5f);

        }
        public void update(float dt) {
            CoinAnimation.update(dt);

        }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return CoinAnimation.getFrame();


    }

}
