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
 * 访客管理
 *
 * @author 
 * @email
 * @date 2021-03-20
 */
@TableName("fangke")
public class FangkeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangkeEntity() {

	}

	public FangkeEntity(T t) {
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
     * 姓名
     */
    @TableField(value = "name")

    private String name;


    /**
     * 手机号
     */
    @TableField(value = "phone")

    private String phone;


    /**
     * 身份证号
     */
    @TableField(value = "id_number")

    private String idNumber;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 住址
     */
    @TableField(value = "zhuzhi")

    private String zhuzhi;


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
	 * 设置：姓名
	 */
    public String getName() {
        return name;
    }


    /**
	 * 获取：姓名
	 */

    public void setName(String name) {
        this.name = name;
    }
    /**
	 * 设置：手机号
	 */
    public String getPhone() {
        return phone;
    }


    /**
	 * 获取：手机号
	 */

    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
	 * 设置：身份证号
	 */
    public String getIdNumber() {
        return idNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：住址
	 */
    public String getZhuzhi() {
        return zhuzhi;
    }


    /**
	 * 获取：住址
	 */

    public void setZhuzhi(String zhuzhi) {
        this.zhuzhi = zhuzhi;
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
        return "Fangke{" +
            "id=" + id +
            ", name=" + name +
            ", phone=" + phone +
            ", idNumber=" + idNumber +
            ", sexTypes=" + sexTypes +
            ", zhuzhi=" + zhuzhi +
            ", createTime=" + createTime +
        "}";
    }
}
