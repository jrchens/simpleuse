package cn.com.simpleuse.sys.mappers;

import cn.com.simpleuse.sys.domain.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    int removeByPrimaryKey(Config record);

//    List<Config> selectByCfgName(@Param("cfgName") String cfgName);
    List<Config> selectByCfgName(String cfgName);
}