package sw.archi.intermediator.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping(value = "/auth/{tableName}/{id}")
    public ResponseEntity<JSONObject> getAuthByTableNId(
            @Parameter(description = "Id") @PathVariable int id,
            @Parameter(description = "tableName") @PathVariable String tableName) {
        return generalService
                .getDataById(IMConstants.authModuleBaseUrl, IMConstants.authModuleApiPrefix, tableName, id)
                .toResponseEntity();
    }
}
