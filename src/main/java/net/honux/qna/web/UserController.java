package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        User user = null;
        try {
            user = userRepository.findById(uid).get();
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage","해당 사용자를 찾을 수 없습니다.");
            return "/error";
        }
        model.addAttribute(user);
        return "/edit";
    }

    @PostMapping("/edit")
    public String edit(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list";
    }
}
