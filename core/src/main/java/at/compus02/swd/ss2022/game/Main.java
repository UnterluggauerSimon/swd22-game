package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.factories.DecorationFactory;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Sign;
import at.compus02.swd.ss2022.game.input.GameInput;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private GameInput gameInput = new GameInput();

	private Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;

	@Override
	public void create() {
		TileFactory tileFactory = new TileFactory();
		PlayerFactory playerFactory = new PlayerFactory();
		DecorationFactory decorationFactory = new DecorationFactory();

		float startX = 0 - viewport.getMaxWorldWidth() / 2;
		float startY = viewport.getMaxWorldHeight() / 2;
		float endX = viewport.getMaxWorldWidth() / 2;
		float endY = 0 - viewport.getMaxWorldHeight() / 2;
		System.out.println(startY);
		//create Backgroudn
		gameObjects = tileFactory.createGameObjects(gameObjects, GameObjectType.Water, 255, startX, endX, startY, endY);
		gameObjects = tileFactory.createGameObjects(gameObjects, GameObjectType.Gras, 255, -192, 160, 192, -160);
		gameObjects = tileFactory.createGameObjects(gameObjects, GameObjectType.Gravel, 40, -32, 0, 192, -160);
		gameObjects = tileFactory.createGameObjects(gameObjects, GameObjectType.Gravel, 40, -192, 160, 32, 0);

		//create Decoration Top Left
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Bush, 5, -192, -64, 192, 64);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.BigTree, 3, -192, -64, 192, 64);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.LittleTree, 2, -192, -64, 192, 64);

		//create Decoration Top Right
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Bush, 5, 32, 160, 192, 64);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Log, 3, 32, 160, 192, 64);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.LittleTree, 2, 32, 160, 192, 64);

		//create Decoration Bottom Left
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Bush, 5, -192, -64, -32, -160);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.BigTree, 3, -192, -64, -32, -160);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.LittleTree, 2, -192, -64, -32, -160);

		//create Decoration Bottom Right
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Bush, 5, 32, 160, -32, -160);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.BigTree, 3, 32, 160, -32, -160);
		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.LittleTree, 2, 32, 160, -32, -160);

		gameObjects = decorationFactory.createGameObjects(gameObjects, GameObjectType.Sign, 1, -16,-16,16,16);
		gameObjects = playerFactory.createGameObjects(gameObjects, GameObjectType.Knight, 1, -60,0,16,0);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this.gameInput);
	}

	private void act(float delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.act(delta);
		}
	}

	private void draw() {
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		for(GameObject gameObject : gameObjects) {
			gameObject.draw(batch);
		}
		font.draw(batch, "Hello Game", -220, -220);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			float x = gameObjects.get(gameObjects.size - 1).getX();
			float y = gameObjects.get(gameObjects.size - 1).getY();
			gameObjects.get(gameObjects.size - 1).setPosition(x-1,y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			float x = gameObjects.get(gameObjects.size - 1).getX();
			float y = gameObjects.get(gameObjects.size - 1).getY();
			gameObjects.get(gameObjects.size - 1).setPosition(x+1,y);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			float x = gameObjects.get(gameObjects.size - 1).getX();
			float y = gameObjects.get(gameObjects.size - 1).getY();
			gameObjects.get(gameObjects.size - 1).setPosition(x,y+1);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			float x = gameObjects.get(gameObjects.size - 1).getX();
			float y = gameObjects.get(gameObjects.size - 1).getY();
			gameObjects.get(gameObjects.size - 1).setPosition(x,y-1);
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;
		while(deltaAccumulator > logicFrameTime) {
			deltaAccumulator -= logicFrameTime;
			act(logicFrameTime);
		}
		draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}