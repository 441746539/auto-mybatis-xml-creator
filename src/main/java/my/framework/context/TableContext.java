package my.framework.context;

import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.AssociationAttributeItem;
import my.framework.entity.CollectionAttributeItem;
import my.framework.entity.DefaultAttributeItem;
import my.framework.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class TableContext {

    private static List<Class> initedClass = new ArrayList<Class>();
    private static List<TableItem> tableInfos = new ArrayList<TableItem>();
    public static Map<Class,TableItem> tableInfosMap = new HashMap<Class, TableItem>();

    /**
     * 获取context list
     * @return
     */
    public static List<TableItem> getTableInfos(){
        return tableInfos;
    }

    /**
     * 根据实体类class 查询table info context
     * @param clazz
     * @return
     */
    public static TableItem findByKey(Class clazz){
        if(null == tableInfosMap)
            return null;
        else
            return tableInfosMap.get(clazz);
    }

    /**
     * 初始化上下文
     * @param clazz
     * @param items
     */
    public static void initContext(Class clazz, List<AbstractAttributeItem> items) {
        //已经初始化过的跳过
        if(initedClass.contains(clazz))
            return;
        //加入context
        TableItem tableItem = CoreUtils.extractTableInfo(clazz);
        tableItem.items = items;
        tableItem.entitySimpleName = clazz.getSimpleName();
        tableInfos.add(tableItem);
        //放到map
        tableInfosMap.put(clazz,tableItem);
        //放到已处理
        initedClass.add(clazz);

        //遍历属性中级联实体类
        CollectionAttributeItem collectionAttribute = null;
        AssociationAttributeItem associationAttribute = null;
        for (AbstractAttributeItem item:
                items ) {
            //collection
            if(item instanceof CollectionAttributeItem){
                collectionAttribute = (CollectionAttributeItem)item;
                Class relClazz = collectionAttribute.itemClass;
                initContext(relClazz,CoreUtils.extractTargetInfo(relClazz));
                continue;
            }
            if(item instanceof AssociationAttributeItem){
                associationAttribute = (AssociationAttributeItem)item;
                Class relClazz = associationAttribute.itemClass;
                initContext(relClazz,CoreUtils.extractTargetInfo(relClazz));
            }
        }
    }

    public class TableItem{
        public Class entityClazz;
        public String entitySimpleName;
        public String tableName;
        public String parameterType;
        public String resultMap;
        public List<AbstractAttributeItem> items;
    }
}
