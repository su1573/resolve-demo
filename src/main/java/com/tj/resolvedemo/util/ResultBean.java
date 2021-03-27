package com.tj.resolvedemo.util;

/**
 * @program: driving-agent
 * @Date: 2021/1/11 0011 21:21
 * @Author: Mr.SU
 * @Description:
 */
    public class ResultBean<T> {
        private int code;
        private String message;
        private T data;
        private String token;

        private ResultBean() {
        }

        public static ResultBean error(int code, String message) {
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(code);
            resultBean.setMessage(message);
            return resultBean;
        }
        public static<T> ResultBean error(int code, String message,T data) {
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(code);
            resultBean.setMessage(message);
            resultBean.setData(data);
            return resultBean;
        }

        public static ResultBean success(String message) {
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(200);
            resultBean.setMessage(message);
            return resultBean;
        }

        public static<T>  ResultBean success(String message,T data) {
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(200);
            resultBean.setMessage(message);
            resultBean.setData(data);
            return resultBean;
        }
        public static ResultBean success(String message,Object data,String token) {
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(200);
            resultBean.setMessage(message);
            resultBean.setData(data);
            resultBean.setToken(token);
            return resultBean;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
}
