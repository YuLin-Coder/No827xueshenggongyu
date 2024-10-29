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

import com.dao.SusheDao;
import com.entity.SusheEntity;
import com.service.SusheService;
import com.entity.view.SusheView;

/**
 * 宿舍信息 服务实现类
 * @author 
 * @since 2021-03-20
 */
@Service("susheService")
@Transactional
public class SusheServiceImpl extends ServiceImpl<SusheDao, SusheEntity> implements SusheService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<SusheView> page =new Query<SusheView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
