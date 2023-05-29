package vsu.julia.holidaycalendarapp.model;

public enum HolidayType {
    NATIONAL("Национальный"),
    LOCAL("Местный"),
    RELIGIOUS("Религиозный");
    private final String holidayType;

    HolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    @Override
    public String toString() {
        return holidayType;
    }
}
