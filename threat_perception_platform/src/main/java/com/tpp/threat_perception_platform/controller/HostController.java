package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.AssetsParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.param.ThreatParam;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HostController {
    @Autowired
    private HostService hostService;
    /**
     * 通过POST请求获取主机列表
     *
     * @param param 包含查询条件的参数对象
     * @return 返回查询结果的ResponseResult对象
     */
    @PostMapping("host/list")
    public ResponseResult hostList(MyParam param){
        return hostService.findHostList(param);
    }

    /**
     * 获取所有主机列表
     * 不做分页，返回数据库中所有主机信息
     * @return 返回查询结果的ResponseResult对象
     */
    @PostMapping("host/list/all")
    public ResponseResult hostListAll(){
        return hostService.findHostList();
    }

    /**
     * 通过POST请求，批量删除主机。
     *
     * @param ids 主机ID数组，用于指定需要删除的主机。
     * @return 返回操作结果，包含成功与否及可能的错误信息。
     */
    @PostMapping("host/delete")
    public ResponseResult deleteHost(@RequestParam("ids[]") Integer[] ids){
        return hostService.deleteHost(ids);
    }

    /**
     * 资产探测请求处理，使用AssetsParm对象装载请求参数
     *
     * @param assetsParam 主机信息对象，包含主机的详细信息。
     * @return 返回操作结果，包含成功与否及可能的错误信息。
     */
    @PostMapping("host/assets")
    public ResponseResult assets(@RequestBody AssetsParam assetsParam){
        return hostService.assets(assetsParam);
    }


    /**
     * 威胁探测请求处理，使用ThreatParm对象装载请求参数
     *
     * @param threatParam 主机信息对象，包含主机的详细信息。
     * @return 返回操作结果，包含成功与否及可能的错误信息。
     */
    @PostMapping("host/threat")
    public ResponseResult threat(@RequestBody ThreatParam threatParam){
        return hostService.threat(threatParam);
    }

    /**
     * 查询所有主机的类型返回前端，统计到饼状图中
     * @return 返回查询结果的ResponseResult对象
     */
    @PostMapping("host/list/types")
    public ResponseResult hostListTypes(){
        return hostService.findHostTypes();
    }

    @GetMapping("console/host")
    public ResponseResult listType() {
        return hostService.findAll2();
    }

}
