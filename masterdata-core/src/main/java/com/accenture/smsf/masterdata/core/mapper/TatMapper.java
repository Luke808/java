package com.accenture.smsf.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.smsf.masterdata.core.entity.Tat;
import com.accenture.smsf.masterdata.core.entity.TatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TatMapper extends BaseMapper<Tat> {
    long countByExample(TatExample example);

    int deleteByExample(TatExample example);

    List<Tat> selectByExample(TatExample example);

    int updateByExampleSelective(@Param("record") Tat record, @Param("example") TatExample example);

    int updateByExample(@Param("record") Tat record, @Param("example") TatExample example);
}