package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.AssociationAttributeItem;
import my.framework.entity.CollectionAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class InsertSqlGenerator {

    public static final String QUERY_METHOD = "insert";

    public static void createSql(StringBuffer result, Class clazz) {
        //获取clazz对应的table 信息
        TableContext.TableItem tableInfo = TableContext.findByKey(clazz);
        String tableName = tableInfo.tableName;
        String parameterType = tableInfo.parameterType;
        List<AbstractAttributeItem> attibuteItems = tableInfo.items;

        result.append("<insert id=\"").append(QUERY_METHOD).append("\" parameterType=\"")
                .append(parameterType).append("\">\n");

        StringBuffer insertItem = new StringBuffer();
        StringBuffer valueItem = new StringBuffer();

        //遍历属性生成sql
        DefaultAttributeItem defaltItem = null;
        for (AbstractAttributeItem attibuteItem:
                attibuteItems) {
            //普通属性
            if(attibuteItem instanceof DefaultAttributeItem){
                defaltItem = (DefaultAttributeItem)attibuteItem;
                appendInsertItem(insertItem,defaltItem);
                appendValueItem(valueItem,defaltItem);
            }
        }

        result.append("insert into ").append(tableName).append("(\n")
                .append("<trim prefix=\"\" suffixOverrides=\",\"> \n")
                .append(insertItem)
                .append("</trim> \n")
                .append(")\n")
                .append("values (\n")
                .append("<trim prefix=\"\" suffixOverrides=\",\"> \n")
                .append(valueItem)
                .append("</trim> \n")
                .append(")\n");
        result.append("</insert>\n");
    }

    /**
     * 拼接insert的数据库列
     * @param insertItem
     * @param defaltItem
     */
    private static void appendInsertItem(StringBuffer insertItem ,DefaultAttributeItem defaltItem){
        insertItem.append("<if test=\"").append(defaltItem.attrName).append(" != null &amp;&amp; ").append(defaltItem.attrName).append(" != ''\">\n")
                .append("`").append(defaltItem.columnName).append("`").append(",\n")
                .append("</if>\n");
    }
    /**
     * 拼接where条件
     * @param valueItem
     * @param defaltItem
     */
    private static void appendValueItem(StringBuffer valueItem ,DefaultAttributeItem defaltItem){
        valueItem.append("<if test=\"").append(defaltItem.attrName).append(" != null &amp;&amp; ").append(defaltItem.attrName).append(" != ''\">\n")
                .append("#{").append(defaltItem.attrName).append(",jdbcType=VARCHAR},\n" +
                "</if>\n");
    }

    /**
     * 生成Association关联查询的sql
     * @param sb
     * @param item
     */
    public static void createAssociateSelect(StringBuffer sb, AssociationAttributeItem item){
        Class itemClass = item.itemClass;
        TableContext.TableItem tableItem = TableContext.findByKey(itemClass);
        String resultMap = tableItem.resultMap;
        String tableName = tableItem.tableName;
        sb.append("<select id=\"").append(item.associateSelectMethod()).append("\" parameterType=\"string\" resultMap=\"").append(resultMap).append("\">\n" +
                "        select * from ").append(tableName).append(" where ").append(item.relColumnInforeignTable).append(" = #{").append(item.foreignKeyColunm).append(",jdbcType=VARCHAR}\n" +
                "    </select>\n");
    }

    /**
     * 生成collection关联查询的sql
     * @param sb
     * @param item
     */
    public static void createCollectSelect(StringBuffer sb, CollectionAttributeItem item) {
        Class itemClass = item.itemClass;
        TableContext.TableItem tableItem = TableContext.findByKey(itemClass);
        String resultMap = tableItem.resultMap;
        String tableName = tableItem.tableName;
        sb.append("<select id=\"").append(item.associateSelectMethod()).append("\" parameterType=\"string\" resultMap=\"").append(resultMap).append("\">\n" +
                "        select * from ").append(tableName).append(" where ").append(item.relColumnInforeignTable).append(" = #{").append(item.foreignKeyColunm).append(",jdbcType=VARCHAR}\n" +
                "    </select>\n");
    }
}
