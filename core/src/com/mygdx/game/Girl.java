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
import com.badlogic.gdx.utils.BooleanArray;

/**
 * Created by vinkriv on 6/8/2017.
 */

public class Girl extends Actor{
    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    TextureRegion textureRegion;
    TextureRegion textureRegion2;
    Sprite runninggirl;
    float time=0;
    float time2=0;
    SpriteBatch batch = new SpriteBatch();
    Boolean isTouched=false;
    int y = (int)(Math.random()*(Gdx.graphics.getHeight()-400)+100);
    Sprite explosion;
    TextureAtlas textureAtlas2;
    Animation<TextureRegion> animation2;

    public Girl(){
        textureAtlas = new TextureAtlas(Gdx.files.internal("SpriteSheets/runninggirl.atlas"));
        animation = new Animation(1/6f,textureAtlas.getRegions());
        textureAtlas2 = new TextureAtlas(Gdx.files.internal("SpriteSheets/explosion.atlas"));
        animation2 = new Animation<TextureRegion>(1/10f,textureAtlas2.getRegions());
        runninggirl = new Sprite(new Texture(Gdx.files.internal("Sprites/0.png")));
        setX(0);
        setY(y);
        setBounds(getX(),getY(),runninggirl.getWidth(),runninggirl.getHeight());
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(Gdx.graphics.getWidth(), getY());
        moveToAction.setDuration(1f);
        final RemoveActorAction removeActor = new RemoveActorAction();
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(moveToAction);
        sequenceAction.addAction(removeActor);
        addAction(sequenceAction);
        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (event.getTarget() instanceof Girl) {
                    isTouched=true;
                    MyGdxGame.score++;
                    //callBack.sendData();
                    explosion = new Sprite(new Texture(Gdx.files.internal("fire/0.png")));
                }
                return true;
            }
        });




    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        /*if (isTouched){
            batch.draw(animation2.getKeyFrame(time), this.getX(), this.getY());
            time2+=Gdx.graphics.getDeltaTime();
            if (animation2.isAnimationFinished(time2)) {
                remove();
            }
        }
        else
         */
        if (isTouched) {
            time2 += Gdx.graphics.getDeltaTime();
            if (animation2.isAnimationFinished(time2)) {
                remove();
            }
        }
        runninggirl.draw(batch);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isTouched) {
            time += Gdx.graphics.getDeltaTime();
            textureRegion = animation.getKeyFrame(time, true);
            runninggirl.setRegion(textureRegion);
            runninggirl.setPosition(getX(), getY());
            runninggirl.setScale(getScaleX(), getScaleY());
            if (runninggirl.getX() > Gdx.graphics.getWidth() - runninggirl.getWidth()) {
                remove();
            }
        }
        if (isTouched){
            time += Gdx.graphics.getDeltaTime();
            textureRegion2 = animation2.getKeyFrame(time, true);
            runninggirl.setRegion(textureRegion2);
            runninggirl.setPosition(getX(), getY());
            runninggirl.setScale(1.5f, 1.5f);
        }
    }

}

