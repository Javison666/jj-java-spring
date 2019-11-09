package cn.jiweiqing.base.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @Author: yb.zhang
 * @Date: 2018/10/16 16:25
 * @Description: 返回结果的基本类
 */
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private boolean success;

    private String msg;

    private int code;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(boolean success, int code, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultBean<?> bean = (ResultBean<?>) o;
        if (code != bean.code) {
            return false;
        }
        if (msg != null ? !msg.equals(bean.msg) : bean.msg != null) {
            return false;
        }
        return data != null ? data.equals(bean.data) : bean.data == null;
    }

    @Override
    public int hashCode() {
        int result = msg != null ? msg.hashCode() : 0;
        result = 31 * result + code;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
