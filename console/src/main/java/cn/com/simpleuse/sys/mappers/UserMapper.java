package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int removeByPrimaryKey(User record);

    List<User> selectByUsernameAndViewname(@Param("username") String username, @Param("viewname") String viewname);
}