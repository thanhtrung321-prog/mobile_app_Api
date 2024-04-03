package com.example.vothanhtrung_shop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.vothanhtrung_shop.R;
import com.example.vothanhtrung_shop.adaptar.PopularAddaptar;
import com.example.vothanhtrung_shop.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup Image Slider
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        ImageSlider imageSlider = binding.imageSlider;
        imageSlider.setImageList(imageList, ScaleTypes.FIT);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {
                SlideModel selectedItem = imageList.get(position);
                String itemMessage = "Hình Ảnh Hiện Tại " + position;
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doubleClick(int position) {
                // TODO: Implement doubleClick action
            }
        });
        // tạo mảng để in ra sản phẩm
        // Setup RecyclerView
        List<String> foodNames = new ArrayList<>();
        foodNames.add("burger");
        foodNames.add("sandwich");
        foodNames.add("momo");
        foodNames.add("item");
        foodNames.add("item");
        foodNames.add("item");
        foodNames.add("item");


        List<String> prices = new ArrayList<>();
        prices.add("$5");
        prices.add("$7");
        prices.add("$8");
        prices.add("$10");
        prices.add("$18");
        prices.add("$13");
        prices.add("$20");
        List<Integer> populerFoodImages = new ArrayList<>();
        populerFoodImages.add(R.drawable.menu1);
        populerFoodImages.add(R.drawable.menu2);
        populerFoodImages.add(R.drawable.menu3);
        populerFoodImages.add(R.drawable.menu4);
        populerFoodImages.add(R.drawable.menu5);
        populerFoodImages.add(R.drawable.menu6);
        populerFoodImages.add(R.drawable.menu7);

        PopularAddaptar adapter = new PopularAddaptar(foodNames, prices, populerFoodImages);
        binding.PopulerRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.PopulerRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}