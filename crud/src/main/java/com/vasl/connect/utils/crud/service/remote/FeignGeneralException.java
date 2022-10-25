package com.vasl.connect.utils.crud.service.remote;

public class FeignGeneralException extends RuntimeException {
    private Integer status;
    private String body;

    public FeignGeneralException(Integer status) {
        this.status = status;
    }
    public FeignGeneralException(Integer status , String message) {
        this.body = message;
        this.status = status;
    }

    public FeignGeneralException(Throwable cause) {
        super(cause);
    }

    public Integer getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public static class ServiceUnavailable extends RuntimeException {

        private String message;

        public ServiceUnavailable(String message) {
            super(message);
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}