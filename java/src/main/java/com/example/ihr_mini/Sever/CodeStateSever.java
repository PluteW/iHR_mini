package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.CodeState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeStateSever {

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
