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
	
	public MapArea(World world) {
		this.world = world;
	}
	
	protected MapArea(World world, Vector2 position) {
		this.position = position;
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
	
	protected abstract void update(float deltaTime);
	protected abstract void render(SpriteBatch batch);
	protected abstract void destroy();
}
