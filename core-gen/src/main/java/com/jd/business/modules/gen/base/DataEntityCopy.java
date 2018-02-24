package com.jd.business.modules.gen.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import common.base.entity.BaseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * 数据Entity类
 * @author horrific
 * @version 2017-12-22
 */
public abstract class DataEntityCopy<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 备注
	 */
	protected String remarks;


	/**
	 * 创建日期
	 */
	protected Date createDate;


	/**
	 * 更新日期
	 */
	protected Date updateDate;

	/**
	 * 删除标记（0：正常；1：删除；2：审核）
	 */
	protected String delFlag;
	
	public DataEntityCopy() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntityCopy(String id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(UUID.randomUUID().toString());
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		this.updateDate = new Date();
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
