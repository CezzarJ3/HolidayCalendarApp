package vsu.julia.holidaycalendarapp.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Holiday {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("country")
    private Country country;
    @SerializedName("date")
    private HolidayDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HolidayDate getDate() {
        return date;
    }

    public void setDate(HolidayDate date) {
        this.date = date;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Holiday(" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country=" + country +
                ", date=" + date +
                ')';
    }

    public static class Country {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Country(" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ')';
        }
    }

    public static class HolidayDate {
        @SerializedName("iso")
        private String isoDate;
        private LocalDate jDate;

        public String getIsoDate() {
            return isoDate;
        }

        public void setIsoDate(String isoDate) {
            this.isoDate = isoDate;
            Log.i("DateDto", isoDate);
//            jDate = LocalDate.parse(isoDate.split("T")[0]);
        }

        public LocalDate getjDate() {
            return LocalDate.parse(isoDate.split("T")[0]);
        }

        @Override
        public String toString() {
            return "HolidayDate(" +
                    "isoDate='" + isoDate + '\'' +
                    ", jDate=" + jDate +
                    ')';
        }
    }

}
