package com.mertyalcin.creditcenter.core.utils.results;

//Custom response with success
public class SuccessResult extends Result {
	public SuccessResult() {
		super(true);
	}

	public SuccessResult(String message) {
		super(true, message);
	}
}