package com.ezchen.Gust.mapareas;

import static com.ezchen.Gust.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ezchen.Gust.handlers.B2DVars;

public class PlatformArea extends MapArea {
	
	// Box2d definitions will use half this width
	public PlatformArea(World world, Vector2 position) {
		super(world, position);
		width = 200;
		create(world, position.x + width/2, position.y);
	}
	
	public PlatformArea(World world, float positionX, float positionY) {
		super(world, positionX, positionY);
		
		width = 200;
		// We want to define MapArea's position as the bottom left corner but
		// box2d bodies are defined using the center of the body
		create(world, positionX + width/2, positionY);
	}
	
	private void create(World world, float positionX, float positionY) {
		// create body definition
		BodyDef bDef = new BodyDef();
		bDef.position.set(positionX / PPM, positionY / PPM);
		bDef.type = BodyType.StaticBody;
		
		// create the body
		Body body = world.createBody(bDef);
		
		// create a shape so we can define a fixture
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((width / 2) / PPM, 50 / PPM);
		
		// create the fixture
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.filter.categoryBits = B2DVars.BIT_GROUND;
		fDef.filter.maskBits = B2DVars.BIT_PLAYER | -1;
		
		body.createFixture(fDef).setUserData("Platform");
		
		shape.dispose();
	}
	
	@Override
	protected void update(float deltaTime) {
		// bodies.getUserData().update();
	}

	@Override
	protected void render(SpriteBatch batch) {
		// bodies.getUserData().render();
	}

	@Override
	protected void destroy() {
	}

}
