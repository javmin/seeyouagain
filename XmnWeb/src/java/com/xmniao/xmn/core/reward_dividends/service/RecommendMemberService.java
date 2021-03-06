/**
 * 
 */
package com.xmniao.xmn.core.reward_dividends.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;  

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillDao;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.live_anchor.dao.BLiveFansRankDao;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.BVerAnchorRelationDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.BVerAnchorRelation;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.reward_dividends.dao.BursEarningsRelationDao;
import com.xmniao.xmn.core.reward_dividends.dao.TLiveGivedGiftVkeDao;
import com.xmniao.xmn.core.reward_dividends.dao.TLivePrivilegeDao;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.reward_dividends.entity.TLiveGivedGiftVke;
import com.xmniao.xmn.core.reward_dividends.entity.TLivePrivilege;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.ResponsePageList;
import com.xmniao.xmn.core.thrift.service.liveService.ResponseIDKeyData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmermanagerment.dao.TSaasOrderDao;
import com.xmniao.xmn.core.xmermanagerment.dao.TSaasSoldOrderDao;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasOrder;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasSoldOrder;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;
import com.xmniao.xmn.core.xmnpay.entity.LiveWallet;
import com.xmniao.xmn.core.xmnpay.service.LiveWalletService;

@Service
public class RecommendMemberService extends BaseService<TLivePrivilege> {

	@Autowired
	private TLivePrivilegeDao livePrivilegeDao;
	
	@Autowired
	private BLiveFansRankDao liveFansRankDao;
	
	@Autowired
	private BursService bursService;
	
	@Autowired
	private TLiveAnchorService liveAnchorService;
	
	@Autowired
	private TSaasSoldOrderDao saasSoldOrderDao;
	
	@Autowired
	private BVerAnchorRelationDao verAnchorRelationDao;
	
	@Autowired
	private BLiverDao liverDao;
	
	
	@Autowired
	private AllBillDao allBillDao;
	
	@Autowired
	private TSaasOrderDao saasOrderDao;
	
    /**
     * 商家dao
     */
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private BursEarningsRelationDao bursEarningsRelationDao;
	
	/**
	 * 注入扩展钱包服务
	 */
	@Resource(name = "walletExpansionServiceClient")
	private ThriftClientProxy walletExpansionServiceClient;

    @Autowired
	private TLiveGivedGiftVkeDao liveGivedGiftVkeDao;
    
