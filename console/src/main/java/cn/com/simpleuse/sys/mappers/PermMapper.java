package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.Perm;

public interface PermMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);
}