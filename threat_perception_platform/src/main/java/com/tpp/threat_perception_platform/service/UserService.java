package com.tpp.threat_perception_platform.service;

import com.tpp.threat_perception_platform.param.ChangePwdParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;

/**
 * 用户接口
 */
public interface UserService {

    /**
     * 用户列表
     * @param param
     * @return
     */
    ResponseResult userList(MyParam param);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 保存用户
     * @param user
     * @return
     */
    ResponseResult save(User user);

    /**
     * 编辑用户
     * @param user
     * @return
     */
    ResponseResult edit(User user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    ResponseResult delete(Integer[] ids);

    /**
     * 修改密码
     * @param changePwdParam
     * @return
     */
    ResponseResult updatePwd(Integer id, String userName, String oldPwd, String newPwd);

}
