package d1.project.nanjing.liuhe.unify.manage.service;

import com.github.houbb.heaven.util.secrect.Md5Util;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.CacheManger;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.common.utils.validatecode.IVerifyCodeGen;
import d1.project.nanjing.liuhe.common.utils.validatecode.SimpleCharVerifyCodeGenImpl;
import d1.project.nanjing.liuhe.common.utils.validatecode.VerifyCode;
import d1.project.nanjing.liuhe.unify.manage.dao.DictionaryDao;
import d1.project.nanjing.liuhe.unify.manage.entity.DictionaryEntity;
import d1.project.nanjing.liuhe.unify.manage.model.PublicSendSmsVm;
import net.dcrun.component.ehcache.IEhcacheService;
import net.dcrun.component.sms.aliyun.ISmsAliyunService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.util.Assert;

@Service
public class PublicService {

    private final IEhcacheService ehcacheService;
    private final ISmsAliyunService smsAliyunService;
    private final DictionaryDao dictionaryDao;

    @Value("${nanjing.liuhe.sms.templateId}")
    private String templateId;

    @Value("${nanjing.liuhe.sms.tokenKey}")
    private String tokenKey;

    public PublicService(ISmsAliyunService smsAliyunService, DictionaryDao dictionaryDao) {
        this.smsAliyunService = smsAliyunService;
        this.dictionaryDao = dictionaryDao;
        this.ehcacheService = CacheManger.getInstance().getCache();
    }

    /**
     * 发送短信验证码
     *
     * @param model
     * @author wkk
     **/
    public void sendSms(PublicSendSmsVm model) throws Exception {
        String md5 = Md5Util.md5(model.getPhone() + tokenKey);
//        Assert.isTrue(!model.getSmsToken().equals(md5), "发送失败");
        if (!model.getSmsToken().equals(md5)) throw new DoValidException("发送失败");

        String smsCode = BaseUtils.getSmsCode();
        ehcacheService.setExpireString(model.getPhone(), smsCode, 10 * 60);//将验证码绑定到某个key
        Map<String,String> param = new HashMap<>();
        param.put("name", "南京六合");
        param.put("smsCode", smsCode);
        smsAliyunService.send(param, model.getPhone(), templateId);
    }

    /**
     * 生成验证码
     *
     * @param parms
     * @param request
     * @param response
     * @author wkk
     * @date 2021/7/6 14:37
     * @return void
     **/
    public void verifyCode(String parms, HttpServletRequest request, HttpServletResponse response) throws IOException, DoValidException {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        //设置长宽
        VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
        String code = verifyCode.getCode();
        if (StringUtils.isEmpty(parms)) {
            throw new DoValidException("绑定验证码的参数不能为空");
        }
        ehcacheService.setExpireString(parms, code, 10 * 60);//将验证码绑定到某个key
        System.out.println("生成的验证码：" + code + ", key : " + parms);
        //将VerifyCode绑定session
//        request.getSession().setAttribute("VerifyCode", code);
        //设置响应头
        response.setHeader("Pragma", "no-cache");
        //设置响应头
        response.setHeader("Cache-Control", "no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires", 0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        response.getOutputStream().write(verifyCode.getImgBytes());
        response.getOutputStream().flush();
    }

    /**
     * 生成验证码
     *
     * @param parms
     * @author wkk
     * @date 2021/7/6 14:37
     * @return void
     **/
    public String verifyCode(String parms) throws IOException, DoValidException {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        //设置长宽
        VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
        String code = verifyCode.getCode();
        if (StringUtils.isEmpty(parms)) {
            throw new DoValidException("绑定验证码的参数不能为空");
        }
        ehcacheService.setExpireString(parms, code, 10 * 60);//将验证码绑定到某个key
        System.out.println("生成的验证码：" + code + ", key : " + parms);

        return byteToBase64(verifyCode.getImgBytes());
    }

    /**
     * 查询字典
     * @param type
     * @author wkk
     **/
    public List<DictionaryEntity> findByType(String type) {
        Assert.isTrue(!StringUtils.isEmpty(type), "字典类型不可为空");
        return dictionaryDao.findAllByType(type);
    }

    /**
     * byte转Base64
     * @param b
     * @author wkk
     * @date 2021/7/14 10:26
     * @return java.lang.String
     **/
    private String byteToBase64(byte[] b) {
        return DatatypeConverter.printBase64Binary(b);
    }


}
