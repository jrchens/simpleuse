package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.UserRoleRel;

public interface UserRoleRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRel record);

    int insertSelective(UserRoleRel record);

    UserRoleRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleRel record);

    int updateByPrimaryKey(UserRoleRel record);
}