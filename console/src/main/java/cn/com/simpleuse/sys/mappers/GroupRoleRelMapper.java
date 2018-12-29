package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.GroupRoleRel;

public interface GroupRoleRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupRoleRel record);

    int insertSelective(GroupRoleRel record);

    GroupRoleRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupRoleRel record);

    int updateByPrimaryKey(GroupRoleRel record);
}