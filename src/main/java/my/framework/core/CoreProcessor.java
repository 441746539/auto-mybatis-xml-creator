package my.framework.core;

import my.framework.context.TableContext;
import my.framework.entity.AbstractAttributeItem;
import my.framework.util.CoreUtils;
import my.framework.util.StringBufferUtil;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class CoreProcessor {

    public static StringBuffer doProcess(String daoInterfaceFullname,Class clazz){
        StringBuffer result = new StringBuffer();
        createHeader(result,daoInterfaceFullname);
        //1.解析各个字段属性
        List<AbstractAttributeItem> items = CoreUtils.extractTargetInfo(clazz);
        //2.初始化上下文
        TableContext.initContext(clazz,items);
        //3.生成mapper信息和crud sql
        InfoGenerator.createResultMapper(result);
        StringBufferUtil.changeLine(result);
        SelectSqlGenerator.createSql(result,clazz);
        StringBufferUtil.changeLine(result);
        InsertSqlGenerator.createSql(result,clazz);
        StringBufferUtil.changeLine(result);
        UpdateSqlGenerator.createSql(result,clazz);
        StringBufferUtil.changeLine(result);
        DeleteSqlGenerator.createSql(result,clazz);

        //3.尾部
        appendFooter(result);
        return result;
    }

    //创建头信息
    private static void createHeader(StringBuffer sb, String daoInterfaceFullname){
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper\n" +
                "        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\""+daoInterfaceFullname+"\">\n");
    }
    //补充结尾
    public static void appendFooter(StringBuffer sb){
        sb.append("</mapper>");
    }

}
