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

import com.entity.FangkeEntity;

import com.service.FangkeService;
import com.entity.view.FangkeView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 访客管理
 * 后端接口
 * @author
 * @email
 * @date 2021-03-20
*/
@RestController
@Controller
@RequestMapping("/fangke")
public class FangkeController {
    private static final Logger logger = LoggerFactory.getLogger(FangkeController.class);

    @Autowired
    private FangkeService fangkeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        PageUtils page = fangkeService.queryPage(params);

        //字典表数据转换
        List<FangkeView> list =(List<FangkeView>)page.getList();
        for(FangkeView c:list){
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
        FangkeEntity fangke = fangkeService.selectById(id);
        if(fangke !=null){
            //entity转view
            FangkeView view = new FangkeView();
            BeanUtils.copyProperties( fangke , view );//把实体数据重构到view中

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
    public R save(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());
        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .eq("name", fangke.getName())
            .eq("phone", fangke.getPhone())
            .eq("id_number", fangke.getIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            .eq("zhuzhi", fangke.getZhuzhi())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            fangke.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      fangke.set
        //  }
            fangkeService.insert(fangke);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangkeEntity fangke, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fangke:{}",this.getClass().getName(),fangke.toString());
        //根据字段查询是否有相同数据
        Wrapper<FangkeEntity> queryWrapper = new EntityWrapper<FangkeEntity>()
            .notIn("id",fangke.getId())
            .eq("name", fangke.getName())
            .eq("phone", fangke.getPhone())
            .eq("id_number", fangke.getIdNumber())
            .eq("sex_types", fangke.getSexTypes())
            .eq("zhuzhi", fangke.getZhuzhi())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangkeEntity fangkeEntity = fangkeService.selectOne(queryWrapper);
        if(fangkeEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      fangke.set
            //  }
            fangkeService.updateById(fangke);//根据id更新
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
        fangkeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}

