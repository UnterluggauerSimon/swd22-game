package at.compus02.swd.ss2022.game.Umrechner;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.playableChars.MainPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MapCalculator
{
    public float arrayInitToMapPixel(int position)
    {
       return (float) ((position - 8 )* 32);
    }

    public int mapPixelToArrayInt(float pixel)
    {
            return Math.round((pixel + 255) / 32);
    }

    public boolean isMoveAllowed(GameObject[][] newMap, GameObject gameObject)
    {
        float newX = gameObject.getX();
        float newY = gameObject.getY();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            newX-= 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            newX+= 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            newY+=4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newY-=4;
        }

        if(newMap[mapPixelToArrayInt(newX)][mapPixelToArrayInt(newY-8)].isAllowedToWalk())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isMoveAllowed(GameObject[][] newMap, float targetX, float targetY)
    {
        if(newMap[mapPixelToArrayInt(targetX)][mapPixelToArrayInt(targetY-8)].isAllowedToWalk())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
