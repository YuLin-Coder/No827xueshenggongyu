package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ZichanEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 宿舍资产信息 服务类
 * @author 
 * @since 2021-03-20
 */
public interface ZichanService extends IService<ZichanEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}