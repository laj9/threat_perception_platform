package com.tpp.threat_perception_platform.controller;

import com.tpp.threat_perception_platform.param.ChangePwdParam;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.PasswordUpdateRequest;
import com.tpp.threat_perception_platform.pojo.User;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关的接口
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 通过POST请求获取用户列表
     *
     * @param param 包含查询用户列表所需参数的实体类
     * @return 返回包含用户列表信息的ResponseResult对象
     */
    @PostMapping("/user/list")
    public ResponseResult userList(MyParam param){
        return userService.userList(param);
    }


    /**
     * 通过POST请求保存用户信息。
     *
     * 该方法接收一个User对象作为请求体，将其保存到后台系统中。
     * 使用@RequestBody注解表明请求体中的数据将被绑定到方法参数上。
     * 使用@PostMapping注解指定该方法处理的请求类型为POST，请求的URL为/user/save。
     *
     * @param user 用户对象，包含需要保存的用户信息。
     * @return ResponseResult 对象，包含保存操作的结果信息。
     */
    @PostMapping("/user/save")
    public ResponseResult userSave(@RequestBody User user){
        return userService.save(user);
    }

    /**
     * 处理用户编辑请求。
     * 通过@RequestBody注解，将请求体中的数据绑定到User对象上，然后调用userService的edit方法进行用户信息的更新。
     *
     * @param user 包含待更新用户信息的实体对象。
     * @return 返回处理结果的ResponseResult对象。
     */
    @PostMapping("/user/edit")
    public ResponseResult userEdit(@RequestBody User user){
        return userService.edit(user);
    }

    /**
     * 通过POST请求删除用户。
     *
     * 本方法接收一个包含用户ID数组的请求，用于批量删除用户。删除操作由userService.delete方法实现。
     * 使用@PathVariable注解将URL路径中的id绑定到方法参数上，使用@RequestParam注解将请求参数绑定到方法参数上。
     *
     * @param ids 用户ID数组，用于指定需要删除的用户。
     * @return 返回删除操作的结果，包含成功与否的信息和可能的错误码。
     */
    @PostMapping("/user/delete")
    public ResponseResult userEdit(@RequestParam("ids[]") Integer[] ids){
        return userService.delete(ids);
    }

    @PostMapping("/user/modifyPwd")
    public ResponseResult modifyPwd(@RequestBody PasswordUpdateRequest passwordUpdateRequest){
        System.out.println("passwordUpdateRequest: " + passwordUpdateRequest);
        int id = passwordUpdateRequest.getId();
        String userName = passwordUpdateRequest.getUserName();
        String oldPwd = passwordUpdateRequest.getOldPwd();
        String newPwd = passwordUpdateRequest.getNewPwd();

        System.out.println("userid: " + id + ", Username: " + userName + ", Old Pwd: " + oldPwd + ", New Pwd: " + newPwd);

        // 实现更新密码的业务逻辑，可能需要根据username查询用户，然后比对oldPwd，最后更新newPwd
        return userService.updatePwd(id, userName, oldPwd, newPwd);
    }
}
