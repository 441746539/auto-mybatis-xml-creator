package my.framework.util;

import my.framework.annotation.*;
import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.AssociationAttributeItem;
import my.framework.entity.CollectionAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class CoreUtils {

    /**
     * 解析table 相关全局信息
     * @param clazz
     * @return
     */
    public static TableContext.TableItem extractTableInfo(Class clazz){
        TableContext.TableItem tableItem = new TableContext().new TableItem();

        TableInfo dBColumn = (TableInfo)clazz.getAnnotation(TableInfo.class);
        if(dBColumn == null)
            throw new RuntimeException("类【"+clazz.getName()+"】缺失@TableInfo注解!");
        String tableName = dBColumn.tableName();
        String parameterType = dBColumn.parameterType();
        String resultMap = dBColumn.resultMap();

        if(StringUtil.isNull(tableName)||StringUtil.isNull(parameterType)||StringUtil.isNull(resultMap))
            throw new RuntimeException("类【"+clazz.getName()+"】@TableInfo上的参数填写不能有空值!");
        tableItem.entityClazz = clazz;
        tableItem.tableName =tableName;
        tableItem.parameterType =parameterType;
        tableItem.resultMap =resultMap;

        return tableItem;
    }

    /**
     * 提取属性信息
     * @param clazz
     * @return
     */
    public static List<AbstractAttributeItem> extractTargetInfo(Class clazz) {
        List<AbstractAttributeItem> list = new ArrayList<AbstractAttributeItem>();

        Field[] fields = clazz.getDeclaredFields();
        if(fields.length == 0)
            return list;

        //各种注解参数
        Skip skipAnno = null;
        DBColumn dbColumnAttr = null;
        CollectionColumn collectionColumn=null;
        AssosiationColumn assosiationColumn=null;
        PrimaryId primaryId = null;
        DateType dateType = null;

        String dbColumnName="";
        for (Field filed:
                fields) {

            filed.setAccessible(true);

            //@Skip表示跳过该属性
            skipAnno = filed.getAnnotation(Skip.class);
            if (null!=skipAnno)
                continue;

            //映射数据库字段info
            //普通属性
            primaryId = filed.getAnnotation(PrimaryId.class);
            dbColumnAttr = filed.getAnnotation(DBColumn.class);
            dateType = filed.getAnnotation(DateType.class);
            if(null != dbColumnAttr|| primaryId != null){
                if(null != dbColumnAttr)
                    dbColumnName=dbColumnAttr.value();//数据库里的列名
                if(StringUtil.isNull(dbColumnName)) {
                    dbColumnName = filed.getName();
                }

                DefaultAttributeItem attributeItem = new DefaultAttributeItem();
                primaryId = filed.getAnnotation(PrimaryId.class);
                if(null != primaryId)
                    attributeItem.isPrimaryId = true;
                attributeItem.attrName = filed.getName();
                attributeItem.columnName = dbColumnName;
                list.add(attributeItem);

                //解析dataType
                if(null != dateType){
                    attributeItem.isDateType = true;
                    if(StringUtil.isNotNull(dateType.pattern())){
                        attributeItem.datePattern = dateType.pattern();
                    }else{
                        throw new RuntimeException("类【"+clazz.getName()+"】@DateType上的参数填写不能有空值!");
                    }
                }

                continue;
            }

            //级联属性
            collectionColumn = filed.getAnnotation(CollectionColumn.class);
            if(null != collectionColumn){
                CollectionAttributeItem attributeItem = new CollectionAttributeItem();
                attributeItem.attrName = filed.getName();
                attributeItem.itemClass = collectionColumn.itemClass();
                attributeItem.foreignKeyColunm = collectionColumn.foreignKeyColunm().toLowerCase();//mysql sql里列名小写
                attributeItem.relColumnInforeignTable = collectionColumn.relColumnInforeignTable().toLowerCase();//mysql sql里列名小写
                list.add(attributeItem);
                continue;
            }
            assosiationColumn = filed.getAnnotation(AssosiationColumn.class);
            if(null != assosiationColumn){
                AssociationAttributeItem attributeItem = new AssociationAttributeItem();
                attributeItem.attrName = filed.getName();
                attributeItem.itemClass = filed.getType();
                attributeItem.foreignKeyColunm = assosiationColumn.foreignKeyColunm().toLowerCase();//mysql sql里列名小写
                attributeItem.relColumnInforeignTable = assosiationColumn.relColumnInforeignTable().toLowerCase();//mysql sql里列名小写
                list.add(attributeItem);
                continue;
            }
        }
        return list;
    }
}
