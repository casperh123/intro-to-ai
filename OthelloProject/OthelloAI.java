package OthelloProject;

public class OthelloAI implements IOthelloAI {

    private int depth;
    private int max_depth = 4;

    public OthelloAI() {
    }

    public Position MiniMax(GameState state) {
        return MaxValue(state).move;
    }

    public UtilityMove MaxValue(GameState state) {
        if(state.isFinished()) {
            return new UtilityMove(Utility(state), null);
        }

        UtilityMove utilityMove = new UtilityMove(Integer.MIN_VALUE, null);

        for(Position position : state.legalMoves()) {
            GameState newState = new GameState(state.getBoard(), state.getPlayerInTurn());
            newState.insertToken(position);

            UtilityMove secondMove = MinValue(newState);

            if(secondMove.utility > utilityMove.utility) {
                utilityMove = new UtilityMove(secondMove.utility, position);
            }
        }

        return utilityMove;
    }

    public UtilityMove MinValue(GameState state) {
        if(state.isFinished()) {
            return new UtilityMove(Utility(state), null);
        }

        UtilityMove utilityMove = new UtilityMove(Integer.MAX_VALUE, null);

        for(Position position : state.legalMoves()) {
            GameState newState = new GameState(state.getBoard(), state.getPlayerInTurn());
            newState.insertToken(position);

            UtilityMove secondMove = MinValue(newState);

            if(secondMove.utility < utilityMove.utility) {
                utilityMove = new UtilityMove(secondMove.utility, position);
            }
        }

        return utilityMove;
    }

    public int Utility(GameState state) {
        return state.countTokens()[1];
    }

    @Override
    public Position decideMove(GameState s) {
        depth = 0;

        Position position = MiniMax(s);
        return position;
    }

    public class UtilityMove {
        public int utility;
        public Position move;

        public UtilityMove(int utility, Position move) {
            this.utility = utility;
            this.move = move;
        }
    }
}
