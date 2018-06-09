package cn.lkangle.entity;

import java.util.List;

/**
 * @Author: Soft
 * @Date: 2018/6/9 21:21
 * @Desc: 接收数据
 */
public class ResponseData {

    private String code;
    private String message;
    // 目标数据
    private Data   data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String  platformame;
        private String  black_type;
        private String  is_black;
        private Integer totalcount;

        private List<Object> data;

        public String getPlatformame() {
            return platformame;
        }

        public void setPlatformame(String platformame) {
            this.platformame = platformame;
        }

        public String getBlack_type() {
            return black_type;
        }

        public void setBlack_type(String black_type) {
            this.black_type = black_type;
        }

        public String getIs_black() {
            return is_black;
        }

        public void setIs_black(String is_black) {
            this.is_black = is_black;
        }

        public Integer getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(Integer totalcount) {
            this.totalcount = totalcount;
        }

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }
    }
}
