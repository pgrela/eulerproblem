package pgrela.eulerproblem.problem84.monopoly;

import static pgrela.eulerproblem.problem84.monopoly.Field.JAIL;

public class GameState {
    public static final GameState GAME_STATE_IN_JAIL = gameState(JAIL);
    Field field;
    int doubleRollsInRow;

    public Field getField() {
        return field;
    }

    public int getDoubleRollsInRow() {
        return doubleRollsInRow;
    }

    public GameState(Field field, int doubleRollsInRow) {
        this.field = field;
        this.doubleRollsInRow = doubleRollsInRow;
    }

    public static GameState gameState(Field field, int doubleRollsInRow) {
        return new GameState(field, doubleRollsInRow);
    }

    public static GameState gameState(Field field) {
        return new GameState(field, 0);
    }
}
