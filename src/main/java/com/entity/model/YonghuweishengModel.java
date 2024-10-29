package com.entity.model;

import com.entity.YonghuweishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学生卫生
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-20
 */
public class YonghuweishengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户id
     */
    private Integer yonghuId;


    /**
     * 卫生
     */
    private Integer weishengTypes;


    /**
     * 卫生详情
     */
    private String weishengContent;


    /**
     * 检查时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户id
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户id
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：卫生
	 */
    public Integer getWeishengTypes() {
        return weishengTypes;
    }


    /**
	 * 设置：卫生
	 */
    public void setWeishengTypes(Integer weishengTypes) {
        this.weishengTypes = weishengTypes;
    }
    /**
	 * 获取：卫生详情
	 */
    public String getWeishengContent() {
        return weishengContent;
    }


    /**
	 * 设置：卫生详情
	 */
    public void setWeishengContent(String weishengContent) {
        this.weishengContent = weishengContent;
    }
    /**
	 * 获取：检查时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：检查时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
