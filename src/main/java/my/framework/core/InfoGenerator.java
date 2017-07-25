package my.framework.core;

import my.framework.annotation.AssosiationColumn;
import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.AssociationAttributeItem;
import my.framework.entity.CollectionAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class InfoGenerator {

    /**
     * 创建mapper信息
     * @param result
     */
    public static void createResultMapper(StringBuffer result) {

        List<TableContext.TableItem> tableInfos = TableContext.getTableInfos();

        List<AssociationAttributeItem> associationAttributes = new ArrayList<>();
        List<CollectionAttributeItem> collectionAttributes = new ArrayList<>();
        //每个关联表都要构建mapper
        for (TableContext.TableItem tableInfo:
        tableInfos) {
            String resultEntity = tableInfo.entitySimpleName;
            String resultMap = tableInfo.resultMap;
            result.append("<resultMap type=\""+resultEntity+"\" id=\""+resultMap+"\">\n");

            List<AbstractAttributeItem> items = tableInfo.items;

            //遍历属性
            DefaultAttributeItem defaltItem;
            AssociationAttributeItem associationItem;
            CollectionAttributeItem collectionItem;
            for (AbstractAttributeItem item:
                    items ) {
                //普通属性
                if(item instanceof DefaultAttributeItem){
                    defaltItem = (DefaultAttributeItem)item;
                    if(defaltItem.isPrimaryId){
                        result.append("<id column=\""+defaltItem.columnName.toUpperCase()+"\" property=\""+defaltItem.attrName+"\" jdbcType=\"VARCHAR\"/>\n");
                    }else{
                        result.append("<result column=\""+defaltItem.columnName.toUpperCase()+"\" property=\""+defaltItem.attrName+"\" jdbcType=\"VARCHAR\"/>\n");
                    }
                }
                //association属性
                if(item instanceof AssociationAttributeItem){
                    associationItem = (AssociationAttributeItem)item;
                    result.append("<association property=\"").append(associationItem.attrName).append("\"")
                            .append(" column=\"").append(associationItem.foreignKeyColunm).append("\"")
                            .append(" javaType=\"").append(associationItem.itemClass.getSimpleName()).append("\"")
                            .append(" select=\"").append(associationItem.associateSelectMethod()).append("\"/>");
                    associationAttributes.add(associationItem);
                }
                //collection属性
                if(item instanceof CollectionAttributeItem){
                    collectionItem = (CollectionAttributeItem)item;
                    result.append("<collection property=\"").append(collectionItem.attrName).append("\"")
                            .append(" column=\"").append(collectionItem.foreignKeyColunm).append("\"")
                            .append(" javaType=\"ArrayList\"")
                            .append(" ofType=\"").append(collectionItem.itemClass.getSimpleName()).append("\"")
                            .append(" select=\"").append(collectionItem.associateSelectMethod()).append("\"/>");
                    collectionAttributes.add(collectionItem);
                }
            }
            result.append("</resultMap>\n");
        }

        //处理所有外部关联字段生成关联查询sql
        for (AssociationAttributeItem associationAttribute:
             associationAttributes) {
            InsertSqlGenerator.createAssociateSelect(result,associationAttribute);
        }
        for (CollectionAttributeItem collectionAttribute:
                collectionAttributes) {
            InsertSqlGenerator.createCollectSelect(result,collectionAttribute);
        }
    }
}
