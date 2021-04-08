package com.sabeeldev.galleryapp.RetrofitUtils;

import android.os.Handler;
import android.util.Log;

import com.sabeeldev.galleryapp.Model.Images;
import com.sabeeldev.galleryapp.View.GetImagesView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sabeeldev.galleryapp.Activities.ImageDisplay.viewModel;

public class PostWebAPIData {
    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    GetImagesView getImagesView;

    public PostWebAPIData(GetImagesView getImagesView) {
        this.getImagesView = getImagesView;
    }

    public void getImagesData() {
        if (NetworkConnectivity.isOnline()) {
            Call<Images> call = apiInterface.getAllImages("21052296-38a7695f8ea789cf80417e3a3");
            call.enqueue(new Callback<Images>() {
                @Override
                public void onResponse(Call<Images> call, Response<Images> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Images.hits> allpictures = response.body().getHits();
                        viewModel.clearImages();
                        for (int i = 0; i < response.body().getHits().size(); i++) {
                            viewModel.insertImages(new com.sabeeldev.galleryapp.RoomDatabase.Images(allpictures.get(i).getLargeImageURL(), "" +
                                    allpictures.get(i).getPreviewURL(), "" + allpictures.get(i).getTags()));
                        }
                        getImagesView.onGetImages("0", "Success");
                    }
                }

                @Override
                public void onFailure(Call<Images> call, Throwable t) {
                    Log.d("MyResponse", "failure" + t.getMessage());
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getImagesData();
                }
            }, 4000);
        }
    }


    public void getSearchImages(String query) {
        if (NetworkConnectivity.isOnline()) {
            Call<Images> call = apiInterface.getAllImages("21052296-38a7695f8ea789cf80417e3a3", query, "photo");
            call.enqueue(new Callback<Images>() {
                @Override
                public void onResponse(Call<Images> call, Response<Images> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Images.hits> allpictures = response.body().getHits();
                        viewModel.clearImages();
                        for (int i = 0; i < allpictures.size(); i++) {
                            viewModel.insertImages(new com.sabeeldev.galleryapp.RoomDatabase.Images(allpictures.get(i).getLargeImageURL(), "" +
                                    allpictures.get(i).getPreviewURL(), "" + allpictures.get(i).getTags()));
                        }
                        getImagesView.onGetImages("0", "Success");
                    }
                }

                @Override
                public void onFailure(Call<Images> call, Throwable t) {
                    Log.d("MyResponse", "failure" + t.getMessage());
                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getImagesData();
                }
            }, 4000);
        }
    }
}
