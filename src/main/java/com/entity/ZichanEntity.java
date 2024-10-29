package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 宿舍资产信息
 *
 * @author 
 * @email
 * @date 2021-03-20
 */
@TableName("zichan")
public class ZichanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZichanEntity() {

	}

	public ZichanEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 宿舍id
     */
    @TableField(value = "sushe_id")

    private Integer susheId;


    /**
     * 资产名字
     */
    @TableField(value = "zichan_name")

    private String zichanName;


    /**
     * 资产类型
     */
    @TableField(value = "zichan_types")

    private Integer zichanTypes;


    /**
     * 备注
     */
    @TableField(value = "zichan_content")

    private String zichanContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：资产名字
	 */
    public String getZichanName() {
        return zichanName;
    }


    /**
	 * 获取：资产名字
	 */

    public void setZichanName(String zichanName) {
        this.zichanName = zichanName;
    }
    /**
	 * 设置：资产类型
	 */
    public Integer getZichanTypes() {
        return zichanTypes;
    }


    /**
	 * 获取：资产类型
	 */

    public void setZichanTypes(Integer zichanTypes) {
        this.zichanTypes = zichanTypes;
    }
    /**
	 * 设置：备注
	 */
    public String getZichanContent() {
        return zichanContent;
    }


    /**
	 * 获取：备注
	 */

    public void setZichanContent(String zichanContent) {
        this.zichanContent = zichanContent;
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

    @Override
    public String toString() {
        return "Zichan{" +
            "id=" + id +
            ", susheId=" + susheId +
            ", zichanName=" + zichanName +
            ", zichanTypes=" + zichanTypes +
            ", zichanContent=" + zichanContent +
            ", createTime=" + createTime +
        "}";
    }
}
