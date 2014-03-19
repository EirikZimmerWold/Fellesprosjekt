package Alarm;

import java.util.ArrayList;

public class alarmModel {
	
	// Fields
		private int hour;
		private int minute;
		private int day;
		private String month;
		private int year;
		
		private int endHour;
		private int endminute;
		private int endDay;
		private String endMonth;
		private int endYear;
		
		private String date;
		private int interval;

	// Should have used ComboBoxModel
		private String[] numberOfDay = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		private String[] stringOfMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		private String[] numberOfYear = {"2014", "2015", "2016", "2017", "2018"};
		
	// Constructor
		alarmModel(String adate){
			this.date = adate;
			this.splitDate(adate);

		}
		
	// This method will give values from a Date-String to fields.
		private void splitDate(String date){
			
			ArrayList<String> ar = new ArrayList<String>();
			for (String value: date.split("-", 4)){
				ar.add(value);
			}
			
			String tempTime = ar.get(3);
			for (String value: tempTime.split(":",2)){
				ar.add(value);
			}
			
			this.year = Integer.parseInt((String) ar.get(0));
			this.month = ar.get(1);
			this.day = Integer.parseInt((String) ar.get(2));
			this.hour = Integer.parseInt((String) ar.get(4));
			this.minute = Integer.parseInt((String) ar.get(5));
			
			this.endDay = this.day;
			this.endMonth = this.month;
			this.endYear = this.year;
			this.endHour = this.hour;
			this.endminute = this.minute;
		}
		
	// Methods
		
		
	// Setter and getter
		public void setInterval(int newInterval){
			this.interval = newInterval;
		}
		
		public String[] getNumberOfDay() {
			return numberOfDay;
		}

		public String[] getStringOfMonths() {
			return stringOfMonths;
		}
		
		public String[] getNumberOfYear() {
			return this.numberOfYear;
		}
		
		public String getDate(){
			return date;
		}
		
		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public int getMinute() {
			return minute;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getEndHour() {
			return endHour;
		}

		public void setEndHour(int endHour) {
			this.endHour = endHour;
		}

		public int getEndminute() {
			return endminute;
		}

		public void setEndminute(int endminute) {
			this.endminute = endminute;
		}

		public int getEndDay() {
			return endDay;
		}

		public void setEndDay(int endDay) {
			this.endDay = endDay;
		}

		public String getEndMonth() {
			return endMonth;
		}

		public void setEndMonth(String endMonth) {
			this.endMonth = endMonth;
		}

		public int getEndYear() {
			return endYear;
		}

		public void setEndYear(int endYear) {
			this.endYear = endYear;
		}
		
}
