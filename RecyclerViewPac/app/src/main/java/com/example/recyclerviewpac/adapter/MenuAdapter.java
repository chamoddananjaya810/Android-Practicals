package com.example.recyclerviewpac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewpac.Entity.Recipe;
import com.example.recyclerviewpac.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<Recipe> recipeList;

    public MenuAdapter(List<Recipe> list) {
        this.recipeList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.menuName.setText(recipe.getMenu_name());
        holder.menuPrice.setText(recipe.getMenu_price());
        holder.productImage.setImageURI(recipe.getProduct_img());

        if (recipe.getProduct_img()!=null){

            holder.productImage.setImageURI(recipe.getProduct_img());
        }

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;

        TextView menuName, menuPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            menuName = itemView.findViewById(R.id.menu_name);
            menuPrice = itemView.findViewById(R.id.menu_price);

        }
    }
}
