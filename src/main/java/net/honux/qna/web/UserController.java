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
    public String list(Model model, HttpSession session) {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "/users/loginForm";
        }
        model.addAttribute("users", userRepository.findAll());

        if (HttpSessionUtils.isSessionAdmin(session)) {
            return "users/admin";
        }

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
             System.out.printf("Login Fail for user%s: password %s != %s\n", user.getEmail(), user.getPassword(), password);
            return "redirect:/users/loginForm";
        }
        session.setAttribute(HttpSessionUtils.SESSION_USER_KEY, user);
        System.out.printf("Login Success %s", user.getEmail());
        return "redirect:/";
    }

    //logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.SESSION_USER_KEY);
        return "redirect:/";
    }


    // user data update form
    @GetMapping("/{uid}/updateForm")
    public String updateForm(@PathVariable Long uid, Model model, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionUser = (User) HttpSessionUtils.getSessionUser(session);

        System.out.println(sessionUser.isAdmin());

        if (!sessionUser.getUid().equals(uid) && !sessionUser.isAdmin()) {
            throw new IllegalAccessException("User don't have right permission to access");
        }
        User updateUser = userRepository.findById(uid).get();
        model.addAttribute(updateUser);
        return "/users/update";


    }

    //update user data
    @PutMapping("{uid}/update")
    public String update(@PathVariable Long uid, User updatedUser, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionUser = HttpSessionUtils.getSessionUser(session);

        if (!sessionUser.getUid().equals(uid) && !sessionUser.isAdmin()) {
            throw new IllegalAccessException("You don't have a right permission to access");
        }

        User user = userRepository.findById(uid).get();
        user.update(updatedUser);
        userRepository.save(user);
        session.setAttribute(HttpSessionUtils.SESSION_USER_KEY, user);
        return "redirect:/users";
    }
}
