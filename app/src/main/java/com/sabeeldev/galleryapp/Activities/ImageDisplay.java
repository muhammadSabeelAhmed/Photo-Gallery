package com.sabeeldev.galleryapp.Activities;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Fade;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sabeeldev.galleryapp.Presenter.GetImagesPresenter;
import com.sabeeldev.galleryapp.R;
import com.sabeeldev.galleryapp.ViewModel.DbViewmModel;
import com.sabeeldev.galleryapp.RoomDatabase.Images;
import com.sabeeldev.galleryapp.View.GetImagesView;
import com.sabeeldev.galleryapp.fragments.pictureBrowserFragment;
import com.sabeeldev.galleryapp.ImageUtils.MarginDecoration;
import com.sabeeldev.galleryapp.ImageUtils.PicHolder;
import com.sabeeldev.galleryapp.ImageUtils.itemClickListener;
import com.sabeeldev.galleryapp.ImageUtils.picture_Adapter;

import java.util.List;


public class ImageDisplay extends AppCompatActivity implements itemClickListener, GetImagesView {
    public static DbViewmModel viewModel;
    RecyclerView imageRecycler;
    ProgressBar load;
    TextView folderName;
    GetImagesPresenter getImagesPresenter;
    picture_Adapter picture_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(ImageDisplay.this).get(DbViewmModel.class);
        }
        dbHandler();
        folderName = findViewById(R.id.foldername);
        folderName.setText("Photo Gallery");
        getImagesPresenter = new GetImagesPresenter(this::onGetImages);
        getImagesPresenter.onGetImagesPresenter();

        imageRecycler = findViewById(R.id.recycler);
        imageRecycler.addItemDecoration(new MarginDecoration(this));
        load = findViewById(R.id.loader);
        picture_adapter = new picture_Adapter(ImageDisplay.this, this);
        imageRecycler.setAdapter(picture_adapter);

        initSearchView();
    }

    private void dbHandler() {
        viewModel.getAllImagess().observe(this, new Observer<List<Images>>() {
            @Override
            public void onChanged(List<Images> allpics) {
                picture_adapter.setAdapter(allpics);
            }
        });
    }

    private void initSearchView() {
        SearchView simpleSearchView = (SearchView) findViewById(R.id.imageSearch);

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getImagesPresenter.onGetSearchImagesPresenter(query);
                load.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });
    }

    @Override
    public void onPicClicked(PicHolder holder, int position, List<Images> pics) {
        pictureBrowserFragment browser = pictureBrowserFragment.newInstance(pics, position, ImageDisplay.this);

        // Note that we need the API version check here because the actual transition classes (e.g. Fade)
        // are not in the support library and are only available in API 21+. The methods we are calling on the Fragment
        // ARE available in the support library (though they don't do anything on API < 21)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //browser.setEnterTransition(new Slide());
            //browser.setExitTransition(new Slide()); uncomment this to use slide transition and comment the two lines below
            browser.setEnterTransition(new Fade());
            browser.setExitTransition(new Fade());
        }

        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.picture, position + "picture")
                .add(R.id.displayContainer, browser)
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {

    }

    @Override
    public void onGetImages(String error, String message) {
        if (error.equals("0")) {
            load.setVisibility(View.GONE);
        }
    }
}
