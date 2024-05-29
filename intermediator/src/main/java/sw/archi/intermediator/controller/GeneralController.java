package sw.archi.intermediator.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.archi.intermediator.constants.IMConstants;
import sw.archi.intermediator.service.GeneralService;

@RestController
@CrossOrigin
@RequestMapping(
        value = "/api",
        produces = {"application/json;charset=UTF-8"})
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    private Map.Entry<String, String> getModuleInfo(String moduleName) {
        switch (moduleName) {
            case "auth":
                return Map.entry(IMConstants.authModuleBaseUrl, IMConstants.authModuleApiPrefix);
            case "confjour":
                return Map.entry(IMConstants.confjourModuleBaseUrl, IMConstants.confjourModuleApiPrefix);
            default:
                return Map.entry(null, null);
        }
    }

    @GetMapping(value = "/{moduleName}/{tableName}/{id}")
    public ResponseEntity<JSONObject> getAuthByTableNId(
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
}
