package vsu.julia.holidaycalendarapp.sqldb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ISOCodesDbHandler {
    private final SqliteDbHelper SQLITE_DB_HELPER;

    public ISOCodesDbHandler(Context context) {
        SQLITE_DB_HELPER = new SqliteDbHelper(context);
    }

    public String getCodeByCountry(String country) {
        SQLiteDatabase database = SQLITE_DB_HELPER.getReadableDatabase();
        String[] projection = {
                ISOCodeContract.ISOCodeEntry.CODE_COLUMN,
        };

        String selection = ISOCodeContract.ISOCodeEntry.COUNTY_COLUMN + "=?";
        String[] selectionArgs = {country};

        Cursor cursor = database.query(
                ISOCodeContract.ISOCodeEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<String> codes = new ArrayList<>();
        while (cursor.moveToNext()) {
            String code = cursor.getString(cursor.getColumnIndexOrThrow(ISOCodeContract.ISOCodeEntry.CODE_COLUMN));
            codes.add(code);
        }
        cursor.close();

        Log.i("DB-Handler", codes.get(0));
        return codes.get(0);
    }

    public List<String> getAllCountries() {
        SQLiteDatabase database = SQLITE_DB_HELPER.getReadableDatabase();
        String[] projection = {
                ISOCodeContract.ISOCodeEntry._ID,
                ISOCodeContract.ISOCodeEntry.CODE_COLUMN,
                ISOCodeContract.ISOCodeEntry.COUNTY_COLUMN,
        };

        Cursor cursor = database.query(
                ISOCodeContract.ISOCodeEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<String> countries = new ArrayList<>();
        while (cursor.moveToNext()) {
            String country = cursor.getString(cursor.getColumnIndexOrThrow(ISOCodeContract.ISOCodeEntry.COUNTY_COLUMN));
            Log.i("DB-Handler", country);
            countries.add(country);
        }
        cursor.close();
        return countries;
    }
}
