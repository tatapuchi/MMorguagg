package com.vibhor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vibhor.game.Weapons;
import com.vibhor.game.sprites.Bird;
import com.vibhor.game.sprites.Blob;
import com.vibhor.game.sprites.Burb;
import com.vibhor.game.sprites.Coin;
import com.vibhor.game.sprites.Dot;
import com.vibhor.game.sprites.Jellyfish;

import java.util.ArrayList;

public class Playstate extends State {
    public Coin coin;
    public Bird bird;
    private Texture dust;
    private Texture bg;
    private Texture Cabinet;
    private Texture Box;
    public Texture gun;
    private Texture Target;
    private Blob bot;
    private Array<Rectangle> bullets;
    private long lastDropTime;
    private Blob droid;
    private Texture Tree;
    public static Sprite gunsprite;
    private float countDown;
    private BitmapFont Font;
    private int randomNumber;




    private Sound Gunshot;
    private Sound Thump;

    private Sprite DUST;
    private Texture Desk;
    private Texture Computer;
    private Dot Ex;
    private Texture Block;
    private Texture Huddroid;
    private Jellyfish Jelly;
    private Burb Birb;

    //Inventory
    private Texture Common;
    private Texture Uncommon;
    private Texture Rare;
    private Texture Epic;
    private Texture Legendary;
    private Texture Set;
    private Texture Mythical;
    private Texture Ancient;
    private Texture Titan;
    private Texture Forbidden;
    private Texture Cosmetic;

    private Viewport viewport;

    private Texture Hatchet;
    private Texture Campaign;
    private int Money;

    private Texture InterE;
    private Texture Revolver;
    private static Sprite Magnum;
    public static Weapons ZigZag;


    private Texture Lightsaber;
    private Body Guag;
    private Box2DDebugRenderer b2dr;
    private World world;

    public ArrayList<Bullet> Bullets;


    public static final float PPM = 32;

    public boolean Isweaponequipped;
    public boolean Wasweaponequipped;
    public boolean CampaignMode;


    private SpriteBatch HudBatch;
    private Sprite Axe;
    private Weapons AXE;


    Preferences prefs = Gdx.app.getPreferences("My Preferences");

    public Playstate(GameStateManager gsm) {
        super(gsm);

        //Prefs
        float Rotation = prefs.getFloat("Rotation");

        //inventory
        Common = new Texture("Common.png");
        Uncommon = new Texture("Uncommon.png");
        Rare = new Texture("Rare.png");
        Epic = new Texture("Epic.png");
        Legendary = new Texture("Legendary.png");
        Set = new Texture("Set.png");
        Mythical = new Texture("Mythical.png");
        Ancient = new Texture("Ancient.png");
        Titan = new Texture("Titan.png");
        Forbidden = new Texture("Forbidden.png");
        Cosmetic = new Texture("Cosmetic.png");

        Lightsaber = new Texture("Lightsaber.png");
        InterE = new Texture("InterE.png");
        Revolver = new Texture("Revolver.png");
        Campaign = new Texture("Map.png");

        Hatchet = new Texture("Iron-Hatchet.png");
        Axe = new Sprite(Hatchet);
        AXE = new Weapons(Hatchet,Axe);


       Magnum = new Sprite(Revolver);

        coin = new Coin(300,250);
        bird = new Bird(50, 50);
        bot = new Blob(50, 300);
        droid = new Blob(400, 300);
        Birb = new Burb(330,60);
        Jelly = new Jellyfish(240,70);

        ZigZag = new Weapons(Revolver, Magnum);



        Ex = new Dot();
        cam.setToOrtho(false, 400, 240);
        bg = new Texture("Generic Map Box 2.png");
        dust = new Texture("Dust.png");
        Cabinet = new Texture("Cabinet.png");
        Box = new Texture("BOXA1.png");
        gun = new Texture("gun.png");
        Target = new Texture("Target.png");
        Tree = new Texture("Tree.png");
        Desk = new Texture("Desk.png");
        Computer = new Texture("Computer.png");
        Block = new Texture("Block.png");
        Huddroid = new Texture("Huddroid.png");

        DUST = new Sprite(dust);

        gunsprite = new Sprite(gun);
        Font = new BitmapFont();
        Gunshot = Gdx.audio.newSound(Gdx.files.internal("Gunshot.wav"));
        Thump = Gdx.audio.newSound(Gdx.files.internal("Thump.wav"));
        world = new World(new Vector2(0, 0), false);
        b2dr = new Box2DDebugRenderer();

        Bullets = new ArrayList<Bullet>();

        Isweaponequipped = false;
        Wasweaponequipped = false;
        CampaignMode = false;

        Money = 0;

        HudBatch = new SpriteBatch();
    }

