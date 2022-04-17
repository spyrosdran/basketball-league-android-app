package com.example.basketballleague.ui.top5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Top5ViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public Top5ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is top 5 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}