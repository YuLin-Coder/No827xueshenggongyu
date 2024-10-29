package com.dao;

import com.entity.SusheYonghuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SusheYonghuView;

/**
 * 人员与宿舍关系 Dao 接口
 *
 * @author 
 * @since 2021-03-20
 */
public interface SusheYonghuDao extends BaseMapper<SusheYonghuEntity> {

   List<SusheYonghuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
