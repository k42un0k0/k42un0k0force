package org.example.k42un0k0force.spcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sp/users")
public class SpUsersController {
    @GetMapping("")
    public String index() {
        return "users/index";
    }
}
