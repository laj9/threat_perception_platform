package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.ApplicationMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Application;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    /**
     * 将探测到的应用信息插入数据库
     * @param applications
     * @return
     */
    @Override
    public int addApplication(List<Application> applications) {
        // 1.将数据库中原有的数据删除，以免重复
        // 根据探测到的主机的mac地址，将该主机在数据库中已有的信息全部删除，以免重复
        // 获取mac地址
        String mac = applications.get(0).getMac();
        // 删除数据
        applicationMapper.deleteByMac(mac);

        // 2.插入数据
        return applicationMapper.insertBatch(applications);
    }

    /**
     * 获取应用信息列表
     * @param param
     * @return
     */
    @Override
    public ResponseResult getAllApplication(MyParam param) {
        // 1.进行分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2.获取账号列表
        List<Application> applicationList = applicationMapper.selectAll(param);

        // 3.获取分页信息
        PageInfo pageInfo = new PageInfo(applicationList);

        // 4.返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除应用信息
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteApplication(Integer[] ids) {
        // 待删除的ID数组
        applicationMapper.delete(ids);

        // 返回表示删除成功的结果对象
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }
}
