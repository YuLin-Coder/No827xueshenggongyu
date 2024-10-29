package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ZichanDao;
import com.entity.ZichanEntity;
import com.entity.view.ZichanView;
import com.service.ZichanService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 宿舍资产信息 服务实现类
 * @author 
 * @since 2021-03-20
 */
@Service("zichanService")
@Transactional
public class ZichanServiceImpl extends ServiceImpl<ZichanDao, ZichanEntity> implements ZichanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<ZichanView> page =new Query<ZichanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
