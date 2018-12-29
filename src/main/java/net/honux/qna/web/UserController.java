package net.honux.qna.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("create")
    public String create(String email, String name, String password, String password2, Model model) {
        System.out.printf("create page: %s %s %s %s\n", email, name, password, password2);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        return "create";
    }

}
