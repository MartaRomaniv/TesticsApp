package ua.com.krch.chaos.duckonmmon.zefirka.testics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.adapter.ListAdapter;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.constant.Constants;

public class ChooseTestActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ListAdapter.OnItemClickListener listener = position -> {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(Constants.TEST, Constants.tests.get(position));
        startActivity(intent);
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_test);
        ButterKnife.bind(this);

        ListAdapter listAdapter = new ListAdapter(Constants.tests, listener);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
