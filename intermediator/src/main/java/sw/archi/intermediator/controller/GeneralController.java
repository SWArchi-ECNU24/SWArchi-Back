package sw.archi.intermediator.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URLDecoder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sw.archi.commonutils.constants.SWConstants;
import sw.archi.intermediator.constants.IMConstants;
import sw.archi.intermediator.service.GeneralService;

@RestController
@Tag(name = "中间层模块")
@CrossOrigin
@RequestMapping(
        value = "/api",
        produces = {"application/json;charset=UTF-8"})
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    private Map.Entry<String, String> getModuleInfo(String moduleName) {
        switch (moduleName) {
            case SWConstants.authModuleName:
                return Map.entry(IMConstants.authModuleBaseUrl, IMConstants.authModuleApiPrefix);
            case SWConstants.confjourModuleName:
                return Map.entry(IMConstants.confjourModuleBaseUrl, IMConstants.confjourModuleApiPrefix);
            default:
                return Map.entry(null, null);
        }
    }

    @Operation(summary = "根据Id获取数据接口")
    @GetMapping(value = "/{moduleName}/{tableName}/{id}")
    public ResponseEntity<JSONObject> getDataByTableNId(
            @Parameter(description = "moduleName") @PathVariable String moduleName,
            @Parameter(description = "Id") @PathVariable int id,
            @Parameter(description = "tableName") @PathVariable String tableName) {
        return generalService
                .getDataById(
                        getModuleInfo(moduleName).getKey(),
                        getModuleInfo(moduleName).getValue(),
                        tableName,
                        id)
                .toResponseEntity();
    }

    @Operation(summary = "获取数据接口")
    @GetMapping(value = "/{moduleName}/{tableName}/fuzzy/{keywords}")
    public ResponseEntity<JSONObject> getDataByTable(
            @Parameter(description = "moduleName") @PathVariable String moduleName,
            @Parameter(description = "keywords") @PathVariable String keywords,
            @Parameter(description = "tableName") @PathVariable String tableName)
            throws Exception {
        return generalService
                .getData(
                        getModuleInfo(moduleName).getKey(),
                        getModuleInfo(moduleName).getValue(),
                        tableName,
                        JSON.parseObject(URLDecoder.decode(keywords, SWConstants.UTF8)))
                .toResponseEntity();
    }

    @Operation(summary = "根据Id删除数据接口")
    @DeleteMapping(value = "/{moduleName}/{tableName}")
    public ResponseEntity<JSONObject> deleteDataByTableNId(
            @Parameter(description = "moduleName") @PathVariable String moduleName,
            @Parameter(description = "Id") @RequestParam int id,
            @Parameter(description = "tableName") @PathVariable String tableName) {
        return generalService
                .deleteDataById(
                        getModuleInfo(moduleName).getKey(),
                        getModuleInfo(moduleName).getValue(),
                        tableName,
                        id)
                .toResponseEntity();
    }

    @Operation(summary = "新增数据接口")
    @PostMapping(value = "/{moduleName}/{tableName}")
    public ResponseEntity<JSONObject> addDataByTable(
            @Parameter(description = "moduleName") @PathVariable String moduleName,
            @Parameter(description = "tableName") @PathVariable String tableName,
            @Parameter(description = "value") @RequestParam String value)
            throws Exception {
        return generalService
                .addNUpdateDataById(
                        getModuleInfo(moduleName).getKey(),
                        getModuleInfo(moduleName).getValue(),
                        tableName,
                        null,
                        JSON.parseObject(URLDecoder.decode(value, SWConstants.UTF8)))
                .toResponseEntity();
    }

    @Operation(summary = "根据Id修改数据接口")
    @PutMapping(value = "/{moduleName}/{tableName}")
    public ResponseEntity<JSONObject> updateDataByTableNId(
            @Parameter(description = "moduleName") @PathVariable String moduleName,
            @Parameter(description = "Id") @RequestParam int id,
            @Parameter(description = "tableName") @PathVariable String tableName,
            @Parameter(description = "value") @RequestParam String value)
            throws Exception {
        return generalService
                .addNUpdateDataById(
                        getModuleInfo(moduleName).getKey(),
                        getModuleInfo(moduleName).getValue(),
                        tableName,
                        id,
                        JSON.parseObject(URLDecoder.decode(value, SWConstants.UTF8)))
                .toResponseEntity();
    }
}
