package com.henriquealmeida.democrud.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-/MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
}
