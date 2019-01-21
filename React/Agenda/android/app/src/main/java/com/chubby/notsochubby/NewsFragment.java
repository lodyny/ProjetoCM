package com.chubby.notsochubby;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chubby.notsochubby.models.adapters.NewsAdapter;
import com.chubby.notsochubby.models.entities.NewsAndCategory;
import com.chubby.notsochubby.viewmodels.NewsViewModel;

import java.util.List;


public class NewsFragment extends Fragment {

    public NewsFragment() {}

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        final NewsAdapter adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getNews().observe(this, new Observer<List<NewsAndCategory>>() {
            @Override
            public void onChanged(List<NewsAndCategory> news) {
                adapter.submitList(news);
            }
        });
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsAndCategory news) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                Bundle b = new Bundle();
                b.putInt(NewsActivity.KEY, news.getNews().getId());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        return v;
    }


}
