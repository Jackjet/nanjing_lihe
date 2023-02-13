package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageStatusEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Message.MessageStatusFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.MessageStatusService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息管理-接收方
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController
@RequestMapping("/webadmin/message/status")
public class MessageStatusController {

    private final MessageStatusService messageManagementService;

    public MessageStatusController(MessageStatusService messageManagementService) {
        this.messageManagementService = messageManagementService;
    }


    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<MessageStatusEntity>> findAll(MessageStatusFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, messageManagementService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
