package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    int removeByPrimaryKey(Group record);

    List<Group> selectByViewname(@Param("viewname") String viewname/*, @Param("sort") String sort, @Param("order") String order*/);
}