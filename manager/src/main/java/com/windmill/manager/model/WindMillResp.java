package com.windmill.manager.model;

import java.time.LocalDateTime;

public class WindMillResp {

	private String status;
	private LocalDateTime ackDateTime;
	private String ackTimeZone;

	public WindMillResp() {
		super();
	}

	public WindMillResp(String status, LocalDateTime ackDateTime, String ackTimeZone) {
		super();
		this.status = status;
		this.ackDateTime = ackDateTime;
		this.ackTimeZone = ackTimeZone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAckDateTime() {
		return ackDateTime;
	}

	public void setAckDateTime(LocalDateTime ackDateTime) {
		this.ackDateTime = ackDateTime;
	}

	public String getAckTimeZone() {
		return ackTimeZone;
	}

	public void setAckTimeZone(String ackTimeZone) {
		this.ackTimeZone = ackTimeZone;
	}
}
