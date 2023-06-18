package vsu.julia.holidaycalendarapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vsu.julia.holidaycalendarapp.model.Holiday;

public class HolidaysAdapter extends RecyclerView.Adapter<HolidaysAdapter.HolidayHolder> {

    private final List<Holiday> holidayDtoList;


    public HolidaysAdapter(List<Holiday> holidayDtoList) {
        this.holidayDtoList = holidayDtoList;
    }

    @NonNull
    @Override
    public HolidayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holidayElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_element, parent, false);
        return new HolidayHolder(holidayElement);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayHolder holder, int position) {
        holder.holidayName.setText(holidayDtoList.get(position).getName());
        holder.holidayDescription.setText(holidayDtoList.get(position).getDescription());
        holder.holidayDate.setText(holidayDtoList.get(position).getDate().getIsoDate());
//
//        Bundle bundle = new Bundle();
//
//        bundle.putString("name", holidayDtoList.get(position).getName());
//        bundle.putString("description", holidayDtoList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return holidayDtoList.size();
    }

    static class HolidayHolder extends RecyclerView.ViewHolder {
        protected TextView holidayName = itemView.findViewById(R.id.holidayNamePlaceholer);
        protected TextView holidayDescription = itemView.findViewById(R.id.holidayDescriptionPlaceholder);
        protected TextView holidayDate = itemView.findViewById(R.id.holidayDate);

        public HolidayHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
