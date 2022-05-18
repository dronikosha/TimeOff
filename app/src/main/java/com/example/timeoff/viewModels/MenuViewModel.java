package com.example.timeoff.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.CartRepo;

import java.util.ArrayList;

public class MenuViewModel extends ViewModel {
    private MutableLiveData<ArrayList<FoodItem>> currentProduct;

    public MutableLiveData<ArrayList<FoodItem>> getCurrentName() {
        CartRepo cartRepository = new CartRepo();

        if (currentProduct == null) {
            currentProduct = new MutableLiveData<ArrayList<FoodItem>>();
        }
        currentProduct.setValue(cartRepository.getCart());
        return currentProduct;
    }
}
