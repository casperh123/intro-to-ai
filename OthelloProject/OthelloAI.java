package OthelloProject;

import jdk.jshell.execution.Util;

public class OthelloAI implements IOthelloAI {
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
            state.insertToken(position);

            UtilityMove secondMove = MinValue(state);

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

        UtilityMove utilityMove = new UtilityMove(Integer.MIN_VALUE, null);

        for(Position position : state.legalMoves()) {
            state.insertToken(position);

            UtilityMove secondMove = MaxValue(state);

            if(secondMove.utility < utilityMove.utility) {
                utilityMove = new UtilityMove(secondMove.utility, position);
            }
        }

        return utilityMove;
    }

    public int Utility(GameState state) {
        return 1;
    }

    @Override
    public Position decideMove(GameState s) {
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
