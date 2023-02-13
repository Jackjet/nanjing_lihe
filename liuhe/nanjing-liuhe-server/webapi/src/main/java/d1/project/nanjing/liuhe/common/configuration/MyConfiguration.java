package d1.project.nanjing.liuhe.common.configuration;

import net.dcrun.component.file.server.FileServerService;
import net.dcrun.component.file.server.IFileServerService;
import net.dcrun.component.http.HttpService;
import net.dcrun.component.http.IHttpService;
import net.dcrun.component.security.HmacSignService;
import net.dcrun.component.security.IHmacSignService;
import net.dcrun.component.sms.aliyun.ISmsAliyunService;
import net.dcrun.component.sms.aliyun.SmsAliyunService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Buter
 * @date 2020/3/10 10:37
 */
@Configuration
public class MyConfiguration {

    @Value("${nanjing.liuhe.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${nanjing.liuhe.sms.accessKeySecret}")
    private String accessKeySecret;
    @Value("${nanjing.liuhe.sms.signName}")
    private String signName;

    @Bean
    IHttpService httpService() {
        return new HttpService();
    }

    @Bean
    IFileServerService fileServerService() {
        return new FileServerService();
    }

    @Bean
    IHmacSignService hmacSignService() {
        return new HmacSignService();
    }

    @Bean
    ISmsAliyunService smsAliyunService() {
        SmsAliyunService aliyunDoSMS = new SmsAliyunService();
        aliyunDoSMS.init(accessKeyId, accessKeySecret, signName);
        return aliyunDoSMS;
    }
}
