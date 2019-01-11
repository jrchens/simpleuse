package cn.com.simpleuse.sys.service;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.domain.Role;
import com.github.pagehelper.Page;

import java.util.List;

public interface RoleService {

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int removeByPrimaryKey(Role record);
    int batchRemoveByPrimaryKey(List<Role> list);
    Page<Role> selectByViewname(String viewname, Integer pageNum, Integer pageSize);
}
