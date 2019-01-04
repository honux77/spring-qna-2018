package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main(Model model) {
        long num = userRepository.count();
        model.addAttribute("num", num);
        return "index";
    }

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("Create User: " + user);
        userRepository.save(user);
        //return "redirect:/list";
        return "create";
    }

    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable Long uid, Model model) {
        User user = userRepository.getOne(uid);
        if (user == null) {
            return "redirect:/error.html";
        } else {
            model.addAttribute(user);
            return "edit";
        }
    }

    @PostMapping("/edit")
    public String edit(Long uid, String email, String name, String password) {
        User u = userRepository.getOne(uid);
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        userRepository.save(u);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list";
    }
}
