package com.accenture.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.masterdata.core.entity.CutoffTime;
import com.accenture.masterdata.core.entity.CutoffTimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CutoffTimeMapper extends BaseMapper<CutoffTime> {
    long countByExample(CutoffTimeExample example);

    int deleteByExample(CutoffTimeExample example);

    List<CutoffTime> selectByExample(CutoffTimeExample example);

    int updateByExampleSelective(@Param("record") CutoffTime record, @Param("example") CutoffTimeExample example);

    int updateByExample(@Param("record") CutoffTime record, @Param("example") CutoffTimeExample example);
}