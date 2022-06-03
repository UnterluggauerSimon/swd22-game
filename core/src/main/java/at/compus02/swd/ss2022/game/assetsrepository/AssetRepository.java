package at.compus02.swd.ss2022.game.assetsrepository;

import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.gameobjects.BadKnight;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetRepository
{
    private static AssetRepository assetRepository = new AssetRepository();
    private HashMap<GameObjectType, Texture> textures = new HashMap<>();

    private AssetRepository()
    {
        System.out.println("Erstelle AssetRepository");
    }

    public static AssetRepository getAssetRepository()
    {
        return assetRepository;
    }

    public void preloadAssests()
    {
        textures.put(GameObjectType.BadKnight,new Texture( "bad_knight.png"));
        textures.put(GameObjectType.BigTree,new Texture( "big_tree.png"));
        textures.put(GameObjectType.Bush,new Texture( "bush.png"));
        textures.put(GameObjectType.Flame,new Texture( "flames.png"));
        textures.put(GameObjectType.GoodKnight,new Texture( "good_knight.png"));
        textures.put(GameObjectType.Hearth,new Texture( "hearth.png"));
        textures.put(GameObjectType.Knight,new Texture( "knight.png"));
        textures.put(GameObjectType.LittleTree,new Texture( "little_tree.png"));
        textures.put(GameObjectType.Log,new Texture( "log.png"));
        textures.put(GameObjectType.Questmaster,new Texture( "questmaster.png"));
        textures.put(GameObjectType.Sign,new Texture( "sign.png"));
        textures.put(GameObjectType.Stone,new Texture( "stone.png"));
        textures.put(GameObjectType.Gras,new Texture( "tile_gras.png"));
        textures.put(GameObjectType.Gravel,new Texture( "tile_gravel.png"));
        textures.put(GameObjectType.Wall,new Texture( "tile_wall.png"));
        textures.put(GameObjectType.Water,new Texture( "tile_water.png"));
        textures.put(GameObjectType.Bridge, new Texture("bridge.png"));
    }

    public Texture getTexture(GameObjectType object)
    {
        for (Map.Entry<GameObjectType, Texture> entry : textures.entrySet())
        {
            if(entry.getKey().equals(object))
                return textures.get(entry.getKey());
        }

        return null;
    }

    public void dispose()
    {
        for (Map.Entry<GameObjectType, Texture> entry : textures.entrySet())
        {
            entry.getValue().dispose();
        }
    }
}
