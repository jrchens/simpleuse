package cn.com.simpleuse.sys.service.impl;

import cn.com.simpleuse.sys.domain.Group;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.mappers.GroupMapper;
import cn.com.simpleuse.sys.service.GroupService;
import cn.com.simpleuse.sys.util.ErrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public int insertSelective(Group record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setCruser(user);
            record.setCrtime(now);
            return groupMapper.insertSelective(record);
        } catch (Exception e) {
            logger.error("GroupServiceImpl.insertSelective.err", e);
            throw new SysServiceException(ErrUtil.getInertErr());
        }
    }

    @Override
    public Group selectByPrimaryKey(Long id) {
        try {
            return groupMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("GroupServiceImpl.selectByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Group record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setMduser(user);
            record.setMdtime(now);
            return groupMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("GroupServiceImpl.updateByPrimaryKeySelective.err", e);
            throw new SysServiceException(ErrUtil.getUpdateErr());
        }
    }


    @Override
    public int removeByPrimaryKey(Group record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setMduser(user);
            record.setMdtime(now);
            return groupMapper.removeByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("GroupServiceImpl.removeByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getRemoveErr());
        }
    }

    @Override
    public int batchRemoveByPrimaryKey(List<Group> list) {
        try {
            int aff = 0;
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            for (Group record : list
            ) {
                record.setMduser(user);
                record.setMdtime(now);
                aff += groupMapper.removeByPrimaryKey(record);
            }
            return aff;
        } catch (Exception e) {
            logger.error("GroupServiceImpl.batchRemoveByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getRemoveErr());
        }
    }

    @Override
    public Page<Group> selectByViewname(String viewname, Integer pageNum, Integer pageSize, String sort, String order) {
        try {
            Page<Group> page = PageHelper.startPage(pageNum, pageSize, true);
            if (StringUtils.hasText(sort)) {
                List<String> sl = Lists.newArrayList(sort);
                if (StringUtils.hasText(order)) {
                    sl.add(order);
                }
                page.setOrderBy(Joiner.on(" ").join(sl));
            }
            groupMapper.selectByViewname(viewname);
            return page;
        } catch (Exception e) {
            logger.error("GroupServiceImpl.selectByViewname.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }
}
