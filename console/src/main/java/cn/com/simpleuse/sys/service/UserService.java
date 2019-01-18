package cn.com.simpleuse.sys.service;

import cn.com.simpleuse.sys.domain.Role;
import cn.com.simpleuse.sys.domain.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int removeByPrimaryKey(List<Long> idList);

//    int batchRemoveByPrimaryKey(List<User> record);

    Page<User> selectByUsernameAndViewname(String username, String viewname, Integer pageNum, Integer pageSize,String sort,String order);
}
