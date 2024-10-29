package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.SusheYonghuEntity;
import com.entity.ZichanEntity;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.SusheEntity;

import com.entity.view.SusheView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 宿舍信息
 * 后端接口
 * @author
 * @email
 * @date
*/
@RestController
@Controller
@RequestMapping("/sushe")
public class SusheController {
    private static final Logger logger = LoggerFactory.getLogger(SusheController.class);

    @Autowired
    private SusheService susheService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private SusheYonghuService susheYonghuService;
    @Autowired
    private ZichanService zichanService;


    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            EntityWrapper<SusheYonghuEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("yonghu_id",request.getSession().getAttribute("userId"));
            SusheYonghuEntity susheYonghuEntity = susheYonghuService.selectOne(wrapper);
            if(susheYonghuEntity!= null){
                params.put("id",susheYonghuEntity.getSusheId());
            }else{
                params.put("id",-999);
            }
        }
        PageUtils page = susheService.queryPage(params);

        //字典表数据转换
        List<SusheView> list =(List<SusheView>)page.getList();
        for(SusheView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheEntity sushe = susheService.selectById(id);
        if(sushe !=null){
            //entity转view
            SusheView view = new SusheView();
            BeanUtils.copyProperties( sushe , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到该宿舍");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());
        String building = sushe.getBuilding();
        String unit = sushe.getUnit();
        String room = sushe.getRoom();
        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .eq("building", building)
            .eq("unit", unit)
            .eq("room",room);
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){
            Date date = new Date();
            sushe.setCreateTime(date);
            sushe.setSusheNumber(0);
            susheService.insert(sushe);

            Integer susheId = sushe.getId();

            List<ZichanEntity> zichanList = new ArrayList<>();

            ZichanEntity zhuoziZichan = new ZichanEntity();
            zhuoziZichan.setZichanName("桌子");
            zhuoziZichan.setZichanTypes(1);
            zhuoziZichan.setSusheId(susheId);
            zhuoziZichan.setCreateTime(date);
            zichanList.add(zhuoziZichan);

            ZichanEntity kongtiaoZichan = new ZichanEntity();
            kongtiaoZichan.setZichanName("空调");
            kongtiaoZichan.setZichanTypes(1);
            kongtiaoZichan.setSusheId(susheId);
            kongtiaoZichan.setCreateTime(date);
            zichanList.add(kongtiaoZichan);

            ZichanEntity bandengZichan = new ZichanEntity();
            bandengZichan.setZichanName("板凳");
            bandengZichan.setZichanTypes(1);
            bandengZichan.setSusheId(susheId);
            bandengZichan.setCreateTime(date);
            zichanList.add(bandengZichan);

            ZichanEntity chuangZichan = new ZichanEntity();
            chuangZichan.setZichanName("床");
            chuangZichan.setZichanTypes(1);
            chuangZichan.setSusheId(susheId);
            chuangZichan.setCreateTime(date);
            zichanList.add(chuangZichan);

            zichanService.insertBatch(zichanList);
            return R.ok();
        }else {
            return R.error(511,"表中已有公寓:"+building+",单元:"+unit+",房间号:"+room+"的房间");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());
        String building = sushe.getBuilding();
        String unit = sushe.getUnit();
        String room = sushe.getRoom();
        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .notIn("id",sushe.getId())
            .eq("building", building)
            .eq("unit", unit)
            .eq("room", room);
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){
            susheService.updateById(sushe);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中已有公寓:"+building+",单元:"+unit+",房间号:"+room+"的房间");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        if(ids != null && ids.length>0){
            //删除宿舍
            susheService.deleteBatchIds(Arrays.asList(ids));
            //级联删除宿舍用户关系
            susheYonghuService.delete(new EntityWrapper<SusheYonghuEntity>().in("sushe_id", Arrays.asList(ids)));
            //级联删除宿舍资产
            zichanService.delete(new EntityWrapper<ZichanEntity>().in("sushe_id", Arrays.asList(ids)));
        }
        return R.ok();
    }


}

