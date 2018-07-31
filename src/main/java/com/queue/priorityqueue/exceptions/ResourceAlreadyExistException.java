package com.queue.priorityqueue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistException(String exception) {
		super(exception);
	}
}
