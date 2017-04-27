package com.ymu.infrastructure.web.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.ymu.infrastructure.web.json.jsonview.Views;

/**
 * ajax请求，json返回通用对象。
 */
public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    boolean isSuccess;
    @JsonView(Views.Public.class)
    int code;
    @JsonView(Views.Public.class)
    String description;
    @JsonView(Views.Public.class)
    Object data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResponseBody{" +
                "isSuccess=" + isSuccess +
                ", description='" + description + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
