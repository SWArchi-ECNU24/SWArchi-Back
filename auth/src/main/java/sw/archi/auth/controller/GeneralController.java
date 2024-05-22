package sw.archi.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URLDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sw.archi.auth.service.GeneralService;
import sw.archi.commonutils.constants.SWConstants;

@RestController
@Tag(name = "Auth模块")
@RequestMapping(
        value = "/api/auth",
        produces = {"application/json;charset=UTF-8"})
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @Operation(summary = "根据Id获取数据接口")
    @GetMapping(value = "/get/{tableName}/{id}")
    public JSONObject getDataById(
            @Parameter(description = "表名") @PathVariable String tableName,
            @Parameter(description = "id") @PathVariable int id)
            throws Exception {
        return generalService.getDataById(tableName, id);
    }

    @Operation(summary = "获取全部数据接口")
    @GetMapping(value = "/get/{tableName}")
    public JSONObject getData(@Parameter(description = "表名") @PathVariable String tableName) throws Exception {
        return generalService.getData(tableName);
    }

    @Operation(summary = "根据Id删除数据接口")
    @DeleteMapping(value = "/delete")
    public JSONObject deleteData(
            @Parameter(description = "表名") @RequestParam String tableName,
            @Parameter(description = "id") @RequestParam int id)
            throws Exception {
        return generalService.deleteData(tableName, id);
    }

    @Operation(summary = "新增数据接口")
    @PostMapping(value = "/add")
    public JSONObject addData(
            @Parameter(description = "表名") @RequestParam String tableName,
            @Parameter(description = "JSON String格式数据") @RequestParam String value)
            throws Exception {
        return generalService.addAndUpdateData(
                tableName, null, JSON.parseObject(URLDecoder.decode(value, SWConstants.UTF8)));
    }

    @Operation(summary = "根据Id修改数据接口")
    @PutMapping(value = "/update")
    public JSONObject updateData(
            @Parameter(description = "表名") @RequestParam String tableName,
            @Parameter(description = "id") @RequestParam int id,
            @Parameter(description = "JSON String格式数据") @RequestParam String value)
            throws Exception {
        return generalService.addAndUpdateData(
                tableName, id, JSON.parseObject(URLDecoder.decode(value, SWConstants.UTF8)));
    }
}
