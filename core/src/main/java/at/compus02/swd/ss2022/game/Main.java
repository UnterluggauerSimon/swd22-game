package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.assetsrepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.DecorationFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.input.GameInput;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.observer.PlayerChannel;
import at.compus02.swd.ss2022.game.playableChars.Enemy;
import at.compus02.swd.ss2022.game.playableChars.MainEnemy;
import at.compus02.swd.ss2022.game.playableChars.MainPlayer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
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
	private Array<GameObject> lifes = new Array<>();

	AssetRepository assetRepository = AssetRepository.getAssetRepository();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;
	private Sound mp3Sound;

	public MainPlayer mainPlayer;
	public MainEnemy mainEnemy;
	public Enemy enemy;

	public String movedDirection;

	GameObject[][] newMap = new GameObject[16][16];
	MapCalculator mapCalculator = new MapCalculator();

	PlayerChannel playerChannel = new PlayerChannel();


	@Override
	public void create() {
		//Background music
		//Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("assets/GameSound.mp3"));
		//mp3Sound.loop(0.2f);
		assetRepository.preloadAssests();

		TileFactory tileFactory = new TileFactory();
		DecorationFactory decorationFactory = new DecorationFactory();

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

		for (int j = 2; j < 6 ; j++) {
			newMap[10][j] = tileFactory.createSingleGameObject(GameObjectType.Water, mapCalculator.arrayInitToMapPixel(10), mapCalculator.arrayInitToMapPixel(j));
		}

		for (int j = 10; j < newMap.length ; j++) {
			newMap[j][6] = tileFactory.createSingleGameObject(GameObjectType.Water, mapCalculator.arrayInitToMapPixel(j), mapCalculator.arrayInitToMapPixel(6));
		}

		newMap[11][6] = tileFactory.createSingleGameObject(GameObjectType.BridgeUp, mapCalculator.arrayInitToMapPixel(11), mapCalculator.arrayInitToMapPixel(6));
		newMap[10][4] = tileFactory.createSingleGameObject(GameObjectType.Bridge, mapCalculator.arrayInitToMapPixel(10), mapCalculator.arrayInitToMapPixel(4));


		gameObjects.addAll(decorationFactory.createGameObjects(gameObjects, GameObjectType.Sign,1,130,130, 130,130));



		mainPlayer = MainPlayer.getInstance();
		playerChannel.update("Spieler wurde erstellt");
		gameObjects.add(mainPlayer);


		mainPlayer.addObserver(playerChannel);

		mainEnemy = MainEnemy.getInstance();
		mainEnemy.setPosition(30, 0);
		gameObjects.add(mainEnemy);

		enemy = Enemy.getInstance();
		enemy.setPosition(15, 3);
		gameObjects.add(enemy);

		lifes.add(decorationFactory.createSingleGameObject(GameObjectType.Hearth, -50, 130));
		lifes.add(decorationFactory.createSingleGameObject(GameObjectType.Hearth, -18, 130));
		lifes.add(decorationFactory.createSingleGameObject(GameObjectType.Hearth, 14, 130));

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

		for(GameObject gameObject : lifes) {
			gameObject.draw(batch);
		}

		if(mapCalculator.isMoveAllowed(newMap,mainPlayer))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			{
				mainPlayer.moveLeft(Input.Keys.LEFT);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				mainPlayer.moveRight(Input.Keys.RIGHT);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
				mainPlayer.moveUp(Input.Keys.UP);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				mainPlayer.moveDown(Input.Keys.DOWN);
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.F))
		{
			mainPlayer.eliminate(Input.Keys.F);
		}

		mainEnemy.followPlayer(newMap);
		enemy.runFromPlayer(newMap);

		//font.draw(batch, "aktuelle spieler Pixel x:"+ mainPlayer.getX() + " y:" + mainPlayer.getY(), -100, -100);
		//font.draw(batch, "aktuelle Map Position x:"+ mapCalculator.mapPixelToArrayInt(newX) + " y:" + mapCalculator.mapPixelToArrayInt(newY), -100, -200);

		//font.draw(batch, "aktuelle Enemy Pixel x:"+ mainEnemy.getX() + " y:" + mainEnemy.getY(), -100, -150);
		if(mainEnemy.getY() == mainPlayer.getY() && mainEnemy.getX()==mainPlayer.getX())
		{
			font.draw(batch, "Player wurde gefangen!!", mainEnemy.getX(), mainEnemy.getY());
			if(lifes.size > 0)
			{
				lifes.removeIndex(lifes.size - 1);
				mainEnemy.setPosition(130, 130);
			}
		}




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
		assetRepository.dispose();
		mp3Sound.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}