package com.sherlockkk.ares.common.constants;

import java.time.format.DateTimeFormatter;

/**
 * 公共常量池
 *
 * @author SongJian
 */
public interface CommonConstant {
    int RESULT_OK_200 = 200;
    int RESULT_NO_AUTH = 401;
    int RESULT_LOGIN_AGAIN = 420;
    int RESULT_INTERNAL_SERVER_ERROR_500 = 500;
    int RESULT_PARAMETER_ERROR = 508;
    int RESULT_PARAMETER_ALREADY_EXIST = 509;

    String SYMBOL_COLON = ":";
    String SYMBOL_STAR = "*";
    String SYMBOL_COMMA = ",";

    String ACCESS_NOT_ALLOWED = "AccessNotAllowed：";
    String ACCESS_UNAUTHORIZED = "Unauthorized:";

    String TIP_UNAUTHORIZED = "访问权限不足，请联系管理员";

    String HTTP_METHOD_POST = "POST";
    String HTTP_METHOD_DELETE = "DELETE";
    String HTTP_METHOD_PUT = "PUT";
    String HTTP_METHOD_GET = "GET";
    String HTTP_METHOD_OPTION = "OPTION";

    String TAG_BATCH = "/batch";
    String TAG_LIST = "/list";

    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter PATTERN_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
