package com.accenture.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.masterdata.core.entity.ClientServiceLevel;
import com.accenture.masterdata.core.entity.ClientServiceLevelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientServiceLevelMapper extends BaseMapper<ClientServiceLevel> {
    long countByExample(ClientServiceLevelExample example);

    int deleteByExample(ClientServiceLevelExample example);

    List<ClientServiceLevel> selectByExample(ClientServiceLevelExample example);

    int updateByExampleSelective(@Param("record") ClientServiceLevel record, @Param("example") ClientServiceLevelExample example);

    int updateByExample(@Param("record") ClientServiceLevel record, @Param("example") ClientServiceLevelExample example);
}