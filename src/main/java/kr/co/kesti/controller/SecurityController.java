package kr.co.kesti.controller;

import kr.co.kesti.model.response.ApiResponse;
import kr.co.kesti.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class SecurityController {
    @PostMapping("/isAuthenticated")
    public Map<String, Object> isAuthenticated() {
        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", SessionUtils.isAuthenticated());
        return response;
    }
}