package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    //list all members
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    //user register form
    @GetMapping("/form")
    public String registerForm() {
        return "users/register";
    }

    //user register
    @PostMapping("")
    public String create(User user) {
        System.out.println("Create User: " + user);
        userRepository.save(user);
        return "/users/create";
    }

    //login form
    @GetMapping("/loginForm")
    public String loginForm() {
        return "users/login";
    }

    //login
    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (!user.getPassword().equals(password)) {
            //debug
             System.out.printf("Login Fail for user%s: password %s != %s\n", user.getEmail(), user.getPassword(), password);
            return "redirect:/users/loginForm";
        }
        session.setAttribute("user", user);
        System.out.printf("Login Success %s", user.getEmail());
        return "redirect:/";
    }

    //logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }


    @GetMapping("/{uid}/form")
    public String edit(@PathVariable Long uid, Model model) {
        User user = null;
        try {
            user = userRepository.findById(uid).get();
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage","해당 사용자를 찾을 수 없습니다.");
            return "/error";
        }
        model.addAttribute(user);
        return "/users/update";
    }

    @PutMapping("{uid}/update")
    public String edit(@PathVariable Long uid, User newUser) {
        User user = userRepository.findById(uid).get();
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users";
    }

}
