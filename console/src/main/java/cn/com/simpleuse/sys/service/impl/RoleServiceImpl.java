package cn.com.simpleuse.sys.service.impl;

import cn.com.simpleuse.sys.domain.Role;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.mappers.RoleMapper;
import cn.com.simpleuse.sys.service.RoleService;
import cn.com.simpleuse.sys.util.ErrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertSelective(Role record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setCruser(user);
            record.setCrtime(now);
            return roleMapper.insertSelective(record);
        } catch (Exception e) {
            logger.error("RoleServiceImpl.insertSelective.err", e);
            throw new SysServiceException(ErrUtil.getInertErr());
        }
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        try {
            return roleMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("RoleServiceImpl.selectByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setMduser(user);
            record.setMdtime(now);
            return roleMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("RoleServiceImpl.updateByPrimaryKeySelective.err", e);
            throw new SysServiceException(ErrUtil.getUpdateErr());
        }
    }


    @Override
    public int removeByPrimaryKey(Role record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setMduser(user);
            record.setMdtime(now);
            return roleMapper.removeByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("RoleServiceImpl.removeByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getRemoveErr());
        }
    }

    @Override
    public int batchRemoveByPrimaryKey(List<Role> list) {
        try {
            int aff = 0;
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            for (Role record:list
                 ) {
                record.setMduser(user);
                record.setMdtime(now);
                aff += roleMapper.removeByPrimaryKey(record);
            }
            return aff;
        } catch (Exception e) {
            logger.error("RoleServiceImpl.batchRemoveByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getRemoveErr());
        }
    }

    @Override
    public Page<Role> selectByViewname(String viewname, Integer pageNum, Integer pageSize) {
        try {
            Page<Role> page = PageHelper.startPage(pageNum, pageSize, true);
            roleMapper.selectByViewname(viewname);
            return page;
        } catch (Exception e) {
            logger.error("RoleServiceImpl.selectByViewname.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }
}
