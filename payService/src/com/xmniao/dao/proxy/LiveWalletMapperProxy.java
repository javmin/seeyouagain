package com.xmniao.dao.proxy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.common.RedisService;
import com.xmniao.dao.LiveWalletMapper;
import com.xmniao.entity.LiveWallet;
import com.xmniao.exception.LiveWalletZbalanceLockException;
@Service
@Primary
public class LiveWalletMapperProxy implements LiveWalletMapper{
	
	
	public static final int LOCK_ZBALANCE_STATE = -1;
	@Autowired
	private LiveWalletMapper liveWalletMapper;

	/**
	 * 直播缓存开关
	 */
	public static boolean LIVE_REDIS_KEY;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	@Value("#{configProperties['liveRedis']}")  
	private Boolean liveRedis;
	

	public Boolean getLiveRedis() {
		return liveRedis;
	}

	@Bean(name="LIVE_REDIS_KEY")
	private Boolean createLiveRedis(){
		LIVE_REDIS_KEY=liveRedis;
		return LIVE_REDIS_KEY;
	}


	public void deleteUidRedisKey(String uid){
		if(LIVE_REDIS_KEY){
			if(StringUtils.isNotBlank(uid)){
				redisTemplate.delete(RedisService.LIVE_WALLET_REDIS_KEY+uid);
			}
		}
	}
	
	
	public boolean checkIsUpdateZbalanceByWalletId(Integer walletId,BigDecimal zbalance){
		Map<String,Object> zbalanceLock=liveWalletMapper.getzbalanceLockById(walletId);
		if(zbalanceLock!=null&&((BigDecimal)zbalanceLock.get("zbalance")).compareTo(zbalance)>0){
			return true;
		}
		return false;
	}
	
	public boolean checkIsUpdateZbalanceByUid(Integer uid,BigDecimal zbalance){
		Map<String,Object> zbalanceLock=liveWalletMapper.getzbalanceLockByUid(uid);
		if(zbalanceLock!=null&&((BigDecimal)zbalanceLock.get("zbalance")).compareTo(zbalance)>0){
			return true;
		}
		return false;
	}
	
	
	@Override
	public Map<String, Object> getLiveWallet(Map<String, String> map) {
		return liveWalletMapper.getLiveWallet(map);
	}

	@Override
	public int addLiveWallet(Map<String, Object> map) {
		return liveWalletMapper.addLiveWallet(map);
	}

	@Override
	public int updateWalletState(Map<String, String> map) {
		return liveWalletMapper.updateWalletState(map);
	}

	@Override
	public int updateWallet(Map<String, String> map) {
		boolean choose;
		if(StringUtils.isNotBlank(map.get("id"))){
			choose=checkIsUpdateZbalanceByWalletId(Integer.valueOf(map.get("id")), new BigDecimal(map.get("zbalance")));
		}else{
			choose=checkIsUpdateZbalanceByUid(Integer.valueOf(map.get("uid")), new BigDecimal(map.get("zbalance")));
		}
		if(choose){
			throw new LiveWalletZbalanceLockException();
		}
		return liveWalletMapper.updateWallet(map);
	}

	@Override
	public int insertWalletRecord(Map<String, Object> map) {
		return liveWalletMapper.insertWalletRecord(map);
	}

	@Override
	public List<Map<String, Object>> getWalletRecord(Map<String, Object> map) {
		return liveWalletMapper.getWalletRecord(map);
	}

	@Override
	public Map<String, Object> countDayEgg(Map<String, String> map) {
		return liveWalletMapper.countDayEgg(map);
	}

	@Override
	public Map<String, Object> countTotalEgg(Map<String, String> map) {
		return liveWalletMapper.countTotalEgg(map);
	}

	@Override
	public List<Map<String, Object>> platformCoin(Map<String, Object> map) {
		return liveWalletMapper.platformCoin(map);
	}

	@Override
	public List<Map<String, Object>> liveList(Map<String, Object> map) {
		return liveWalletMapper.liveList(map);
	}

	@Override
	public List<Map<String, Object>> personList(Map<String, Object> map) {
		return liveWalletMapper.personList(map);
	}

	@Override
	public Long countRemarks(String remarks, String walletId, String rtype) {
		return liveWalletMapper.countRemarks(remarks, walletId, rtype);
	}

	@Override
	public List<Map<String, Object>> getBirdCoinList(Map<String, Object> paraMap) {
		return liveWalletMapper.getBirdCoinList(paraMap);
	}

	@Override
	public Map<String, Object> getWeekBeans(Map<String, String> paraMap) {
		return liveWalletMapper.getWeekBeans(paraMap);
	}

	@Override
	public Map<String, Object> getMonthBeans(Map<String, String> paraMap) {
		return liveWalletMapper.getMonthBeans(paraMap);
	}

	@Override
	public List<Map<String, Object>> countBeansByDay(Map<String, String> paraMap) {
		return liveWalletMapper.countBeansByDay(paraMap);
	}

	@Override
	public Long sumBeans(Map<String, String> paraMap) {
		return liveWalletMapper.sumBeans(paraMap);
	}

	@Override
	public List<Map<String, Object>> BeansDetailByDay(Map<String, String> paraMap) {
		return liveWalletMapper.BeansDetailByDay(paraMap);
	}

	@Override
	public Map<String, Object> countIncomeBirdegg(Map<String, String> paraMap) {
		return liveWalletMapper.countIncomeBirdegg(paraMap);
	}

