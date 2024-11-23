package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;
import java.io.Console;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;

	Texture UpBirdTexture;
	Texture DownBirdTexture;

	Texture backgroudTexture;

	Texture upTubeTexture;
	Texture downTubeTexture;

	FitViewport viewport;

	Sprite birdSprite;

	Array<Sprite> downSprites;
	Array<Sprite> upSprites;
	Sprite up;
	Sprite down;
	Rectangle birdRectangle;

	Rectangle upRetangle;
	Rectangle downRetangle;



	@Override
	public void create () {
		upTubeTexture = new Texture("UpTube.png");
		downTubeTexture = new Texture("DownTube.png");
		backgroudTexture = new Texture("background.png");
		UpBirdTexture =  new Texture("UpBird.png");
		DownBirdTexture =  new Texture(Gdx.files.internal("DownBird.png"));

		spriteBatch = new SpriteBatch();
		viewport = new FitViewport(8, 5);
		birdSprite = new Sprite(DownBirdTexture);
		birdSprite.setSize(50, 50);

		up = new Sprite(upTubeTexture);

		down = new Sprite(downTubeTexture);

		upRetangle = new Rectangle();
		upRetangle = new Rectangle();

		birdRectangle = new Rectangle();


		viewport = new FitViewport(800,480, viewport.getCamera());
	}

	@Override
	public void render () {
		drawScreen();
		keyPressed();
		logic();
	}
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
	
	@Override
	public void dispose () {
		UpBirdTexture.dispose();
		DownBirdTexture.dispose();

		backgroudTexture.dispose();

		upTubeTexture.dispose();
		downTubeTexture.dispose();
	}

	private void drawScreen(){
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();
		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
		spriteBatch.begin();
		float worldWidth = viewport.getWorldWidth();
		float worldHeight = viewport.getWorldHeight();
		spriteBatch.draw(backgroudTexture, 0, 0, worldWidth, worldHeight);
		birdSprite.draw(spriteBatch);

		spriteBatch.draw(up, 260, 320, 50, 220);
		spriteBatch.draw(down, 120, 0, 50, 220);

		spriteBatch.draw(up, 260, 320, 50, 220);
		spriteBatch.draw(down, 120, 0, 50, 220);


		spriteBatch.end();

	}
	private void keyPressed(){
		float speed = 256f;
		float down = 64f;
		float delta = Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			birdSprite.translateX(speed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			birdSprite.translateY(speed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			birdSprite.translateX(-speed * delta);
		}
		birdSprite.translateY(-down * delta);

	}

	private void logic() {
		float worldWidth = viewport.getWorldWidth();
		float worldHeight = viewport.getWorldHeight();
		float birdWidth = birdSprite.getWidth();
		float birdHeight = birdSprite.getHeight();
		birdSprite.setX(MathUtils.clamp(birdSprite.getX(), 0, worldWidth - 50));
		birdSprite.setY(MathUtils.clamp(birdSprite.getY(), 0, worldHeight - 50));

		float delta = Gdx.graphics.getDeltaTime();
		birdRectangle.contains(birdSprite.getX(), birdSprite.getY(), birdWidth, worldHeight);

	}
}
