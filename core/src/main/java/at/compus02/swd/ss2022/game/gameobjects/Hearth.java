package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hearth implements  GameObject
{

    private Texture image;
    private Sprite sprite;

    public Hearth()
    {
        image = new Texture("hearth.png");
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
