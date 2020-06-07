package org.insurance.policies;

import java.util.HashMap;
import java.util.Map;

// Enum for various risk types. Enum can be easily updated with additional values adding
// ENUMNAME(coefficient below limit value, coefficient above limit value, limit value, is limit value included in upper limit)
public enum RiskType {
    FIRE(0.014, 0.024, 100.0, false),
    THEFT(0.11, 0.05, 15.0, true);

    private static final Map<Double, RiskType> BY_COEFFICIENT_BELOW = new HashMap<>();
    private static final Map<Double, RiskType> BY_COEFFICIENT_ABOVE = new HashMap<>();
    private static final Map<Double, RiskType> BY_LIMIT = new HashMap<>();
    private static final Map<Boolean, RiskType> BY_INCLUDE_ABOVE = new HashMap<>();

    static {
        for (RiskType rt : values()) {
            BY_COEFFICIENT_BELOW.put(rt.coefficientBelow, rt);
            BY_COEFFICIENT_ABOVE.put(rt.coefficientAbove, rt);
            BY_LIMIT.put(rt.limit, rt);
            BY_INCLUDE_ABOVE.put(rt.includeAbove, rt);
        }
    }

    public final Double coefficientBelow;
    public final Double coefficientAbove;
    public final Double limit;
    public final Boolean includeAbove;

    private RiskType(Double coefficientBelow, Double coefficientAbove, Double limit, Boolean includeAbove) {
        this.coefficientBelow = coefficientBelow;
        this.coefficientAbove = coefficientAbove;
        this.limit = limit;
        this.includeAbove = includeAbove;
    }

    public static RiskType valueOfBelow(Double coefficientBelow) {
        return BY_COEFFICIENT_BELOW.get(coefficientBelow);
    }

    public static RiskType valueOfAbove(Double coefficientAbove) {
        return BY_COEFFICIENT_ABOVE.get(coefficientAbove);
    }

    public static RiskType valueOfLimit(Double limit){
        return BY_LIMIT.get(limit);
    }

    public static RiskType valueOfInclude(Boolean includeAbove){
        return BY_INCLUDE_ABOVE.get(includeAbove);
    }
}
