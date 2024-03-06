package pl.mech.inpost.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mech.inpost.config.DiscountProperties;
import pl.mech.inpost.web.request.ConfigRequest;

@RestController
@RequestMapping("config")
@RequiredArgsConstructor
public class ConfigController {

    private final DiscountProperties discountProperties;

    @Operation(summary = "You can use this operation for change discount policy in fly without restarting service. For testing purpose")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void changeDiscountPolicy(@RequestBody ConfigRequest configRequest) {
        discountProperties.setPolicy(configRequest.getPolicy());
    }
}
