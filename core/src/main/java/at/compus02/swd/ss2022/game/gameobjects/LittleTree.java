package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetsrepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LittleTree implements  GameObject
{

    private Texture image;
    private Sprite sprite;

    public LittleTree()
    {
        AssetRepository assetRepository = AssetRepository.getAssetRepository();
        image = assetRepository.getTexture(GameObjectType.LittleTree);
        sprite = new Sprite(image);
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
        return 0;
    }

    @Override
    public float getY()
    {
        return 0;
    }

    @Override
    public boolean isAllowedToWalk()
    {
        return false;
    }
}
