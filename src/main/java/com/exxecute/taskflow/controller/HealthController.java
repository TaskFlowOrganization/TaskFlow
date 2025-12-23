package com.exxecute.taskflow.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
    public class HealthController {

        @GetMapping("/")
        public String home(HttpServletResponse response) {
            int status = response.getStatus();
            System.out.println("status: " + status);
            return "OK";
        }
    }
