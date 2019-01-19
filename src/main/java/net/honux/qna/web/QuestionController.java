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

        User sessionUser = HttpSessionUtils.getSessionUser(session);
        Question question = new Question(sessionUser, title, text);
        questionRepository.save(question);
        return "redirect:/";
    }
}
