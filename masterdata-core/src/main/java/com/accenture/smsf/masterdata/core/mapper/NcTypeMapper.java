package com.accenture.smsf.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.smsf.masterdata.core.entity.NcType;
import com.accenture.smsf.masterdata.core.entity.NcTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NcTypeMapper extends BaseMapper<NcType> {
    long countByExample(NcTypeExample example);

    int deleteByExample(NcTypeExample example);

    List<NcType> selectByExample(NcTypeExample example);

    int updateByExampleSelective(@Param("record") NcType record, @Param("example") NcTypeExample example);

    int updateByExample(@Param("record") NcType record, @Param("example") NcTypeExample example);
}