package ua.com.krch.chaos.duckonmmon.zefirka.testics.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.application.App;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Test;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Test> tests;
    private OnItemClickListener listener;

    private App app = App.getINSTANCE();

    public ListAdapter(List<Test> tests,OnItemClickListener onItemClickListener) {
        this.tests = tests;
        listener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Test test = tests.get(position);

        holder.topicName.setText(test.getName());
        holder.topicImage.setImageResource(test.getImage());

        int markForTest = app.getPreferences().getInt(test.getKey(), 0);

        String markText = app.getString(R.string.mark_template, markForTest, test.getMaxMark());

        holder.mark.setText(markText);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topic_image)
        ImageView topicImage;
        @BindView(R.id.topic_name)
        TextView topicName;
        @BindView(R.id.topic_mark)
        TextView mark;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener((v)-> listener.onItemClick(getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
