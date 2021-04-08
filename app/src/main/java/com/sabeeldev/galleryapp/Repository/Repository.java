package com.sabeeldev.galleryapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.sabeeldev.galleryapp.RoomDatabase.Images;
import com.sabeeldev.galleryapp.RoomDatabase.ImagesDao;
import com.sabeeldev.galleryapp.RoomDatabase.ImagesDatabase;

import java.util.List;

public class Repository {
    ImagesDao imagesDao;

    private LiveData<List<Images>> images;

    public Repository(Application application) {
        ImagesDatabase imagesDatabase = ImagesDatabase.getInstance(application);
        imagesDao = imagesDatabase.imagesDao();
        images = imagesDao.showAllImages();
    }

    public void insertImages(Images images) {
        new insertImagesAsyncTask(imagesDao).execute(images);
    }

    public void updateImages(Images images) {
        new updateImagesAsyncTask(imagesDao).execute(images);
    }

    public void clearImages() {
        new deleteImagesAsyncTask(imagesDao).execute();
    }

    public LiveData<List<Images>> getAllImages() {
        return images;
    }

    private class insertImagesAsyncTask extends AsyncTask<Images, Void, Void> {
        ImagesDao imagesDao;

        public insertImagesAsyncTask(ImagesDao imagesDao) {
            this.imagesDao = imagesDao;
        }

        @Override
        protected Void doInBackground(Images... clients) {
            imagesDao.insertImages(clients[0]);
            return null;
        }
    }

    private class updateImagesAsyncTask extends AsyncTask<Images, Void, Void> {
        ImagesDao imagesDao;

        public updateImagesAsyncTask(ImagesDao imagesDao) {
            this.imagesDao = imagesDao;
        }

        @Override
        protected Void doInBackground(Images... clients) {
            imagesDao.insertImages(clients[0]);
            return null;
        }
    }

    private class deleteImagesAsyncTask extends AsyncTask<Void, Void, Void> {
        ImagesDao imagesDao;

        public deleteImagesAsyncTask(ImagesDao imagesDao) {
            this.imagesDao = imagesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            imagesDao.clearAllImages();
            return null;
        }
    }
}
