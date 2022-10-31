package my.repo.boot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyRepoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyRepoApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}