package d1.project.nanjing.liuhe.common.utils;

import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.CacheManger;
import net.coobird.thumbnailator.Thumbnails;
import net.dcrun.component.ehcache.IEhcacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.regex.Pattern;

/**
 * d1project
 *
 * @author kikki
 * @date 2020-09-07 16:00
 */
public class BaseUtils {

    private static final String[] HAN_ARR = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNIT_ARR = {"十", "百", "千", "万", "十", "白", "千", "亿", "十", "百", "千"};
    private static Map<String, ScheduledFuture<?>> timedMap = new HashMap<>(1);
    private static Random rand = new Random();

    public static String generate32Id() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 获取真实ip地址，避免获取代理ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        String ipAddresses = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if (ipAddresses == null || ipAddresses.length() == 0 || unknown.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || unknown.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || unknown.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || unknown.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("X-Real-IP");
        }
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String toHanStr(Integer number) {
        String numStr = number + "";
        StringBuilder result = new StringBuilder();
        int numLen = numStr.length();

        for (int i = 0; i < numLen; i++) {
            int num = numStr.charAt(i) - 48;
            if (i != numLen - 1 && num != 0) {
                result.append(HAN_ARR[num]).append(UNIT_ARR[numLen - 2 - i]);
                if (number >= 10 && number < 20) {
                    result = new StringBuilder(result.substring(1));
                }
            } else {
                if (!(number >= 10 && number % 10 == 0)) {
                    result.append(HAN_ARR[num]);
                }
            }
        }

        return result.toString();

    }

    public static Map<String, ScheduledFuture<?>> getTimedMap() {
        return timedMap;
    }

    public static void setTimedMap(Map<String, ScheduledFuture<?>> timedMap) {
        BaseUtils.timedMap = timedMap;
    }

    /**
     * 复制属性
     *
     * @param source 来源
     * @param target 目标
     * @return T
     */
    public static <T> T copyProperties(T source, T target) {
        String[] nullProperties = getNullProperties(source);
        BeanUtils.copyProperties(source, target, nullProperties);
        return target;
    }

    /**
     * 获取对象的空属性
     */
    public static String[] getNullProperties(Object src) {
        // 1.获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        // 2.获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        // 3.获取Bean的空属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (propertyValue == null) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }

    /**
     * 简单校验图片格式
     * @param suffix
     **/
    public static void verifyImageFomat(String suffix) throws DoValidException {
        String img = "png,ico,svg,bmp,jpeg,jpg,gif";
        for (String s : img.split(",")) {
            if (s.equalsIgnoreCase(suffix)) return;
        }
        throw new DoValidException("图片格式不正确：" + suffix);
    }

    /**
     * 简单校验附件格式
     * @param suffix
     **/
    public static void verifyAttachmentFomat(String suffix) throws DoValidException {
        String img = "zip,zipx,rar,tar,tgz,7z,gz,doc,docx,xls,xlsx,ppt,pptx,txt,xml,pdf,png,ico,svg,bmp,jpeg,jpg,gif,avi,rmvb,rm,asf,divx,mpg,mpeg,mpe,wmv,mp4,mkv,vob";
        for (String s : img.split(",")) {
            if (s.equalsIgnoreCase(suffix)) return;
        }
        throw new DoValidException("附件格式不正确：" + suffix);
    }

    /**
     * 简单校验视频格式
     * @param suffix
     **/
    public static void verifyVideoFomat(String suffix) throws DoValidException {
        String img = "avi,rmvb,rm,asf,divx,mpg,mpeg,mpe,wmv,mp4,mkv,vob";
        for (String s : img.split(",")) {
            if (s.equalsIgnoreCase(suffix)) return;
        }
        throw new DoValidException("视频格式不正确：" + suffix);
    }

    /**
     * 验证，验证码是否正确
     * @param id
     * @param code
     * @author wkk
     **/
    public static void verifyCode(String id, String code) throws DoValidException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(code)) throw new DoValidException("验证码无效");
        IEhcacheService cache = CacheManger.getInstance().getCache();
        //验证图像验证码
        if (!cache.containsKey(id)) throw new DoValidException("验证码无效");
        if (!code.equalsIgnoreCase(cache.getString(id))) {
            cache.removeData(id);
            throw new DoValidException("验证码无效");
        }
        cache.removeData(id);
    }

    /**
     * 验证，短信验证码
     * @param id
     * @param code
     * @author wkk
     **/
    public static void verifySmsCode(String id, String code) throws DoValidException {
        IEhcacheService cache = CacheManger.getInstance().getCache();
        if (!cache.containsKey(id) || !code.equalsIgnoreCase(cache.getString(id))) throw new DoValidException("验证码无效");
        cache.removeData(id);
    }

    /**
     * 生成6位随机数字，作为短信验证码
     * @author wkk
     **/
    public static String getSmsCode() {
        int MAX = 999999;
        int MIN = 0;
        String bu = "";
        int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;
        for (int i = 0; i < 6 - (randNumber+"").length(); i++) {
            bu += "0";
        }
        return bu + randNumber;
    }

    /**
     * 图片压缩，按质量压缩
     * @param inputStream
     * @param imgPath
     * @author wkk
     * @date 2021/8/6 10:10
     * @return void
     **/
    public static void compressImg(InputStream inputStream, String imgPath,String imgName) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(imgPath + imgName)) {
            Thumbnails.of(inputStream)
                    .scale(1f) //图片大小（长宽）压缩比例 从0-1，1表示原图
                    .outputQuality(0.5f) //图片质量压缩比例 从0-1，越接近1质量越好
                    .toOutputStream(fileOutputStream);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 复制文件，生成随机名称
     * @param files
     * @author wkk
     * @date 2021/8/9 11:23
     * @return org.springframework.web.multipart.MultipartFile[]
     **/
    public static MultipartFile[] copyMockMultipartFile(MultipartFile[] files) throws IOException {
        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //File转换成MultipartFile
            files[i] = new MockMultipartFile(files[i].getName(), BaseUtils.generate32Id() + suffixName, files[i].getContentType(), files[i].getInputStream());
        }
        return files;
    }

    /**
     * 正则匹配
     *
     * @param contet
     * @param regx
     * @return
     */
    public static boolean Regx(String contet, String regx) {
        return !StringUtils.isEmpty(contet) && Pattern.matches(regx, contet);
    }

    /**
     * Map转换
     * @param orgMap
     * @author wkk
     * @date 2021/9/1 15:34
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String, Object> transformMapKey(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }

        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = fieldToProperty(key);
            resultMap.put(newKey, orgMap.get(key));
        }
        return resultMap;
    }

    /**
     * 字段转换成对象属性 例如：user_name to userName
     *
     * @param field
     * @return
     */
    public static String fieldToProperty(String field) {
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(String.valueOf(chars[j]).toUpperCase());
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
