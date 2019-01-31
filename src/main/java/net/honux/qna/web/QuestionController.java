package net.honux.qna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Question question = questionRepository.findById(id).orElseThrow(IllegalAccessException::new);
        model.addAttribute("question", question);
        if (HttpSessionUtils.isSessionUserQuesion(session, question)) {
            model.addAttribute("owner",question.getAuthor().getUid());
        }
        return "/question/document";
    }

    @GetMapping("/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        Question question = questionRepository.findById(id).orElseThrow(IllegalAccessException::new);
        if (!HttpSessionUtils.isSessionUserQuesion(session, question)) {
            throw new IllegalAccessException("수정권한이 없습니다.");
        }
        model.addAttribute("question", question);
        return "/question/updateForm";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, Question question, HttpSession session) {
        System.out.println(question);
        return "redirect:/question/" + id;
    }
}
