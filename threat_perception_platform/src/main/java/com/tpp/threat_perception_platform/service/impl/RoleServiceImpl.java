package com.tpp.threat_perception_platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpp.threat_perception_platform.dao.RoleMapper;
import com.tpp.threat_perception_platform.param.MyParam;
import com.tpp.threat_perception_platform.pojo.Role;
import com.tpp.threat_perception_platform.response.ResponseResult;
import com.tpp.threat_perception_platform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 获取角色列表的接口实现。
     *
     * @param param 包含分页信息和查询条件的参数对象。
     * @return 返回包含角色列表和总条数的ResponseResult对象。
     */
    @Override
    public ResponseResult getRoleList(MyParam param) {
        // 根据传入的分页信息启动分页插件
        // 处理业务逻辑，返回结果
        // 分页逻辑
        //获取第page页，pageLimit条的内容，默认查询总数count
        PageHelper.startPage(param.getPage(), param.getLimit());

        // 查询所有角色，根据param中的条件
        List<Role> roleList = roleMapper.selectAll(param);

        // 创建PageInfo对象，用于包装分页信息
        // 获取分页信息
        PageInfo pageInfo = new PageInfo(roleList);

        // 从PageInfo中获取总条数
        // 获取总条数
        Long count = pageInfo.getTotal();

        // 返回包含角色列表和总条数的ResponseResult对象
        return new ResponseResult(count, pageInfo.getList());
    }

    /**
     * 添加角色信息。
     *
     * @param role 待添加的角色对象，包含角色名称等信息。
     * @return 如果角色添加成功，返回添加成功的响应结果；如果角色已存在，返回错误信息。
     * @Override
     */
    public ResponseResult addRole(Role role) {
        // 检查是否已存在相同名称的角色
        // 校验数据，保证添加的角色数据库中不存在
        if (roleMapper.selectByRoleName(role.getRoleName()) != null) {
            // 如果角色已存在，返回错误信息
            return new ResponseResult(400, "角色已存在");
        } else {
            // 如果角色不存在，则插入新的角色信息
            roleMapper.insertSelective(role);
            // 返回添加成功的响应结果
            return new ResponseResult(0, "添加成功");
        }
    }

    /**
     * 编辑角色信息的方法
     *
     * @param role 包含待更新角色信息的Role对象
     * @return 返回一个ResponseResult对象，其中包含操作结果的状态码和消息
     *
     * 方法首先尝试根据角色ID查询现有角色信息。如果角色不存在，则返回一个包含错误消息和状态码的ResponseResult对象。
     * 如果角色存在，则更新角色信息，并返回一个表示操作成功的ResponseResult对象。
     */
    public ResponseResult editRole(Role role) {
        // 根据角色ID查询角色信息
        // 根据id查询角色信息
        Role role1 = roleMapper.selectByPrimaryKey((long)role.getRoleId());

        // 检查角色是否存在
        // 如果角色不存在，返回错误信息
        if (role1 == null) {
            // 如果角色不存在，返回错误消息和状态码400
            return new ResponseResult(400, "角色不存在");
        } else {
            // 如果角色存在，更新角色信息
            // 如果角色存在，更新角色信息
            roleMapper.updateByPrimaryKeySelective(role);
            // 更新成功后，返回成功消息和状态码0
            // 返回更新成功的响应结果
            return new ResponseResult(0, "更新成功");
        }
    }


    /**
     * 删除角色方法
     * @Author AJNUY
     *
     * @param ids 角色ID数组，表示需要删除的角色的ID
     * @return ResponseResult 对象，包含删除操作的结果信息
     * @override 重写了父类或接口中的方法
     *
     * 该方法通过遍历给定的角色ID数组，调用roleMapper的deleteByPrimaryKey方法，
     * 逐个删除对应ID的角色。删除操作完成后，构造并返回一个表示删除成功的ResponseResult对象。
     */
    @Override
    public ResponseResult deleteRole(Integer[] ids) {
        // 遍历ids数组，对每个角色ID执行删除操作
        for (Integer id : ids) {
            // 数据校验，确保删除的角色是数据库中存在的
            if (roleMapper.selectByPrimaryKey(id.longValue()) == null) {
                // 如果角色不存在，返回错误消息和状态码400
                return new ResponseResult(400, "角色不存在");
            }else{
                roleMapper.deleteByPrimaryKey(id.longValue());
            }
        }
        // 返回表示删除成功的结果对象
        return new ResponseResult(0, "删除成功!");
    }

}
