package net.honux.qna.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("create")
    public String create(User user) {
        System.out.println("create " + user);
        return "create";
    }

}
