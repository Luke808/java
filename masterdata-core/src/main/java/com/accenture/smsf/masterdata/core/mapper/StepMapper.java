package com.accenture.smsf.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.smsf.masterdata.core.entity.Step;
import com.accenture.smsf.masterdata.core.entity.StepExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StepMapper extends BaseMapper<Step> {
    long countByExample(StepExample example);

    int deleteByExample(StepExample example);

    List<Step> selectByExample(StepExample example);

    int updateByExampleSelective(@Param("record") Step record, @Param("example") StepExample example);

    int updateByExample(@Param("record") Step record, @Param("example") StepExample example);
}