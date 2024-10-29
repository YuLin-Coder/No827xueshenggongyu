package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.SusheEntity;
import java.util.Map;

/**
 * 宿舍信息 服务类
 * @author 
 * @since 2021-03-20
 */
public interface SusheService extends IService<SusheEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}