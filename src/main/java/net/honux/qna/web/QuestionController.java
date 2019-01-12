package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        if(!HttpSessionUtils.isUserLogin(session)) {
            return "/users/loginForm";
        }
        return "question/form";
    }

    @PostMapping("/post")
    public String create(String title, String text, HttpSession session) {
        if(!HttpSessionUtils.isUserLogin(session)) {
            return "/users/loginForm";
        }
        String name = HttpSessionUtils.getSessionUser(session).getName();
        Question question = new Question(name, title, text);
        questionRepository.save(question);
        return "redirect:/";
    }
}
