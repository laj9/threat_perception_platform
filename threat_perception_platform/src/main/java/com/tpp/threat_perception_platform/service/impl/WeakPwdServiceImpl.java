package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.WeakPwdMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.WeakPwd;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.WeakPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeakPwdServiceImpl implements WeakPwdService {

    @Autowired
    private WeakPwdMapper weakPwdMapper;

    @Override
    public int addWeakPwd(List<WeakPwd> weakPwds) {
        if (weakPwds.isEmpty()) {
            return 0; // Return 0 if the list is empty
        }
        // 1.将数据库中原有的数据删除，以免重复
        // 根据探测到的主机的mac地址，将该主机在数据库中已有的信息全部删除，以免重复
        // 获取mac地址
        String mac = weakPwds.get(0).getMac();
        // 删除数据
        weakPwdMapper.deleteByMac(mac);
        // 2.将新数据入库
        return weakPwdMapper.insertBatch(weakPwds);
    }

    /**
     * 查询弱密码信息列表
     * @param param
     * @return
     */
    @Override
    public ResponseResult getAllWeakPwd(MyParam param) {
        // 1. 分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2. 获取列表
        List<WeakPwd> weakPwdList = weakPwdMapper.selectAll(param);

        // 3. 获取分页信息
        PageInfo pageInfo = new PageInfo(weakPwdList);

        // 4. 封装返回结果
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteWeakPwd(Integer[] ids) {
        // 1. 删除
        weakPwdMapper.delete(ids);
        return new ResponseResult<>(0, "删除成功");
    }
}
