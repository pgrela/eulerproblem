package pgrela.eulerproblem.problem84.monopoly;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public enum Field {
    GO, A1, CC1, A2, T1, R1, B1, CH1, B2, B3,
    JAIL, C1, U1, C2, C3, R2, D1, CC2, D2, D3,
    FP, E1, CH2, E2, E3, R3, F1, F2, U2, F3,
    G2J, G1, G2, CC3, G3, R4, CH3, H1, T2, H2;

    private static Set<Field> chances = newHashSet(CH1, CH2, CH3);
    private static Set<Field> communityChoices = newHashSet(CC1, CC2, CC3);
    private static Set<Field> railways = newHashSet(R1, R2, R3, R4);
    private static Set<Field> utilityCompanies = newHashSet(U1, U2);

    public static BiMap<Field, Integer> fields = HashBiMap.create();

    static {
        for (int i = 0; i < Field.values().length; i++) {
            fields.put(Field.values()[i], i);
        }
    }

    public static Field jump(Field from, int moveBy) {
        int next = number(from) + moveBy + fields.size();
        return fields.inverse().get(next % fields.size());
    }

    public static boolean isChanceField(Field field) {
        return chances.contains(field);
    }

    public static boolean isRailway(Field field) {
        return railways.contains(field);
    }

    public static boolean isUtilityCompany(Field field) {
        return utilityCompanies.contains(field);
    }

    public static boolean isCommunityChoiceField(Field field) {
        return communityChoices.contains(field);
    }

    public static Field nextRailway(Field from) {
        do {
            from = jump(from, 1);
        } while (!isRailway(from));
        return from;
    }

    public static Field nextUtilityCompany(Field from) {
        do {
            from = jump(from, 1);
        } while (!isUtilityCompany(from));
        return from;
    }

    public static Integer number(Field first) {
        return fields.get(first);
    }
}
