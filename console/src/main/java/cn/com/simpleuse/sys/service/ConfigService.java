package cn.com.simpleuse.sys.service;

import cn.com.simpleuse.sys.domain.Config;
import com.github.pagehelper.Page;

import java.util.List;

public interface ConfigService {
    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    int removeByPrimaryKey(Long id);

    int batchRemoveByPrimaryKey(List<Long> ids);
    int batchDisableEnableByPrimaryKey(List<Config> list);

    Page<Config> selectByCfgName(String cfgName, Integer pageNum, Integer pageSize);
}
