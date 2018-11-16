package io.github.qianlixy.demo.server.cachemanager.vo;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private boolean success;
	private int code;
	private String message;
	private Map<String, Object> data;

	public boolean isSuccess() {
		return success;
	}

	public Result setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public int getCode() {
		return code;
	}

	public Result setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public Result addData(String key, Object value) {
		if (null == this.data) {
			this.data = new HashMap<>();
		}
		this.data.put(key, value);
		return this;
	}
	
	public static Result success() {
		return new Result().setSuccess(true).setCode(200);
	}
	
	public static Result fail(int code, String message) {
		return new Result().setSuccess(false).setCode(code).setMessage(message);
				
	}
}
