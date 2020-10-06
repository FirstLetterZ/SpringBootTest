package com.zpf.demo.global;

public enum ErrorCode {
    PARAMS_NO_ALLOWED(10001, "参数不合规"),
    PARAMS_OUT_INDEX(10002, "参数超出约定上限"),
    RESULT_EMPTY(10003, "查询结果为空，请检查查询参数"),

    SAVE_FAIL(50001, "数据保存失败"),
    REMOVE_FAIL(50002, "数据删除失败"),
    UPDATE_FAIL(50003, "数据更新失败"),
    QUERY_FAIL(50004, "数据查询失败");

    private int code;
    private String desc;

    private ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
