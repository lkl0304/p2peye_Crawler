package cn.lkangle.utils;

import java.util.*;

/**
 * @Author: Soft
 * @Date: 2018/6/9 21:38
 * @Desc: 特殊常量
 */
public interface Const {
    String cookie = "A4gK_987c_saltkey=TngbBvH7; " +
            "A4gK_987c_lastvisit=1528544374; " +
            "TYID=enANiFsbyoZq6LdwCaDSAg==; " +
            "bdp_data_is_new_user=true; " +
            "__firstReferrerKey__=%7B%22%24first_referrer%22%3A%22%22%2C%22%24first_referrer_host%22%3A%22%22%7D; " +
            "__jsluid=ca1b179e08b2e13283a6e91e0a29c95c; " +
            "PHPSESSID=5pmo5j4suc4mdl9gbhdo585842; " +
            "Hm_lvt_556481319fcc744485a7d4122cb86ca7=1528547977,1528548630; " +
            "Hm_lpvt_556481319fcc744485a7d4122cb86ca7=1528549641; " +
            "bdp_data2017jssdkcross=%7B%22distinct_id%22%3A%22163e48f25d0791-0a2aeac4547099-737356c-1327104-163e48f25d154b%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22user_id%22%3A%22aUbMGFvp%22%2C%22%24is_first_session%22%3A0%7D%7D; " +
            "A4gK_987c_lastact=1528549640%09ajax.php%09ad;" +
            "__bdpa_session_key__2017__=%7B%22session_time%22%3A1528549646683%2C%22session_id%22%3A%22163e48f25d57b2-02101c94411aff-737356c-1327104-163e48f25d6720%22%2C%22session_hasBeenExpired%22%3A0%2C%22lastSend_sessonId%22%3A%22163e48f25d57b2-02101c94411aff-737356c-1327104-163e48f25d6720%22%7D";

    String UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";

    Map<String, String> headers = new HashMap<String, String>(){{
        put("Cookie", cookie);
        //put("Referer", "https://lu.p2peye.com/shuju/")
        put("User-Agent", UserAgent);
        put("X-Requested-With", "XMLHttpRequest");  // 重点，根据请求方式判断返回什么样的值
    }};

    // 类型列表和对应需要的数据key  结果只用了列名、、、
    Map<String, String[]> typesRefKey = new LinkedHashMap<String, String[]>(){{
        put("new_borrow_paid", new String[]{"newmoney", "collected"});

        put("inflows", new String[]{"moneyinout"});

        put("rate", new String[]{"zh_rate"}); // "hy_rate"

        put("remainder", new String[]{"amount"});

        put("paid_invest_people", new String[]{"count_user"});

        put("borrowing_to_invest", new String[]{"borrow_count", "invest_count"});

        put("invest_vs", new String[]{"newuser_count"}); // "olduser_count"

        put("invest_total_vs", new String[]{"newuser_money"});
    }};
}
