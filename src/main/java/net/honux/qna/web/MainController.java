package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        long num = userRepository.count();
        long qnum = questionRepository.count();
        model.addAttribute("num", num);
        model.addAttribute("qnum", qnum);
        return "index";
    }
}
