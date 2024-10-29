package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.SusheYonghuEntity;
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

import com.entity.SusheweishengEntity;

import com.entity.view.SusheweishengView;
import com.entity.SusheEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 宿舍卫生
 * 后端接口
 * @author
 * @email
 * @date 2021-03-20
*/
@RestController
@Controller
@RequestMapping("/susheweisheng")
public class SusheweishengController {
    private static final Logger logger = LoggerFactory.getLogger(SusheweishengController.class);

    @Autowired
    private SusheweishengService susheweishengService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private SusheYonghuService susheYonghuService;


    //级联表service
    @Autowired
    private SusheService susheService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
//        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
//            EntityWrapper<SusheYonghuEntity> wrapper = new EntityWrapper<>();
//            wrapper.eq("yonghu_id",request.getSession().getAttribute("userId"));
//            SusheYonghuEntity susheYonghuEntity = susheYonghuService.selectOne(wrapper);
//            if(susheYonghuEntity!= null){
//                params.put("susheId",susheYonghuEntity.getSusheId());
//            }else{
//                params.put("susheId",-999);
//            }
//        }
        PageUtils page = susheweishengService.queryPage(params);

        //字典表数据转换
        List<SusheweishengView> list =(List<SusheweishengView>)page.getList();
        for(SusheweishengView c:list){
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
        SusheweishengEntity susheweisheng = susheweishengService.selectById(id);
        if(susheweisheng !=null){
            //entity转view
            SusheweishengView view = new SusheweishengView();
            BeanUtils.copyProperties( susheweisheng , view );//把实体数据重构到view中

            //级联表
            SusheEntity sushe = susheService.selectById(susheweisheng.getSusheId());
            if(sushe != null){
                BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setSusheId(sushe.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheweishengEntity susheweisheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheweisheng:{}",this.getClass().getName(),susheweisheng.toString());
        Wrapper<SusheweishengEntity> queryWrapper = new EntityWrapper<SusheweishengEntity>()
            .eq("sushe_id", susheweisheng.getSusheId())
            .eq("weisheng_types", susheweisheng.getWeishengTypes())
            .eq("weisheng_content", susheweisheng.getWeishengContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheweishengEntity susheweishengEntity = susheweishengService.selectOne(queryWrapper);
        if(susheweishengEntity==null){
            susheweisheng.setInsertTime(new Date());
            susheweisheng.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      susheweisheng.set
        //  }
            susheweishengService.insert(susheweisheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheweishengEntity susheweisheng, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,susheweisheng:{}",this.getClass().getName(),susheweisheng.toString());
        //根据字段查询是否有相同数据
        Wrapper<SusheweishengEntity> queryWrapper = new EntityWrapper<SusheweishengEntity>()
            .notIn("id",susheweisheng.getId())
            .eq("sushe_id", susheweisheng.getSusheId())
            .eq("weisheng_types", susheweisheng.getWeishengTypes())
            .eq("weisheng_content", susheweisheng.getWeishengContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheweishengEntity susheweishengEntity = susheweishengService.selectOne(queryWrapper);
        if(susheweishengEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      susheweisheng.set
            //  }
            susheweishengService.updateById(susheweisheng);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        susheweishengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}

