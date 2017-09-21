/**    
 * 文件名：MXmnSeller.java    
 *    
 * 版本信息：    
 * 日期：2016年11月30日    
 * Copyright 足下 Corporation 2016     
 * 版权所有    
 *    
 */
package com.xmniao.domain.message;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：MXmnSeller
 * 
 * 类描述：  用户浏览/消费商家统计
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年11月30日 下午2:00:36 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class MXmnSeller {
	private Integer uid;
	private Integer xmntype;
	private Integer sellerid;
	private Integer index;
	private Integer operate;
	private String last_time;
	private Integer version;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSellerid() {
		return sellerid;
	}
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getOperate() {
		return operate;
	}
	public void setOperate(Integer operate) {
		this.operate = operate;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getXmntype() {
		return xmntype;
	}
	public void setXmntype(Integer xmntype) {
		this.xmntype = xmntype;
	}
	
}
