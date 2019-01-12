package net.honux.qna;

import net.honux.qna.web.User;
import net.honux.qna.web.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private UserController userController;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run Command Line Runner");
        for(String ss: args) {
            System.out.println(ss);
        }
    }
}
