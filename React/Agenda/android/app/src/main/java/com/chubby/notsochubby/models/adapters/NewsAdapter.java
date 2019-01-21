package com.chubby.notsochubby.models.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chubby.notsochubby.models.utils.Converters;
import com.chubby.notsochubby.models.entities.NewsAndCategory;
import com.chubby.notsochubby.models.GlideApp;
import com.chubby.notsochubby.R;

public class NewsAdapter extends ListAdapter<NewsAndCategory, NewsAdapter.NewViewHolder> {

    private OnItemClickListener listener;

    public NewsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_HEADER = 1;
    private static final DiffUtil.ItemCallback<NewsAndCategory> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewsAndCategory>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsAndCategory oldItem, @NonNull NewsAndCategory newItem) {
            return oldItem.getNews().getId() == newItem.getNews().getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsAndCategory oldItem, @NonNull NewsAndCategory newItem) {
            return oldItem.getNews().getHtmlText().equals(newItem.getNews().getHtmlText()) &&
                    oldItem.getNews().getTitle().equals(newItem.getNews().getTitle()) &&
                    oldItem.getNews().getCatId() == newItem.getNews().getCatId() &&
                    oldItem.getNews().getPublishAt().equals(newItem.getNews().getPublishAt()) &&
                    oldItem.getCategory().getName().equals(newItem.getCategory().getName());
        }
    };

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == ITEM_TYPE_NORMAL) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        } else if (viewType == ITEM_TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_new_item, parent, false);
        } else {
            throw new RuntimeException("Wrong Item Type.");
        }
        return new NewViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder newViewHolder, int i) {
        NewsAndCategory currentNews = getNewsAt(i);
        newViewHolder.tvNewTitle.setText(currentNews.getNews().getTitle());

        String publishDate = Converters.calendarToDate(currentNews.getNews().getPublishAt()) + " | ";
        String category = currentNews.getCategory().getName();
        SpannableString publishAndCategory = new SpannableString(publishDate + category);
        publishAndCategory.setSpan(new ForegroundColorSpan(ContextCompat.getColor(newViewHolder.tvNewPublishCategory.getContext(), R.color.colorSecondary)),
                publishDate.length(), publishDate.length() + category.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        newViewHolder.tvNewPublishCategory.setText(publishAndCategory);
        GlideApp.with(newViewHolder.ivNew.getContext())
                .load(currentNews.getNews().getImagePath())
                .centerCrop()
                .into(newViewHolder.ivNew);
    }

    private NewsAndCategory getNewsAt(int position) {
        return getItem(position);
    }

    class NewViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNewTitle;
        public TextView tvNewPublishCategory;
        public ImageView ivNew;

        NewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvNewTitle = itemView.findViewById(R.id.tvNewTitle);
            this.tvNewPublishCategory = itemView.findViewById(R.id.tvNewPublishCategory);
            this.ivNew = itemView.findViewById(R.id.ivNew);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NewsAndCategory news);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
