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

import com.dao.FangkeDao;
import com.entity.FangkeEntity;
import com.service.FangkeService;
import com.entity.view.FangkeView;

/**
 * 访客管理 服务实现类
 * @author 
 * @since 2021-03-20
 */
@Service("fangkeService")
@Transactional
public class FangkeServiceImpl extends ServiceImpl<FangkeDao, FangkeEntity> implements FangkeService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<FangkeView> page =new Query<FangkeView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
