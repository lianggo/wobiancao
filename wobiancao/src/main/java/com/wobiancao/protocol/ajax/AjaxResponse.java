package com.wobiancao.protocol.ajax;

import com.wobiancao.utils.JsonUtils;

public class AjaxResponse {

	protected int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JsonUtils.objectToJson(this);
	}

}
