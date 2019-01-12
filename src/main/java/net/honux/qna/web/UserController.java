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
        session.setAttribute("session-user", user);
        System.out.printf("Login Success %s", user.getEmail());
        return "redirect:/";
    }

    //logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }


    // user data update form
    @GetMapping("/{uid}/updateForm")
    public String updateForm(@PathVariable Long uid, Model model, HttpSession session) throws IllegalAccessException {
        User user = (User) session.getAttribute("session-user");
        if (user == null) {
            return "redirect:/users/loginForm";
        }

        if (!user.getUid().equals(uid)) {
            throw new IllegalAccessException("You don't have a right permission to access");
        }
        model.addAttribute(user);
        return "/users/update";
    }

    //update user data
    @PutMapping("{uid}/update")
    public String update(@PathVariable Long uid, User updatedUser, HttpSession session) throws IllegalAccessException {
        User sessionUser = (User) session.getAttribute("session-user");
        if (sessionUser == null) {
            return "redirect:/users/loginForm";

        }
        if (!sessionUser.getUid().equals(uid)) {
            throw new IllegalAccessException("You don't have a right permission to access");
        }

        User user = userRepository.findById(uid).get();
        user.update(updatedUser);
        userRepository.save(user);
        session.setAttribute("session-user", user);
        return "redirect:/users";
    }

}
