package cn.jiweiqing.base.bean;


/**
 * @author xin.xue
 * @Description 结果返回集合
 */
public class ResponseUtils {

    /**
     * 接口返回数据格式
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResultBean response(int code, String msg) {
        return new ResultBean(code, msg);
    }

    /**
     * 接口返回数据格式
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultBean response(int code, String msg, Object data) {
        return new ResultBean(code, msg, data);
    }

    /**
     * 业务逻辑返回数据格式
     *
     * @param success
     * @param msg
     * @param data
     * @return
     */
    public static ResultBean response(boolean success, String msg, Object data) {
        return new ResultBean(success, msg, data);
    }

    public static <DataType> ResultBean<DataType> success(DataType data) {
        return new ResultBean<>(true, 200, "操作成功！", data);
    }

    public static <DataType> ResultBean<DataType> error(int code, String message) {
        return new ResultBean<>(false, code, message, null);
    }

}
