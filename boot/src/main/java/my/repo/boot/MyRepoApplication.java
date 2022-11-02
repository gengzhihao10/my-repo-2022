package my.repo.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "my.repo.*")
@MapperScan("my.repo.infrastructure.mapper")
public class MyRepoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyRepoApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}