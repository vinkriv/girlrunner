package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas textureAtlas;
	TextureRegion textureRegion;
	Animation<TextureRegion> stillAnimation;
	float timeForStill = 0.0f;
	float x = 0.0f;
	int y;
	Stage stage;
	Girl runner;
	static int score=0;


	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		runner = new Girl();
		stage.addActor(new Girl());
		stage.setKeyboardFocus(runner);
		batch = new SpriteBatch();
		/*
		Label text;
		Label.LabelStyle textStyle;
		BitmapFont font = new BitmapFont();
		textStyle = new Label.LabelStyle();
		textStyle.font = font;
		text = new Label(String.valueOf(System.currentTimeMillis()),textStyle);
		text.setFontScale(1f,1f);
		stage.addActor(text);
		*/
		//This will display 10 frames in one second
		//1/20 will display 20 frames in one second

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		timeForStill += Gdx.graphics.getDeltaTime();
		stage.act();
		stage.draw();
		if (stage.getActors().size == 0) {
			runner = new Girl();
			stage.addActor(runner);
		}
		BitmapFont font = new BitmapFont();
		batch.begin();
		font.getData().setScale(5f);
		font.draw(batch,String.valueOf(score),100,Gdx.graphics.getHeight());
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
	}

}