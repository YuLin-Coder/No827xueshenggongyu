package com.dao;

import com.entity.YonghuweishengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YonghuweishengView;

/**
 * 学生卫生 Dao 接口
 *
 * @author 
 * @since 2021-03-20
 */
public interface YonghuweishengDao extends BaseMapper<YonghuweishengEntity> {

   List<YonghuweishengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
