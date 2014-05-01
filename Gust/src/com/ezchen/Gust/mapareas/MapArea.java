package com.ezchen.Gust.mapareas;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class MapArea {
	
	protected Vector2 position;
	protected List<Body> bodies = new ArrayList<Body>();
	protected World world;
	
	// Box2d will use half of each of these definitions divided by PPM
	protected int width;
	protected int height;

	protected MapArea(World world, Vector2 position) {
		this.world = world;
		this.position = new Vector2(position);
	}
	
	protected MapArea(World world, float positionX, float positionY) {
		position = new Vector2(positionX, positionY);
	}
	
	public List<Body> getBodies() {
		return bodies;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public float getLeftEdge() {
		return position.x;
	}
	
	public float getRightEdge() {
		return position.x + width;
	}
	
	public float getTopEdge() {
		return position.y + height;
	}
	
	public float getBottomEdge() {
		return position.y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	protected abstract void update(float deltaTime);
	protected abstract void render(SpriteBatch batch);
	protected abstract void destroy();
}
