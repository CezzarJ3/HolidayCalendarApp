package vsu.julia.holidaycalendarapp.sqldb;

import android.provider.BaseColumns;

public final class ISOCodeContract {
    private ISOCodeContract() {
    }

    public static class ISOCodeEntry implements BaseColumns {
        public static final String TABLE_NAME = "iso_code";
        public static final String CODE_COLUMN = "code";
        public static final String COUNTY_COLUMN = "country";
    }
}
