package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.assetsrepository.AssetRepository;
import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy implements GameObject
{
    private Texture image;
    private Sprite sprite;
    private int life;

    public Enemy(GameObjectType gameObjectType, int initialLife)
    {
        AssetRepository assetRepository = AssetRepository.getAssetRepository();
        image = assetRepository.getTexture(gameObjectType);
        sprite = new Sprite(image);
        life = initialLife;
    }

    MapCalculator mapCalculator = new MapCalculator();


    public void moveLeft(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, getX() - 1, getY()))
        {
            setPosition(getX() - 1, getY());
        }
    }

    public void moveRight(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, getX() + 1, getY()))
        {
            setPosition(getX() + 1, getY());
        }
    }

    public void moveUp(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, getX(), getY() + 1))
        {
            setPosition(getX(), getY() + 1);
        }
    }

    public void moveDown(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, getX(), getY() - 1))
        {
            setPosition(getX(), getY() - 1);
        }
    }

    @Override
    public void act(float delta)
    {

    }

    @Override
    public void setPosition(float x, float y)
    {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    @Override
    public float getX()
    {
        return sprite.getX();
    }

    @Override
    public float getY()
    {
        return sprite.getY();
    }

    @Override
    public boolean isAllowedToWalk()
    {
        return false;
    }

    public int getLife()
    {
        return life;
    }

    public void setLife(int life)
    {
        this.life = life;
    }
}
