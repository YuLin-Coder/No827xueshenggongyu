package com.dao;

import com.entity.SusheweishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SusheweishengView;

/**
 * 宿舍卫生 Dao 接口
 *
 * @author 
 * @since 2021-03-20
 */
public interface SusheweishengDao extends BaseMapper<SusheweishengEntity> {

   List<SusheweishengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
