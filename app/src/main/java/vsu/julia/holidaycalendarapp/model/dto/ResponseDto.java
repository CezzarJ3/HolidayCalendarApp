package vsu.julia.holidaycalendarapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {
    @JsonProperty("holidays")
    private List<HolidayDto> holidayDtos;

    public List<HolidayDto> getHolidayDtos() {
        return holidayDtos;
    }

    public void setHolidayDtos(List<HolidayDto> holidayDtos) {
        this.holidayDtos = holidayDtos;
    }
}
