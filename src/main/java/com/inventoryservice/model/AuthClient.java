package com.inventoryservice.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-serviceS", url = "http://localhost:8084/auth")
public interface AuthClient {

    @PostMapping (value = "/validate")
    TokenValidationResponse validateToken(@RequestBody TokenValidationRequest token);

}

