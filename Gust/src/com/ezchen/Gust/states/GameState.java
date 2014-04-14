package com.ezchen.Gust.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ezchen.Gust.handlers.GameStateManager;
import com.ezchen.Gust.Gust;

public abstract class GameState {
	protected GameStateManager gsm;
	protected Gust gust;
	
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected OrthographicCamera hudCam;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		gust = gsm.game();
		batch = gust.getSpriteBatch();
		camera = gust.getCamera();
		hudCam = gust.getHudCam();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
