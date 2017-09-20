package com.xmniao.xmn.core.xmermanagerment.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SystemConstants;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasOrder
 * 
 * 类描述： 寻蜜客签约套餐实体
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月3日 下午2:37:50
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSaasOrder extends BaseEntity {

	private static final long serialVersionUID = -1508364791853682819L;

	private Integer id;

	private String ordersn;

	private Integer dpid;

	private Integer uid;

	private Integer nums;

	private BigDecimal amount;

	private Integer status;

	private String reason;

	private Date sdate;
	
	private String sdateStr;

	private Date zdate;
	
	private String zdateStr;

	private Integer payType;

	private BigDecimal samount;

	private String payId;

	private String payCode;

	private BigDecimal agio;

	// 新增属性 add by hls
	private Integer stock;// 剩余套餐库存数量
	
	// 时间范围:开始和结束时间，用于搜索 add by lifeng
	private Date sDate;
	private Date eDate;
	
	private BigDecimal price;
	private Integer soldNums;
	private String parentid;//推荐人ID
	private String strStatus;//订单状态
	
	//寻蜜客名字 手机
	private String xmerName;
	private String xmerPhone;
	//支付金额
	private BigDecimal pay_money;
	private Integer version;
	private Integer returnnums;
	
	private Integer saasChannel;
	
	private Integer fromUid;
	
	private String mbEcno;
	
	private String mbTransNo;
	
	private String uidRelationChain;
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn == null ? null : ordersn.trim();
	}

	public Integer getDpid() {
		return dpid;
	}

	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}

	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getSamount() {
		return samount;
	}

	public void setSamount(BigDecimal samount) {
		this.samount = samount;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId == null ? null : payId.trim();
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode == null ? null : payCode.trim();
	}

	public BigDecimal getAgio() {
		return agio;
	}

	public void setAgio(BigDecimal agio) {
		this.agio = agio;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSoldNums() {
		return soldNums;
	}

	public void setSoldNums(Integer soldNums) {
		this.soldNums = soldNums;
	}
	
	public String getPayTypeText(){
		if(this.saasChannel!= null && this.saasChannel==2){
			return "EC报单";
		}else if(this.saasChannel!= null && this.saasChannel==3){
			return "V客兑换2";
		}else if(this.saasChannel!= null && this.saasChannel==4){
			return "V客赠送给主播";
		}
		return SystemConstants.getPayTypeText(payType+"");
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getStrStatus() {
		if(status==null){
			return "-";
		}
		if(status==0){
			strStatus = "待处理";
		} 
		if(status==1){
			strStatus = "成功";
		} 
		if(status==2){
			strStatus = "失败";
		}
		if(status==3){
			strStatus = "申请退款中";
		}
		if(status==4){
			strStatus = "退款成功";
		}
		if(status==5){
			strStatus = "退款申请不通过";
		}
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
	public String getSdateStr() {
		if(null==sdate) return "-";
		return DateUtil.smartFormat(sdate);
	}

	public void setSdateStr(String sdateStr) {
		this.sdateStr = sdateStr;
	}

	public String getZdateStr() {
		if(null==zdate) return "-";
		return DateUtil.smartFormat(zdate);
	}

	public void setZdateStr(String zdateStr) {
		this.zdateStr = zdateStr;
	}

	public String getXmerName() {
		return xmerName;
	}

	public void setXmerName(String xmerName) {
		this.xmerName = xmerName;
	}

	public String getXmerPhone() {
		return xmerPhone;
	}

	public void setXmerPhone(String xmerPhone) {
		this.xmerPhone = xmerPhone;
	}
	
	public BigDecimal getPay_money() {
		return pay_money;
	}

	public void setPay_money(BigDecimal pay_money) {
		this.pay_money = pay_money;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getReturnnums() {
		return returnnums;
	}

	public void setReturnnums(Integer returnnums) {
		this.returnnums = returnnums;
	}

	public Integer getSaasChannel() {
		return saasChannel;
	}
	
	public String getSaasChannelStr() {
		String str = "-";
		if(saasChannel!=null){
			switch (saasChannel) {
			case 1:
				str="常规购买";
				break;
			case 2:
				str="EC报单";			
				break;
			case 3:
				str="V客兑换";
				break;
			case 4:
				str="V客赠送给主播";
				break;
			default:
				break;
			}
		}
		return str;
	}

	public void setSaasChannel(Integer saasChannel) {
		this.saasChannel = saasChannel;
	}

	public Integer getFromUid() {
		return fromUid;
	}

	public void setFromUid(Integer fromUid) {
		this.fromUid = fromUid;
	}

	public String getMbEcno() {
		return mbEcno;
	}

	public void setMbEcno(String mbEcno) {
		this.mbEcno = mbEcno;
	}

	public String getMbTransNo() {
		return mbTransNo;
	}

	public void setMbTransNo(String mbTransNo) {
		this.mbTransNo = mbTransNo;
	}

	public String getUidRelationChain() {
		return uidRelationChain;
	}

	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}

	@Override
	public String toString() {
		return "TSaasOrder [id=" + id + ", ordersn=" + ordersn + ", dpid="
				+ dpid + ", uid=" + uid + ", nums=" + nums + ", amount="
				+ amount + ", status=" + status + ", reason=" + reason
				+ ", sdate=" + sdate + ", sdateStr=" + sdateStr + ", zdate="
				+ zdate + ", zdateStr=" + zdateStr + ", payType=" + payType
				+ ", samount=" + samount + ", payId=" + payId + ", payCode="
				+ payCode + ", agio=" + agio + ", stock=" + stock + ", sDate="
				+ sDate + ", eDate=" + eDate + ", price=" + price
				+ ", soldNums=" + soldNums + ", returnnums=" + returnnums + ", parentid=" + parentid
				+ ", strStatus=" + strStatus + ", xmerName=" + xmerName
				+ ", xmerPhone=" + xmerPhone + "]";
	}
	
}