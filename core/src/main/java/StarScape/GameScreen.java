package StarScape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen extends BaseScreen {

	private PhysicsActor spaceship;
	private ParticleActor thruster;
	private ParticleActor baseExplosion;

	public GameScreen(BaseGame g) {
		super(g);
	}

	@Override
	public void create() {

		BaseActor background = new BaseActor();
		background.setTexture(new Texture(Gdx.files.internal("space.png")));
		background.setPosition(0,0);
		mainStage.addActor(background);

		spaceship = new PhysicsActor();
		Texture shipTexture = new Texture(Gdx.files.internal("spaceship.png"));
		shipTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		spaceship.storeAnimation("default", shipTexture);
		spaceship.setPosition(400, 300);
		spaceship.setOriginCenter();
		spaceship.setMaxSpeed(200);
		spaceship.setDeceleration(20);
		mainStage.addActor(spaceship);

		thruster = new ParticleActor();
		thruster.load("thruster.pfx", "");
		BaseActor thrusterAdjuster = new BaseActor();
		thrusterAdjuster.setTexture(new Texture(Gdx.files.internal("blank.png")));
		thrusterAdjuster.addActor(thruster);
		thrusterAdjuster.setPosition(0, 32);
		thrusterAdjuster.setRotation(90);
		thrusterAdjuster.setScale(0.25f);
		thruster.start();
		spaceship.addActor(thrusterAdjuster);
		baseExplosion = new ParticleActor();
		baseExplosion.load("explosion.pfx", "");
	}

	@Override
	public void update(float dt) {
		spaceship.setAccelerationXY(0,0);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			spaceship.rotateBy(180 * dt);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			spaceship.rotateBy(-180 * dt);
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			spaceship.addAccelerationAS(spaceship.getRotation(), 100);
			thruster.start();
		}
		else {
			thruster.stop();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.P)
			togglePaused();

		if (keycode == Input.Keys.R)
			game.setScreen(new GameScreen(game));

		if (keycode == Input.Keys.SPACE){
			ParticleActor explosion = baseExplosion.clone();
			explosion.setPosition(MathUtils.random(800), MathUtils.random(600));
			explosion.start();
			mainStage.addActor(explosion);
		}

		return false;
	}
}