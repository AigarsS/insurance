package org.insurance.policies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyObject {
    private String name;
    private List<PolicySubObject> policySubObjects = new ArrayList<>();

    public void addPolicySubObject(PolicySubObject policySubObject){
        policySubObjects.add(policySubObject);
    }
}
