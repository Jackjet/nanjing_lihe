package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageManagementEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Message.MessageManagementByReceiveIdFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.MessageManagementService;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.ActivityView;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门户-消息管理
 * @author wkk
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/message/management")
@RequestMapping("/zqhd/message/management")
public class MessageManagementController {

    private final MessageManagementService messageManagementService;

    public MessageManagementController(MessageManagementService messageManagementService) {
        this.messageManagementService = messageManagementService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ActivityView>> findAll(MessageManagementByReceiveIdFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, messageManagementService.findAllByEnterprise((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<MessageManagementEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, messageManagementService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
