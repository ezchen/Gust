package com.ezchen.Gust;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ezchen.Gust.handlers.GameStateManager;

public class Gust extends Game {
	
	public static final String VERSION = "0.0.1";
	public static final String TITLE = "Gust";
	
	public static final float STEP = 1 / 60f;
	private float accum;
	
	private GameStateManager gsm;
	private OrthographicCamera camera;
	private OrthographicCamera hudCam;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public OrthographicCamera getHudCam() {
		return hudCam;
	}
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 240);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, 320, 240);
		
		gsm = new GameStateManager(this);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		accum += Gdx.graphics.getDeltaTime();
		
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
