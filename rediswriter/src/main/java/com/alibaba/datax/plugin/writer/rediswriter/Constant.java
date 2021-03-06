package com.alibaba.datax.plugin.writer.rediswriter;

public final class Constant {

    public static final String STANDALONE = "singleton";
    public static final String CLUSTER = "cluster";

    // 支持的redis的四种数据类型
    public static final String WRITE_TYPE_STRING = "string";
    public static final String WRITE_TYPE_LIST = "list";
    public static final String WRITE_TYPE_HASH = "hash";
    public static final String WRITE_TYPE_GEO = "geo";

    // 两种redis的操作类型，delete和insert
    public static final String WRITE_MODE_DELETE = "delete";
    public static final String WRITE_MODE_INSERT = "insert";

    // 导入redis list的模式，有lpush，rpush，overwrite,默认overwrite
    public static final String LIST_PUSH_TYPE_OVERWRITE = "overwrite";
    public static final String LIST_PUSH_TYPE_LPUSH = "lpush";
    public static final String LIST_PUSH_TYPE_RPUSH = "rpush";

    // 坐标的参数名称
    public static final String GEO_NAME_LONGITUDE = "longitude";
    public static final String GEO_NAME_LATITUDE = "latitude";
    public static final String GEO_NAME_MEMBER = "member";

}
