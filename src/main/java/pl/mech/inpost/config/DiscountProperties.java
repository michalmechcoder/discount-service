package pl.mech.inpost.config;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import pl.mech.inpost.domain.DiscountType;

import java.math.BigDecimal;
import java.util.List;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "discount")
public class DiscountProperties {

    @Data
    @AllArgsConstructor
    public static class PolicyStep {

        private Integer items;

        private BigDecimal value;
    }

    @NotNull
    private DiscountType policy;

    @NotNull
    private List<PolicyStep> amountPolicySteps;

    @NotNull
    private List<PolicyStep> percentagePolicySteps;
}
