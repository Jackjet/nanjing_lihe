package d1.project.nanjing.liuhe;

import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SignInRetryLimitManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.system.service.PostgreSqlBackupService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Buter
 * @date 2020/3/20 14:59
 */
@Component
public class MyAppRunner implements ApplicationRunner {
    private final PostgreSqlBackupService postgreSqlBackupService;

    public MyAppRunner(@Value("${d1.project.user.lock.retry.count:5}") int lockRetryCount,  //锁定用户的错误次数
                       @Value("${d1.project.user.lock.period:10}") int lockPeriod,//锁定时长
                       PostgreSqlBackupService postgreSqlBackupService) throws DoValidException {
        this.postgreSqlBackupService = postgreSqlBackupService;
        //初始化登录验证
        SignInRetryLimitManager.getInstance(lockRetryCount, lockPeriod);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        postgreSqlBackupService.startAndRun();
        initFolders();
    }

    private void initFolders() throws Exception {
        FileUtils.forceMkdir(new File(Constants.FILES_IMAGES));
        FileUtils.forceMkdir(new File(Constants.FILES_ATTACHMENT));
    }
}