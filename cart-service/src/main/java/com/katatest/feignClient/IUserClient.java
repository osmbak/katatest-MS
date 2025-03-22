package com.katatest.feignClient;


import com.katatest.Dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface IUserClient {
    @GetMapping("/api/auth/userConnected")
    UserDTO getUserConnected();
}

