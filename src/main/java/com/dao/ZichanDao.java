package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ZichanEntity;
import com.entity.view.ZichanView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 宿舍资产信息 Dao 接口
 *
 * @author 
 * @since 2021-03-20
 */
public interface ZichanDao extends BaseMapper<ZichanEntity> {

   List<ZichanView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
