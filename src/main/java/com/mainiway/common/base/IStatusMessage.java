package com.mainiway.common.base;

public interface IStatusMessage {

    String getCode();

    String getMessage();

    enum ResultStatus implements IStatusMessage {
        SUCCESS("1000", "执行成功"), ERROR("1001", "执行失败");

        private String code;
        private String message;

        ResultStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    enum LoginStatus implements IStatusMessage {
        SUCCESS("1000", "登录成功！"), USER_OR_PASSWORD_ERROR("1004", "用户名或密码错误！");

        private String code;
        private String message;

        LoginStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    enum LogoutStatus implements IStatusMessage {
        SUCCESS("1000", "退出成功！"), FAIL("1001", "token失效或不存在");

        private String code;
        private String message;

        LogoutStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    enum AuthStatus implements IStatusMessage {
        NULL("501", "Token为空！"), FAILED("502", "Token 失效或不存在！");

        private String code;
        private String message;

        AuthStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
