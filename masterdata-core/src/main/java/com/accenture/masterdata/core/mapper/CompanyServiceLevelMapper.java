package com.accenture.masterdata.core.mapper;

import com.ac.smsf.codegen.core.mapper.BaseMapper;
import com.accenture.masterdata.core.entity.CompanyServiceLevel;
import com.accenture.masterdata.core.entity.CompanyServiceLevelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyServiceLevelMapper extends BaseMapper<CompanyServiceLevel> {
    long countByExample(CompanyServiceLevelExample example);

    int deleteByExample(CompanyServiceLevelExample example);

    List<CompanyServiceLevel> selectByExample(CompanyServiceLevelExample example);

    int updateByExampleSelective(@Param("record") CompanyServiceLevel record, @Param("example") CompanyServiceLevelExample example);

    int updateByExample(@Param("record") CompanyServiceLevel record, @Param("example") CompanyServiceLevelExample example);
}