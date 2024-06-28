package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.HotfixMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Hotfix;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.HotfixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotfixServiceImpl implements HotfixService {

    @Autowired
    private HotfixMapper hotfixMapper;

    /**
     * 将探测到的补丁信息插入数据库中
     * @param hotfixes
     * @return
     */
    @Override
    public int addHotfix(List<Hotfix> hotfixes) {
        // 1.将数据库中原有的数据删除，以免重复
        // 根据探测到的主机的mac地址，将该主机在数据库中已有的信息全部删除，以免重复
        // 获取mac地址
        String mac = hotfixes.get(0).getMac();
        // 删除数据
        hotfixMapper.deleteByMac(mac);

        // 2.将新数据入库
        return hotfixMapper.insertBatch(hotfixes);
    }

    /**
     * 查询补丁信息列表
     * @param param
     * @return
     */
    @Override
    public ResponseResult getAllHotfix(MyParam param) {
        // 1.进行分页
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 2.获取补丁信息列表
        List<Hotfix> hotfixes = hotfixMapper.selectAll(param);

        // 3.获取分页信息
        PageInfo pageInfo = new PageInfo(hotfixes);

        // 4.返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除补丁信息
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteHotfix(Integer[] ids) {
        // 待删除的ID数组
        hotfixMapper.delete(ids);

        // 返回表示删除成功的结果对象
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }
}
