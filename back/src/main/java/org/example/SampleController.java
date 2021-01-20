package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    protected SampleController(){}

    @GetMapping("/sample")
    String sample(){
        String aaa = "hello world";
        return aaa;
    }
}
