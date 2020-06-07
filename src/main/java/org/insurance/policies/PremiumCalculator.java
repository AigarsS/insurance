package org.insurance.policies;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiumCalculator {

    public Double calculate(Policy policy){

        // Getting list of all PolicySubObjects for further processing
        List<PolicySubObject> policySubObjects = policy.getPolicyObjects()
                .stream()
                .map(policyObject -> policyObject.getPolicySubObjects())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Double sumInsuredFire = calculateHelper(policySubObjects, RiskType.FIRE);
        Double sumInsuredTheft = calculateHelper(policySubObjects, RiskType.THEFT);
        Double premium = sumInsuredFire + sumInsuredTheft;

        return premium;
    }

    // Helper method for calculating premium values depending on risk types
    private Double calculateHelper(List<PolicySubObject> policySubObjects, RiskType riskType) {
        Double coefficient;
        Double premium;

        if (riskType.includeAbove) {
            coefficient = riskType.coefficientAbove;
        } else {
            coefficient = riskType.coefficientBelow;
        }

        Double premiumAbove = policySubObjects.stream()
                .filter(policySubObject -> policySubObject.getRiskType() == riskType )
                .filter(policySubObject -> policySubObject.getSumInsured() > riskType.limit)
                .map(policySubObject -> policySubObject.getSumInsured() * riskType.coefficientAbove)
                .collect(Collectors.summingDouble(Double::doubleValue));

        Double premiumBelow = policySubObjects.stream()
                .filter(policySubObject -> policySubObject.getRiskType() == riskType )
                .filter(policySubObject -> policySubObject.getSumInsured() < riskType.limit)
                .map(policySubObject -> policySubObject.getSumInsured() * riskType.coefficientBelow)
                .collect(Collectors.summingDouble(Double::doubleValue));

        Double premiumEqual = policySubObjects.stream()
                .filter(policySubObject -> policySubObject.getRiskType() == riskType )
                .filter(policySubObject -> policySubObject.getSumInsured().equals(riskType.limit))
                .map(policySubObject -> policySubObject.getSumInsured() * coefficient)
                .collect(Collectors.summingDouble(Double::doubleValue));

        premium = premiumBelow + premiumEqual + premiumAbove;

        return Math.round(premium *100)/100.0 ;

    }


}
