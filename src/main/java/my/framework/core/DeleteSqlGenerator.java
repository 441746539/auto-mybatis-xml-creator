package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.entity.DefaultAttributeItem;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class DeleteSqlGenerator {

    public static final String QUERY_METHOD = "delete";

    /**
     * delete
     * @param result
     * @param clazz
     */
    public static void createSql(StringBuffer result, Class clazz) {
        //获取clazz对应的table 信息
        TableContext.TableItem tableInfo = TableContext.findByKey(clazz);
        String tableName = tableInfo.tableName;
        String parameterType = tableInfo.parameterType;
        List<AbstractAttributeItem> attibuteItems = tableInfo.items;
        result.append("<delete id=\"").append(QUERY_METHOD).append("\" parameterType=\"")
                .append(parameterType).append("\">\n");


        DefaultAttributeItem primaryItem = null;
        for (AbstractAttributeItem attibuteItem:
                attibuteItems) {
            //普通属性
            if(attibuteItem instanceof DefaultAttributeItem){
                primaryItem = (DefaultAttributeItem)attibuteItem;
                if(primaryItem.isPrimaryId)
                    break;
                else
                    primaryItem = null;
            }
        }


        result.append("delete from ").append(tableName).append(" where ");
        if(null == primaryItem)
            result.append(primaryItem.columnName).append(" = ").append("#{").append(primaryItem.attrName).append(",jdbcType=VARCHAR}\n");
        else
            result.append(" id = #{id,jdbcType=VARCHAR} \n");

        result.append("</delete>\n");
    }
}
