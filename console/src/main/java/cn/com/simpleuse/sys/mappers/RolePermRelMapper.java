package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.RolePermRel;

public interface RolePermRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermRel record);

    int insertSelective(RolePermRel record);

    RolePermRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermRel record);

    int updateByPrimaryKey(RolePermRel record);
}