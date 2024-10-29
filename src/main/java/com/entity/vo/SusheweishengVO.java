package com.entity.vo;

import com.entity.SusheweishengEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宿舍卫生
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-03-20
 */
@TableName("susheweisheng")
public class SusheweishengVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 宿舍id
     */

    @TableField(value = "sushe_id")
    private Integer susheId;


    /**
     * 卫生
     */

    @TableField(value = "weisheng_types")
    private Integer weishengTypes;


    /**
     * 卫生详情
     */

    @TableField(value = "weisheng_content")
    private String weishengContent;


    /**
     * 检查时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：宿舍id
	 */
    public Integer getSusheId() {
        return susheId;
    }


    /**
	 * 获取：宿舍id
	 */

    public void setSusheId(Integer susheId) {
        this.susheId = susheId;
    }
    /**
	 * 设置：卫生
	 */
    public Integer getWeishengTypes() {
        return weishengTypes;
    }


    /**
	 * 获取：卫生
	 */

    public void setWeishengTypes(Integer weishengTypes) {
        this.weishengTypes = weishengTypes;
    }
    /**
	 * 设置：卫生详情
	 */
    public String getWeishengContent() {
        return weishengContent;
    }


    /**
	 * 获取：卫生详情
	 */

    public void setWeishengContent(String weishengContent) {
        this.weishengContent = weishengContent;
    }
    /**
	 * 设置：检查时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：检查时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
