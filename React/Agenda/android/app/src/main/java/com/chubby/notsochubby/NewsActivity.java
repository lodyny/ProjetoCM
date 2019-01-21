package com.chubby.notsochubby;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.chubby.notsochubby.models.GlideApp;
import com.chubby.notsochubby.models.utils.Converters;
import com.chubby.notsochubby.models.entities.NewsAndCategory;
import com.chubby.notsochubby.viewmodels.NewsDisplayFactory;
import com.chubby.notsochubby.viewmodels.NewsDisplayViewModel;


import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class NewsActivity extends AppCompatActivity {

    public static final String KEY = "KEYNEWID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        int newId = -1;
        if (b != null)
            newId = b.getInt(KEY);
        final WeakReference<NewsActivity> activityReference = new WeakReference<>(this);
        NewsDisplayViewModel newsViewModel = ViewModelProviders.of(this, new NewsDisplayFactory(this.getApplication(), newId)).get(NewsDisplayViewModel.class);
        newsViewModel.getNews().observe(this, new Observer<NewsAndCategory>() {
            @Override
            public void onChanged(NewsAndCategory news) {
                if(news != null) {
                    ImageView ivNews = findViewById(R.id.ivNew);
                    TextView tvTitle = findViewById(R.id.tvNewTitle);
                    TextView tvPublishCategory = findViewById(R.id.tvNewPublishCategory);
                    TextView tvHtmlText = findViewById(R.id.tvHtmlText);
                    GlideApp.with(activityReference.get())
                            .load(news.getNews().getImagePath())
                            .centerCrop()
                            .into(ivNews);
                    tvTitle.setText(news.getNews().getTitle());
                    String publishCategory = Converters.calendarToDate(news.getNews().getPublishAt()) + " | " + news.getCategory().getName();
                    tvPublishCategory.setText(publishCategory);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        tvHtmlText.setText(Html.fromHtml(news.getNews().getHtmlText(), Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        tvHtmlText.setText(Html.fromHtml(news.getNews().getHtmlText()));
                    }
                    tvHtmlText.setMovementMethod(LinkMovementMethod.getInstance());

                } else {
                    Toast.makeText(activityReference.get(), "Não foi possível carregar a notícia.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