    //Weapons----------------------------------------------------------------------------------------------------------------------------------------------------
    @Override

    //JUMP Place
    protected void HandleInput() {
        float rotation = (float) Math.toDegrees(MathUtils.atan2((Gdx.graphics.getWidth() / 2) - Gdx.input.getX(), (Gdx.graphics.getHeight() / 2) - Gdx.input.getY()))-5 ;
        if(AXE.IsEquipped){
            Axe.setRotation(rotation + 90);
        }

        if ((Axe.getRotation() < 90 || Axe.getRotation() > -90) || Gdx.input.getX() > Gdx.graphics.getWidth()/2) {

            if (Axe.isFlipX()) {
                Axe.flip(true, false);
                ZigZag.IsFlipped = false;
            }
        }
        if ((Axe.getRotation() > 90 ||Axe.getRotation() < -90)) {

            if (!Axe.isFlipX()) {
                Axe.flip(true, false);
                AXE.IsFlipped = true;
            }


            Axe.rotate(180);
        }
        if(!AXE.WasEquipped){
            Axe.setPosition(80,90);

            Axe.setRotation(0);
        }

        if((bird.getPosition().x > Axe.getX() - 50 && bird.getPosition().x <  Axe.getX() + 55) && (bird.getPosition().y >  Axe.getY() - 40 && bird.getPosition().y <  Axe.getY() + 30)) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                if (!AXE.IsEquipped) {
                    AXE.IsEquipped = true;
                    AXE.WasEquipped = true;
                    Isweaponequipped = false;
                } else if (AXE.IsEquipped) {
                    AXE.IsEquipped = false;
                    //Thump.play();
                }
            }


            if (AXE.IsEquipped) {

                if (!Axe.isFlipX()) {

                    Axe.setPosition(bird.getPosition().x + 20, bird.getPosition().y - 20 );
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                       //AXE SWINGING HERE


                        Axe.rotate(-90);
                    }
                }


                if (Axe.isFlipX()) {


                    Axe.setPosition(bird.getPosition().x - 35 , bird.getPosition().y - 20);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

                        Axe.rotate(90);
                        //AXE SWINGING HERE
                    }
                }
            } else if (!AXE.IsEquipped) {

                Axe.setRotation(0);

            }





        }





















//----------------------------------------------------------------------------------------------------------------------------
      if(ZigZag.IsEquipped){
        Magnum.setRotation(rotation - 90);
}

        if ((Magnum.getRotation() < 90 || Magnum.getRotation() > -90) || Gdx.input.getX() > Gdx.graphics.getWidth()/2) {

            if (Magnum.isFlipX()) {
                Magnum.flip(true, false);
                ZigZag.IsFlipped = false;
            }
        }
        if ((Magnum.getRotation() > 90 ||Magnum.getRotation() < -90)) {

            if (!Magnum.isFlipX()) {
                Magnum.flip(true, false);
                ZigZag.IsFlipped = true;
            }


            Magnum.rotate(180);
        }
        if(!ZigZag.WasEquipped){
            Magnum.setPosition(10,10);

            Magnum.setRotation(0);
        }

        if((bird.getPosition().x > Magnum.getX() - 50 && bird.getPosition().x <  Magnum.getX() + 55) && (bird.getPosition().y >  Magnum.getY() - 40 && bird.getPosition().y <  Magnum.getY() + 30)) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                if (!ZigZag.IsEquipped) {
                    ZigZag.IsEquipped = true;
                    ZigZag.WasEquipped = true;
                    Isweaponequipped = false;
                } else if (ZigZag.IsEquipped) {
                    ZigZag.IsEquipped = false;
                    //Thump.play();
                }
            }


            if (ZigZag.IsEquipped) {

                if (ZigZag.IsFlipped) {

                    Magnum.setPosition(bird.getPosition().x + 35, bird.getPosition().y );
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        Gunshot.play();
                        Magnum.setPosition(bird.getPosition().x + 30, bird.getPosition().y );
                    }
                }


                if (!ZigZag.IsFlipped) {


                   Magnum.setPosition(bird.getPosition().x - 35 , bird.getPosition().y - 5);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        Gunshot.play();
                        Magnum.setPosition(bird.getPosition().x - 30, bird.getPosition().y - 10);
                    }
                }
            } else if (!ZigZag.IsEquipped) {

               Magnum.setRotation(0);

            }

        }
