package com.zhongda.monitor.business.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.ItemAvgData;

public interface ItemMapper {
 
 ItemAvgData selectItemAvgData(String tableName,@Param("poJoId")String poJoId);
}
