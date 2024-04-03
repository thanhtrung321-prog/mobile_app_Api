package com.example.vothanhtrung_shop.adaptar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vothanhtrung_shop.databinding.PopulerItemBinding;
import java.util.List;

public class PopularAddaptar extends RecyclerView.Adapter<PopularAddaptar.PopularViewHolder> {

    private final List<String> items;
    private final List<String> prices;
    private final List<Integer> images;

    public PopularAddaptar(List<String> items, List<String> prices, List<Integer> images) {
        this.items = items;
        this.prices = prices;
        this.images = images;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopulerItemBinding binding = PopulerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        String item = items.get(position);
        String price = prices.get(position);
        int image = images.get(position);
        holder.bind(item, price, image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(PopularAddaptar.OnItemClickListener test) {
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        private final PopulerItemBinding binding;

        public PopularViewHolder(@NonNull PopulerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item, String price, int image) {
            binding.foodNamePopuler.setText(item);
            binding.PricePopuler.setText(price);
            binding.imageView5.setImageResource(image);
        }
    }
}
