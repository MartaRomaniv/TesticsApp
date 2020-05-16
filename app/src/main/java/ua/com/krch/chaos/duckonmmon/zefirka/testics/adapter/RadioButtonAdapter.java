package ua.com.krch.chaos.duckonmmon.zefirka.testics.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.application.App;

public class RadioButtonAdapter extends RecyclerView.Adapter<RadioButtonAdapter.ViewHolder> {

    private List<String> answers;
    private OnItemClickListener listener;



    private App app = App.getINSTANCE();
    private int checkedPosition = -1;

    public RadioButtonAdapter(List<String> answers, OnItemClickListener onItemClickListener,int checkedPosition) {
        this.answers = answers;
        listener = onItemClickListener;
        this.checkedPosition = checkedPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answers_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.radioButton.setText(answers.get(position));
        holder.radioButton.setChecked(position == checkedPosition);
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radiobutton)
        RadioButton radioButton;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener((v) -> {
                listener.onItemClick(answers.get(getAdapterPosition()));
                checkedPosition = getAdapterPosition();
                notifyDataSetChanged();
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String answer);
    }
}
