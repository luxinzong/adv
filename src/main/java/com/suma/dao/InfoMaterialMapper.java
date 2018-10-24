package com.suma.dao;

import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoMaterialMapper extends BaseDAO<InfoMaterial, InfoMaterialExample, Long> {
    int updateByDoubleId(InfoMaterial infoMaterial);
}