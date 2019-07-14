package time.series.model;

import java.sql.Timestamp;
import java.util.Map;

import time.series.api.Resolution;

public class TimeSeries {
	private String meterId;
	private String customerId;
	private Resolution resolution;
	private Timestamp from;
	private Timestamp to;
	private int week;
	private Map<String, Double> values;
	private Double total;

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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}
}
