package OthelloProject;

public class OthelloAI implements IOthelloAI {
    public OthelloAI() {
    }

    public Position MinimMax(GameState state) {

        Position optimalPosition = null;
        //player <- game.TO-MOVE(state)
        //value, move <- MAX-VALUE(game, state)

        return optimalPosition;
    }

    public Position MaxValue(GameState state) {
        return null;
    }

    @Override
    public Position decideMove(GameState s) {
        return MinimMax(s);
    }
}
