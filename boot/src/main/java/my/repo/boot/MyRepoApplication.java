package my.repo.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "my.repo.*")
@MapperScan("my.repo.infrastructure.mapper")
@EnableFeignClients(basePackages = "my.repo.present.feign")
@EnableAsync
@ComponentScan(basePackages = {"my.repo.*","my.repo.common.config"})
public class MyRepoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MyRepoApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}