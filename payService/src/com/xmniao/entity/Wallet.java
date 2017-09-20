package com.xmniao.entity;

import java.math.BigDecimal;

public class Wallet {
    private Integer accountid;

    private Integer uid;

    private Integer sellerid;

    private Integer jointid;

    private String pPwd;

    private String applyDate;

    private String effectDate;

    private String cancelDate;

    private Integer status;

    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal commision;

    private BigDecimal zbalance;

    private BigDecimal integral;

    private BigDecimal sellerAmount;

    private BigDecimal quota;

    private String sign;

    private Integer signType;

    private String lastDate;

    private Integer fatherId;
    
    private String sellername;
    
    private String account;
    
    private BigDecimal deposit;
    
    private String comment;
    
    private BigDecimal returnDeposit;
    
    private Integer allianceid;
    
    private Boolean zbalanceLock;

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getReturnDeposit() {
		return returnDeposit;
	}

	public void setReturnDeposit(BigDecimal returnDeposit) {
		this.returnDeposit = returnDeposit;
	}

	public Integer getAllianceid() {
		return allianceid;
	}

	public void setAllianceid(Integer allianceid) {
		this.allianceid = allianceid;
	}

	public Boolean getZbalanceLock() {
		return zbalanceLock;
	}

	public void setZbalanceLock(Boolean zbalanceLock) {
		this.zbalanceLock = zbalanceLock;
	}

	public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

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

    public Integer getJointid() {
        return jointid;
    }

    public void setJointid(Integer jointid) {
        this.jointid = jointid;
    }

    public String getpPwd() {
        return pPwd;
    }

    public void setpPwd(String pPwd) {
        this.pPwd = pPwd == null ? null : pPwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getZbalance() {
        return zbalance;
    }

    public void setZbalance(BigDecimal zbalance) {
        this.zbalance = zbalance;
    }

    public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(BigDecimal sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
    
}