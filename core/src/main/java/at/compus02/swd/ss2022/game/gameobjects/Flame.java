package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetsrepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Flame implements  GameObject {
    private Texture image;
    private Sprite sprite;

    public Flame() {
        AssetRepository assetRepository = AssetRepository.getAssetRepository();
        image = assetRepository.getTexture(GameObjectType.Flame);
        sprite = new Sprite(image);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public boolean isAllowedToWalk()
    {
        return false;
    }
}
