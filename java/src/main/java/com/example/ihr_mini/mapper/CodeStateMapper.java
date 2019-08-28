package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.CodeState;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeStateMapper {

    public List<String> getCodeStateCodes();

    public CodeState getCodeStatementById(String id);

    public CodeState getCodeStatementByCode(String code);

    public List<String> getCodeStateIds(String code);

    public List<String> getStatementsByCode(String code);

    public String getStatementById(String id);

    public String getCodeById(String id);

    public String getCodeByStatement(String statement);

    public String getIdByStatement(String statement);
}
