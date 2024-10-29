package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.SusheweishengEntity;
import java.util.Map;

/**
 * 宿舍卫生 服务类
 * @author 
 * @since 2021-03-20
 */
public interface SusheweishengService extends IService<SusheweishengEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}