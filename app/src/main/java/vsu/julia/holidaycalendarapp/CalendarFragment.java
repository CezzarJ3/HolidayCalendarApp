package vsu.julia.holidaycalendarapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vsu.julia.holidaycalendarapp.databinding.FragmentCalendarBinding;
import vsu.julia.holidaycalendarapp.databinding.FragmentCreateHolidayBinding;
import vsu.julia.holidaycalendarapp.model.Holiday;
import vsu.julia.holidaycalendarapp.model.HolidayType;
import vsu.julia.holidaycalendarapp.sqldb.HolidayDbHandler;
import vsu.julia.holidaycalendarapp.viewmodel.HolidayViewModel;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;
    private HolidayDbHandler holidayDbHandler;
    private HolidayViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        holidayDbHandler = new HolidayDbHandler(getContext());
        viewModel = new ViewModelProvider(requireActivity()).get(HolidayViewModel.class);
        viewModel.setIsoCodesDbHandler(holidayDbHandler);

        fillCountrySpinner();

        binding.holidaysCalendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = String.format(Locale.ROOT, "%02d/%02d/%04d", dayOfMonth, month + 1, year);
            Log.i("Calendar", date);
            viewModel.getHolidays(binding.countryAutoCompleteInputText.getText().toString(), date, HolidayType.NON.toString());
        });

        viewModel.getIsDataValid().observe(getViewLifecycleOwner(), b -> {
            Log.i("Home", b.toString());
            if (!b) {
                Toast.makeText(getContext(), "Неверный формат даты", Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getHolidayLiveData().observe(getViewLifecycleOwner(), holidays -> {
            if (holidays != null) {
                if (holidays.size() > 0) {
                    binding.holidayNamePlaceholer2.setText(holidays.get(0).getName());
                    binding.holidayDescriptionPlaceholder.setText(holidays.get(0).getDescription());
                } else {
                    holidays.size();
                    Toast.makeText(getContext(), "Праздники не найдены", Toast.LENGTH_LONG).show();
                }
//                else {
//                    Navigation.findNavController(requireView()).navigate(R.id.SecondFragment);
//                }
            } else {
//                Toast.makeText(getContext(), "Праздники не найдены", Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void fillCountrySpinner() {
        List<String> countries = holidayDbHandler.getAllCountries();
        String[] arraySpinner = countries.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arraySpinner);
        binding.countryAutoCompleteInputText.setThreshold(1);
        binding.countryAutoCompleteInputText.setAdapter(adapter);
    }
}