package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        if(!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }
        return "question/form";
    }

    @PostMapping("/post")
    public String create(String title, String text, HttpSession session) {
        if(!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionUser = HttpSessionUtils.getSessionUser(session);
        Question question = new Question(sessionUser, title, text);
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }
        Optional<Question> byId = questionRepository.findById(id);
        if (!byId.isPresent()) {
            throw new IllegalAccessException("그런 게시물이 없습니다.");
        }
        Question question = byId.get();
        model.addAttribute("question", question);
        return "/question/document";
    }
}
