import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicySubObject {
    private String name;
    private Double sumInsured;
    private RiskType riskType;
}
