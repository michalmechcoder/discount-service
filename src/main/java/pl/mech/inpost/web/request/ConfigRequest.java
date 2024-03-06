package pl.mech.inpost.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mech.inpost.domain.DiscountType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigRequest {

    private DiscountType policy;
}
