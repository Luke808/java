package com.accenture.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.masterdata.core.entity.Spt;
import com.accenture.masterdata.core.entity.SptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SptMapper extends BaseMapper<Spt> {
    long countByExample(SptExample example);

    int deleteByExample(SptExample example);

    List<Spt> selectByExample(SptExample example);

    int updateByExampleSelective(@Param("record") Spt record, @Param("example") SptExample example);

    int updateByExample(@Param("record") Spt record, @Param("example") SptExample example);
}