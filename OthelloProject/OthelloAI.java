package OthelloProject;

public class OthelloAI implements IOthelloAI {

    private int depth;
    private int max_depth = 4;

    public OthelloAI() {
    }

    public Position MiniMax(GameState state) {
        return MaxValue(state, new StupidNumber(Integer.MIN_VALUE), new StupidNumber(Integer.MAX_VALUE)).move;
    }

    public UtilityMove MaxValue(GameState state, StupidNumber alpha, StupidNumber beta) {
        if(state.isFinished()) {
            return new UtilityMove(Utility(state), null);
        }

        UtilityMove utilityMove = new UtilityMove(Integer.MIN_VALUE, null);

        for(Position position : state.legalMoves()) {
            GameState newState = new GameState(state.getBoard(), state.getPlayerInTurn());
            newState.insertToken(position);

            UtilityMove secondMove = MinValue(newState, alpha, beta);

            if(secondMove.utility > utilityMove.utility) {
                utilityMove = new UtilityMove(secondMove.utility, position);
                alpha.Set(Math.max(alpha.Get(), utilityMove.utility));
            } else if(utilityMove.utility >= beta.Get()) return utilityMove;
        }

        return utilityMove;
    }

    public UtilityMove MinValue(GameState state, StupidNumber alpha, StupidNumber beta) {
        if(state.isFinished()) {
            return new UtilityMove(Utility(state), null);
        }

        UtilityMove utilityMove = new UtilityMove(Integer.MAX_VALUE, null);

        for(Position position : state.legalMoves()) {
            GameState newState = new GameState(state.getBoard(), state.getPlayerInTurn());
            newState.insertToken(position);

            UtilityMove secondMove = MaxValue(newState, alpha, beta);

            if(secondMove.utility < utilityMove.utility) {
                utilityMove = new UtilityMove(secondMove.utility, position);
                beta.Set(Math.min(beta.Get(), utilityMove.utility));
            } else if(utilityMove.utility <= alpha.Get()) return utilityMove;
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

    public class StupidNumber {
        private int Number;

        public StupidNumber(int yoo) {
            this.Number = yoo;
        }

        public void Set(int number) {
            this.Number = number;
        }

        public int Get() {
            return Number;
        }
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
