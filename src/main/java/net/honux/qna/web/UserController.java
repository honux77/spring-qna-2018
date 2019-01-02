package net.honux.qna.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> userList;
    {
        userList = new ArrayList<User>();
    }

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("Create User: " + user);
        userList.add(user);
        return "redirect:/list";
    }

    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable int uid, Model model) {
        User user = getUser(uid);
        if (user == null) {
            return "redirect:/error.html";
        } else {
            model.addAttribute(user);
            return "edit";
        }
    }

    @PostMapping("/edit")
    public String edit(int uid, String email, String name, String password, String password2) {
        User u = getUser(uid);
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setPassword2(password2);
        return "redirect:/list";
    }


    private User getUser(int uid) {
        User user = null;
        for (User u:userList) {
            if (u.getUid() == uid) {
                user = u;
                break;
            }
        }
        return user;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userList);
        return "list";
    }

}
