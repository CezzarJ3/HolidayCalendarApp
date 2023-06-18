package vsu.julia.holidaycalendarapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vsu.julia.holidaycalendarapp.databinding.FragmentHomeBinding;
import vsu.julia.holidaycalendarapp.model.Holiday;
import vsu.julia.holidaycalendarapp.model.HolidayType;
import vsu.julia.holidaycalendarapp.sqldb.HolidayDbHandler;
import vsu.julia.holidaycalendarapp.viewmodel.HolidayViewModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HolidayDbHandler holidayDbHandler;
    private HolidayViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        holidayDbHandler = new HolidayDbHandler(getContext());

        viewModel = new ViewModelProvider(requireActivity()).get(HolidayViewModel.class);
        viewModel.setIsoCodesDbHandler(holidayDbHandler);
        fillCountrySpinner();
        fillTypeSpinner();

        binding.dateInput.setText("");
        binding.countryAutoCompleteInput.setText("");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("FirstFrag", HolidayType.getTypeName("Национальный").name());

        binding.searchHolidayButton.setOnClickListener(v -> {
            Log.i("FirstFrag", "button clicked");
            String date = binding.dateInput.getText().toString();
            Log.i("FirstFrag", date + " date");
            String country = binding.countryAutoCompleteInput.getText().toString();
            Log.i("FirstFrag", country + " country");
            String type = binding.typeInput.getSelectedItem().toString();
            viewModel.getHolidays(country, date, type);
        });

        binding.addFavouriteButton.setOnClickListener(v -> {
            Log.i("FirstFrag", "Add to favourite button clicked");
            viewModel.addHolidayToFavourite(
                    binding.holidayNamePlaceholer.getText().toString(),
                    binding.countryAutoCompleteInput.getText().toString(),
                    binding.holidayDescriptionPlaceholder.getText().toString(),
                    binding.dateInput.getText().toString());
            Toast.makeText(getContext(), "Праздник добавлен в избранное", Toast.LENGTH_LONG).show();
        });

        binding.createHoliday.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.createHolidayFragment);
        });

        binding.showFavouriteButton.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.favouriteHolidaysFragment);
        });

        binding.showCalendar.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.calendarFragment);
        });

        viewModel.getIsDataValid().observe(getViewLifecycleOwner(), b -> {
            Log.i("Home", b.toString());
            if (!b) {
                Toast.makeText(getContext(), "Неверный формат даты", Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getHolidayLiveData().observe(getViewLifecycleOwner(), holidays -> {
            if (holidays != null) {
                if (holidays.size() == 1) {
                    binding.holidayNamePlaceholer.setText(holidays.get(0).getName());
                    binding.holidayDescriptionPlaceholder.setText(holidays.get(0).getDescription());
                } else if (holidays.size() == 0) {
                    Toast.makeText(getContext(), "Праздники не найдены", Toast.LENGTH_LONG).show();
                } else {
                    Navigation.findNavController(requireView()).navigate(R.id.SecondFragment);
                }
            } else {
//                Toast.makeText(getContext(), "Праздники не найдены", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillCountrySpinner() {
        List<String> countries = holidayDbHandler.getAllCountries();
        String[] arraySpinner = countries.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arraySpinner);
        binding.countryAutoCompleteInput.setThreshold(1);
        binding.countryAutoCompleteInput.setAdapter(adapter);
    }

    private void fillTypeSpinner() {
        String[] types = Arrays.stream(HolidayType.values()).map(HolidayType::toString).toArray(String[]::new);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, types);
        adapter.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);
        binding.typeInput.setAdapter(adapter);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        viewModel.getHolidayLiveData().setValue(List.of(viewModel.getHolidayLiveData().getValue().get(0)));
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (viewModel.getHolidayLiveData().getValue() != null) {
            if (viewModel.getHolidayLiveData().getValue().size() > 0) {
                viewModel.getHolidayLiveData().setValue(List.of(viewModel.getHolidayLiveData().getValue().get(0)));
            }
        } else {
            viewModel.getHolidayLiveData().setValue(new ArrayList<>());
        }
    }
}