//----------------------------------------------------------------------------------------------------------------------------------------------------




        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            Money += 1;
        }




        //Weapons----------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && (Isweaponequipped)){
         Bullets.add(new Bullet(gunsprite.getX(), gunsprite.getY()));

            prefs.putFloat("Rotation", gunsprite.getRotation());
            //Gdx.app.log("Position X", String.valueOf(gunsprite.getX()));
            //Gdx.app.log("Position Y", String.valueOf(gunsprite.getY()));
            //Gdx.app.log("Rotation", String.valueOf(gunsprite.getRotation()));
        }

//Basic Movement----------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            bird.jump();}

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            bird.down();}

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bird.left();}


        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            bird.right();}

        //camera zooming
        if (Gdx.input.isKeyPressed(Input.Keys.X) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            cam.zoom -= 0.0025;}

        if (Gdx.input.isKeyPressed(Input.Keys.Y) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            cam.zoom += 0.0025;}


        //Basic Movement----------------------------------------------------------------------------------------------------------------------------------------------------------------


        //ROTATING THE GUN----------------------------------------------------------------------------------------------------------------------------------------

// Put this code around if ur close enough or not

if(!Wasweaponequipped){
    gunsprite.setPosition(150,200);
}

        if((bird.getPosition().x > gunsprite.getX() - 50 && bird.getPosition().x < gunsprite.getX() + 55) && (bird.getPosition().y > gunsprite.getY() - 40 && bird.getPosition().y < gunsprite.getY() + 30)) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                if (!Isweaponequipped && !ZigZag.IsEquipped) {
                    Isweaponequipped = true;
                    Wasweaponequipped = true;
                    ZigZag.IsEquipped = false;
                } else if (Isweaponequipped) {
                    Isweaponequipped = false;
                    //Thump.play();
                }
            }


            if (Isweaponequipped) {

                if ((gunsprite.getRotation() < 90 || gunsprite.getRotation() > -90)) {

                    if (gunsprite.isFlipX()) {
                        gunsprite.flip(true, false);
                    }

                    if (!bird.getTexture().isFlipX()) {
                        bird.getTexture().flip(true, false);
                    }


                    gunsprite.setPosition(bird.getPosition().x - 35, bird.getPosition().y - 10);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        Gunshot.play();
                        gunsprite.setPosition(bird.getPosition().x - 25, bird.getPosition().y - 10);
                    }
                }


                if ((gunsprite.getRotation() > 90 || gunsprite.getRotation() < -90)) {

                    if (!gunsprite.isFlipX()) {
                        gunsprite.flip(true, false);
                    }

                    if (bird.getTexture().isFlipX()) {
                        bird.getTexture().flip(true, false);
                    }

                    gunsprite.rotate(180);

                    gunsprite.setPosition(bird.getPosition().x + 20, bird.getPosition().y - 10);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        Gunshot.play();
                        gunsprite.setPosition(bird.getPosition().x + 15, bird.getPosition().y - 10);
                    }
                }
            } else if (!Isweaponequipped) {

                gunsprite.setRotation(0);
                // gunsprite.setPosition(250,250);

            }

        }

        //Bot interaction-----------------------------------------------------------------------------------------------------------------------------------

        if (randomNumber == 8 || randomNumber == 9) {
            if (!bot.getTexture().isFlipX()) {
                bot.nglitch();
            }
        }

        if (randomNumber == 20 || randomNumber == 19) {
            if (bot.getTexture().isFlipX()) {
                bot.glitch();
            }
        }

        //Bot interaction-----------------------------------------------------------------------------------------------------------------------------------


    }

