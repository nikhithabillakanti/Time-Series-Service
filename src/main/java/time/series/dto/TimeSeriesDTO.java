package time.series.dto;

import java.sql.Timestamp;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import time.series.api.Resolution;

public class TimeSeriesDTO {

	@NotEmpty
	@JsonProperty("meter_id")
	private String meterId;

	@NotEmpty
	@JsonProperty("customer_id")
	private String customerId;

	@NotNull
	private Resolution resolution;

	@NotNull
	private Timestamp from;

	@NotNull
	private Timestamp to;

	@NotNull
	private Map<String, Double> values;

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public Timestamp getFrom() {
		return new Timestamp(from.getTime());
	}

	public void setFrom(Timestamp from) {
		this.from = from != null ? new Timestamp(from.getTime()) : null;
	}

	public Timestamp getTo() {
		return new Timestamp(to.getTime());
	}

	public void setTo(Timestamp to) {
		this.to = to != null ? new Timestamp(to.getTime()) : null;
	}

	public Map<String, Double> getValues() {
		return values;
	}

	public void setValues(Map<String, Double> values) {
		this.values = values;
	}
}
