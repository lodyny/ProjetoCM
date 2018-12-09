package com.chubby.notsochubby;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.chubby.notsochubby.Models.ChubbyDatabase;
import com.chubby.notsochubby.Models.Dao.NewsCategoryDao;
import com.chubby.notsochubby.Models.Dao.NewsDao;
import com.chubby.notsochubby.Models.Dao.SpotCategoryDao;
import com.chubby.notsochubby.Models.Dao.SpotDao;
import com.chubby.notsochubby.Models.Entities.News;
import com.chubby.notsochubby.Models.Entities.NewsCategory;
import com.chubby.notsochubby.Models.Entities.Spot;
import com.chubby.notsochubby.Models.Entities.SpotCategory;
import com.chubby.notsochubby.Models.LocalDateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.LocalDateTime;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadDBAsyncTask(this).execute();
    }

    private void goToMain(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    static class DownloadDBAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<SplashScreen> activityReference;

        DownloadDBAsyncTask(SplashScreen context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            final CountDownLatch latch = new CountDownLatch(2);
            final AtomicBoolean success = new AtomicBoolean(true);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
            final Gson gson = builder.create();
            final OkHttpClient client = new OkHttpClient();
            //News JSON
            String newsCatUrl = "https://chubby.westeurope.cloudapp.azure.com/chubby/api/getNewsCategories.php";
            Request newsCatRequest = new Request.Builder()
                    .url(newsCatUrl)
                    .build();
            client.newCall(newsCatRequest).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call,@NonNull IOException e) {
                    success.set(false);
                    latch.countDown();
                }

                @Override
                public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                    if(response.isSuccessful() && response.body() != null){
                         ChubbyDatabase db = ChubbyDatabase.getInstance(activityReference.get());
                         NewsCategoryDao categoriesDao = db.newsCategoryDao();
                         List<NewsCategory> categories = gson.fromJson(response.body().string(), new TypeToken<List<NewsCategory>>() {}.getType());
                        categoriesDao.insert(categories);
                        String newsUrl = "https://chubby.westeurope.cloudapp.azure.com/chubby/api/getNews.php";
                        Request newsRequest = new Request.Builder()
                                .url(newsUrl)
                                .build();
                         client.newCall(newsRequest).enqueue(new Callback() {
                             @Override
                             public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                 success.set(false);
                                 latch.countDown();
                             }
                             @Override
                             public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                 if(response.isSuccessful() && response.body() != null) {
                                     ChubbyDatabase db = ChubbyDatabase.getInstance(activityReference.get());
                                     NewsDao newsDao = db.newsDao();
                                     List<News> news = gson.fromJson(response.body().string(), new TypeToken<List<News>>() {}.getType());
                                     newsDao.insert(news);
                                 } else{
                                     success.set(false);
                                 }
                                 latch.countDown();
                             }
                         });
                    } else {
                        success.set(false);
                        latch.countDown();
                    }
                }
            });

            //Spots JSON
            String spotsCatUrl = "https://chubby.westeurope.cloudapp.azure.com/chubby/api/getSpotsCategories.php";
            Request spotsCatRequest = new Request.Builder()
                    .url(spotsCatUrl)
                    .build();
            client.newCall(spotsCatRequest).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call,@NonNull IOException e) {
                    success.set(false);
                    latch.countDown();
                }

                @Override
                public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                    if(response.isSuccessful() && response.body() != null){
                        ChubbyDatabase db = ChubbyDatabase.getInstance(activityReference.get());
                        SpotCategoryDao categoriesDao = db.spotCategoryDao();
                        List<SpotCategory> categories = gson.fromJson(response.body().string(), new TypeToken<List<SpotCategory>>() {}.getType());
                        categoriesDao.insert(categories);
                        String newsUrl = "https://chubby.westeurope.cloudapp.azure.com/chubby/api/getSpots.php";
                        Request spotsRequest = new Request.Builder()
                                .url(newsUrl)
                                .build();
                        client.newCall(spotsRequest).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                success.set(false);
                                latch.countDown();
                            }
                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if(response.isSuccessful() && response.body() != null) {
                                    ChubbyDatabase db = ChubbyDatabase.getInstance(activityReference.get());
                                    SpotDao spotDao = db.spotDao();
                                    List<Spot> spots = gson.fromJson(response.body().string(), new TypeToken<List<Spot>>() {}.getType());
                                    spotDao.insert(spots);
                                } else{
                                    success.set(false);
                                }
                                latch.countDown();
                            }
                        });
                    } else {
                        success.set(false);
                        latch.countDown();
                    }
                }
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                success.set(false);
            }
            return success.get();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            SplashScreen activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            if(!success) Toast.makeText(activity.getApplicationContext(), "Erro na ligação com o servidor.", Toast.LENGTH_SHORT).show();
            activity.goToMain();
        }
    }
}
