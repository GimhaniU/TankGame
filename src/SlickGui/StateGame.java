package SlickGui;

import Parser.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Nuwantha on 12/16/2015.
 */
public class StateGame extends StateBasedGame {
    GameContainer gamecontainer=null;
    public StateGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.gamecontainer=gameContainer;
        this.addState(new SetUpGame());
    }

    public void update(Map map) throws SlickException {
        GameState currentState = this.getCurrentState();
        currentState.update(this.gamecontainer, this, 0);
    }
}
