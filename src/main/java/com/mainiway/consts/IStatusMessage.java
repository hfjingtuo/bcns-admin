package com.mainiway.consts;

public abstract interface IStatusMessage {
	public abstract String getCode();

	public abstract String getMessage();

	public static enum SystemStatus implements IStatusMessage {
		IDLE("1000", "空闲状态"),
		IMPORT_DATA("1001", "导入状态，请稍候"),
		EXPORT_DATA("1002", "导出状态，请稍候");
		
		private String code;
		private String message;
	
		private SystemStatus(String code, String message) {
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

	public static enum ResultStatus implements IStatusMessage {
		SUCCESS("1000", "执行成功"), ERROR("1001", "执行失败");

		private String code;
		private String message;

		private ResultStatus(String code, String message) {
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

	public static enum ImportStates implements IStatusMessage {
		SUCCESS("1000", "导入成功"), ERROR("1001", "导入失败");

		private String code;
		private String message;

		private ImportStates(String code, String message) {
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

	public static enum LoginStatus implements IStatusMessage {
		SUCCESS("1000", "登录成功！"), ERROR("1001", "登录失败！"), INVALID_USER_INFO("1002", "登录信息有误！"),
		VALIDATIONCODE_ERROR("1003", "VALIDATIONCODE_ERROR"),
		USER_OR_PASSWORD_ERROR("1004", "用户名或密码错误！"), USER_NOLOGIN("1006", "用户没有登录！");

		private String code;
		private String message;

		private LoginStatus(String code, String message) {
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

	public static enum AuthStatus implements IStatusMessage {
		SUCCESS("1000", "验证成功！"), FAILED("1001", "Token 有效期到期或Token错误！");

		private String code;
		private String message;

		private AuthStatus(String code, String message) {
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

	public static enum DBOperateStatus implements IStatusMessage {
		SUCCESS("1000", "SUCCESS"), ERROR("1001", "ERROR");

		private String code;
		private String message;

		private DBOperateStatus(String code, String message) {
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
