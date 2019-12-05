package com.android.luomeiji_rider.bean;

public class SignUpBean {

    /**
     * code : 1
     * msg : 请求成功
     * data : {"driverUserId":"1"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String userId;
        private String nickName;
        private String avatar;
        private String phoneNum;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "userId='" + userId + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", phoneNum='" + phoneNum + '\'' +
                    '}';
        }
    }

}
