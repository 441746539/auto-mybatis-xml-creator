package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class SelectSqlGenerator {
    public static final String QUERY_METHOD_LIST = "list";
    public static final String QUERY_METHOD_ONE = "queryOne";
    public static final String TABLE_ALIAS = "a";

    /**
     * 创建sql
     *
     * @param result
     * @param clazz  生成的slq用的clazz ,会用到上面的@DBColumn获取table名
     */
    public static void createSql(StringBuffer result, Class clazz) {
        //获取clazz对应的table 信息
        TableContext.TableItem tableInfo = TableContext.findByKey(clazz);
        String tableName = tableInfo.tableName;
        String parameterType = tableInfo.parameterType;
        String resultMap = tableInfo.resultMap;
        List<AbstractAttributeItem> attibuteItems = tableInfo.items;

        doCreateSql(QUERY_METHOD_LIST, result, tableName, parameterType, resultMap, attibuteItems, false);
        doCreateSql(QUERY_METHOD_ONE, result, tableName, parameterType, resultMap, attibuteItems, false);

        if (tableInfo.mainTable && tableInfo.hasSpecialAttr) {//主表且有特殊属性 才需要添加 非级联类型的查询
            doCreateSql(QUERY_METHOD_LIST, result, tableName, parameterType, resultMap, attibuteItems, true);
            doCreateSql(QUERY_METHOD_ONE, result, tableName, parameterType, resultMap, attibuteItems, true);
        }
    }

    private static void doCreateSql(String type, StringBuffer result, String tableName, String parameterType, String resultMap, List<AbstractAttributeItem> attibuteItems, boolean noRelate) {
        if (noRelate)
            resultMap = resultMap + "_NoRelate";
        String seleteId = noRelate ? type + "NoRelate" : type;
        result.append("<select id=\"").append(seleteId).append("\" parameterType=\"")
                .append(parameterType).append("\" resultMap=\"").append(resultMap + "\">\n");

        StringBuffer selectItem = new StringBuffer();
        StringBuffer whereParam = new StringBuffer();

        //遍历属性生成sql
        DefaultAttributeItem defaltItem = null;
        for (AbstractAttributeItem attibuteItem :
                attibuteItems) {
            //普通属性
            if (attibuteItem instanceof DefaultAttributeItem) {
                defaltItem = (DefaultAttributeItem) attibuteItem;
                appendSelectItem(selectItem, defaltItem);
                if (QUERY_METHOD_LIST.equals(type))
                    appendWhereParam(whereParam, defaltItem);
            }
        }

        if (QUERY_METHOD_ONE.equals(type))
            whereParam.append(" and a.id = #{id,jdbcType=VARCHAR}\n");

        selectItem = selectItem.deleteCharAt(selectItem.length() - 1);//删掉最后逗号
        result.append("select ").append(selectItem).append(" from ").append(tableName).append("  ").append(TABLE_ALIAS).append("\n")
                .append(" where 1=1 \n").append(whereParam);

        result.append("</select>\n");
    }

    /**
     * 拼接select至from中间的查询项目
     *
     * @param selectItem
     * @param defaltItem
     */
    private static void appendSelectItem(StringBuffer selectItem, DefaultAttributeItem defaltItem) {
        selectItem.append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(",");
    }

    /**
     * 拼接where条件
     *
     * @param whereParam
     * @param defaltItem
     */
    private static void appendWhereParam(StringBuffer whereParam, DefaultAttributeItem defaltItem) {

        if (defaltItem.isDateType) {
            whereParam.append(" <if test=\"").append(defaltItem.attrName).append("Begin").append(" != null &amp;&amp; ").append(defaltItem.attrName).append("Begin").append("  != ''\">\n")
                    .append("<![CDATA[ and date_format(").append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(", '%Y-%m-%d') >= date_format(#{").append(defaltItem.attrName).append("Begin").append(",jdbcType=VARCHAR}, '%Y-%m-%d') ]]>\n")
                    .append("</if>\n")
                    .append("<if test=\"").append(defaltItem.attrName).append("End").append("  != null &amp;&amp; ").append(defaltItem.attrName).append("End").append("  != ''\">\n")
                    .append("<![CDATA[ and date_format(").append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(", '%Y-%m-%d') <= date_format(#{").append(defaltItem.attrName).append("End").append(",jdbcType=VARCHAR}, '%Y-%m-%d') ]]>\n")
                    .append("</if>");
        } else {
            whereParam.append("<if test=\"").append(defaltItem.attrName).append(" != null &amp;&amp; ").append(defaltItem.attrName).append(" != ''\">\n ")
                    .append("and ").append(TABLE_ALIAS).append(".").append(defaltItem.columnName).append(" = #{").append(defaltItem.attrName).append(",jdbcType=VARCHAR}\n" +
                    "</if>\n");
        }
    }
}
