package com.virtusa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class DemoController {

    private static final Log LOGGER = LogFactory.getLog(DemoController.class);

    @GetMapping
    public String hello(@RequestParam(name = "name", defaultValue = "Rocks!!!") String name) {
        LOGGER.info("name="+name);
        return "Welcome to Splunk, " + name;
    }
}
