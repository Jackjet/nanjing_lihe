package d1.project.nanjing.liuhe;

import d1.framework.webapi.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NanjingLiuheApplication extends BaseApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(NanjingLiuheApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
