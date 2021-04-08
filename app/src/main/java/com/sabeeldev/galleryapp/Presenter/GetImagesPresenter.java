package com.sabeeldev.galleryapp.Presenter;

import android.icu.text.TimeZoneFormat;

import com.sabeeldev.galleryapp.RetrofitUtils.PostWebAPIData;
import com.sabeeldev.galleryapp.View.GetImagesView;

public class GetImagesPresenter implements IGetImagesPresenter {
    GetImagesView getImagesView;
    PostWebAPIData postWebAPIData;

    public GetImagesPresenter(GetImagesView getImagesView) {
        this.getImagesView = getImagesView;
        postWebAPIData = new PostWebAPIData(getImagesView);
    }

    @Override
    public void onGetImagesPresenter() {
        postWebAPIData.getImagesData();
    }

    @Override
    public void onGetSearchImagesPresenter(String query) {
        postWebAPIData.getSearchImages(query);
    }
}
