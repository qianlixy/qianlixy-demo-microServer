package io.github.qianlixy.demo.microservice.fileupload.vo;

public class UploadResult {

	private boolean success;
	private String errorMsg;
	private String fileUrl;

	public UploadResult(boolean success) {
		this.success = success;
	}
	
	public UploadResult(boolean success, String errorMsg) {
		this.success = success;
		this.errorMsg = errorMsg;
	}

	public UploadResult(boolean success, String errorMsg, String fileUrl) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.fileUrl = fileUrl;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
