package vsu.julia.holidaycalendarapp.sqldb;

import android.provider.BaseColumns;

public final class HolidayDBContract {
    private HolidayDBContract() {
    }

    public static class ISOCodeEntry implements BaseColumns {
        public static final String TABLE_NAME = "iso_code";
        public static final String CODE_COLUMN = "code";
        public static final String COUNTY_COLUMN = "country";
    }

    public static class HolidayEntry implements BaseColumns {
        public static final String TABLE_NAME = "holiday";
        public static final String NAME_COLUMN = "name";
        public static final String DESCRIPTION_COLUMN = "description";
        public static final String COUNTRY_COLUMN = "country";
        public static final String DATE_COLUMN = "date";
    }
}
