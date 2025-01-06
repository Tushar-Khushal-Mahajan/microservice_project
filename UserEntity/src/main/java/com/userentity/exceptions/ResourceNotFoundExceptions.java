package com.userentity.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {

	public ResourceNotFoundExceptions() {
		super("Resource Not found");
	}

	public ResourceNotFoundExceptions(String msg) {
		super(msg);
	}
}
