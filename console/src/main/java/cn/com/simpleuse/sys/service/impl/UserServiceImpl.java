package cn.com.simpleuse.sys.service.impl;

import cn.com.simpleuse.sys.domain.User;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.mappers.UserMapper;
import cn.com.simpleuse.sys.service.UserService;
import cn.com.simpleuse.sys.util.ErrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertSelective(User record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            record.setCruser(user);
            record.setCrtime(now);

            record.setPassword(encryptPassword(record.getUsername(),record.getPassword()));

            return userMapper.insertSelective(record);
        } catch (Exception e) {
            logger.error("UserServiceImpl.insertSelective.err", e);
            throw new SysServiceException(ErrUtil.getInertErr());
        }
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        try {
            return userMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("UserServiceImpl.selectByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }

    private String encryptPassword(String username, String password){
        Assert.hasText(username,"encrypt user password username can't null or empty");
        Assert.hasText(password,"encrypt user password password can't null or empty");

        String encUsername = Hashing.md5().hashString(username, Charsets.UTF_8).toString();
        String encPassword = Hashing.md5().hashString(password, Charsets.UTF_8).toString();

        return Hashing.md5().hashString("{".concat(encUsername).concat(":").concat(encPassword).concat("}"),Charsets.UTF_8).toString();
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();


            String password = record.getPassword();
            if (StringUtils.hasText(password)) {
                record.setPassword(encryptPassword(record.getUsername(),record.getPassword()));
            } else {
                record.setPassword(null);
            }

            record.setMduser(user);
            record.setMdtime(now);
            return userMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("UserServiceImpl.updateByPrimaryKeySelective.err", e);
            throw new SysServiceException(ErrUtil.getUpdateErr());
        }
    }


    @Override
    public int removeByPrimaryKey(List<Long> idList) {
        try {
            int aff = 0;
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();

            for (Long id : idList
            ) {
                User record = new User();
                record.setId(id);
                record.setMduser(user);
                record.setMdtime(now);
                aff += userMapper.removeByPrimaryKey(record);
            }
            return aff;
        } catch (Exception e) {
            logger.error("UserServiceImpl.removeByPrimaryKey.err", e);
            throw new SysServiceException(ErrUtil.getRemoveErr());
        }
    }

//    @Override
//    public int batchRemoveByPrimaryKey(List<User> list) {
//        try {
//            int aff = 0;
//            Date now = DateTime.now().toDate();
//            String user = SecurityUtils.getSubject().getPrincipal().toString();
//            for (User record : list
//            ) {
//                record.setMduser(user);
//                record.setMdtime(now);
//                aff += userMapper.removeByPrimaryKey(record);
//            }
//            return aff;
//        } catch (Exception e) {
//            logger.error("UserServiceImpl.batchRemoveByPrimaryKey.err", e);
//            throw new SysServiceException(ErrUtil.getRemoveErr());
//        }
//    }

    @Override
    public Page<User> selectByUsernameAndViewname(String username, String viewname, Integer pageNum, Integer pageSize, String sort, String order) {
        try {
            Page<User> page = PageHelper.startPage(pageNum, pageSize, true);
            if (StringUtils.hasText(sort)) {
                List<String> sl = Lists.newArrayList(sort);
                if (StringUtils.hasText(order)) {
                    sl.add(order);
                }
                page.setOrderBy(Joiner.on(" ").join(sl));
            }
            userMapper.selectByUsernameAndViewname(username, viewname);
            return page;
        } catch (Exception e) {
            logger.error("UserServiceImpl.selectByUsernameAndViewname.err", e);
            throw new SysServiceException(ErrUtil.getQueryErr());
        }
    }
}
