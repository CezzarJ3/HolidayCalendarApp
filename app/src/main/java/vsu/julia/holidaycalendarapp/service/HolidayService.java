package vsu.julia.holidaycalendarapp.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import vsu.julia.holidaycalendarapp.model.ResponseDto;

public interface HolidayService {
    @GET("holidays")
    Call<ResponseDto> getHolidays(@QueryMap Map<String, String> options);
}
