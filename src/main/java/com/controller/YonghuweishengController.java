package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.YonghuweishengEntity;

import com.service.YonghuweishengService;
import com.entity.view.YonghuweishengView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 学生卫生
 * 后端接口
 * @author
 * @email
 * @date 2021-03-20
*/
@RestController
@Controller
@RequestMapping("/yonghuweisheng")
public class YonghuweishengController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuweishengController.class);

    @Autowired
    private YonghuweishengService yonghuweishengService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
//        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
//            params.put("yonghuId",request.getSession().getAttribute("userId"));
//        }
        PageUtils page = yonghuweishengService.queryPage(params);

        //字典表数据转换
        List<YonghuweishengView> list =(List<YonghuweishengView>)page.getList();
        for(YonghuweishengView c:list){
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
        YonghuweishengEntity yonghuweisheng = yonghuweishengService.selectById(id);
        if(yonghuweisheng !=null){
            //entity转view
            YonghuweishengView view = new YonghuweishengView();
            BeanUtils.copyProperties( yonghuweisheng , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yonghuweisheng.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody YonghuweishengEntity yonghuweisheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghuweisheng:{}",this.getClass().getName(),yonghuweisheng.toString());
        Wrapper<YonghuweishengEntity> queryWrapper = new EntityWrapper<YonghuweishengEntity>()
            .eq("yonghu_id", yonghuweisheng.getYonghuId())
            .eq("weisheng_types", yonghuweisheng.getWeishengTypes())
            .eq("weisheng_content", yonghuweisheng.getWeishengContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuweishengEntity yonghuweishengEntity = yonghuweishengService.selectOne(queryWrapper);
        if(yonghuweishengEntity==null){
            yonghuweisheng.setInsertTime(new Date());
            yonghuweisheng.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yonghuweisheng.set
        //  }
            yonghuweishengService.insert(yonghuweisheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuweishengEntity yonghuweisheng, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yonghuweisheng:{}",this.getClass().getName(),yonghuweisheng.toString());
        //根据字段查询是否有相同数据
        Wrapper<YonghuweishengEntity> queryWrapper = new EntityWrapper<YonghuweishengEntity>()
            .notIn("id",yonghuweisheng.getId())
            .eq("yonghu_id", yonghuweisheng.getYonghuId())
            .eq("weisheng_types", yonghuweisheng.getWeishengTypes())
            .eq("weisheng_content", yonghuweisheng.getWeishengContent())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuweishengEntity yonghuweishengEntity = yonghuweishengService.selectOne(queryWrapper);
        if(yonghuweishengEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yonghuweisheng.set
            //  }
            yonghuweishengService.updateById(yonghuweisheng);//根据id更新
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
        yonghuweishengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}

