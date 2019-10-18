package com.windmill.manager.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindMillDTO {

	@Size(max = 16, min = 16, message = "SerialNumber must be 16 characters")
	@NotBlank(message = "SerialNumber must not be empty")
	private String serialNumber;

	private String name;

	private String address;

	@NotNull(message = "Latitude must not be null")
	@Digits(integer = 3, fraction = 6, message = "Latitude must be less than or equal to 3 integeral digits, 6 fraction digits")
	private Double latitude;

	@NotNull(message = "Longitude must not be null")
	@Digits(integer = 3, fraction = 6, message = "Longitude must be less than or equal to 3 integeral digits, 6 fraction digits")
	private Double longitude;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
