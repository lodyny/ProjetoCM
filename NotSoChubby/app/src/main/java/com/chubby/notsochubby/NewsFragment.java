package com.chubby.notsochubby;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chubby.notsochubby.Models.Adapters.NewsAdapter;
import com.chubby.notsochubby.Models.Entities.NewsAndCategory;
import com.chubby.notsochubby.ViewModels.NewsViewModel;

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
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_fragment_news_display));
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
