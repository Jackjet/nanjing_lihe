package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageManagementEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Message.MessageManagementFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Message.MessageManagementInsertVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.MessageManagementService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 消息管理
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/message/management")
@RequestMapping("/webadmin/message/management")
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
    public Result<Page<MessageManagementEntity>> findAll(MessageManagementFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, messageManagementService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 新增
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody MessageManagementInsertVm model, HttpServletRequest request) {
        try {
            messageManagementService.save(model, request);
            return ResultUtil.success(Constants.ADD_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.ADD_FAIL + e.getMessage());
        }
    }
}
