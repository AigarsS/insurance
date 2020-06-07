import org.insurance.policies.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PremiumCalculatorTest {
    private PremiumCalculator premiumCalculator;
    private Policy policy;
    private PolicyObject policyObject;

    @BeforeEach
    void setUp() {
        policy= new Policy();
        policyObject = new PolicyObject();
    }

    @Test
    void calculateSmall() {
        PolicySubObject tvSubObject = new PolicySubObject("TV", 100.0, RiskType.FIRE);
        PolicySubObject bikeSubObject = new PolicySubObject("Bike", 8.0, RiskType.THEFT);
        policyObject.addPolicySubObject(tvSubObject);
        policyObject.addPolicySubObject(bikeSubObject);
        policy.addPolicyObject(policyObject);

        premiumCalculator = new PremiumCalculator();
        Double premium = premiumCalculator.calculate(policy);

        assertThat(premium).isEqualTo(2.28);
    }

    @Test
    void calculateBig() {
        PolicySubObject sofaSubObject = new PolicySubObject("Sofa", 500.0, RiskType.FIRE);
        PolicySubObject computerSubObject = new PolicySubObject("Computer", 102.5, RiskType.THEFT);
        policyObject.addPolicySubObject(sofaSubObject);
        policyObject.addPolicySubObject(computerSubObject);
        policy.addPolicyObject(policyObject);

        premiumCalculator = new PremiumCalculator();
        Double premium = premiumCalculator.calculate(policy);

        assertThat(premium).isEqualTo(17.13);
    }
}