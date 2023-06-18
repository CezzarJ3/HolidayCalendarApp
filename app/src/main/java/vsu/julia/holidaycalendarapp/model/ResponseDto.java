package vsu.julia.holidaycalendarapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ResponseDto {
    @SerializedName("response")
    private HolidayResponse response;

    public HolidayResponse getResponse() {
        return response;
    }

    public void setResponse(HolidayResponse response) {
        this.response = response;
    }

    @NonNull
    @Override
    public String toString() {
        return "Response(" +
                "response=" + response +
                ')';
    }
}
