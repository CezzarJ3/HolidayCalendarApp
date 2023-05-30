package vsu.julia.holidaycalendarapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import vsu.julia.holidaycalendarapp.databinding.FragmentFirstBinding;
import vsu.julia.holidaycalendarapp.model.dto.HolidayDto;
import vsu.julia.holidaycalendarapp.service.HolidayApiClient;
import vsu.julia.holidaycalendarapp.sqldb.ISOCodesDbHandler;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private ISOCodesDbHandler isoCodesDbHandler;

    private HolidayApiClient holidayApiClient;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        isoCodesDbHandler = new ISOCodesDbHandler(getContext());
        holidayApiClient = new HolidayApiClient(new RestTemplate());
        fillCountrySpinner();

        HolidayDto holidayDto = holidayApiClient.getHoliday("RU", 2023);
        binding.holidayNamePlaceholer.setText(holidayDto.getName());
        binding.holidayDescriptionPlaceholder.setText(holidayDto.getDescription());
        return binding.getRoot();
    }

    private void fillCountrySpinner() {
        List<String> countries = isoCodesDbHandler.getAllCountries();
        String[] arraySpinner = countries.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arraySpinner);
//        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.countryAutoCompleteInput.setThreshold(1);
        binding.countryAutoCompleteInput.setAdapter(adapter);
    }

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}