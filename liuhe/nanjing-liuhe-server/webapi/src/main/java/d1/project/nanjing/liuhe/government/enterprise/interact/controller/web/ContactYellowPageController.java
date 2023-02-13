package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ContactYellowPageEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ContactYellowPageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 联系人黄页
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/contact/yellow/page")
@RequestMapping("/zqhd/contact/yellow/page")
public class ContactYellowPageController {

    private final ContactYellowPageService contactYellowPageService;

    public ContactYellowPageController(ContactYellowPageService contactYellowPageService) {
        this.contactYellowPageService = contactYellowPageService;
    }

    /**
     * 列表查询
     *
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<List<ContactYellowPageEntity>> findAll() {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, contactYellowPageService.findAll());
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
