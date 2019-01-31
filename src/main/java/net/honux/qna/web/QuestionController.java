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
    public String create(String title, String contents, HttpSession session) {
        if(!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        User sessionUser = HttpSessionUtils.getSessionUser(session);
        Question question = new Question(sessionUser, title, contents);
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
            model.addAttribute("update",question.getId());
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
    public String update(@PathVariable Long id, String title, String contents, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (!HttpSessionUtils.isSessionUserQuesion(session, question)) {
            throw new IllegalAccessException("수정권한이 없습니다.");
        }


        question.setTitle(title);
        question.setContents(contents);
        questionRepository.save(question);
        return "redirect:/question/" + id;
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session) throws IllegalAccessException {
        if (!HttpSessionUtils.isUserLogin(session)) {
            return "redirect:/users/loginForm";
        }

        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (!HttpSessionUtils.isSessionUserQuesion(session, question)) {
            throw new IllegalAccessException("삭제권한이 없습니다.");
        }

        questionRepository.delete(question);
        return "redirect:/";
    }
}
