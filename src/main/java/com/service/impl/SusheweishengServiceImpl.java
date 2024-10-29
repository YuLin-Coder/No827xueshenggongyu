package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.dao.SusheweishengDao;
import com.entity.SusheweishengEntity;
import com.service.SusheweishengService;
import com.entity.view.SusheweishengView;

/**
 * 宿舍卫生 服务实现类
 * @author 
 * @since 2021-03-20
 */
@Service("susheweishengService")
@Transactional
public class SusheweishengServiceImpl extends ServiceImpl<SusheweishengDao, SusheweishengEntity> implements SusheweishengService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<SusheweishengView> page =new Query<SusheweishengView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
