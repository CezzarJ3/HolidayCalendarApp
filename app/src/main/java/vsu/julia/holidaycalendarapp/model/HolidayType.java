package vsu.julia.holidaycalendarapp.model;

public enum HolidayType {
    NON("Не выбрано"),
    NATIONAL("Национальный"),
    LOCAL("Местный"),
    RELIGIOUS("Религиозный");
    private final String holidayType;

    HolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public static HolidayType getTypeName(final String holidayType) {
        for (HolidayType typeH : HolidayType.values()) {
            if (holidayType.equals(typeH.toString())) {
                return typeH;
            }
        }
        return NON;
    }

    @Override
    public String toString() {
        return holidayType;
    }
}
