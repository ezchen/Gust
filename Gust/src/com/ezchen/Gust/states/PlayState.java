package com.ezchen.Gust.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ezchen.Gust.handlers.GameStateManager;

public class PlayState extends GameState {

	private World world;
	private Box2DDebugRenderer debugRenderer;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		debugRenderer = new Box2DDebugRenderer();
		
		// create platform
		
		// create body definition
		BodyDef bDef = new BodyDef();
		bDef.position.set(160, 120);
		bDef.type = BodyType.StaticBody;
		
		// create the body
		Body body = world.createBody(bDef);
		
		// create a shape so we can define a fixture
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50, 5);
		
		// create the fixture
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		body.createFixture(fDef);
		
		// create box
		bDef.position.set(160, 200);
		bDef.type = BodyType.DynamicBody;
		body = world.createBody(bDef);
		
		shape.setAsBox(5, 5);
		body.createFixture(fDef);
		
	}
	
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// draw screen
		debugRenderer.render(world, camera.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
