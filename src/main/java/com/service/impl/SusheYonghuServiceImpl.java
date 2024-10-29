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

import com.dao.SusheYonghuDao;
import com.entity.SusheYonghuEntity;
import com.service.SusheYonghuService;
import com.entity.view.SusheYonghuView;

/**
 * 人员与宿舍关系 服务实现类
 * @author 
 * @since 2021-03-20
 */
@Service("susheYonghuService")
@Transactional
public class SusheYonghuServiceImpl extends ServiceImpl<SusheYonghuDao, SusheYonghuEntity> implements SusheYonghuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<SusheYonghuView> page =new Query<SusheYonghuView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
