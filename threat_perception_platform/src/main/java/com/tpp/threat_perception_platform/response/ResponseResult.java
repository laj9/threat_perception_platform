package com.tpp.threat_perception_platform.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 响应结果类
 *
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code = 0;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;

    /**
     * 条数 用于layui的分页
     */
    private Long count;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Long count, T data) {
        this.count = count;
        this.data = data;
    }
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