	/**
	 * 注入直播钱包服务
	 */
	@Autowired
	private LiveWalletService liveWalletService;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
    //1 2 10, 2 5 25, 3 10 50, 5 30 150;
	public Pageable<TLivePrivilege> getLivePrivilegeInfoList(TLivePrivilege livePrivilege) {
		Pageable<TLivePrivilege> livePrivilegeInfoList = new Pageable<TLivePrivilege>(livePrivilege);
		
		livePrivilegeInfoList.setContent(getLivePrivilegeList(livePrivilege));
		livePrivilegeInfoList.setTotal(countLivePrivilege(livePrivilege));
		
	    return livePrivilegeInfoList;
	}
	
	
	public List<TLivePrivilege> getLivePrivilegeList(TLivePrivilege livePrivilege){	
		List<TLivePrivilege> livePrivilegeList = null;
		
		if (StringUtils.isNotBlank(livePrivilege.getPhone()) || StringUtils.isNotBlank(livePrivilege.getNickname())  ) {
			Burs burs = new Burs();
			if (StringUtils.isNotBlank(livePrivilege.getPhone()) ) {
				String phone = livePrivilege.getPhone();
				burs.setPhone(phone);
			}
			if (StringUtils.isNotBlank(livePrivilege.getNickname()) ) {
				String nickname = livePrivilege.getNickname();
				burs.setNname(nickname);
			}		
			List<Burs> bursList = bursService.getUrsList(burs);
			if (bursList != null && bursList.size() > 0 ){
				livePrivilege.setUid(bursList.get(0).getUid());
			}else{
	            return livePrivilegeList;
			}
		}
		
		//分页查询(要根据uid分组)V客信息
		livePrivilegeList = livePrivilegeDao.getRecommendMemberList(livePrivilege);
		if (livePrivilegeList != null){
			//设置V客等级
			BLiveFansRank fansRank = new BLiveFansRank();
			fansRank.setRankType(2);  //V客类型
			List<BLiveFansRank> liveFansRankList = liveFansRankDao.getList(fansRank);  //v客等级
			List<Integer> uids=new ArrayList<Integer>();
			for (TLivePrivilege bean: livePrivilegeList){
				if (liveFansRankList != null){
					for (BLiveFansRank object: liveFansRankList){
						if (bean.getLedgerLevel()!= null && bean.getLedgerLevel().equals(Integer.parseInt(object.getId().toString()))){
							 bean.setRankName(object.getRankName());
							 bean.setTotalRecommendSellerStr(object.getSaasNumber() == null ? "0" : object.getSaasNumber().toString());  //SAAS推荐总名额
							 bean.setTotalRecommendLiveStr(object.getAnchorNumber() == null ? "0" : object.getAnchorNumber().toString());  //主播推荐总名额
						}
					}
				}
				//查询用户uid
				Integer uid = bean.getUid();
				if(uid != null){
					uids.add(uid);
				}
			}
			//查询用户信息, V客推荐主播信息, V客签约店铺信息
			if(uids!=null && uids.size()>0){
				List<BLiver> liverList = liveAnchorService.getUrsDetailInfoList(uids.toArray());
				if (liverList != null ){
					for (TLivePrivilege bean: livePrivilegeList){
						for (BLiver object: liverList){
							if (bean.getUid()!= null && bean.getUid().equals(object.getUid()) ){
								if (object.getNickname() != null)   //昵称
									bean.setNickname(object.getNickname());
								if (object.getName() != null)   //真实姓名
									bean.setName(object.getName());
								if (object.getPhone() != null)  //手机号码
									bean.setPhone(object.getPhone());
								if (object.getUidRelationChain() != null){   //上级,
									String superiorUid = "", uidRelationChain = "", topLevelUid = "";
									uidRelationChain = object.getUidRelationChain();
									String[] uidArray = uidRelationChain.split(",");
									int length = uidArray.length;
									if(length>1){
										superiorUid = uidArray[length-2];
										String superiorUidTemp = superiorUid.replaceAll("^(0+)", "");//去掉开头拼接的0
										bean.setSuperiorStr(superiorUidTemp);
									}
									if(length>2){
										topLevelUid=uidArray[length-3];
										String topLevelUidTemp = topLevelUid.replaceAll("^(0+)", "");//去掉uid开头拼接的0
										bean.setTopLevelStr(topLevelUidTemp);
									}
								}
							}
						}
					}
				}
				//获取商家已推荐数量
				List<Map<String, String>> recommendSellerCountList = saasSoldOrderDao.getRecommendSellerCountList(uids.toArray());
				if (recommendSellerCountList != null ){
					for (TLivePrivilege bean: livePrivilegeList){
						for (Map<String, String> object: recommendSellerCountList){
							if (bean.getUid()!= null && bean.getUid().equals(object.get("uid")) ){
								if (object.get("number") != null){   //已推荐的商家数据
									bean.setRecommendSeller(Integer.parseInt(String.valueOf(object.get("number"))));
								}
							}
						}
					}
				}
				//获取主播已推荐数量
				List<Map<String, String>> recommendLiveCountList = verAnchorRelationDao.getRecommendLiveCountList(uids.toArray());
				if (recommendLiveCountList != null ){
					for (TLivePrivilege bean: livePrivilegeList){
						for (Map<String, String> object: recommendLiveCountList){
							if (bean.getUid()!= null && bean.getUid().equals(object.get("uid")) ){
								if (object.get("number") != null) {  //已推荐的商家数据
									bean.setRecommendLive(Integer.parseInt(String.valueOf(object.get("number"))));
								}
							}
						}
					}
				}
				//获取消费订单
				List<Bill> recommendSellerList = allBillDao.getRecommendSellerList(uids.toArray());
				if (recommendSellerList != null ){
					for (TLivePrivilege bean: livePrivilegeList){
						BigDecimal sellerProfitAmount = new BigDecimal("0");
						for (Bill object: recommendSellerList){
							if (bean.getUid()!= null && bean.getUid().equals(object.getUid()) ){
								if (object.getCommission() != null && object.getSaasChannel() != null) {  //已推荐的商家数据
									JSONObject json = JSON.parseObject(object.getCommission());  
									if (object.getSaasChannel().equals(3) ){//3V客SAAS签约 4主播(V客赠送)SAAS签约
										if (json.getString("mike_amount") != null){
											sellerProfitAmount = sellerProfitAmount.add(new BigDecimal(json.getString("mike_amount")).setScale(2, BigDecimal.ROUND_HALF_UP));
										}
									}else{
										if (json.getString("parent_mike_amount") != null){
											sellerProfitAmount = sellerProfitAmount.add(new BigDecimal(json.getString("parent_mike_amount")).setScale(2, BigDecimal.ROUND_HALF_UP));
										}
									}
								}
							}
						}
						if (sellerProfitAmount.compareTo(new BigDecimal("0")) != 0)
						    bean.setSellerProfitAmount(sellerProfitAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
				}
				List<LiveWallet> liveWalletList = liveWalletService.selectLiveWalletByUids(uids.toArray());
				if(liveWalletList!=null && liveWalletList.size()>0){
					for(TLivePrivilege liver:livePrivilegeList){
						for(LiveWallet wallet:liveWalletList){
							if(liver.getUid().compareTo(wallet.getUid())==0){
								liver.setRestrictive(wallet.getRestrictive());
								liver.setLimitBalance(wallet.getLimitBalance());
								break;
							}
						}
					}
				}
				
				//查询主播贡献的总收益
				if (uids.size() > 0)
					this.searchTotalLiverProfitList(uids, livePrivilegeList);
			}
		}
		
		return livePrivilegeList;
	}
	
	public Long countLivePrivilege(TLivePrivilege livePrivilege) {
		Long totalNum = 0l;
		
		if (StringUtils.isNotBlank(livePrivilege.getPhone()) || StringUtils.isNotBlank(livePrivilege.getNickname())  ) {
			Burs burs = new Burs();
			if (StringUtils.isNotBlank(livePrivilege.getPhone()) ) {
				String phone = livePrivilege.getPhone();
				burs.setPhone(phone);
			}
			if (StringUtils.isNotBlank(livePrivilege.getNickname()) ) {
				String nickname = livePrivilege.getNickname();
				burs.setNname(nickname);
			}		
			List<Burs> bursList = bursService.getUrsList(burs);
			if (bursList != null && bursList.size() > 0 ){
				livePrivilege.setUid(bursList.get(0).getUid());
			}else{
	            return totalNum;
			}
		}
		totalNum = livePrivilegeDao.countRecommendMember(livePrivilege);
		return totalNum;
	}
	
	
	public Map<String, Object> countLivePrivilegeInfo(TLivePrivilege livePrivilege){
		Map<String, Object>  resultMaps = new HashMap<String, Object> ();
		livePrivilege.setLimit(-1);
		List<TLivePrivilege> livePrivilegeList = getLivePrivilegeList(livePrivilege);
		Integer vkeCount = 0, vkeLevel1 = 0, vkeLevel2 = 0, vkeLevel3 = 0, vkeLevel5 = 0, vkeLive = 0, vkeSeller = 0;
		BigDecimal liveProft = new BigDecimal(0);
		BigDecimal sellerProft = new BigDecimal(0);
		vkeCount = livePrivilegeList.size();
		for (TLivePrivilege bean: livePrivilegeList){
			liveProft = liveProft.add(bean.getLiveProfitAmount() == null ? new BigDecimal(0) : bean.getLiveProfitAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			sellerProft = sellerProft.add(bean.getSellerProfitAmount()  == null ? new BigDecimal(0) : bean.getSellerProfitAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			if ("一星V客".equals(bean.getRankName()) ){
				vkeLevel1 += 1;
			} else if ("二星V客".equals(bean.getRankName()) ){
				vkeLevel2 += 1;
			}else if ("三星V客".equals(bean.getRankName()) ){
				vkeLevel3 += 1;
			}else if ("五星V客".equals(bean.getRankName()) ){
				vkeLevel5 += 1;
			}
			vkeLive += bean.getRecommendLive() == null ? 0 : bean.getRecommendLive() ;
			vkeSeller += bean.getRecommendSeller() == null ? 0 : bean.getRecommendSeller();
		}
		resultMaps.put("vkeCount", vkeCount);
		resultMaps.put("vkeLevel1", vkeLevel1);
		resultMaps.put("vkeLevel2", vkeLevel2);
		resultMaps.put("vkeLevel3", vkeLevel3);
		resultMaps.put("vkeLevel5", vkeLevel5);
		
		resultMaps.put("vkeLive", vkeLive);
		resultMaps.put("liveProft", liveProft);
		resultMaps.put("vkeSeller", vkeSeller);
		resultMaps.put("sellerProft", sellerProft);
		
		return resultMaps;
	}
	
	
	/**
	 * 方法描述：通过观众给主播送礼对V客产生的分成记录表获取收益 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月16日上午9:41:21 <br/>
	 * @param uids
	 * @param livePrivilegeList
	 */
	public void searchTotalLiverProfitList(List<Integer> uids, List<TLivePrivilege> livePrivilegeList) {
		List<Map<String, Object>> liveGiftCountList = liveGivedGiftVkeDao.getVerProfitCountLiveGift(uids.toArray());
		for (TLivePrivilege livePrivilege: livePrivilegeList) {
			if (livePrivilege.getUid() != null){
				for (Map<String, Object> object : liveGiftCountList) {
					if (object.get("vkeUid") != null && livePrivilege.getUid().equals(object.get("vkeUid")) ){
						if (object.get("profit") != null){ //获得商户收益(历史收益)
							Double profit = (Double) object.get("profit");
							livePrivilege.setLiveProfitAmount(new BigDecimal(profit));
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 方法描述：主播贡献的总收益 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月3日下午6:35:31 <br/>
	 * @param uids
	 * @param livePrivilegeList
	 */
	public void searchLiverTotalProfitList(List<Integer> uids, List<TLivePrivilege> livePrivilegeList) {
		
		try {
			List<String> uidList = new ArrayList<String>();
	        for (Integer id : uids) { 
	        	uidList.add(String.valueOf(id));
	        }
			//连接接口进行查询
			com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client client = (com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client) 
					(walletExpansionServiceClient.getClient());
			log.info("查询主播贡献的收益开始");
			ResponsePageList resultList = client.getExpansionByUids(uidList, 10);
			
			if(resultList.dataInfo.state == 0){
				this.getSellerTotalProfitFromMap(resultList.pageList, livePrivilegeList);
			}else{
				log.error("调用查询主播贡献的收益失败");
				throw new RuntimeException("查询主播贡献的收益失败, 错误信息:"+ resultList.dataInfo.getMsg());

			}
//			log.info("查询储值卡充值消费详细记录结束，返回值：" + livePurseDataList.size());
		} catch (Exception e) {
			log.error("查询主播贡献的收益失败", e);
//			throw new ApplicationException("查询直播钱包异常", e, new Object[] { uid });
		} finally {
			walletExpansionServiceClient.returnCon();
		}
	}
	
	/**
	 * 方法描述：获得商户收益 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月3日下午6:35:58 <br/>
	 * @param pramsMapList
	 * @param livePrivilegeList
	 * @throws Exception
	 */
	public void getSellerTotalProfitFromMap(List<Map<String,String>> pramsMapList, List<TLivePrivilege> livePrivilegeList) throws Exception{
		for (TLivePrivilege livePrivilege: livePrivilegeList) {
			if (livePrivilege.getUid() != null){
				for (Map<String, String> object : pramsMapList) {
					if (StringUtils.isNotBlank(object.get("uid")) && livePrivilege.getUid().equals(Integer.parseInt(object.get("uid"))) ){
						if (StringUtils.isNotBlank(object.get("totalAmount")) ){ //获得商户收益(历史收益)
							BigDecimal totalAmount = new BigDecimal(object.get("totalAmount"));
							totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);  
							livePrivilege.setLiveProfitAmount(totalAmount);
						}
					}
				}
			}
		}
	}
	

	/**
	 * 方法描述：放弃推荐主播 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月24日下午5:58:56 <br/>
	 * @param verAnchorRelation
	 * @return
	 * @throws Exception
	 */
	public int updateOptionState(BVerAnchorRelation verAnchorRelation) throws Exception{
		//更改数据库中的推荐状态 anchor_uid:推荐主播uid
		verAnchorRelation.setUpdateTime(new Date());
		
		return verAnchorRelationDao.updateOptionState(verAnchorRelation);
	}
	
	
	public List<BVerAnchorRelation> getVkeProfitLiveGiftDetailList(TLivePrivilege livePrivilege){
		//查询用户uid
		Integer uid = livePrivilege.getUid();
		//查询推荐的主播信息
		List<BVerAnchorRelation> verAnchorRelationList = verAnchorRelationDao.getRecommendLiveList(uid);
		
		//观众给主播送礼对V客产生的分成记录表
		List<TLiveGivedGiftVke> liveGivedGiftVkeList = liveGivedGiftVkeDao.getVerProfitCountLiveGiftList(uid);
		
		for (BVerAnchorRelation verAnchorRelation: verAnchorRelationList) {
			for (TLiveGivedGiftVke liveGivedGiftVke: liveGivedGiftVkeList) {
				if (verAnchorRelation.getUid() != null && verAnchorRelation.getUid().equals(liveGivedGiftVke.getAnchorUid()) ){
					if (liveGivedGiftVke.getProfit() != null ){  //统计收益
						verAnchorRelation.setFromLiveProfit(liveGivedGiftVke.getProfit());
					}
					if (liveGivedGiftVke.getMoney() != null  ){ //主播累计鸟蛋
//						BigDecimal rate = new BigDecimal(resultMap.get("balanceTotal")).setScale(2, BigDecimal.ROUND_HALF_UP);
						verAnchorRelation.setLiveTotalProfit(liveGivedGiftVke.getMoney());
					}
				}
			}
		}
	
		return verAnchorRelationList;
	}
	
	
	/**
	 * 方法描述：获取推荐主播收益明细信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月3日下午3:44:04 <br/>
	 * @param livePrivilege
	 * @return
	 */
	public List<BVerAnchorRelation> getVerRecommendLiveDetailList(TLivePrivilege livePrivilege){
		//查询用户uid
		Integer uid = livePrivilege.getUid();
		//查询推荐的主播信息
		List<BVerAnchorRelation> verAnchorRelationList = verAnchorRelationDao.getRecommendLiveList(uid);
		
		//通过接口查询主播的收益信息
		this.searchLiveProfitList(uid, verAnchorRelationList);
	
		return verAnchorRelationList;
	}
	
	
	
	/**
	 * 方法描述：查询主播的累计收益与贡献给V客的收益 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月4日上午11:46:24 <br/>
	 * @param uid
	 * @param verAnchorRelationList
	 */
	public void searchLiveProfitList(Integer uid, List<BVerAnchorRelation> verAnchorRelationList) {
		StringBuffer sb = new StringBuffer();
		for (BVerAnchorRelation verAnchorRelation: verAnchorRelationList) {
			if (verAnchorRelation.getUid() != null){
				sb.append("," + verAnchorRelation.getUid());
			}
		}
		String liverIds = "";
		if (sb.length() > 1){
			liverIds = sb.toString().substring(1);
			
			try {
				//连接接口进行查询
				com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client client = (com.xmniao.xmn.core.thrift.service.liveService.WalletExpansionService.Client) 
						(walletExpansionServiceClient.getClient());
				log.info("查询主播的累计收益与贡献给V客的收益记录开始");
				Map<String, String> params = new HashMap<String, String>();
				params.put("uid", uid.toString());  //用户id  (V客)
				params.put("liverIds", liverIds);  //主播uid
				ResponseIDKeyData  resultList = client.getIncomeByLivers(params);
				if (resultList.state == 0) {
					getLiveProfitFromMap(resultList.resultMap, verAnchorRelationList);
				}else{
					log.error("调用查询主播的累计收益与贡献给V客的收益失败");
					throw new RuntimeException("查询主播的累计收益与贡献给V客的收益失败, 错误信息:"+ resultList.getMsg());
				}
//				log.info("查询储值卡充值消费详细记录结束，返回值：" + livePurseDataList.size());

			} catch (Exception e) {
				log.error("查询直播钱包失败", e);
//				throw new ApplicationException("查询直播钱包异常", e, new Object[] { uid });
			} finally {
				walletExpansionServiceClient.returnCon();
			}
		}

	}
	
	public void getLiveProfitFromMap(Map<String, Map<String,String>> paramsMap, List<BVerAnchorRelation> verAnchorRelationList) throws Exception{
		for (BVerAnchorRelation verAnchorRelation: verAnchorRelationList) {
			Map<String,String> resultMap = paramsMap.get(verAnchorRelation.getUid().toString());
			if (StringUtils.isNotBlank(resultMap.get("amountTotal")) ){  //统计收益
				BigDecimal amountTotal = new BigDecimal(resultMap.get("amountTotal"));
				amountTotal = amountTotal.setScale(2, BigDecimal.ROUND_HALF_UP);  
				verAnchorRelation.setFromLiveProfit(amountTotal);
			}
			if (StringUtils.isNotBlank(resultMap.get("balanceTotal")) ){ //主播累计鸟蛋
				BigDecimal rate = new BigDecimal(resultMap.get("balanceTotal")).setScale(2, BigDecimal.ROUND_HALF_UP);
				verAnchorRelation.setLiveTotalProfit(rate);
			}
		}
	}
	
	
	/**
	 * 方法描述：获取推荐商户收益明细信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月3日下午3:44:04 <br/>
	 * @param livePrivilege
	 * @return
	 */
	public List<Bill> getVerRecommendSellerDetailList(TLivePrivilege livePrivilege){
		List<Integer> uids=new ArrayList<Integer>();
		//V客uid
		Integer uid = livePrivilege.getUid();
		if(uid != null){
			uids.add(uid);
		}
		//统计推荐的商家信息
		List<Bill> billList = allBillDao.getRecommendSellerInfoList(uid);
		//查询推荐的商家订单列表
		List<Bill> sellerBillList = allBillDao.getRecommendSellerList(uids.toArray());
		//统计商家贡献给主播的收益
		for (Bill bill :billList){
			BigDecimal sellerProfitAmount = new BigDecimal("0");
			for (Bill object :sellerBillList){
				if ( bill.getSellerid().equals(object.getSellerid()) ){
					if (object.getCommission() != null && object.getSaasChannel() != null) {  //已推荐的商家数据
						JSONObject json = JSON.parseObject(object.getCommission());  
						if (object.getSaasChannel().equals(3) ){//3V客SAAS签约 4主播(V客赠送)SAAS签约
							if (json.getString("mike_amount") != null){
								sellerProfitAmount = sellerProfitAmount.add(new BigDecimal(json.getString("mike_amount")).setScale(2, BigDecimal.ROUND_HALF_UP));
							}
						}else{
							if (json.getString("parent_mike_amount") != null){
								sellerProfitAmount = sellerProfitAmount.add(new BigDecimal(json.getString("mike_amount")).setScale(2, BigDecimal.ROUND_HALF_UP));
							}
						}
					}
				}
			}
			bill.setFromSellerProfit(sellerProfitAmount);  //商家贡献收益
		}
		return billList;
	}
	
	/**
	 * 方法描述：放弃主播推荐名额<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月4日下午5:40:05 <br/>
	 * @param verAnchorRelation  主播的uid
	 * @return
	 * @throws Exception
	 */
	public int abandonVerAnchorRelation(Integer uid) throws Exception {
		int result = 0;
		//  1.判断是否有被V客赠送过SAAS套餐。 2.判断主播是否签约(SAAS)店铺。3. 把签约店铺的关系转移给V客, 4.退还SAAS名额给V客
		this.handleRelation(uid);
		
		// 5.删除主播寻密客身分信息
		BursEarningsRelation bursEarningsRelation = new BursEarningsRelation();
		bursEarningsRelation.setUid(uid);
		bursEarningsRelation.setObjectOriented(6); //
		bursEarningsRelationDao.deleteRelationByUid(bursEarningsRelation);
		
		// 6.更改V客与主播推荐关系
		BVerAnchorRelation verAnchorRelation = new BVerAnchorRelation();
		verAnchorRelation.setAnchorUid(uid);
		verAnchorRelation.setUpdateTime(new Date());
		verAnchorRelation.setRecommendStatus(4); // 推荐状态 1 审核中 2 审核通过 3 审核被拒 4
		verAnchorRelation.setRefuseReason("后台管理手工操作V客放弃主播");
		result = verAnchorRelationDao.abandonVerAnchorRelation(verAnchorRelation);
		
		return result;
	}
	
	/**
	 * 方法描述：1.判断是否有被V客赠送过SAAS套餐。 2.判断主播是否签约(SAAS)店铺。3. 把签约店铺的关系转移给V客, 4.退还SAAS名额给V客 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月12日上午11:37:56 <br/>
	 * @param liverUid
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void handleRelation(Integer liverUid) throws Exception {
		  Map<String,Object> vkeMaps = new HashMap<String,Object>();
	 	  Map<String,Object> liveMaps = new HashMap<String,Object>();
		 // 1.判断是否有被V客赠送过SAAS套餐。
		  TSaasOrder saasOrder = new TSaasOrder();
		  saasOrder.setUid(liverUid);
		  saasOrder.setSaasChannel(4);//saas购买渠道 1常规购买 2 脉客购买 3 V客兑换 4 主播接受V客赠送;
		  saasOrder.setStatus(1);//默认0 0 未支付 1 支付成功 2 支付失败 3 申请退款中 4 退款成功 5 退款申请不通过
		  List<TSaasOrder> saasOrderInfoList = saasOrderDao.getSaasOrderList(saasOrder);
		  if (saasOrderInfoList != null && saasOrderInfoList.size() > 0) {  //有赠送的情况
			  Integer vkeUid = 0;//得到V客的uid
			  for (TSaasOrder saasOrderInfo : saasOrderInfoList){  //赠送过多次SAAS名额
				  vkeUid = saasOrderInfo.getFromUid();
				  break;
			  }
			  // 2.判断主播是否签约(SAAS)店铺。
			  TSaasSoldOrder saasSoldOrder = new TSaasSoldOrder();
			  saasSoldOrder.setUid(liverUid);
			  saasSoldOrder.setSaasChannel(4); //签约的SAAS获取渠道 1常规 2脉客 3 V客 4主播接受V客赠送
			  saasSoldOrder.setStatus(1);   //默认0 0 未支付 1 支付成功 2 支付失败 3 申请退款中 4 退款成功 5 退款申请不通过
			  List<TSaasSoldOrder> saasSoldOrderList = saasSoldOrderDao.getSaasSoldOrderList(saasSoldOrder);
			  if (saasSoldOrderList != null && saasSoldOrderList.size() > 0){  //有签约过店铺的情况
				  //  3.把签约店铺的关系转移给V客。
				  for (TSaasSoldOrder bean :saasSoldOrderList) {
					 String ordersn = "02" + this.getBid();
                     //3.1新增一条V客对应店铺的关系信息
					 TSaasSoldOrder newSaasSoldOrder = new TSaasSoldOrder();
				     BeanUtils.copyProperties(bean, newSaasSoldOrder);
				     newSaasSoldOrder.setId(null);
				     newSaasSoldOrder.setUid(vkeUid);
				     newSaasSoldOrder.setOrdersn(ordersn);
				     newSaasSoldOrder.setSaasChannel(3);  //签约的SAAS获取渠道 1常规 2脉客 3 V客 4主播接受V客赠送
				     newSaasSoldOrder.setUdate(new Date());
//				     newSaasSoldOrder.setSaasOrdersn(saasOrdersn);
				     saasSoldOrderDao.insertSelective(newSaasSoldOrder);
					 //3.2解除主播对应店铺的关系信息
				     bean.setStatus(4);  //订单状态: 默认0 0 未支付(存入草稿) 1 (支付成功） 2 (已作废), 3(已转移给V客)
				     bean.setUdate(new Date());  //更新时间
				     bean.setReason("V客弃主播, 主播签约的店铺关系转移给V客");
					 saasSoldOrderDao.transferSellerToVke(bean);
					  
					 //3.3更改店铺的负责人
					 TSeller seller = sellerDao.getObject(new Long(bean.getSellerid()));
					 seller.setUid(vkeUid);
					 seller.setSaasType(3);  //1 寻觅客 2中脉 3 V客 4主播(V客赠送)SAAS签约',0
					 seller.setUdate(new Date());
					 sellerDao.xmerAbandonSeller(seller);
				  }
			  }
			  
			  // 4.退还SAAS名额给V客
			  for (TSaasOrder saasOrderInfo : saasOrderInfoList){  // V客赠送SAAS给主播所有记录(2条)
//				  Integer nums = saasOrderInfo.getNums();  //签约数量
				  Integer stock = saasOrderInfo.getStock() == null ? 0 : saasOrderInfo.getStock();  //库存(SAAS名额)
//				  Integer soldnums = saasOrderInfo.getSoldNums() == null ? 0 : saasOrderInfo.getSoldNums(); //库存(SAAS名额)
			 	  boolean flag = false;  //已经全部退还了
			 	  
			 	  if (stock > 0){//如果没有剩余套餐库存数量
//					  if (soldnums > 0){  //V客卖出数量
	//					  Integer vkeSaasNums = 0; //可以退还的数量
	//					  Integer returnNums = 0; //退还的订单数
						  saasOrder = new TSaasOrder();
						  saasOrder.setUid(vkeUid);
						  saasOrder.setSaasChannel(3);//saas购买渠道 1常规购买 2 脉客购买 3 V客兑换 4 主播接受V客赠送;
						  saasSoldOrder.setStatus(1);   //默认0 0 未支付 1 支付成功 2 支付失败 3 申请退款中 4 退款成功 5 退款申请不通过
						  List<TSaasOrder> vkeSaasOrderInfoList = saasOrderDao.getSaasOrderList(saasOrder);    //查询当前V客所有兑换的SAAS订单
						  for (TSaasOrder vkeSaasOrder : vkeSaasOrderInfoList){  //V客兑换的SAAS订单
							  vkeMaps = new HashMap<String,Object>();
							  liveMaps = new HashMap<String,Object>();
							  
							  //判断总数和加回去的库存是否不超过
							  vkeMaps.put("ordersn", vkeSaasOrder.getOrdersn());
							  vkeMaps.put("version", vkeSaasOrder.getVersion()  == null ? 0 : vkeSaasOrder.getVersion());
							  
							  //签约数量0, 剩余套餐库存数量0, 卖出数量0, 退还数量 
	//					 	  Integer vkeNums = vkeSaasOrder.getNums();  //签约数量
	//						  Integer vkeStock = vkeSaasOrder.getStock();  //剩余套餐库存数量
	//						  Integer vkeReturnnums = vkeSaasOrder.getReturnnums();
							  Integer vkeSoldnums = vkeSaasOrder.getSoldNums()  == null ? 0 : vkeSaasOrder.getSoldNums(); //卖出数量
							  if (vkeSoldnums > 0 && stock > 0){//主播剩余套餐库存数量
						 		  if ( vkeSoldnums >= stock ){  //10  10
					 				  vkeMaps.put("stock", stock); 
									  vkeMaps.put("soldnums", stock); 
									  flag = true;	
					 			  }else if (vkeSoldnums < stock){
					 				 vkeMaps.put("stock",  vkeSoldnums ); 
									 vkeMaps.put("soldnums", vkeSoldnums);  	
									 stock = stock- vkeSoldnums;
						 		  }
						 		  saasOrderDao.returnVkeSaasOrderNums(vkeMaps);
						 		  if (flag){
						 			 //已经全部退还, 退出循环
						 			 break;
						 		  }
							  }
//						  }
					  }
			 	  }else{
			 		  //全部都签约了, 上述已经转移店铺, 更改库存为0即可
			 		  flag = true;		
			 	  }

				  if (flag){
			 		  //主播的SAAS名额和库存及已签约改为0,
			 		 liveMaps.put("ordersn", saasOrderInfo.getOrdersn());
			 		 liveMaps.put("version", saasOrderInfo.getVersion());  
			 		 saasOrderDao.updataLiveSaasOrderNums(liveMaps);  
			 	  }
			  }
			  
		  }  
	}
	
	//生成订单格式 : 160501 100655 1254。格式6位年月日+小时 分 秒+4位随机数
	public static String getBid() {
		String dateStr = DateUtil.formatDate(new Date(), DateUtil.YMDHMS);
		String[] da = dateStr.replace(" ", "-").replace(":", "-")
				.substring(2, dateStr.length()).split("-");
		StringBuilder db = new StringBuilder();
		for (int i = 0; i < da.length; i++) {
			String dr = da[i];
			db.append(dr);
		}
		return db.append(((int) (Math.random() * 9000 + 1000)) + "").toString();
	}
	
	
	/* ************************************* 添加V客关系 ****************************************** */
	
	/**
	 * 方法描述：获取剩余推荐的数量<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午11:47:21 <br/>
	 * @param vkeUid
	 * @param type 1：主播， 2：SAAS
	 * @return
	 */
	public Integer getVkeLeaveUseQuota(Integer vkeUid, Integer type) {
	    Integer leaveQuota = 0, usedQuota = 0, totalQuota = 0;
		// 先查出总推荐数量 
	    BLiveFansRank liveFansRank = liveFansRankDao.getQuotaByVkeUid(vkeUid);
	    if (liveFansRank != null) {
	    if (type.equals(1) ){  //主播
	    	totalQuota = liveFansRank.getAnchorNumber() == null ? 0 : liveFansRank.getAnchorNumber();
			//获取主播已推荐数量
	    	Object[] uids = { vkeUid };  
			List<Map<String, String>> resultMaps = verAnchorRelationDao.getRecommendLiveCountList(uids);
	    	for (Map<String, String> object:resultMaps) {
    		    if ( vkeUid.equals(object.get("uid"))){
    			    usedQuota = Integer.parseInt(String.valueOf(object.get("number")));
    			    break;
    		    }
	    	}
	    	
	    }else if (type.equals(2) ){  //SAAS
	    	totalQuota = liveFansRank.getSaasNumber() == null ? 0 : liveFansRank.getSaasNumber();
			// 查询出已推荐
	    	Object[] uids = { vkeUid };  
	    	List<Map<String, String>> resultMaps = saasSoldOrderDao.getRecommendSellerCountList(uids);
	    	for (Map<String, String> object:resultMaps) {
	    		if ( vkeUid.equals(object.get("uid"))){
	    			 usedQuota = Integer.parseInt(String.valueOf(object.get("number")));
	    			 break;
	    		 }
	    	 }
	    }
	    
	    leaveQuota = totalQuota - usedQuota;
	    }
		// 获取剩余数量
		return leaveQuota;
	}
	
	
	/**
	 * 方法描述：根据主播查询推荐V客信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:37:47 <br/>
	 * @param liveUid
	 */
	public String getVkeNameByLive(Integer liveUid) {
		String vkeName = "";
		Map<String, Object> resultMap = verAnchorRelationDao.getVkeNameByLiveUid(liveUid);
		if (resultMap != null){
			vkeName = (String)resultMap.get("nname");
		}
		return vkeName;
	}

	/**
	 * 方法描述：根据店铺查询对应的推荐人信息<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:38:15 <br/>
	 * @param vkeUid
	 * @param sellerId
	 */
	public String getVkeNameBySeller(Integer sellerId) {
		String vkeName = "";
		Map<String, Object> resultMap = saasSoldOrderDao.getVkeNameBySellerId(sellerId);
		if (resultMap != null){
			Integer uid = (Integer)resultMap.get("uid");
			if (uid != null){
				Burs burs = new Burs();
				burs.setUid(uid);
				Burs bean = bursService.getUrs(burs);
				if (bean != null ){
					vkeName = bean.getUname() ;
				}
			}
		}
		return vkeName;
	}
	
	/**
	 * 方法描述：V客绑定对应的主播 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:39:48 <br/>
	 */
	public void addLiveRelationship(Integer vkeUid, Integer anchorUid) {
        //往verAnchorRelationDao中添加记录信息
		BVerAnchorRelation verAnchorRelation = new BVerAnchorRelation();
		verAnchorRelation.setUid(vkeUid);
		verAnchorRelation.setAnchorUid(anchorUid);  //主播编号
		
		verAnchorRelation.setRecommendStatus(2);  //已审核
		verAnchorRelation.setRelationType(1);  //V客推荐
		verAnchorRelation.setCreateTime(new Date());
		verAnchorRelationDao.insertSelective(verAnchorRelation);
	}
	
	/**
	 * 方法描述：V客绑定对应的主播 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月31日上午10:39:48 <br/>
	 */
	public void addSellerRelationship(Integer vkeUid, Integer sellerId) {
		// 1.查询出SAAS兑换订单
		TSaasOrder saasOrder = new TSaasOrder();
		saasOrder.setUid(vkeUid);
		saasOrder.setSaasChannel(3);//saas购买渠道 1常规购买 2 脉客购买 3 V客兑换 4 主播接受V客赠送;
		List<TSaasOrder> vkeSaasOrderList = saasOrderDao.getSaasOrderList(saasOrder); //查询当前V客所有兑换的SAAS订单
		for(TSaasOrder bean: vkeSaasOrderList){
			if (bean.getStock() > 0 ){
				// 2.往saasSoldOrder中插入数据
				String ordersn = "02" + this.getBid(); 
				TSaasSoldOrder saasSoldOrder = new TSaasSoldOrder();
				saasSoldOrder.setId(null);
				saasSoldOrder.setUid(vkeUid);
				saasSoldOrder.setSellerid(sellerId);
				saasSoldOrder.setOrdersn(ordersn);
				saasSoldOrder.setSaasChannel(3);  //签约的SAAS获取渠道 1常规 2脉客 3 V客 4主播接受V客赠送
                saasSoldOrder.setCreateDate(new Date());
			    saasSoldOrder.setSaasOrdersn(bean.getOrdersn());
			    saasSoldOrder.setStatus(1);//支付成功
			    saasSoldOrderDao.insertSelective(saasSoldOrder);
			    
				// 3.扣减库存信息
	            Map<String, Object> uMap = new HashMap<String, Object>();   // 封装更新参数
	            uMap.put("uid", vkeUid);
	            uMap.put("isAddStock", false);
	            uMap.put("edate", new Date());
	            uMap.put("ordersn", bean.getOrdersn());
	            uMap.put("version", bean.getVersion());
	            // 更新
	            int result = saasOrderDao.updateSaasOrderSaleInfo(uMap);
	            if (result == 0) {
	                log.error("版本不一致，给寻蜜客【" + vkeUid + "】扣取SAAS软件失败。");
	            }
				// 3.3更改店铺的负责人
				TSeller seller = sellerDao.getObject(new Long(sellerId));
				if (seller != null){
					seller.setUid(vkeUid);
					seller.setSaasType(3); // 1 寻觅客 2中脉 3 V客 4主播(V客赠送)SAAS签约',0
					seller.setUdate(new Date());
					sellerDao.xmerAbandonSeller(seller);
				}
				
				break;
			}
		}
	}

}
