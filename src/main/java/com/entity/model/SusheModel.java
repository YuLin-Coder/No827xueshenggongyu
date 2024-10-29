package com.entity.model;

import com.entity.SusheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宿舍信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-03-20
 */
public class SusheModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 公寓
     */
    private String building;


    /**
     * 单元
     */
    private String unit;


    /**
     * 房间号
     */
    private String room;


    /**
     * 已住人员
     */
    private Integer susheNumber;


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
	 * 获取：公寓
	 */
    public String getBuilding() {
        return building;
    }


    /**
	 * 设置：公寓
	 */
    public void setBuilding(String building) {
        this.building = building;
    }
    /**
	 * 获取：单元
	 */
    public String getUnit() {
        return unit;
    }


    /**
	 * 设置：单元
	 */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
	 * 获取：房间号
	 */
    public String getRoom() {
        return room;
    }


    /**
	 * 设置：房间号
	 */
    public void setRoom(String room) {
        this.room = room;
    }
    /**
	 * 获取：已住人员
	 */
    public Integer getSusheNumber() {
        return susheNumber;
    }


    /**
	 * 设置：已住人员
	 */
    public void setSusheNumber(Integer susheNumber) {
        this.susheNumber = susheNumber;
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
