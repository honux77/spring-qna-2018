package net.honux.qna;

import net.honux.qna.web.User;
import net.honux.qna.web.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class Step2InitRunner implements CommandLineRunner {

    @Autowired
    private UserController userController;

    @Override
    public void run(String... args) throws Exception {

        String csvFile = "user.csv";
        String line = "";
        if (args.length != 0) {
            csvFile = args[0];
        }

        File f = new ClassPathResource(csvFile).getFile();
        BufferedReader br = new BufferedReader(new FileReader(f));
        while((line = br.readLine()) != null) {
            String[] u = line.split(",");
            User user = new User(u[0], u[1], u[2], u[3]);
            userController.create(user);
        }
        br.close();
    }
}
