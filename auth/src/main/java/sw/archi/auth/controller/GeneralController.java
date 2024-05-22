package sw.archi.auth.controller;

import com.alibaba.fastjson.JSONObject;
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
@RequestMapping(
        value = "/api/auth",
        produces = {"application/json;charset=UTF-8"})
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping(value = "/{tableName}/{id}")
    public JSONObject getDataById(@PathVariable String tableName, @PathVariable int id) throws Exception {
        return generalService.getDataById(tableName, id);
    }

    @GetMapping(value = "/{tableName}")
    public JSONObject getData(@PathVariable String tableName) throws Exception {
        return generalService.getData(tableName);
    }

    @DeleteMapping(value = "/{tableName}/{id}")
    public JSONObject deleteData(@PathVariable String tableName, @PathVariable int id) throws Exception {
        return generalService.deleteData(tableName, id);
    }

    @PostMapping(value = "/{tableName}/{value}")
    public JSONObject addData(@PathVariable String tableName, @PathVariable JSONObject value) throws Exception {
        return generalService.addAndUpdateData(tableName, null, value);
    }

    @PutMapping(value = "/{tableName}/{id}/{value}")
    public JSONObject updateData(@PathVariable String tableName, @PathVariable int id, @PathVariable JSONObject value)
            throws Exception {
        return generalService.addAndUpdateData(tableName, id, value);
    }
}
