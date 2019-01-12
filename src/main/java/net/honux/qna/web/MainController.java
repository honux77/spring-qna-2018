package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("num", num);
        model.addAttribute("qnum", qnum);
        model.addAttribute("questions", questions);
        return "index";
    }
}
