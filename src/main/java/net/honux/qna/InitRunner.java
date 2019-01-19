package net.honux.qna;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run My Command Line Runner");
    }
}
