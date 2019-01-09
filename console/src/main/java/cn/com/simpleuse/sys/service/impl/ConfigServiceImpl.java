package cn.com.simpleuse.sys.service.impl;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.mappers.ConfigMapper;
import cn.com.simpleuse.sys.service.ConfigService;
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
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public int insert(Config record) {
        try {
            return configMapper.insert(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.insert.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("SAVE_ERR"));
        }
    }

    @Override
    public int insertSelective(Config record) {
        try {
            return configMapper.insertSelective(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.insertSelective.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("SAVE_SELECTIVE_ERR"));
        }
    }

    @Override
    public Config selectByPrimaryKey(Long id) {
        try {
            return configMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.selectByPrimaryKey.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("GET_ERR"));
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Config record) {
        try {
            return configMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.updateByPrimaryKeySelective.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("UPDATE_SELECTIVE_ERR"));
        }
    }

    @Override
    public int updateByPrimaryKey(Config record) {
        try {
            return configMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.updateByPrimaryKey.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("UPDATE_ERR"));
        }
    }

    @Override
    public int removeByPrimaryKey(Long id) {
        try {
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            Config config = new Config();
            config.setId(id);
            config.setMduser(user);
            config.setMdtime(now);
            return configMapper.removeByPrimaryKey(config);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.removeByPrimaryKey.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("REMOVE_SELECTIVE_ERR"));
        }
    }

    @Override
    public int batchRemoveByPrimaryKey(List<Long> ids) {
        try {
            int aff = 0;
            Date now = DateTime.now().toDate();
            String user = SecurityUtils.getSubject().getPrincipal().toString();
            for (Long id : ids
            ) {
                Config config = new Config();
                config.setId(id);
                config.setMduser(user);
                config.setMdtime(now);
                aff += configMapper.removeByPrimaryKey(config);
            }
            return aff;
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.batchRemoveByPrimaryKey.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("BATCH_REMOVE_ERR"));
        }
    }

    @Override
    public Page<Config> selectByCfgName(String cfgName, Integer pageNum, Integer pageSize) {
        try {
            Page<Config> page = PageHelper.startPage(pageNum, pageSize,true);
            configMapper.selectByCfgName(cfgName);
            return page;
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.selectByCfgName.error", e);
            throw new SysServiceException(ErrUtil.getErrCode("QUERY_SELECTIVE_ERR"));
        }
    }
}
