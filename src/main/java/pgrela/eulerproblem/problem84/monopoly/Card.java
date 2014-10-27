package pgrela.eulerproblem.problem84.monopoly;

import java.util.List;

import com.google.common.collect.Lists;

public enum Card {
    DO_NOTHING,
    ADVANCE_TO_GO,
    GO_TO_JAIL,
    GO_TO_C1,
    GO_TO_E3,
    GO_TO_H2,
    GO_TO_R1,
    GO_TO_NEXT_RAILWAY_COMPANY1,
    GO_TO_NEXT_RAILWAY_COMPANY2,
    GO_TO_NEXT_U_UTILITY_COMPANY,
    GO_BACK_3_SQUARES;
    public static final int DECK_SIZE = 16;
    public static List<Card> chanceFunctionalCards = Lists.newArrayList(
            ADVANCE_TO_GO,
            GO_TO_JAIL,
            GO_TO_C1,
            GO_TO_E3,
            GO_TO_H2,
            GO_TO_R1,
            GO_TO_NEXT_RAILWAY_COMPANY1,
            GO_TO_NEXT_RAILWAY_COMPANY2,
            GO_TO_NEXT_U_UTILITY_COMPANY,
            GO_BACK_3_SQUARES);
    public static List<Card> communityChestFunctionalCards = Lists.newArrayList(
            ADVANCE_TO_GO,
            GO_TO_JAIL);
}
