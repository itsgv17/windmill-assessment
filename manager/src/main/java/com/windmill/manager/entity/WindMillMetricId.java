package com.windmill.manager.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WindMillMetricId implements Serializable{

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "date_time")
	private LocalDateTime dateTime;

	public WindMillMetricId() {
		super();
	}

	public WindMillMetricId(String serialNumber, LocalDateTime dateTime) {
		super();
		this.serialNumber = serialNumber;
		this.dateTime = dateTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
