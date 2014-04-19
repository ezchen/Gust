package com.ezchen.Gust.entities;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class B2DSprite {

	public Body body;
	protected float width;
	protected float height;
	
	public B2DSprite(Body body) {
		this.body = body;
	}
	
	public Body getBody() {
		return body;
	}
	
	public float setWidth(float width) {
		float temp = this.width;
		this.width = width;
		return temp;
	}
	
	public float getWidth(float width) {
		float temp = this.width;
		this.width = width;
		return temp;
	}
	
	public float getHeight() {
		return height;
	}
	
	public abstract void update(float deltaTime);
	public abstract void render(float deltaTime);
	
}
