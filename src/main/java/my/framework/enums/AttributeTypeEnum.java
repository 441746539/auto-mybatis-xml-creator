package my.framework.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiang.su on 2017/7/24.
 */
public enum AttributeTypeEnum {

    DEFAULT(0, "DEFAULT"),
    COLLECTION(1, "COLLECTION"),
    ASSOSIASION(2, "ASSOSIASION");

    private Integer key;
    private String desc;

    AttributeTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static Map<Integer, String> attributeTypeMap;

    static {
        if (null == attributeTypeMap || attributeTypeMap.isEmpty()) {
            attributeTypeMap = new HashMap<Integer, String>();
            for (AttributeTypeEnum enum_ : AttributeTypeEnum.values()) {
                attributeTypeMap.put(enum_.getKey(), enum_.getDesc());
            }
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
