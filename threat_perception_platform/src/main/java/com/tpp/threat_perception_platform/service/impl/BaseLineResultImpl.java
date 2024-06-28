package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.BaseLineResultMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.BaseLineResult;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.BaseLineResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseLineResultImpl implements BaseLineResultService {

    @Autowired
    private BaseLineResultMapper baseLineResultMapper;


    /**
     * 添加基线检测结果
     * @param baseLineResults
     * @return
     */
    @Override
    public int addBaseLineResult(List<BaseLineResult> baseLineResults) {
        if (baseLineResults.isEmpty()) {
            return 0;
        }
        // 1. 删除数据库中同MAC的数据，避免数据重复
        // 获取mac地址
        String mac = baseLineResults.get(0).getMac();
        baseLineResultMapper.deleteByMac(mac);

        // 2. 插入数据
        return baseLineResultMapper.insertBatch(baseLineResults);
    }

    /**
     * 查询基线检测结果
     *
     * @param myParam
     * @return
     */
    @Override
    public ResponseResult findBaseLineResultList(MyParam myParam) {
        // 1. 获取分页信息
        PageHelper.startPage(myParam.getPage(), myParam.getLimit());

        // 2. 查询数据
        List<BaseLineResult> baseLineResults = baseLineResultMapper.selectAll(myParam);

        // 3. 进行分页
        PageInfo pageInfo = new PageInfo(baseLineResults);

        // 4. 封装数据并传递
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除基线检测结果
     *
     * @param ids
     * @return
     */
    @Override
    public ResponseResult deleteBaseLineResult(Integer[] ids) {
        baseLineResultMapper.delete(ids);
        return new ResponseResult(0, "删除成功");
    }

}
