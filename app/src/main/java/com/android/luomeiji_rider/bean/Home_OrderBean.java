package com.android.luomeiji_rider.bean;

import java.util.List;

public class Home_OrderBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"order_id":"0","order_type":"1","rider_id":"0","order_price":"56.00","service_time":"12:20","business_address":"取货地址-米知味","business_id":"0","customer_address":"送货地址","detailed_address":"取货详细地址","customer_detailed_address":"送货详细地址"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 0
         * order_type : 1
         * rider_id : 0
         * order_price : 56.00
         * service_time : 12:20
         * business_address : 取货地址-米知味
         * business_id : 0
         * customer_address : 送货地址
         * detailed_address : 取货详细地址
         * customer_detailed_address : 送货详细地址
         */

        private String order_id;
        private String order_type;
        private String rider_id;
        private String order_price;
        private String service_time;
        private String business_address;
        private String business_id;
        private String customer_address;
        private String detailed_address;
        private String customer_detailed_address;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getRider_id() {
            return rider_id;
        }

        public void setRider_id(String rider_id) {
            this.rider_id = rider_id;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public String getBusiness_address() {
            return business_address;
        }

        public void setBusiness_address(String business_address) {
            this.business_address = business_address;
        }

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public String getCustomer_address() {
            return customer_address;
        }

        public void setCustomer_address(String customer_address) {
            this.customer_address = customer_address;
        }

        public String getDetailed_address() {
            return detailed_address;
        }

        public void setDetailed_address(String detailed_address) {
            this.detailed_address = detailed_address;
        }

        public String getCustomer_detailed_address() {
            return customer_detailed_address;
        }

        public void setCustomer_detailed_address(String customer_detailed_address) {
            this.customer_detailed_address = customer_detailed_address;
        }
    }
}
