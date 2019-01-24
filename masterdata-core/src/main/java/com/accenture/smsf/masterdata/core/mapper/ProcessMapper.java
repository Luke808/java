package com.accenture.smsf.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.smsf.masterdata.core.entity.Process;
import com.accenture.smsf.masterdata.core.entity.ProcessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessMapper extends BaseMapper<Process> {
    long countByExample(ProcessExample example);

    int deleteByExample(ProcessExample example);

    List<Process> selectByExample(ProcessExample example);

    int updateByExampleSelective(@Param("record") Process record, @Param("example") ProcessExample example);

    int updateByExample(@Param("record") Process record, @Param("example") ProcessExample example);
}