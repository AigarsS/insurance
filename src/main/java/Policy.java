import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    private String number;
    private String status;
    private List<PolicyObject> policyObjects = new ArrayList<>();

    public void addPolicyObject(PolicyObject policyObject){
        policyObjects.add(policyObject);
    }
}
