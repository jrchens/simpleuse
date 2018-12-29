package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.UserGroupRel;

public interface UserGroupRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserGroupRel record);

    int insertSelective(UserGroupRel record);

    UserGroupRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserGroupRel record);

    int updateByPrimaryKey(UserGroupRel record);
}