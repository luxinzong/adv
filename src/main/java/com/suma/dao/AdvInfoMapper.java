package com.suma.dao;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvInfoMapper extends BaseDAO<AdvInfo,AdvInfoExample,Long>{

    AdvInfo selectAdvInfo(@Param("name") String name, @Param("startDate") String startDate,@Param("endDate") String endDate, @Param("status") Integer status, @Param("advTypeId") Long advTypeId);

}