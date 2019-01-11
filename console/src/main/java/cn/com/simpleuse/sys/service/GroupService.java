package cn.com.simpleuse.sys.service;

import cn.com.simpleuse.sys.domain.Group;
import com.github.pagehelper.Page;

import java.util.List;

public interface GroupService {

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int removeByPrimaryKey(Group record);
    int batchRemoveByPrimaryKey(List<Group> list);
    Page<Group> selectByViewname(String viewname, Integer pageNum, Integer pageSize,String sort,String order);
}
