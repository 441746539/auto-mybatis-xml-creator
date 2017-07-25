package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class SelectSqlGenerator {
    public static final String QUERY_METHOD = "list";
    public static final String TABLE_ALIAS = "a";

    /**
     * 创建sql
     * @param result
     * @param clazz 生成的slq用的clazz ,会用到上面的@DBColumn获取table名
     */
    public static void createSql(StringBuffer result, Class clazz) {
        //获取clazz对应的table 信息
        TableContext.TableItem tableInfo = TableContext.findByKey(clazz);
        String tableName = tableInfo.tableName;
        String parameterType = tableInfo.parameterType;
        String resultMap = tableInfo.resultMap;
        List<AbstractAttributeItem> attibuteItems = tableInfo.items;

        result.append("<select id=\"").append(QUERY_METHOD).append("\" parameterType=\"")
                .append(parameterType).append("\" resultMap=\"").append(resultMap+"\">\n");

        StringBuffer selectItem = new StringBuffer();
        StringBuffer whereParam = new StringBuffer();

        //遍历属性生成sql
        DefaultAttributeItem defaltItem = null;
        for (AbstractAttributeItem attibuteItem:
                attibuteItems) {
            //普通属性
            if(attibuteItem instanceof DefaultAttributeItem){
                defaltItem = (DefaultAttributeItem)attibuteItem;
                appendSelectItem(selectItem,defaltItem);
                appendWhereParam(whereParam,defaltItem);
            }
        }
        selectItem = selectItem.deleteCharAt(selectItem.length()-1);//删掉最后逗号
        result.append("select ").append(selectItem).append(" from ").append(tableName).append("  ").append(TABLE_ALIAS).append("\n")
                .append(" where 1=1 \n").append(whereParam);

        result.append("</select>\n");
    }

    /**
     * 拼接select至from中间的查询项目
     * @param selectItem
     * @param defaltItem
     */
    private static void appendSelectItem(StringBuffer selectItem ,DefaultAttributeItem defaltItem){
        selectItem.append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(",");
    }
    /**
     * 拼接where条件
     * @param whereParam
     * @param defaltItem
     */
    private static void appendWhereParam(StringBuffer whereParam ,DefaultAttributeItem defaltItem){
        whereParam.append("<if test=\"").append(defaltItem.attrName).append(" != null &amp;&amp; ").append(defaltItem.attrName).append(" != ''\">\n ")
               .append("and ").append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(" = #{").append(defaltItem.attrName).append(",jdbcType=VARCHAR}\n" +
                         "</if>\n");
    }
}
