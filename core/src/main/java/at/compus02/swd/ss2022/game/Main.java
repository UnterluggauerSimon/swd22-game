package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.assetsrepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.DecorationFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.input.GameInput;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.movement.MoveChars;
import at.compus02.swd.ss2022.game.observer.PlayerChannel;
import at.compus02.swd.ss2022.game.playableChars.Enemy;
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

import java.util.LinkedList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private GameInput gameInput = new GameInput();

	private Array<GameObject> gameObjects = new Array<>();
	private Array<GameObject> lifes = new Array<>();
	private LinkedList<GameObject> enemies = new LinkedList<>();

	AssetRepository assetRepository = AssetRepository.getAssetRepository();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;

	public MainPlayer mainPlayer;
	public String movedDirection;

	GameObject[][] newMap = new GameObject[16][16];
	GameObject[][] looserMap = new GameObject[16][16];

	MapCalculator mapCalculator = new MapCalculator();

	PlayerChannel playerChannel = new PlayerChannel();


	@Override
	public void create() {
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

		for (int i = 0; i < looserMap.length; i++) {
			for (int j = 0; j < looserMap[i].length; j++) {
				looserMap[i][j] = tileFactory.createSingleGameObject(GameObjectType.Gravel, mapCalculator.arrayInitToMapPixel(i), mapCalculator.arrayInitToMapPixel(j));
			}
		}

		newMap[11][6] = tileFactory.createSingleGameObject(GameObjectType.BridgeUp, mapCalculator.arrayInitToMapPixel(11), mapCalculator.arrayInitToMapPixel(6));
		newMap[10][4] = tileFactory.createSingleGameObject(GameObjectType.Bridge, mapCalculator.arrayInitToMapPixel(10), mapCalculator.arrayInitToMapPixel(4));

		gameObjects.addAll(decorationFactory.createGameObjects(gameObjects, GameObjectType.Sign,1,130,130, 130,130));

		mainPlayer = MainPlayer.getInstance();
		playerChannel.update("Spieler wurde erstellt");
		gameObjects.add(mainPlayer);

		Enemy enemy = new Enemy(GameObjectType.Log, 0);
		enemy.setPosition(40,40);
		enemies.add(enemy);

		Enemy enemy2 = new Enemy(GameObjectType.Questmaster, 1);
		enemy2.setPosition(40,40);
		enemies.add(enemy2);

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
		int counter = 0;

		MoveChars moveChars = new MoveChars();

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

		for(GameObject gameObject : enemies)
		{
			gameObject.draw(batch);
		}

		moveChars.movePlayerNormal(newMap, mainPlayer);

        try
        {
            moveChars.followPlayer(newMap, enemies.getFirst());
        }
        catch (Exception e)
        {
            System.out.println("Enemy1 died!");
        }

        try
        {
            moveChars.runFromPlayer(newMap, enemies.getLast());
        }
        catch (Exception e)
        {
            System.out.println("Enemy2 died!");
        }

		for (GameObject enemy : enemies)
		{
			if(enemy.getY() == mainPlayer.getY() && enemy.getX() == mainPlayer.getX())
			{
				font.draw(batch, "Player wurde gefangen!!", enemy.getX(), enemy.getY());
				if(lifes.size > 0)
				{
					lifes.removeIndex(lifes.size - 1);
					enemy.setPosition(130, 130);
				}
			}
		}

		if(lifes.size == 0)
		{
			for (GameObject[] objects : looserMap) {
				for (GameObject object : objects) {
					object.draw(batch);
				}
			}
			font.draw(batch, "You DIED looser!", -20, 0);
		}


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            int posleft = (int) mainPlayer.getX() - 1;
            int posright = (int) mainPlayer.getX() + 1;
            int posup = (int) mainPlayer.getY() - 1;
            int posdown = (int) mainPlayer.getY() + 1;

            for (GameObject enemy : enemies)
            {
                if ((enemy.getX() <= posright || enemy.getX() >= posleft) && (enemy.getY() <= posdown || enemy.getY() >= posup))
                {
                    float enemyX = enemy.getX();
                    float enemyY = enemy.getY();

                    Enemy e = (Enemy) enemy;
                    int newLife = e.getLife() - 1;
                    if (newLife == 0)
                    {
                        enemies.remove(e);
                    }

                    e.setLife(newLife);
                }
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
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}