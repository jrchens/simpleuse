package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int removeByPrimaryKey(Role record);
    List<Role> selectByViewname(String viewname);
}