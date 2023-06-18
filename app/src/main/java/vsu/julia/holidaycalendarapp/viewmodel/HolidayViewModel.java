package vsu.julia.holidaycalendarapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vsu.julia.holidaycalendarapp.model.Holiday;
import vsu.julia.holidaycalendarapp.model.HolidayType;
import vsu.julia.holidaycalendarapp.model.ResponseDto;
import vsu.julia.holidaycalendarapp.service.HolidayService;
import vsu.julia.holidaycalendarapp.sqldb.HolidayDbHandler;
import vsu.julia.holidaycalendarapp.util.Utils;

public class HolidayViewModel extends ViewModel {
    private final static String BASE_URL = "https://calendarific.com/api/v2/";
    private final static String API_KEY = "50342769aff8652ae0838deb0c3b82286b012553";

    private HolidayDbHandler holidayDbHandler;
    private final MutableLiveData<List<Holiday>> holidayLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Holiday>> favouritesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isDataValid = new MutableLiveData<>();
    private final List<Holiday> favouriteHolidays = new ArrayList<>();
    private final HolidayService holidayService;

    public HolidayViewModel() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        holidayService = retrofit.create(HolidayService.class);
    }

    public void setIsoCodesDbHandler(HolidayDbHandler holidayDbHandler) {
        this.holidayDbHandler = holidayDbHandler;
    }

    public MutableLiveData<List<Holiday>> getHolidayLiveData() {
        return holidayLiveData;
    }

    public MutableLiveData<List<Holiday>> getFavouritesLiveData() {
        getFavouriteHolidays();
        return favouritesLiveData;
    }

    public void getHolidays(String country, String date, String type) {
        isDataValid.setValue(true);
        Log.i("HolidayViewModel", "Entered getHolidays method");
        Map<String, String> options = new HashMap<>();
        options.put("api_key", API_KEY);
        options.put("country", holidayDbHandler.getCodeByCountry(country));

        if (Utils.checkYear(date)) {
            options.put("year", date);
        } else if (Utils.checkDate(date)) {
            String[] dateArray = date.split("/");
            options.put("day", dateArray[0]);
            options.put("month", dateArray[1]);
            options.put("year", dateArray[2]);
        } else {
            isDataValid.setValue(false);
            return;
        }

        if (HolidayType.getTypeName(type) != HolidayType.NON) {
            options.put("type", HolidayType.getTypeName(type).name().toLowerCase());
        }

        Call<ResponseDto> call = holidayService.getHolidays(options);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> responseDto) {
                Log.i("HolidayViewModel", responseDto.toString());
                if (responseDto.body() != null) {
                    Log.i("HolidayViewModel", responseDto.body().toString());
                    if (responseDto.isSuccessful() && responseDto.body() != null && !responseDto.body().getResponse().getHolidays().isEmpty()) {
                        List<Holiday> holiday = responseDto.body().getResponse().getHolidays();
                        holidayLiveData.setValue(holiday);
                    } else {
                        holidayLiveData.setValue(new ArrayList<>());
                    }
                } else {
                    holidayLiveData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
                holidayLiveData.setValue(null);
            }
        });
    }

    public void addHolidayToFavourite(String name, String country, String description, String date) {
        Holiday holiday = new Holiday();
        holiday.setName(name);

        Holiday.Country country1 = new Holiday.Country();
        country1.setName(country);
        country1.setId(holidayDbHandler.getCodeByCountry(country));
        holiday.setCountry(country1);

        holiday.setDescription(description);

        Holiday.HolidayDate  date1 = new Holiday.HolidayDate();
        date1.setIsoDate(date);
        holiday.setDate(date1);

        Log.i("HolidayViewModel", "Add to favourite");
        Log.i("HolidayViewModel", holiday.toString());

        holidayDbHandler.saveHoliday(holiday);
    }

    public void addHolidayToFavourite(Holiday holiday) {
        Log.i("HolidayViewModel", "Add to favourite");
        Log.i("HolidayViewModel", holiday.toString());

        holidayDbHandler.saveHoliday(holiday);
    }

    public void getFavouriteHolidays() {
        favouritesLiveData.setValue(holidayDbHandler.getHolidays());
    }

    public MutableLiveData<Boolean> getIsDataValid() {
        return isDataValid;
    }
}
