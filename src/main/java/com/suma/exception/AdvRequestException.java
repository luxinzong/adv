package com.suma.exception;

import lombok.Data;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@Data
public class AdvRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String sessionId;

    private Exception originException;

    public AdvRequestException() {
        super();
    }

    public AdvRequestException(String message, String sessionId) {
        super(message);
        this.sessionId = sessionId;
    }

    public AdvRequestException(String message, Exception originException, String sessionId) {
        super(message);
        this.originException = originException;
        this.sessionId = sessionId;
    }

}
