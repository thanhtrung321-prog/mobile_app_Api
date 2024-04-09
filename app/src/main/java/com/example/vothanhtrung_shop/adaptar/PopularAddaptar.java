package com.example.vothanhtrung_shop.adaptar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vothanhtrung_shop.ApiCaller;
import com.squareup.picasso.Picasso;

import com.bumptech.glide.Glide;
import com.example.vothanhtrung_shop.Product;
import com.example.vothanhtrung_shop.databinding.PopulerItemBinding;
import java.util.List;


public class PopularAddaptar extends RecyclerView.Adapter<PopularAddaptar.PopularViewHolder> {

    static ApiCaller apiCaller;
    private final List<Product> productList;

    public PopularAddaptar(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopulerItemBinding binding = PopulerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        // Implement your logic for item click listener here
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

        public void bind(Product product) {
            binding.foodNamePopuler.setText(product.getTitle());
            binding.PricePopuler.setText(String.valueOf(product.getPrice()));

            Picasso.get()
                    .load(apiCaller.url+"/image/products/"+product.getPhoto())
//                    .placeholder (R.drawable.downloading_200)
//                    .error(R.drawable.error_200)
                    .into (binding.imageView5);
        }
    }
}
