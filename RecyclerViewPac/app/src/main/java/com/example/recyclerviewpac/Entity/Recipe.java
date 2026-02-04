package com.example.recyclerviewpac.Entity;

import android.net.Uri;

public class Recipe {
  private String  menu_name;
  private String menu_price;

  private Uri product_img;

    public Recipe(String menu_name, String menu_price, Uri product_img) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.product_img = product_img;
    }
    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public void setProduct_img(Uri product_img) {
        this.product_img = product_img;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public Uri getProduct_img() {
        return product_img;
    }
}
