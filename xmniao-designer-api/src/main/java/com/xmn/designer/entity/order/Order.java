package com.xmn.designer.entity.order;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long uid;

    private BigDecimal orderAmount;

    private BigDecimal balance;

    private BigDecimal freight;

    private String orderNo;

    private String tradeNo;

    private String outTradeNo;

    private String thirdUid;

    private String payType;

    private Date payTime;

    private String deliveryAddress;

    private String mobile;

    private String consignee;

    private Integer status;

    private Date createTime;

    private Integer deviceType;

    private String leaveNote;

    private String type;

    private String cancelReason;

    private String courierNumber;

    private String postCompany;

    private Long versionLock;
    
    private String mainImage;

    private Integer remindExpress;


    public Integer getRemindExpress() {
        return remindExpress;
    }

    public void setRemindExpress(Integer remindExpress) {
        this.remindExpress = remindExpress;
    }

    public Order(String orderNo) {
        this.orderNo = orderNo;
    }
    public Order() {
        super();
    }


    public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Long getVersionLock() {
		return versionLock;
	}

	public void setVersionLock(Long versionLock) {
		this.versionLock = versionLock;
	}




    public Order(Long uid, String orderNo) {
        super();
        this.uid = uid;
        this.orderNo = orderNo;
    }


    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }


    public String getPostCompany() {
        return postCompany;
    }

    public void setPostCompany(String postCompany) {
        this.postCompany = postCompany;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public void setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid == null ? null : thirdUid.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

	public String getLeaveNote() {
		return leaveNote;
	}

	public void setLeaveNote(String leaveNote) {
		this.leaveNote = leaveNote;
	}


}