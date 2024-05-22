package sw.archi.auth.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.archi.auth.service.GeneralService;

@RestController
@Tag(name = "Auth模块")
@RequestMapping(
        value = "/api/auth",
        produces = {"application/json;charset=UTF-8"})
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @Operation(summary = "根据Id获取数据接口")
    @GetMapping(value = "/{tableName}/{id}")
    public JSONObject getDataById(
            @Parameter(description = "表名") @PathVariable String tableName,
            @Parameter(description = "id") @PathVariable int id)
            throws Exception {
        return generalService.getDataById(tableName, id);
    }

    @Operation(summary = "获取全部数据接口")
    @GetMapping(value = "/{tableName}")
    public JSONObject getData(@Parameter(description = "表名") @PathVariable String tableName) throws Exception {
        return generalService.getData(tableName);
    }

    @Operation(summary = "根据Id删除数据接口")
    @DeleteMapping(value = "/{tableName}/{id}")
    public JSONObject deleteData(
            @Parameter(description = "表名") @PathVariable String tableName,
            @Parameter(description = "id") @PathVariable int id)
            throws Exception {
        return generalService.deleteData(tableName, id);
    }

    @Operation(summary = "新增数据接口")
    @PostMapping(value = "/{tableName}/{value}")
    public JSONObject addData(
            @Parameter(description = "表名") @PathVariable String tableName,
            @Parameter(description = "JSON格式数据") @PathVariable JSONObject value)
            throws Exception {
        return generalService.addAndUpdateData(tableName, null, value);
    }

    @Operation(summary = "根据Id修改数据接口")
    @PutMapping(value = "/{tableName}/{id}/{value}")
    public JSONObject updateData(
            @Parameter(description = "表名") @PathVariable String tableName,
            @Parameter(description = "id") @PathVariable int id,
            @Parameter(description = "JSON格式数据") @PathVariable JSONObject value)
            throws Exception {
        return generalService.addAndUpdateData(tableName, id, value);
    }
}
