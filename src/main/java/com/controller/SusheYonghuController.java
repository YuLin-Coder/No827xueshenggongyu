package com.controller;


import com.alibaba.fastjson.JSONObject;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.utils.StringUtil;
import com.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.SusheYonghuEntity;
import com.service.SusheYonghuService;
import com.entity.view.SusheYonghuView;
import com.service.SusheService;
import com.entity.SusheEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 人员与宿舍关系
 * 后端接口
 * @author
 * @email
 * @date
*/
@RestController
@Controller
@RequestMapping("/susheYonghu")
public class SusheYonghuController {
    private static final Logger logger = LoggerFactory.getLogger(SusheYonghuController.class);

    @Autowired
    private SusheYonghuService susheYonghuService;

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
        PageUtils page = susheYonghuService.queryPage(params);

        //字典表数据转换
        List<SusheYonghuView> list =(List<SusheYonghuView>)page.getList();
        for(SusheYonghuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }
    /**
    * 后端详情没有,只有查看 新增 删除
    */


    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheYonghuEntity susheYonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheYonghu:{}",this.getClass().getName(),susheYonghu.toString());
        //查询该用户是否已经绑定过宿舍
        SusheYonghuEntity susheYonghuEntity1 = susheYonghuService.selectOne(new EntityWrapper<SusheYonghuEntity>().eq("yonghu_id", susheYonghu.getYonghuId()));
        // 查询该宿舍的宿舍表
        SusheEntity susheEntity = susheService.selectById(susheYonghu.getSusheId());
        if(susheYonghuEntity1!=null){
            SusheEntity s = susheService.selectById(susheYonghuEntity1.getSusheId());
            return R.error(511,"该用户已经绑定过公寓:"+s.getBuilding()+",单元:"+s.getUnit()+",房间:"+s.getRoom()+"的宿舍了");
        }else if(susheEntity == null){
            return R.error(511,"宿舍不存在,请联系管理员解决");
        }else if(susheEntity.getSusheNumber() != null && susheEntity.getSusheNumber().intValue() >=8 ){
            return R.error(511,"宿舍人员超额,只允许住8个人");
        }else {
            //更新关系表
            susheYonghu.setCreateTime(new Date());
            susheYonghuService.insert(susheYonghu);

            if(susheEntity.getSusheNumber() ==null){
                susheEntity.setSusheNumber(1);
            }else{
                susheEntity.setSusheNumber(susheEntity.getSusheNumber()+1);
            }
            susheService.updateById(susheEntity);
            return R.ok();
        }
    }

    /**
    * 没有修改方法,只有查看 新增 删除
    */



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestParam Integer id){
        logger.debug("delete:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheYonghuEntity susheYonghuEntity = susheYonghuService.selectById(id);
        if(susheYonghuEntity != null){
            susheYonghuService.deleteById(id);

            SusheEntity susheEntity = susheService.selectById(susheYonghuEntity.getSusheId());
            if(susheEntity != null){
                susheEntity.setSusheNumber(susheEntity.getSusheNumber()-1);
                susheService.updateById(susheEntity);
            }else{
                return R.error(511,"宿舍查不到");
            }
            return R.ok();
        }else{
            return R.error(511,"该宿舍与用户关系不存在");
        }
    }


}

