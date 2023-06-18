package vsu.julia.holidaycalendarapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vsu.julia.holidaycalendarapp.databinding.FragmentFvHolidayBinding;
import vsu.julia.holidaycalendarapp.sqldb.HolidayDbHandler;
import vsu.julia.holidaycalendarapp.viewmodel.HolidayViewModel;

public class FavouriteHolidaysFragment extends Fragment {
    private FragmentFvHolidayBinding binding;

    private HolidaysAdapter holidaysAdapter;
    private HolidayDbHandler holidayDbHandler;
    private HolidayViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFvHolidayBinding.inflate(inflater, container, false);
        holidayDbHandler = new HolidayDbHandler(getContext());
        viewModel = new ViewModelProvider(requireActivity()).get(HolidayViewModel.class);
        viewModel.setIsoCodesDbHandler(holidayDbHandler);
        Log.i("FavFragment-val", viewModel.getFavouritesLiveData().getValue() + "");
        Log.i("FavFragment-val", viewModel.getFavouritesLiveData().getValue() + "");
        holidaysAdapter = new HolidaysAdapter(viewModel.getFavouritesLiveData().getValue());
        binding.recyclerViewHolidays.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.recyclerViewHolidays.setAdapter(holidaysAdapter);
//        binding.countryName.setText("Мои праздники");
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        viewModel.getHolidayLiveData().removeObservers(getViewLifecycleOwner());
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.getHolidayLiveData().removeObservers(getViewLifecycleOwner());
    }
}
