package vsu.julia.holidaycalendarapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import vsu.julia.holidaycalendarapp.databinding.FragmentCreateHolidayBinding;
import vsu.julia.holidaycalendarapp.databinding.FragmentHolidaysListBinding;
import vsu.julia.holidaycalendarapp.model.Holiday;
import vsu.julia.holidaycalendarapp.sqldb.HolidayDbHandler;
import vsu.julia.holidaycalendarapp.util.Utils;
import vsu.julia.holidaycalendarapp.viewmodel.HolidayViewModel;

public class CreateHolidayFragment extends Fragment {
    private FragmentCreateHolidayBinding binding;
    private HolidayDbHandler holidayDbHandler;
    private HolidayViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateHolidayBinding.inflate(inflater, container, false);
        holidayDbHandler = new HolidayDbHandler(getContext());
        viewModel = new ViewModelProvider(requireActivity()).get(HolidayViewModel.class);
        viewModel.setIsoCodesDbHandler(holidayDbHandler);

        binding.createHolidayButton.setOnClickListener(v -> {
            Holiday holiday = new Holiday();
            holiday.setName(binding.name.getText().toString());
            holiday.setDescription(binding.description.getText().toString());

            if (!Utils.checkDate(binding.date.getText().toString())) {
                Toast.makeText(getContext(), "Неверный формат даты", Toast.LENGTH_LONG).show();
                return;
            }

            Holiday.Country country = new Holiday.Country();
            country.setId("RU");
            country.setName(binding.country.getText().toString());
            holiday.setCountry(country);

            Holiday.HolidayDate date = new Holiday.HolidayDate();
            date.setIsoDate(binding.date.getText().toString());

            holiday.setDate(date);

            viewModel.addHolidayToFavourite(holiday);

            Navigation.findNavController(requireView()).navigate(R.id.FirstFragment);
        });
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