	@Override
	public List<Map<String, Object>> BirdeggIncomeList(Map<String, String> paraMap) {
		return liveWalletMapper.BirdeggIncomeList(paraMap);
	}

	@Override
	public List<Map<String, Object>> birdeggIncomeTop(Map<String, Object> objectMap) {
		return liveWalletMapper.birdeggIncomeTop(objectMap);
	}

	@Override
	public List<Map<String, Object>> topThree(Integer anchorId) {
		return liveWalletMapper.topThree(anchorId);
	}

	@Override
	public List<Map<String, Object>> birdCoinInDetail(Map<String, String> paraMap) {
		return liveWalletMapper.birdCoinInDetail(paraMap);
	}

	@Override
	public List<Map<String, Object>> birdCoinOutDetail(Map<String, String> paraMap) {
		return liveWalletMapper.birdCoinOutDetail(paraMap);
	}

	@Override
	public Map<String, Object> getcoinCount(Map<String, String> paraMap) {
		return liveWalletMapper.getcoinCount(paraMap);
	}

	@Override
	public BigDecimal countUnitTimeEgg(Map<String, String> parMap) {
		return liveWalletMapper.countUnitTimeEgg(parMap);
	}

	@Override
	public Long countRegister(String id) {
		return liveWalletMapper.countRegister(id);
	}

	@Override
	public List<Map<String, Object>> getUserRecords(Map<String, String> paraMap) {
		return liveWalletMapper.getUserRecords(paraMap);
	}

	@Override
	public Long countUserRecord(Map<String, String> paraMap) {
		return liveWalletMapper.countUserRecord(paraMap);
	}

	@Override
	public Map<String, String> getWalletMsgByUid(String uid) {
		return liveWalletMapper.getWalletMsgByUid(uid);
	}

	@Override
	public Long countCoinRecord(String orderNo, Integer coinType, Integer type) {
		return liveWalletMapper.countCoinRecord(orderNo, coinType, type);
	}

	@Override
	public Map<String, Object> getbeanCount(String walletId) {
		return liveWalletMapper.getbeanCount(walletId);
	}

	@Override
	public int cleanWallet(Integer uid, String sign) {
		deleteUidRedisKey(uid.toString());
		return liveWalletMapper.cleanWallet(uid, sign);
	}

	@Override
	public int initExchangeCoinAmount(Map<String, String> map) {
		deleteUidRedisKey(map.get("uid"));
		return liveWalletMapper.initExchangeCoinAmount(map);
	}

	@Override
	public BigDecimal sumBirdB(Map<String, String> walletMap) {
		return liveWalletMapper.sumBirdB(walletMap);
	}

	@Override
	public Long countUserWalletRecordList3(Map<String, String> walletMap) {
		return liveWalletMapper.countUserWalletRecordList3(walletMap);
	}

	@Override
	public List<Map<String, Object>> getUserWalletRecordList3(Map<String, String> walletMap) {
		return liveWalletMapper.getUserWalletRecordList3(walletMap);
	}

	@Override
	public LiveWallet selectLiveWalletBySelected(LiveWallet record) {
		return liveWalletMapper.selectLiveWalletBySelected(record);
	}

	@Override
	public int updateByPrimaryKeySelective(LiveWallet record) {
		deleteUidRedisKey(record.getUid().toString());
		if(checkIsUpdateZbalanceByWalletId(record.getId(),record.getZbalance())){
			throw new LiveWalletZbalanceLockException();
		}
		return liveWalletMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateZbalance(LiveWallet liveWallet, LiveWallet oldWallet, BigDecimal deductZbalance) {
		if(oldWallet.getUid()!=null){
			deleteUidRedisKey(oldWallet.getUid().toString());
		}else if(oldWallet.getId()!=null){
			LiveWallet w=liveWalletMapper.getWalletById(oldWallet.getId());
			deleteUidRedisKey(w.getUid().toString());
		}
		if(checkIsUpdateZbalanceByWalletId(oldWallet.getId(), liveWallet.getZbalance())){
			throw new LiveWalletZbalanceLockException();
		}
		return liveWalletMapper.updateZbalance(liveWallet, oldWallet, deductZbalance);
	}

	@Override
	public int updateLimitBalane(LiveWallet liveWallet, BigDecimal limitBalacne) {
		if(liveWallet.getUid()!=null){
			deleteUidRedisKey(liveWallet.getUid().toString());
		}else if(liveWallet.getId()!=null){
			LiveWallet w=liveWalletMapper.getWalletById(liveWallet.getId());
			deleteUidRedisKey(w.getUid().toString());
		}
		return liveWalletMapper.updateLimitBalane(liveWallet, limitBalacne);
	}

	@Override
	public LiveWallet getWalletById(Integer id) {
		return liveWalletMapper.getWalletById(id);
	}


	@Override
	public LiveWallet getWalletByUid(Integer uid) {
		return liveWalletMapper.getWalletByUid(uid);
	}


	@Override
	public Map<String, Object> getzbalanceLockById(Integer walletId) {
		return liveWalletMapper.getzbalanceLockById(walletId);
	}


	@Override
	public Map<String, Object> getzbalanceLockByUid(Integer uid) {
		return liveWalletMapper.getzbalanceLockById(uid);
	}


	@Override
	public List<LiveWallet> getLiveWalletByUids(List<String> uids) {
		return liveWalletMapper.getLiveWalletByUids(uids);
	}


}
