package my.framework.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrackItemEnum {
	//Id(0, "记录的id"), OptUser(1, "调用用户"), Url(2, "访问URL"), LoginIp(3, "登陆IP"), TargetMethod(4, "操作方法"), CreateDate(5,
	//		"操作时间");
	Id(0, "记录的id"), Info(1, "访问信息");
	private Integer key;
	private String desc;

	TrackItemEnum(Integer key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public static Map<Integer, String> trackItemMap;

	static {
		if (null == trackItemMap || trackItemMap.isEmpty()) {
			trackItemMap = new HashMap<Integer, String>();
			for (TrackItemEnum enum_ : TrackItemEnum.values()) {
				trackItemMap.put(enum_.getKey(), enum_.getDesc());
			}
		}
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
