package com.book.common.cache;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @ClassName: SystemWholeCache
 * @Description: 应用全局缓存类
 */
public class ApplicationCache {

	protected Log logger = LogFactory.getLog(this.getClass().getName());

	private ConcurrentMap<String, Object> cacheMap = null;
	
	private ConcurrentMap<String, List<IdValue>> cacheIdVlaue = null;

	private ExecutorService service = Executors.newScheduledThreadPool(2);

	private OverdueThread overdue = null;

	private static class Singleton {
		private static final ApplicationCache singleton = new ApplicationCache();
	}

	public static final ApplicationCache getInstance() {
		return Singleton.singleton;
	}

	private ApplicationCache() {
		cacheMap = new ConcurrentHashMap<String, Object>();
		cacheIdVlaue = new ConcurrentHashMap<String, List<IdValue>>();
		overdue = new OverdueThread();
		service.execute(overdue);
	}

	/**
	 * @Title: put
	 * @Description: 添加数据到缓存中 ,永不过期
	 * @param key 数据主键
	 * @param value 需要缓存的数据
	 * @return void 返回类型
	 * @throws
	 */
	public void put(String key, Object value) {
		put(key, value, 0);
	}
	
	
	public void putIdValue(String key, List<IdValue> value) {
		cacheIdVlaue.put(key, value);
	}

	public List<IdValue> getIdValue(String key) {
		return cacheIdVlaue.get(key);
	}

	/**
	 * @Title: put
	 * @Description: 添加数据到缓存中 ,并设置过期时间
	 * @param key 数据主键
	 * @param value 需要缓存的数据
	 * @param overTime 过期时间，单位（秒），如果过期时间小于等于0，则永不过期
	 * @return void 返回类型
	 * @throws
	 */
	public void put(String key, Object value, long overTime) {
		//直接把数据全部放入
		long startTime = System.currentTimeMillis();
		if(overTime>0){
			overTime = overTime * 1000 + startTime;
		}else{
			overTime = 0;
		}

		OverdueInfoBean bean = new OverdueInfoBean(key, startTime, overTime,value);
		cacheMap.put(key, bean);
	}

	/**
	 * @Title: get
	 * @Description: 根据主键获取数据
	 * @param key
	 * @return Object 返回类型
	 * @throws
	 */
	public Object get(String key) {
		OverdueInfoBean bean=(OverdueInfoBean) cacheMap.get(key);
		if(bean!=null){
			//没有设置缓存
			if(bean.getOverdueTime()==0){
				return bean.getValue();
			}else{//设置了缓存，判断时间，如果已经过期就返回空
				if (System.currentTimeMillis() > bean.getOverdueTime()) {
					logger.info("缓存已过期，主键为：" + bean.getKey());
					cacheMap.remove(key);
					return null;
				}else{
					return bean.getValue();	
				}
			}
		}else{
			return null;
		}
	}
	
	/**
	 * @Title: getStr
	 * @Description: 根据主键获取数据
	 * @param key
	 * @return String 返回类型
	 * @throws
	 */
	public String getStr(String key) {
		Object obj = get(key);
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	/**
	 * @Title: getInt
	 * @Description: 根据主键获取数据
	 * @param key
	 * @return int 如果key不存在，则返回0；
	 * @throws
	 */
	public int getInt(String key) {
		Object obj = get(key);
		if (obj != null) {
			return Integer.valueOf(obj.toString());
		}
		return 0;
	}

	/**
	 * @Title: getBigDecimal
	 * @Description: 根据主键获取数据
	 * @param key
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal getBigDecimal(String key) {
		Object obj = get(key);
		if (obj != null) {
			return new BigDecimal(obj.toString());
		}
		return null;
	}

	/**
	 * 放置缓存数据
	 * @author zoufangfang
	 *
	 */
	@SuppressWarnings("unused")
	private class OverdueInfoBean {

		private String key;

		private long startTime;

		private long overdueTime;

		private Object value;

		public OverdueInfoBean(String key, long startTime, long overdueTime,Object value) {
			this.value=value;
			this.key = key;
			this.startTime = startTime;
			this.overdueTime = overdueTime;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public long getStartTime() {
			return startTime;
		}

		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}

		public long getOverdueTime() {
			return overdueTime;
		}

		public void setOverdueTime(long overdueTime) {
			this.overdueTime = overdueTime;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public boolean isAlive(){
			if(this.getOverdueTime()==0){
				//如果overduetime为0，则为未过期，返回false
				return true;
			}else{
				//设置了缓存，判断时间，如果已经过期就返回空
				if (System.currentTimeMillis() > this.getOverdueTime()) {
					//已过期
					return false;
				}else{
					//未过期
					return true;
				}
			}
		}
	}

	private static int overduecount = 0;
	private static int overduecount_printcount = 1200;//1200*3000=3600000毫秒=1小时，即1小时打印一次日志，用于监控是否线程卡死
	private static long overdue_sleeptime = 3000;

	private class OverdueThread extends Thread {

		private boolean flag = true;
		@Override
		public void run() {
			try {
				while (flag) {
					overduecount++;
					if(overduecount%overduecount_printcount==0){
						logger.info("清理缓存过期线程执行，第["+overduecount+"]次");
					}
					overdueRemove();
					Thread.sleep(overdue_sleeptime);
				}
			} catch (InterruptedException e) {
				logger.error("清理缓存过期线程异常：",e);
			}
		}

		@SuppressWarnings("rawtypes")
		private boolean overdueRemove() {
			try {
				HashSet<String> overdueKeys = new HashSet<String>();
				for(Map.Entry<String,Object> e : cacheMap.entrySet()){
					String key = e.getKey();
					OverdueInfoBean bean = (OverdueInfoBean)e.getValue();
					if(bean.isAlive()){
						continue;
					}else{
						//将过期的key加入overdue
						overdueKeys.add(key);
					}
				}
				for(String key : overdueKeys){
					cacheMap.remove(key);
				}
			} catch (Exception e) {
				logger.error("清理缓存过期异常：",e);
			}
			return true;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationCache.getInstance().put("a","c",3);
		Thread.sleep(5000);
	}
}
