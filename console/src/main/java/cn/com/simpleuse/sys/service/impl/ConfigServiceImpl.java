package cn.com.simpleuse.sys.service.impl;

import cn.com.simpleuse.sys.domain.Config;
import cn.com.simpleuse.sys.exception.SysServiceException;
import cn.com.simpleuse.sys.mappers.ConfigMapper;
import cn.com.simpleuse.sys.service.ConfigService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private static final Integer INSERT_ERROR = 1000;
    private static final Integer UPDATE_ERROR = 1010;
    private static final Integer DELETE_ERROR = 1020;
    private static final Integer REMVOE_ERROR = 1030;
    private static final Integer SELECT_ERROR = 1040;

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public int insert(Config record) {
        try {
            return configMapper.insert(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.insert.error", e);
            throw new SysServiceException(INSERT_ERROR, "");
        }
    }

    @Override
    public int insertSelective(Config record) {
        try {
            return configMapper.insertSelective(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.insertSelective.error", e);
            throw new SysServiceException(INSERT_ERROR, "");
        }
    }

    @Override
    public Config selectByPrimaryKey(Long id) {
        try {
            return configMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.selectByPrimaryKey.error", e);
            throw new SysServiceException(SELECT_ERROR, "");
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Config record) {
        try {
            return configMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.updateByPrimaryKeySelective.error", e);
            throw new SysServiceException(UPDATE_ERROR, "");
        }
    }

    @Override
    public int updateByPrimaryKey(Config record) {
        try {
            return configMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.updateByPrimaryKey.error", e);
            throw new SysServiceException(UPDATE_ERROR, "");
        }
    }

    @Override
    public int removeByPrimaryKey(Long id) {
        try {
            return configMapper.removeByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.removeByPrimaryKey.error", e);
            throw new SysServiceException(REMVOE_ERROR, "");
        }
    }

    @Override
    public int batchRemoveByPrimaryKey(List<Long> ids) {
        try {
            int aff = 0;
            for (Long id : ids
            ) {
                aff += configMapper.removeByPrimaryKey(id);
            }
            return aff;
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.batchRemoveByPrimaryKey.error", e);
            throw new SysServiceException(REMVOE_ERROR, "");
        }
    }

    @Override
    public Page<Config> selectByCfgName(String cfgName, Integer pageNum, Integer pageSize) {
        try {
            Page<Config> page = PageHelper.startPage(pageNum, pageSize);
            configMapper.selectByCfgName(cfgName);
            return page;
        } catch (Exception e) {
            logger.error("ConfigServiceImpl.selectByCfgName.error", e);
            throw new SysServiceException(SELECT_ERROR, "");
        }
    }
}
