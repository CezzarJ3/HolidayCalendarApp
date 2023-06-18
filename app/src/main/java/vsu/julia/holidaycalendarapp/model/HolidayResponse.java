package vsu.julia.holidaycalendarapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayResponse {
    @SerializedName("holidays")
    private List<Holiday> holidays;

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "HolidayResponse(" +
                "holidays=" + holidays +
                ')';
    }
}
