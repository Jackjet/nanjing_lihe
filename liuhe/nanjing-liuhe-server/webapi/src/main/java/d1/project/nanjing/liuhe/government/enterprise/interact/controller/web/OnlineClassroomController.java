package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.OnlineClassroomEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom.OnlineClassroomFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.OnlineClassroomService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线上课堂
 * @author wkk
 **/
//@Auth(Constants.AUTH_ZQHD)
@RestController("/zqhd/online/classroom")
@RequestMapping("/zqhd/online/classroom")
public class OnlineClassroomController {

    private final OnlineClassroomService onlineClassroomService;

    public OnlineClassroomController(OnlineClassroomService onlineClassroomService) {
        this.onlineClassroomService = onlineClassroomService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<OnlineClassroomEntity>> findAll(OnlineClassroomFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, onlineClassroomService.findAllByEnable((JSONObject) JSONObject.toJSON(model)));
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
    public Result<OnlineClassroomEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, onlineClassroomService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
