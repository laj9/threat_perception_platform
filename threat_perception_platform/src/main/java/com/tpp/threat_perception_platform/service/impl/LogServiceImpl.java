package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.LogMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Log;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 将客户端传递来的数据存入数据库中
     *
     * @param logList
     */
    @Override
    public int addLogList(List<Log> logList) {
        return logMapper.insertBatch(logList);
    }

    /**
     * @param log
     * @return
     */
    @Override
    public int addLog(Log log) {
        return logMapper.insertSelective(log);
    }


    /**
     * 展示所有列表
     *
     * @param myParam
     */
    @Override
    public ResponseResult selectAllLogList(MyParam myParam) {
        // 1. 获取分页信息
        PageHelper.startPage(myParam.getPage(), myParam.getLimit());

        // 2. 获取数据
        List<Log> logs = logMapper.selectAll(myParam);

        // 3. 分页
        PageInfo pageInfo = new PageInfo(logs);

        // 4. 返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 登录日志
     *
     * @param myParam
     */
    @Override
    public ResponseResult selectLoginLog(MyParam myParam) {
        // 1. 获取分页信息
        PageHelper.startPage(myParam.getPage(), myParam.getLimit());

        // 2. 获取数据
        List<Log> logs = logMapper.selectLoginLog(myParam);

        // 3. 分页
        PageInfo pageInfo = new PageInfo(logs);

        // 4. 返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 账号日志
     *
     * @param myParam
     */
    @Override
    public ResponseResult selectAccountLog(MyParam myParam) {
        // 1. 获取分页信息
        PageHelper.startPage(myParam.getPage(), myParam.getLimit());

        // 2. 获取数据
        List<Log> logs = logMapper.selectAccountLog(myParam);

        // 3. 分页
        PageInfo pageInfo = new PageInfo(logs);

        // 4. 返回数据
        return new ResponseResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除日志
     *
     * @param ids
     */
    @Override
    public ResponseResult deleteLog(Integer[] ids) {
        // 校验数据，确保数据库中存在该数据
        if (logMapper.select(ids) != null) {
            // 说明数据库中存在数据
            logMapper.delete(ids);
            return new ResponseResult(0, "删除成功");
        }
        return new ResponseResult(1001, "数据库中不存在该记录");
    }

    /**
     * 返回所有数据的列表，用于饼图的统计
     */
    @Override
    public ResponseResult findAllLogList() {
        return new ResponseResult(0, logMapper.findAll());
    }
}