//
//    public Body createBullet(int x, int y, int width, int height) {
//
//        Body dBody;
//        BodyDef def = new BodyDef();
//
//
//        def.type = BodyDef.BodyType.DynamicBody;
//
//        def.position.set(gunsprite.getX() , gunsprite.getY() );
//        def.fixedRotation = false;
//        dBody = world.createBody(def);
//       // dBody.setTransform(0,0,0);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width / 2 , height / 2 );
//
//        dBody.createFixture(shape, 1.0f);
//        shape.dispose();
//        return dBody;
//    }



    @Override
    public void update(float dt) {

ArrayList<Bullet> Bulletstoremove = new ArrayList<Bullet>();
        for(Bullet Shell : Bullets){
                Shell.update(dt);
if(Shell.remove){
    Bulletstoremove.add(Shell);

}
        }
        Bullets.removeAll(Bulletstoremove);
       // revolver.update(dt);
        HandleInput();
      //  viewport.update(800,480,true);
        bird.update(dt);
        bot.update(dt);
        Jelly.update(dt);
        Birb.update(dt);
        droid.update(dt);
        Ex.update(dt);
        coin.update(dt);
        countDown -= dt / 4;
        if (countDown <= 0) {
            randomNumber = (int) (Math.random() * 28 + 1);
            countDown += 1; // add one second

        }
        world.step(1 / 60f, 6, 2);
    }




    //rendering here boi
    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        b2dr.render(world, cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);

        sb.draw(Box, -58, 180);
        sb.draw(Cabinet, 368, 88);
        sb.draw(Desk, 230, 68);
        sb.draw(Computer, 236, 90);

        sb.draw(Cabinet, 350, 88);

        sb.draw(Cabinet, 332, 88);

        sb.draw(Cabinet, 128, 88);
        sb.draw(Target, 250, 290);
        sb.draw(Target, 310, 290);
        sb.draw(Target, 190, 290);

        for(Bullet Shell : Bullets){
            Shell.render(sb);


        }

        if ((bird.getPosition().x > bot.getPosition().x - 80 && bird.getPosition().x < bot.getPosition().x + 65) && (bird.getPosition().y > bot.getPosition().y - 40 && bird.getPosition().y < bot.getPosition().y + 30)) {
            //Exclamation
            if (bot.getTexture().isFlipX()) {
                sb.draw(Ex.getTexture(), bot.getPosition().x + 20, bot.getPosition().y + 45);
            }

            if (!bot.getTexture().isFlipX()) {
                sb.draw(Ex.getTexture(), bot.getPosition().x + 25, bot.getPosition().y + 45);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.T)) {

                sb.draw(Block, bird.getPosition().x - 280, bird.getPosition().y - 480, 1000, 400);
                sb.draw(Huddroid, bird.getPosition().x - 200, bird.getPosition().y - 120, 96 * 2, 32 * 2);
                if (randomNumber == 1) {
                    Font.draw(sb, "IOS is overrated.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 2) {
                    Font.draw(sb, "Why do you have a gun?", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 3) {
                    Font.draw(sb, "Press G to donate money", bird.getPosition().x - 130, bird.getPosition().y - 100);
                    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
                        if (Money > 100 || Money == 100){Money -= 100;}
                        else {Money = 0;}
                    }
                }
                if (randomNumber == 4) {
                    Font.draw(sb, "Stardude... no, my name is droidbot 53.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 5) {
                    Font.draw(sb, "Siri, pssh, don't make me laugh.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 6) {
                    Font.draw(sb, "Ur mom, get rekt.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 7) {
                    Font.draw(sb, "What do you want me to say?", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 8) {
                    Font.draw(sb, "Could you stop interacting with me.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 9) {
                    Font.draw(sb, "You dont seem human to me.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 10) {
                    Font.draw(sb, "Press G to donate money", bird.getPosition().x - 130, bird.getPosition().y - 100);
                    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
                        if (Money > 1000 || Money == 1000){Money -= 1000;}
                        else {Money = 0;}
                    }
                }
                if (randomNumber == 11) {
                    Font.draw(sb, "Press G to donate money", bird.getPosition().x - 130, bird.getPosition().y - 100);
                    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
                        if (Money > 1000 || Money == 1000){Money -= 1000;}
                        else {Money = 0;}
                    }
                }
                if (randomNumber == 12) {
                    Font.draw(sb, "Don't come too close, I might get a virus.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 13) {
                    Font.draw(sb, "That quite the nice gun.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 14) {
                    Font.draw(sb, "PB... how dare you call me...", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 15) {
                    Font.draw(sb, "Amazon, yes the retail giant, I know.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 16) {
                    Font.draw(sb, "Ur mom still.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 17) {
                    Font.draw(sb, "Can you go away?", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 18) {
                    Font.draw(sb, "Just do it, shoot me already.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 19) {
                    Font.draw(sb, "Please leave me alone.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 20) {
                    Font.draw(sb, "...", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 21) {
                    Font.draw(sb, "Press G to donate money", bird.getPosition().x - 130, bird.getPosition().y - 100);
                 if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
                     if (Money > 1000 || Money == 1000){Money -= 1000;}
                     else {Money = 0;}
                 }
                }
                if (randomNumber == 22) {
                    Font.draw(sb, "May i interest you in my religion?", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 23) {
                    Font.draw(sb, "Press G to donate to our dongsheng fund", bird.getPosition().x - 130, bird.getPosition().y - 100);
                    if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
                        if (Money > 1000 || Money == 1000){Money -= 1000;}
                        else {Money = 0;}
                    }
                }
                if (randomNumber == 24) {
                    Font.draw(sb, "01100010 01110101 01110100 01110100 00001101 00001010", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 25) {
                    Font.draw(sb, "Error 404 player not found", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 26) {
                    Font.draw(sb, "Lol, offline?", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 27) {
                    Font.draw(sb, "Just connect to the goddamn internet already man!", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
                if (randomNumber == 28) {
                    Font.draw(sb, "I can see why the server isn't letting you join.", bird.getPosition().x - 130, bird.getPosition().y - 100);
                }
            }

        }






        cam.position.set(bird.getPosition().x, bird.getPosition().y, 0);
        cam.update();



        sb.draw(Birb.getTexture(), Birb.getPosition().x, Birb.getPosition().y);
            sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.draw(bot.getTexture(), bot.getPosition().x, bot.getPosition().y);
        sb.draw(droid.getTexture(), droid.getPosition().x, droid.getPosition().y);

        sb.draw(Jelly.getTexture(), Jelly.getPosition().x, Jelly.getPosition().y);





       // Colt.WeaponSprite.draw(sb);

        gunsprite.draw(sb);
        Magnum.draw(sb);
        Axe.draw(sb);





        if((bird.getPosition().x > gunsprite.getX() - 50 && bird.getPosition().x < gunsprite.getX() + 55) && (bird.getPosition().y > gunsprite.getY() - 40 && bird.getPosition().y < gunsprite.getY() + 30) && (!Isweaponequipped)) {
            sb.draw(Ex.getTexture(), gunsprite.getX() + 36, gunsprite.getY() + 32);
        }

        if((bird.getPosition().x > Magnum.getX() - 50 && bird.getPosition().x < Magnum.getX() + 55) && (bird.getPosition().y > Magnum.getY() - 40 && bird.getPosition().y < Magnum.getY() + 30) && (!ZigZag.IsEquipped)){
            sb.draw(Ex.getTexture(), Magnum.getX() + 24, Magnum.getY() + 28);
        }



if((bird.getPosition().x > Axe.getX() - 50 && bird.getPosition().x <  Axe.getX() + 55) && (bird.getPosition().y >  Axe.getY() - 40 && bird.getPosition().y <  Axe.getY() + 30) && (!AXE.IsEquipped)){
    sb.draw(Ex.getTexture(), Axe.getX() + 24, Axe.getY() + 34);

}



        sb.end();

HudBatch.begin();
HudBatch.draw(coin.getTexture(),  580, 400, 64, 64);
Font.draw(HudBatch,  Money + " Coins", 650, 440);
//HudBatch.draw(Campaign, 15, 50, 64, 64);



HudBatch.end();



//end of rendering--------------------------------------------------------------------------------------------------------------------------------------------

















        //Collision with android bot
        if((bird.getPosition().x > bot.getPosition().x -44 && bird.getPosition().x < bot.getPosition().x - 16)&& bird.getPosition().y >bot.getPosition().y- 30){
             bird.push();
        }
        if((bird.getPosition().x > bot.getPosition().x +34 && bird.getPosition().x < bot.getPosition().x + 40)&& bird.getPosition().y >bot.getPosition().y- 30){
            bird.nudge();
        }
        if((bird.getPosition().x > bot.getPosition().x -40 && bird.getPosition().x < bot.getPosition().x + 40)&& bird.getPosition().y >bot.getPosition().y- 34){
            bird.shove();
        }
        if((bird.getPosition().x > droid.getPosition().x -44 && bird.getPosition().x < droid.getPosition().x - 16)&& bird.getPosition().y > droid.getPosition().y- 30){
            bird.push();
        }
        if((bird.getPosition().x > droid.getPosition().x +34 && bird.getPosition().x < droid.getPosition().x + 40)&& bird.getPosition().y > droid.getPosition().y- 30){
            bird.nudge();
        }
        if((bird.getPosition().x > droid.getPosition().x -40 && bird.getPosition().x < droid.getPosition().x + 40)&& bird.getPosition().y > droid.getPosition().y- 34){
            bird.shove();
        }


// Gun rotation and angle calculator
        float x = (float) Math.toDegrees(MathUtils.atan2((Gdx.graphics.getWidth() / 2) - Gdx.input.getX(), (Gdx.graphics.getHeight() / 2) - Gdx.input.getY()))-5 ;
        if(Isweaponequipped) {
            gunsprite.setRotation(x - 90);
        }



        if(x < -180 || x > 0){
            if(!bird.getTexture().isFlipX())
            bird.getTexture().flip(true, false);
        }
        else if (x > 180 || x < 0){
            if(bird.getTexture().isFlipX())
                bird.getTexture().flip(true, false);
        }


        if (randomNumber == 1 || randomNumber == 2 || randomNumber == 3 || randomNumber == 4 || randomNumber == 6 || randomNumber == 7 || randomNumber == 8) {
            if (!bot.getTexture().isFlipX()) {
                bot.getTexture().flip(true, false);

            }
        }
        if (randomNumber == 17 || randomNumber == 12 || randomNumber == 13) {
            if (bot.getTexture().isFlipX()) {
                bot.getTexture().flip(true, false);

            }
        }

    }


    public class Bullet {

        public static final int SPEED = 3;
        public Texture bullet;
        public Sprite Shell;

        float x, y;
        public boolean remove = false;



        public Bullet(float x, float y){
            this.x = x;
            this.y = y;

            if(bullet == null){
                bullet = new Texture("Bullet.png");
                Shell = new Sprite(bullet);

            }

        }

        public void update(float deltaTime){

            x -= SPEED * MathUtils.cosDeg(prefs.getFloat("Rotation"));
            y -= SPEED * MathUtils.sinDeg(prefs.getFloat("Rotation"));
            Shell.setRotation(prefs.getFloat("Rotation"));


            if(y > 800 || y < -200 || x > 800 || x < -400){
                remove = true;

            }
        }
public Texture getTexture(){return bullet;}

        public void render(SpriteBatch sb){

Shell.draw(sb);
Shell.setPosition(x,y);


        }



    }



    @Override
    public void dispose() {
        Cabinet.dispose();
        gun.dispose();
        Gunshot.dispose();
        Font.dispose();
        Box.dispose();
        Tree.dispose();
        Target.dispose();
        world.dispose();
        b2dr.dispose();
    }
}






