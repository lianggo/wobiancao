package com.wobiancao.protocol.ajax;

public class AjaxFailResponse extends AjaxResponse {

	private String message;
	
	public AjaxFailResponse(String message) {
		super();
		this.status = 1;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
