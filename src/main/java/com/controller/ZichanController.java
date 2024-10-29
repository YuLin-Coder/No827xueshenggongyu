package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.SusheEntity;
import com.entity.ZichanEntity;
import com.entity.view.ZichanView;
import com.service.DictionaryService;
import com.service.SusheService;
import com.service.TokenService;
import com.service.ZichanService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 宿舍资产信息
 * 后端接口
 * @author
 * @email
 * @date 2021-03-20
*/
@RestController
@Controller
@RequestMapping("/zichan")
public class ZichanController {
    private static final Logger logger = LoggerFactory.getLogger(ZichanController.class);

    @Autowired
    private ZichanService zichanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private SusheService susheService;


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
        PageUtils page = zichanService.queryPage(params);

        //字典表数据转换
        List<ZichanView> list =(List<ZichanView>)page.getList();
        for(ZichanView c:list){
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
        ZichanEntity zichan = zichanService.selectById(id);
        if(zichan !=null){
            //entity转view
            ZichanView view = new ZichanView();
            BeanUtils.copyProperties( zichan , view );//把实体数据重构到view中

            //级联表
            SusheEntity sushe = susheService.selectById(zichan.getSusheId());
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
    public R save(@RequestBody ZichanEntity zichan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zichan:{}",this.getClass().getName(),zichan.toString());
        Wrapper<ZichanEntity> queryWrapper = new EntityWrapper<ZichanEntity>()
            .eq("sushe_id", zichan.getSusheId())
            .eq("zichan_name", zichan.getZichanName());
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZichanEntity zichanEntity = zichanService.selectOne(queryWrapper);
        if(zichanEntity==null){
            zichan.setCreateTime(new Date());
            zichanService.insert(zichan);
            return R.ok();
        }else {
            return R.error(511,"表中有该宿舍的该资产了");
        }
    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZichanEntity zichan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zichan:{}",this.getClass().getName(),zichan.toString());
        //根据字段查询是否有相同数据
        Wrapper<ZichanEntity> queryWrapper = new EntityWrapper<ZichanEntity>()
            .notIn("id",zichan.getId())
            .andNew()
            .eq("sushe_id", zichan.getSusheId())
            .eq("zichan_name", zichan.getZichanName());
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZichanEntity zichanEntity = zichanService.selectOne(queryWrapper);
        if(zichanEntity==null){
            zichanService.updateById(zichan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有该宿舍的该资产了");
        }
    }

    /**
    * 修改资产状态
    */
    @RequestMapping("/updateZichanTypes")
    public R updateZichanTypes(Integer id,Integer zichanTypes, HttpServletRequest request){
        logger.debug("updateZichanTypes方法:,,Controller:{},,id:{},,zichanTypes:{}",this.getClass().getName(),id,zichanTypes);
        ZichanEntity zichan = new ZichanEntity();
        zichan.setId(id);
        zichan.setZichanTypes(zichanTypes);
        zichanService.updateById(zichan);//根据id更新
        return R.ok();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zichanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


}

