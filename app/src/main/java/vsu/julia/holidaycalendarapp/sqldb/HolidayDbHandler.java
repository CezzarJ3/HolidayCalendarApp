package vsu.julia.holidaycalendarapp.sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vsu.julia.holidaycalendarapp.model.Holiday;

public class HolidayDbHandler {
    private final SqliteDbHelper SQLITE_DB_HELPER;

    public HolidayDbHandler(Context context) {
        SQLITE_DB_HELPER = new SqliteDbHelper(context);
    }

    public String getCodeByCountry(String country) {
        SQLiteDatabase database = SQLITE_DB_HELPER.getReadableDatabase();
        String[] projection = {
                HolidayDBContract.ISOCodeEntry.CODE_COLUMN,
        };

        String selection = HolidayDBContract.ISOCodeEntry.COUNTY_COLUMN + "=?";
        String[] selectionArgs = {country};

        Cursor cursor = database.query(
                HolidayDBContract.ISOCodeEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<String> codes = new ArrayList<>();
        while (cursor.moveToNext()) {
            String code = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.ISOCodeEntry.CODE_COLUMN));
            codes.add(code);
        }
        cursor.close();

        if (codes.isEmpty()) {
            Log.i("DB-Handler", "Code empty for " + country);
            return country;
        } else {
            Log.i("DB-Handler", codes.get(0));
            return codes.get(0);
        }
    }

    public List<String> getAllCountries() {
        SQLiteDatabase database = SQLITE_DB_HELPER.getReadableDatabase();
        String[] projection = {
                HolidayDBContract.ISOCodeEntry._ID,
                HolidayDBContract.ISOCodeEntry.CODE_COLUMN,
                HolidayDBContract.ISOCodeEntry.COUNTY_COLUMN,
        };

        Cursor cursor = database.query(
                HolidayDBContract.ISOCodeEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<String> countries = new ArrayList<>();
        while (cursor.moveToNext()) {
            String country = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.ISOCodeEntry.COUNTY_COLUMN));
            Log.i("DB-Handler", country);
            countries.add(country);
        }
        cursor.close();
        return countries;
    }

    public void saveHoliday(Holiday holiday) {
        SQLiteDatabase database = SQLITE_DB_HELPER.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HolidayDBContract.HolidayEntry.NAME_COLUMN, holiday.getName());
        values.put(HolidayDBContract.HolidayEntry.DESCRIPTION_COLUMN, holiday.getDescription());
        values.put(HolidayDBContract.HolidayEntry.COUNTRY_COLUMN, holiday.getCountry().getName());
        values.put(HolidayDBContract.HolidayEntry.DATE_COLUMN, holiday.getDate().getIsoDate());

        long newRowId = database.insert(HolidayDBContract.HolidayEntry.TABLE_NAME, null, values);
    }

    public List<Holiday> getHolidays() {
        SQLiteDatabase database = SQLITE_DB_HELPER.getReadableDatabase();

        String[] projection = {
                HolidayDBContract.HolidayEntry.NAME_COLUMN,
                HolidayDBContract.HolidayEntry.DESCRIPTION_COLUMN,
                HolidayDBContract.HolidayEntry.COUNTRY_COLUMN,
                HolidayDBContract.HolidayEntry.DATE_COLUMN
        };

        Cursor cursor = database.query(
                HolidayDBContract.HolidayEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Holiday> holidays = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.HolidayEntry.NAME_COLUMN));
            String country = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.HolidayEntry.COUNTRY_COLUMN));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.HolidayEntry.DESCRIPTION_COLUMN));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(HolidayDBContract.HolidayEntry.DATE_COLUMN));
            Log.i("DB-Handler", country);
            Holiday holiday = new Holiday();
            holiday.setName(name);
            holiday.setDescription(description);

            Holiday.Country country1 = new Holiday.Country();
            country1.setId("RU");
            country1.setName("Россия");
            holiday.setCountry(country1);


            Holiday.HolidayDate date1 = new Holiday.HolidayDate();
            date1.setIsoDate(date);
            holiday.setDate(date1);
            holidays.add(holiday);
        }

        return holidays;
    }
}
