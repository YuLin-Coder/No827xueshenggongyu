package com.entity.model;

import com.entity.ZichanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宿舍资产信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-20
 */
public class ZichanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 宿舍id
     */
    private Integer susheId;


    /**
     * 资产名字
     */
    private String zichanName;


    /**
     * 资产类型
     */
    private Integer zichanTypes;


    /**
     * 备注
     */
    private String zichanContent;


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
	 * 获取：宿舍id
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 设置：宿舍id
	 */
    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 获取：资产名字
	 */
    public String getZichanName() {
        return zichanName;
    }


    /**
	 * 设置：资产名字
	 */
    public void setZichanName(String zichanName) {
        this.zichanName = zichanName;
    }
    /**
	 * 获取：资产类型
	 */
    public Integer getZichanTypes() {
        return zichanTypes;
    }


    /**
	 * 设置：资产类型
	 */
    public void setZichanTypes(Integer zichanTypes) {
        this.zichanTypes = zichanTypes;
    }
    /**
	 * 获取：备注
	 */
    public String getZichanContent() {
        return zichanContent;
    }


    /**
	 * 设置：备注
	 */
    public void setZichanContent(String zichanContent) {
        this.zichanContent = zichanContent;
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
