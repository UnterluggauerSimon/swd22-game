package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.Umrechner.MapCalculator;
import at.compus02.swd.ss2022.game.factories.DecorationFactory;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.input.GameInput;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.observer.NewsAgency;
import at.compus02.swd.ss2022.game.observer.PlayerChannel;
import at.compus02.swd.ss2022.game.playableChars.MainEnemy;
import at.compus02.swd.ss2022.game.playableChars.MainPlayer;
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

	public MainPlayer mainPlayer;
	public MainEnemy mainEnemy;

	GameObject[][] newMap = new GameObject[16][16];
	MapCalculator mapCalculator = new MapCalculator();

	NewsAgency newsAgency = new NewsAgency();
	PlayerChannel playerChannel = new PlayerChannel();


	@Override
	public void create() {
		TileFactory tileFactory = new TileFactory();
		newsAgency.addObserver(playerChannel);

		for (int i = 0; i < newMap.length; i++) {
			for (int j = 0; j < newMap[i].length; j++) {
				newMap[i][j] = tileFactory.createSingleGameObject(GameObjectType.Water, mapCalculator.arrayInitToMapPixel(i), mapCalculator.arrayInitToMapPixel(j));
			}
		}

		for (int i = 2; i < newMap.length -2; i++) {
			for (int j = 2; j < newMap[i].length -2 ; j++) {
				newMap[i][j] = tileFactory.createSingleGameObject(GameObjectType.Gras, mapCalculator.arrayInitToMapPixel(i), mapCalculator.arrayInitToMapPixel(j));
			}
		}

		for (int j = 5; j < newMap[5].length -2 ; j++) {
			newMap[5][j] = tileFactory.createSingleGameObject(GameObjectType.Wall, mapCalculator.arrayInitToMapPixel(5), mapCalculator.arrayInitToMapPixel(j));
		}

		mainPlayer = MainPlayer.getInstance();
		playerChannel.update("Spieler wurde erstellt");
		gameObjects.add(mainPlayer);

		mainEnemy = MainEnemy.getInstance();
		mainEnemy.setPosition(160, 160);
		gameObjects.add(mainEnemy);

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

		float newX = mainPlayer.getX();
		float newY = mainPlayer.getY();

		for (GameObject[] gameObject : newMap)
		{
			for (GameObject gameObject1 : gameObject)
				gameObject1.draw(batch);
		}

		for(GameObject gameObject : gameObjects) {
			gameObject.draw(batch);
		}

		if(mapCalculator.isMoveAllowed(newMap,mainPlayer))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			{
				mainPlayer.moveLeft();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				mainPlayer.moveRight();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
				mainPlayer.moveUp();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				mainPlayer.moveDown();
			}
		}

		mainEnemy.followPlayer(newMap);

		font.draw(batch, "aktuelle spieler Pixel x:"+ mainPlayer.getX() + " y:" + mainPlayer.getY(), -100, -100);
		font.draw(batch, "aktuelle Map Position x:"+ mapCalculator.mapPixelToArrayInt(newX) + " y:" + mapCalculator.mapPixelToArrayInt(newY), -100, -200);

		font.draw(batch, "aktuelle Enemy Pixel x:"+ mainEnemy.getX() + " y:" + mainEnemy.getY(), -100, -150);


		playerChannel.observePlayer();
		Gdx.input.setInputProcessor(this.gameInput);
		batch.end();
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