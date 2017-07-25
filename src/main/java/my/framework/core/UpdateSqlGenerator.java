package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class UpdateSqlGenerator {

    public static final String QUERY_METHOD = "update";

    public static void createSql(StringBuffer result, Class clazz) {
        //获取clazz对应的table 信息
        TableContext.TableItem tableInfo = TableContext.findByKey(clazz);
        String tableName = tableInfo.tableName;
        String parameterType = tableInfo.parameterType;
        List<AbstractAttributeItem> attibuteItems = tableInfo.items;

        result.append("<update id=\"").append(QUERY_METHOD).append("\" parameterType=\"")
                .append(parameterType).append("\">\n");

        StringBuffer updateItem = new StringBuffer();

        //遍历属性生成sql
        DefaultAttributeItem primaryItem = null;
        DefaultAttributeItem defaltItem;
        for (AbstractAttributeItem attibuteItem:
                attibuteItems) {
            //普通属性
            if(attibuteItem instanceof DefaultAttributeItem){
                defaltItem = (DefaultAttributeItem)attibuteItem;
                appendUpdateItem(updateItem,defaltItem);

                if(defaltItem.isPrimaryId) {
                    primaryItem = defaltItem;
                }
            }
        }
        result.append("update ").append(tableName).append("\n")
                .append("<set>\n")
                .append(updateItem)
                .append("</set>\n");
        if(null == primaryItem)
            result.append("where  id = #{id,jdbcType=VARCHAR}\n");
        else
            result.append("where ").append(primaryItem.columnName).append(" = ").append("#{").append(primaryItem.attrName).append(",jdbcType=VARCHAR}\n");
        result.append("</update>\n");
    }

    /**
     * 拼接update的数据库列
     * @param insertItem
     * @param defaltItem
     */
    private static void appendUpdateItem(StringBuffer insertItem ,DefaultAttributeItem defaltItem){
        insertItem.append("<if test=\"").append(defaltItem.attrName).append(" != null &amp;&amp; ").append(defaltItem.attrName).append(" != ''\">\n ")
                .append(defaltItem.columnName).append(" = #{").append(defaltItem.attrName).append(",jdbcType=VARCHAR},\n" +
                "</if>\n");
    }
}
