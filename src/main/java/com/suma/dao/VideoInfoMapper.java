package com.suma.dao;

import com.suma.exception.BaseException;
import com.suma.pojo.VideoInfo;
import com.suma.pojo.VideoInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoInfoMapper extends BaseDAO<VideoInfo,VideoInfoExample,Long> {

}