package com.api.constant;

public enum Model {

	Nexus_2_blue(1), Gallexy(2);

	int code;

	private Model(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
