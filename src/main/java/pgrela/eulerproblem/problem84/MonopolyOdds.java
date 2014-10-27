package pgrela.eulerproblem.problem84;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem84.monopoly.Card.DECK_SIZE;
import static pgrela.eulerproblem.problem84.monopoly.Card.chanceFunctionalCards;
import static pgrela.eulerproblem.problem84.monopoly.Card.communityChestFunctionalCards;
import static pgrela.eulerproblem.problem84.monopoly.Field.C1;
import static pgrela.eulerproblem.problem84.monopoly.Field.E3;
import static pgrela.eulerproblem.problem84.monopoly.Field.G2J;
import static pgrela.eulerproblem.problem84.monopoly.Field.GO;
import static pgrela.eulerproblem.problem84.monopoly.Field.H2;
import static pgrela.eulerproblem.problem84.monopoly.Field.JAIL;
import static pgrela.eulerproblem.problem84.monopoly.Field.R1;
import static pgrela.eulerproblem.problem84.monopoly.Field.isChanceField;
import static pgrela.eulerproblem.problem84.monopoly.Field.isCommunityChoiceField;
import static pgrela.eulerproblem.problem84.monopoly.Field.jump;
import static pgrela.eulerproblem.problem84.monopoly.Field.nextRailway;
import static pgrela.eulerproblem.problem84.monopoly.Field.nextUtilityCompany;
import static pgrela.eulerproblem.problem84.monopoly.GameState.gameState;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem84.monopoly.Deck;
import pgrela.eulerproblem.problem84.monopoly.DiceRoll;
import pgrela.eulerproblem.problem84.monopoly.Field;
import pgrela.eulerproblem.problem84.monopoly.GameState;

public class MonopolyOdds implements EulerSolver {

    public static final int DICE = 4;
    public static final int MONTE_CARLO = 10000000;

    public static void main(String[] args) {
        printSolution(MonopolyOdds.class);
    }

    Deck communityDeck = new Deck(communityChestFunctionalCards, DECK_SIZE);
    Deck chancesDeck = new Deck(chanceFunctionalCards, 16);

    public String solveToString() {
        return Stream.iterate(gameState(GO), this::nextTurn)
                .limit(MONTE_CARLO)
                .collect(Collectors.toMap(GameState::getField, gs -> 1, Integer::sum)).entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .map(Field::number)
                .map(i -> String.format("%02d", i))
                .collect(Collectors.joining()).substring(0, 6);
    }

    GameState nextTurn(GameState gameState) {
        DiceRoll diceRoll = new DiceRoll(DICE);

        int doublesInRow = gameState.getDoubleRollsInRow();
        if (diceRoll.isDouble()) {
            doublesInRow++;
        } else {
            doublesInRow = 0;
        }
        if (doublesInRow % 3 == 0 && doublesInRow > 0) {
            return GameState.GAME_STATE_IN_JAIL;
        }
        Field from = jump(gameState.getField(), diceRoll.getSum());
        if (from.equals(G2J)) {
            return GameState.GAME_STATE_IN_JAIL;
        }
        if (isChanceField(from)) {
            return gameState(drawChanceCard(from), doublesInRow);
        }
        if (isCommunityChoiceField(from)) {
            return gameState(drawCommunityChestCard(from), doublesInRow);
        }
        return gameState(from, doublesInRow);
    }

    private Field drawCommunityChestCard(Field from) {
        switch (communityDeck.nextCard()) {
            case ADVANCE_TO_GO:
                return GO;
            case GO_TO_JAIL:
                return JAIL;
            default:
                return from;
        }
    }

    private Field drawChanceCard(Field from) {
        switch (chancesDeck.nextCard()) {
            case ADVANCE_TO_GO:
                return GO;
            case GO_TO_JAIL:
                return JAIL;
            case GO_TO_C1:
                return C1;
            case GO_TO_E3:
                return E3;
            case GO_TO_H2:
                return H2;
            case GO_TO_R1:
                return R1;
            case GO_TO_NEXT_RAILWAY_COMPANY1:
            case GO_TO_NEXT_RAILWAY_COMPANY2:
                return nextRailway(from);
            case GO_TO_NEXT_U_UTILITY_COMPANY:
                return nextUtilityCompany(from);
            case GO_BACK_3_SQUARES:
                return jump(from, -3);
            default:
                return from;
        }
    }


}
