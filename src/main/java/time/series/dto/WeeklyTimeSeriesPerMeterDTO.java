package time.series.dto;

public class WeeklyTimeSeriesPerMeterDTO {
	private int week;
	private double total;
	private String meterId;

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getMeterId() {
		return meterId;
	}
	
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
}
