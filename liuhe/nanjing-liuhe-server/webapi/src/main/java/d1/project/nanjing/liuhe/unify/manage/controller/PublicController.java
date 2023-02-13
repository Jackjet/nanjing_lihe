package d1.project.nanjing.liuhe.unify.manage.controller;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.unify.manage.entity.DictionaryEntity;
import d1.project.nanjing.liuhe.unify.manage.model.PublicSendSmsVm;
import d1.project.nanjing.liuhe.unify.manage.service.PublicService;
import net.dcrun.component.file.server.IFileServerService;
import net.dcrun.component.file.server.UploadResult;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 公共分类
 *
 * @author wkk
 **/
@RestController
public class PublicController {

    private final PublicService publicService;
    private final IFileServerService fileServerService;

    public PublicController(PublicService publicService, IFileServerService fileServerService) {
        this.publicService = publicService;
        this.fileServerService = fileServerService;
    }

    /**
     * 验证码
     *
     * @param verificationCodeId 验证码绑定参数
     * @author wkk
     **/
    @GetMapping("public/verifyCode")
    public Result<String> verifyCode(String verificationCodeId) throws IOException, DoValidException {
        return ResultUtil.success(Constants.SUCCESS, publicService.verifyCode(verificationCodeId));
    }

    /**
     * 下载图片
     *
     * @param fileName 文件名称
     * @param request  request
     * @return 下载流
     * @throws Exception 异常
     */
    @GetMapping("public/download/image/{fileName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        return fileServerService.downloadFile(request, Constants.FILES_IMAGES, fileName);
    }

    /**
     * 下载附件
     *
     * @param fileName 文件名称
     * @param request  request
     * @return 下载流
     * @throws Exception 异常
     */
    @GetMapping("public/download/attachment/{fileName:.+}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        return fileServerService.downloadFile(request, Constants.FILES_ATTACHMENT, fileName);
    }

    /**
     * 发送短信验证码
     *
     * @param model
     * @author wkk
     **/
    @PostMapping("public/sendSms")
    public Result<String> sendSms(@Valid @RequestBody PublicSendSmsVm model) {
        try {
            publicService.sendSms(model);
            return ResultUtil.success(Constants.SEND_SUCCESS);
        } catch (Exception e) {
            return ResultUtil.fail(Constants.SEND_FAIL + e.getMessage());
        }
    }

    /**
     * 管理端-上传图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_WEBADMIN)
    @PostMapping("public/upload/image")
    public UploadResult uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyImageFomat(suffix);
            String uuid = BaseUtils.generate32Id();
            if ("1".equals(type)) {
                BaseUtils.compressImg(file.getInputStream(), Constants.FILES_IMAGES, uuid + "_1." + suffix);
            }
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_IMAGES, "/public/download/image/", uuid + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 管理端-上传多个图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_WEBADMIN)
    @PostMapping("public/upload/images")
    public List<UploadResult> uploadImage(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        try {
            file = BaseUtils.copyMockMultipartFile(file);//生成随机名称
            for (MultipartFile f : file) {
                String[] name = f.getOriginalFilename().split("\\.");
                String suffix = name[name.length - 1];
                BaseUtils.verifyImageFomat(suffix);
                if ("1".equals(type)) {
                    BaseUtils.compressImg(f.getInputStream(), Constants.FILES_IMAGES, name[0] + "_1." + suffix);
                }
            }
            return fileServerService.uploadFiles(file, Constants.FILES_IMAGES, "/public/download/image/");
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 管理端-上传附件
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_WEBADMIN)
    @PostMapping("public/upload/attachment")
    public UploadResult uploadAttachment(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyAttachmentFomat(suffix);
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_ATTACHMENT, "/public/download/attachment/", BaseUtils.generate32Id() + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 管理端-上传视频
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_WEBADMIN)
    @PostMapping("public/upload/video")
    public UploadResult uploadVideo(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyVideoFomat(suffix);
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_ATTACHMENT, "/public/download/attachment/", BaseUtils.generate32Id() + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 质量小站门户-上传图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_ZLXZ)
    @PostMapping("zlxz/consult/upload/image")
    public UploadResult zlxzUploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyImageFomat(suffix);
            String uuid = BaseUtils.generate32Id();
            if ("1".equals(request.getParameter("type"))) {
                BaseUtils.compressImg(file.getInputStream(), Constants.FILES_IMAGES, uuid + "_1." + suffix);
            }
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_IMAGES, "/public/download/image/", uuid + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 质量小站门户-上传多个图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_ZLXZ)
    @PostMapping("zlxz/consult/upload/images")
    public List<UploadResult> zlxzUploadImage(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
        try {
            file = BaseUtils.copyMockMultipartFile(file);//生成随机名称
            for (MultipartFile f : file) {
                String[] name = f.getOriginalFilename().split("\\.");
                String suffix = name[name.length - 1];
                BaseUtils.verifyImageFomat(suffix);
                if ("1".equals(request.getParameter("type"))) {
                    BaseUtils.compressImg(f.getInputStream(), Constants.FILES_IMAGES, name[0] + "_1." + suffix);
                }
            }
            return fileServerService.uploadFiles(file, Constants.FILES_IMAGES, "/public/download/image/");
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 政企互动门户-上传图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_ZQHD)
    @PostMapping("zqhd/public/upload/image")
    public UploadResult zqhdUploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyImageFomat(suffix);
            String uuid = BaseUtils.generate32Id();
            if ("1".equals(request.getParameter("type"))) {
                BaseUtils.compressImg(file.getInputStream(), Constants.FILES_IMAGES, uuid + "_1." + suffix);
            }
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_IMAGES, "/public/download/image/", uuid + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 质量小站门户-上传多个图片
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_ZQHD)
    @PostMapping("zqhd/public/upload/images")
    public List<UploadResult> zqhdUploadImage(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
        try {
            file = BaseUtils.copyMockMultipartFile(file);//生成随机名称
            for (MultipartFile f : file) {
                String[] name = f.getOriginalFilename().split("\\.");
                String suffix = name[name.length - 1];
                BaseUtils.verifyImageFomat(suffix);
                if ("1".equals(request.getParameter("type"))) {
                    BaseUtils.compressImg(f.getInputStream(), Constants.FILES_IMAGES, name[0] + "_1." + suffix);
                }
            }
            return fileServerService.uploadFiles(file, Constants.FILES_IMAGES, "/public/download/image/");
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 政企互动门户-上传附件
     *
     * @param file 文件
     * @return 文件上传信息
     * @throws Exception 异常
     */
    @Auth(Constants.AUTH_ZQHD)
    @PostMapping("zqhd/public/upload/attachment")
    public UploadResult zqhdUploadAttachment(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String[] name = file.getOriginalFilename().split("\\.");
            String suffix = name[name.length - 1];
            BaseUtils.verifyAttachmentFomat(suffix);
            return fileServerService.uploadFileWithSpecialName(file, Constants.FILES_ATTACHMENT, "/public/download/attachment/", BaseUtils.generate32Id() + "." + suffix);
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.UPLOAD_SUCCESS : e.getMessage());
        }
    }

    /**
     * 查询字典
     *
     * @param type
     * @author wkk
     **/
    @GetMapping("public/dictionary/findByType")
    public Result<List<DictionaryEntity>> findByType(String type) throws DoValidException {
        try {
            return ResultUtil.success(Constants.SUCCESS, publicService.findByType(type));
        } catch (Exception e) {
            throw new DoValidException(StringUtils.isEmpty(e.getMessage()) ? Constants.FIND_FAIL : e.getMessage());
        }

    }

}
