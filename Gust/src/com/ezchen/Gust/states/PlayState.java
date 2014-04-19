package com.ezchen.Gust.states;

import static com.ezchen.Gust.handlers.B2DVars.PPM;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ezchen.Gust.Gust;
import com.ezchen.Gust.handlers.B2DVars;
import com.ezchen.Gust.handlers.GameStateManager;
import com.ezchen.Gust.handlers.MyContactListener;
import com.ezchen.Gust.mapareas.MapArea;
import com.ezchen.Gust.mapareas.PlatformArea;

public class PlayState extends GameState {

	private World world;
	private OrthographicCamera b2dCam;
	private Box2DDebugRenderer debugRenderer;
	private Body playerBody;
	private LinkedList<MapArea> areas;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		areas = new LinkedList<MapArea>();
		
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(new MyContactListener());
		debugRenderer = new Box2DDebugRenderer();
		
		areas.add(new PlatformArea(world, 160, 120));
		
		createPlayer();
		
		// set up box2d camera for debugging purposes
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Gust.V_WIDTH / PPM, Gust.V_HEIGHT / PPM);
	}
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		
		// move the camera with the player
		b2dCam.position.x = playerBody.getPosition().x;
		b2dCam.position.y = playerBody.getPosition().y;
		b2dCam.update();
		
		// update the players velocity
		if (playerBody.getLinearVelocity().x < 4) {
			playerBody.applyLinearImpulse(.2f, 0, playerBody.getPosition().x, playerBody.getPosition().y,
					false);
		}
		
		// create new platforms
		if (areas.peekLast() == null ||
				(areas.peekLast().getPosition().x / PPM - playerBody.getPosition().x) < (Gust.V_WIDTH / PPM) * 3) {
			areas.add(new PlatformArea(world, areas.peekLast().getPosition().x+100,areas.peekLast().getPosition().y));
		}
	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// draw screen
		debugRenderer.render(world, b2dCam.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void fling(float velocityX, float velocityY, int button) {
		int velX = (int) velocityX / 500;
		int velY = (int) -velocityY / 500;
		
		playerBody.applyLinearImpulse(velX, velY, 0, 0, true);
	}
	
	private void createPlayer() {
		createPlayer(5, 5, 160, 200);
	}
	
	private void createPlayer(int width, int height, int xPos, int yPos) {
		BodyDef bDef = new BodyDef();
		bDef.position.set(xPos / PPM, yPos / PPM);
		bDef.type = BodyType.DynamicBody;
		playerBody = world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / PPM, height / PPM);
		
		FixtureDef fDef = new FixtureDef();
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fDef.filter.maskBits = -1;
		fDef.shape = shape;
		
		playerBody.createFixture(fDef).setUserData("PlayerBody");
		
		shape.setAsBox(width / PPM, 2 / PPM, new Vector2(0, -height / PPM), 0);
		fDef.shape = shape;
		fDef.isSensor = true;
		
		playerBody.createFixture(fDef).setUserData("Foot");
	}
	
	private void createPlatform() {
		
		// create body definition
		BodyDef bDef = new BodyDef();
		bDef.position.set(160 / PPM, 120 / PPM);
		bDef.type = BodyType.StaticBody;
		
		// create the body
		Body body = world.createBody(bDef);
		
		// create a shape so we can define a fixture
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);
		
		// create the fixture
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.filter.categoryBits = B2DVars.BIT_GROUND;
		fDef.filter.maskBits = B2DVars.BIT_PLAYER;
		
		body.createFixture(fDef).setUserData("Platform");
	}

}
