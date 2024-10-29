package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.SusheYonghuEntity;
import java.util.Map;

/**
 * 人员与宿舍关系 服务类
 * @author 
 * @since 2021-03-20
 */
public interface SusheYonghuService extends IService<SusheYonghuEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);

}