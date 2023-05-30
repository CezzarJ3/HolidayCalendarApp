package vsu.julia.holidaycalendarapp.service;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vsu.julia.holidaycalendarapp.model.dto.HolidayDto;
import vsu.julia.holidaycalendarapp.model.dto.JsonDto;

//@Component
public class HolidayApiClient {
    private final RestTemplate restTemplate;

    private final static String URL = "https://calendarific.com/api/v2/holidays?";
    private final static String API_KEY = "50342769aff8652ae0838deb0c3b82286b012553";

    public HolidayApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
    }

    public List<HolidayDto> getHolidaysByDate(String country, int year, int month, int day) {
        String url = URL + "api_key={apiKei}&country={country}&year={year}&month={month}&day={day}";

        Map<String, Object> params = new HashMap<>();
        params.put("apiKey", API_KEY);
        params.put("country", country);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);

        JsonDto response = restTemplate.getForObject(url, JsonDto.class, params);

        if (response != null) {
            return response.getResponseDto().getHolidayDtos();
        } else {
            return Collections.emptyList();
        }
    }

    public HolidayDto getHoliday(String country, int year) {
        String url = "https://calendarific.com/api/v2/holidays?api_key={apiKey}&country={country}&year={year}";

        Map<String, Object> params = new HashMap<>();
        params.put("apiKey", API_KEY);
        params.put("country", country);
        params.put("year", year);

        JsonDto response = restTemplate.getForObject(url, JsonDto.class, params);

        if (response != null) {
            return response.getResponseDto().getHolidayDtos().get(0);
        } else {
            return null;
        }
    }

//    class RetrieveHolidayTask extends AsyncTask<String, Void, HolidayDto> {
//        private Exception exception;
//
//        @Override
//        protected HolidayDto doInBackground(String... strings) {
//            try {
//
//            } catch (Exception e) {
//                this.exception = e;
//
//                return null;
//            } finally {
//
//            }
//        }
//    }
}

