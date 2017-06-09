package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by vinkriv on 6/8/2017.
 */

public class Girl extends Actor{
    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    TextureRegion textureRegion;
    Sprite runninggirl;
    long tStart = System.currentTimeMillis();

    public Girl(){
        textureAtlas = new TextureAtlas(Gdx.files.internal("SpriteSheets/runninggirl.atlas"));
        animation = new Animation(1/10f,textureAtlas.getRegions());
        textureRegion = animation.getKeyFrame(System.currentTimeMillis()*3-tStart,true);
        runninggirl = new Sprite(textureRegion);
        setBounds(getX(),getY(),runninggirl.getWidth(),runninggirl.getHeight());
        setX(0);
        setY((int)(Math.random()*(Gdx.graphics.getHeight()-356)+100));
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(Gdx.graphics.getWidth(), getY());
        moveToAction.setDuration(3f);
        RemoveActorAction removeActor = new RemoveActorAction();
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(moveToAction);
        sequenceAction.addAction(removeActor);
        //addAction(sequenceAction);
        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (event.getTarget() instanceof Girl) {

                }
                return true;
            }
        });


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        runninggirl.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        runninggirl.setPosition(getX(),getY());
        runninggirl.setScale(getScaleX(),getScaleY());
    }
}
