package com.exxecute.taskflow.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
    public class HealthController {
    private static final Logger logger = Logger.getLogger(HealthController.class.getName());

        @GetMapping("/health")
        public String home(HttpServletResponse response) {
            int status = response.getStatus();
            logger.info(String.valueOf(status));
            return "OK";
        }
    }
