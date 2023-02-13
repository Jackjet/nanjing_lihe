package d1.project.nanjing.liuhe.quality.small.station.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultFindByIdVm;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultFindVm;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultReplyVm;
import d1.project.nanjing.liuhe.quality.small.station.service.ConsultService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站管理端-我要咨询
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/consult")
@RequestMapping("/webadmin/consult")
public class ConsultController {

    private final ConsultService consultService;

    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<ConsultEntity>> findAll(ConsultFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, consultService.findAll((JSONObject) JSONObject.toJSON(model)));
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
    public Result<ConsultFindByIdVm> findById(String id) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, consultService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 回复消息
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PutMapping("replyMessage")
    public Result<String> replyMessage(@Valid @RequestBody ConsultReplyVm model, HttpServletRequest request) {
        try {
            consultService.replyMessage(model, request);
            return ResultUtil.success(Constants.REPLY_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.REPLY_FAIL + e.getMessage());
        }
    }
}
