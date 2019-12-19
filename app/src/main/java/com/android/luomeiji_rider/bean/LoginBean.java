package com.android.luomeiji_rider.bean;

public class LoginBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"rider_id":"31","name":"r","idCard":"1","userName":"昵称","head_portrait":"骑手头像","corpsId":"0","certification":"1"}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rider_id : 31
         * name : r
         * idCard : 1
         * userName : 昵称
         * head_portrait : 骑手头像
         * corpsId : 0
         * certification : 1
         */

        private String rider_id;
        private String name;
        private String idCard;
        private String userName;
        private String head_portrait;
        private String corpsId;
        private String certification;

        public String getRider_id() {
            return rider_id;
        }

        public void setRider_id(String rider_id) {
            this.rider_id = rider_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getCorpsId() {
            return corpsId;
        }

        public void setCorpsId(String corpsId) {
            this.corpsId = corpsId;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }
    }
}
