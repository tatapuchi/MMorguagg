package com.vibhor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.vibhor.game.sprites.Animation;
import com.vibhor.game.states.GameStateManager;
import com.vibhor.game.states.Playstate;

public class Weapons {
    public Sprite WeaponSprite;
    public Texture WeaponTexture;
    public boolean IsEquipped;
    public boolean WasEquipped;
    public boolean IsFlipped;




    public Weapons(Texture WeaponTexture, Sprite WeaponSprite){
        IsEquipped = false;
        WasEquipped = false;
        IsFlipped = false;

        this.WeaponTexture = WeaponTexture;
        this.WeaponSprite = WeaponSprite;



    }



    public void update(float dt) {


    }

}