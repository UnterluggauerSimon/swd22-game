package at.compus02.swd.ss2022.game.input;

import at.compus02.swd.ss2022.game.Main;
import at.compus02.swd.ss2022.game.gameobjects.Knight;
import at.compus02.swd.ss2022.game.playableChars.MainPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {
    @Override
    public boolean keyDown(int keycode) {
        return true;
    }
}
