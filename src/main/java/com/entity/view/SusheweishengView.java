package com.entity.view;

import com.entity.SusheweishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

/**
 * 宿舍卫生
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-03-20
 */
@TableName("susheweisheng")
public class SusheweishengView extends SusheweishengEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 卫生的值
		*/
		private String weishengValue;



		//级联表 sushe
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

	public SusheweishengView() {

	}

	public SusheweishengView(SusheweishengEntity susheweishengEntity) {
		try {
			BeanUtils.copyProperties(this, susheweishengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 卫生的值
			*/
			public String getWeishengValue() {
				return weishengValue;
			}
			/**
			* 设置： 卫生的值
			*/
			public void setWeishengValue(String weishengValue) {
				this.weishengValue = weishengValue;
			}








				//级联表的get和set sushe
					/**
					* 获取： 公寓
					*/
					public String getBuilding() {
						return building;
					}
					/**
					* 设置： 公寓
					*/
					public void setBuilding(String building) {
						this.building = building;
					}
					/**
					* 获取： 单元
					*/
					public String getUnit() {
						return unit;
					}
					/**
					* 设置： 单元
					*/
					public void setUnit(String unit) {
						this.unit = unit;
					}
					/**
					* 获取： 房间号
					*/
					public String getRoom() {
						return room;
					}
					/**
					* 设置： 房间号
					*/
					public void setRoom(String room) {
						this.room = room;
					}
					/**
					* 获取： 已住人员
					*/
					public Integer getSusheNumber() {
						return susheNumber;
					}
					/**
					* 设置： 已住人员
					*/
					public void setSusheNumber(Integer susheNumber) {
						this.susheNumber = susheNumber;
					}










}
