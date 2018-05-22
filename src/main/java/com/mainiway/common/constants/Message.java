package com.mainiway.common.constants;

public interface Message {

//    public static final String account_or_password_cannot_empty = "账号或密码不能为空";
//    public static final String account_or_password_is_error = "账号或密码错误";
//    public static final String account_disabled = "账号已被禁用";
//    public static final String account_not_exist = "账号不存在";
//    public static final String validate_code_cannot_empty = "验证码不能为空";
//    public static final String validate_code_error = "验证码错误";
//    public static final String unknown_error = "未知错误";
//    public static final String permission_denied = "权限不足";
    
    
    /**
     * 特殊码4位
     * 1000  表示操作成功(登录、授权、业务操作成功)
     * 1001  表示登录失败,页面需要跳转到登录页面
     * 9000  表示未知错误,当不知道错误类型时可选
     * 普通码6位(异常提示信息)
     * 示例：100101
     * 第一位和第二位定位异常范围,例如：是系统异常还是业务异常,还是以其他
     * 第三位和第四位定位异常种类,例如：是认证异常还是授权异常,还是以其他
     * 第五位和第六位定位异常子类,例如：是认证异常中的账号密码错误导致
     * 
     * 如需定义异常信息,请按照以上规则依次向下定义
     * ---------------------------------------------------------------
     * 10开头为登录异常码
     * 20开头为数据效验处理异常
     * 30开头系统业务处理异常
     * 90开头为数据库访问异常码
     * 9001为Redis数据库
     * 9002为MySQL数据库
     * 9003为Http请求异常
     * 9004为第三方系统返回结果异常
     */
    public enum Result {
        /**
         * 成功
         */
        _1000(1000, "成功"),
        /**
         * 数据效验处理异常
         */
        _2000(2000, "数据效验处理异常"),
        /**
         * 必填参数不能为空
         */
        _3000(3000, "必填参数不能为空"),
        /**
         * 未知错误
         */
        _9000(9000, "未知错误"),
        /**
         * 用户未登录
         */
        _1001(1001, "用户未登录"),
        /**
         * 账号或密码不能为空
         */
        
        _100101(100101, "账号或密码不能为空"),
        /**
         * 账号或密码错误
         */
        _100102(100102, "账号或密码错误"),
        /**
         * 账号已被禁用
         */
        _100103(100103, "账号已被禁用"),
        /**
         * 账号不存在
         */
        _100104(100104, "账号不存在"),
        /**
         * 账号不唯一
         */
        _100105(100105, "账号不唯一"),
        /**
         * 手机号已存在
         */
        _100106(100106,"手机号已存在"),
        /**
         * 手机号格式不正确
         */
        _100107(100107,"手机号格式不正确"),
        /**
         * 邮箱格式不正确
         */
        _100108(100108,"邮箱格式不正确"),
        /**
         * 短信服务异常
         */
        _100109(100109,"短信服务异常"),
        /**
         * 邮箱服务异常
         */
        _100110(100110,"邮箱服务异常"),
        /**
         * 原密码输入不正确
         */
        _100111(100111,"原密码输入不正确"),
        /**
         * 验证码不能为空
         */
        _100201(100201, "验证码不能为空"),
        /**
         * 验证码错误
         */
        _100202(100202, "验证码错误"),
        /**
         * 验证码已失效，请重新获取
         */
        _100203(100203, "验证码已失效，请重新获取"),
        /**
         * 权限不足
         */
        _100301(100301, "权限不足"),
        /**
         * 店铺id不能为空
         */
        _200001(200001, "店铺id不能为空"),
        /**
         * 文件上传失败
         */
        _300001(300001, "文件上传失败"),
        /**
         * 资源权限不存在
         */
        _401001(401001, "资源权限不存在"),
        /**
         * 存在子节点无法删除
         */
        _401002(401002, "存在子节点无法删除"),
        /**
         * Redis数据库访问失败
         */
        _900101(900101, "Redis数据库访问失败"),
        
        /**
         * HttpClient访问失败
         */
    	_900301(900301, "HttpClient访问失败"),
    	/**
        * 机加工估价结果异常
        */
       _900401(900401, "所选材质当日行情尚未更新，请更换材质");
        
        private Integer code;
        private String msg;

        private Result(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer code() {
            return this.code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String msg() {
            return this.msg;
        }
        
        public void setMsg(String msg) {
            this.msg = msg;
        }

    }
    
}
