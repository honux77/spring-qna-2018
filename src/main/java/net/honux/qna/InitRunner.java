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
        if (args.length == 0 || !args[0].contains("init")) {
            System.out.println("DB Init passed..");
            return;
        }

        System.out.println("Start creating users..");

        String line = "";
        File f = new ClassPathResource("user.csv").getFile();
        BufferedReader br = new BufferedReader(new FileReader(f));

        while((line = br.readLine()) != null) {
            String[] u = line.split(",");
            User user = new User();
            user.setEmail(u[0]);
            user.setName(u[1]);
            user.setPassword(u[2]);
            userController.create(user);
        }
        br.close();
    }
}
