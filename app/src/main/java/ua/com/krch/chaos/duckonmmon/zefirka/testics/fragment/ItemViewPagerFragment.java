package ua.com.krch.chaos.duckonmmon.zefirka.testics.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.adapter.RadioButtonAdapter;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.application.App;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Question;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Test;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.service.QuestionProviderService;

public class ItemViewPagerFragment extends Fragment {
    private static final String TEST = "test";
    private static final String POSITION = "position";
    private static final String PREVIOUS_ANSWER = "previous answer";
    private static final String QUESTION = "question";

    @BindView(R.id.question_text)
    TextView questionTextView;
    @BindView(R.id.question_image)
    ImageView questionImageView;
    @BindView(R.id.progress)
    TextView progressTextView;
    @BindView(R.id.finish_button)
    Button finishButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Test test;
    private Integer position;
    private Question question;
    private String answer;

    private OnFragmentRadioButtonClickListener mListener;

    public ItemViewPagerFragment() {
    }

    public static ItemViewPagerFragment newInstance(Test test, Integer position, String answer, Question question) {
        ItemViewPagerFragment fragment = new ItemViewPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(TEST, test);
        args.putInt(POSITION, position);
        args.putString(PREVIOUS_ANSWER, answer);
        args.putSerializable(QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            test = (Test) getArguments().getSerializable(TEST);
            position = getArguments().getInt(POSITION, -1);
            answer = getArguments().getString(PREVIOUS_ANSWER);
            question = (Question) getArguments().getSerializable(QUESTION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_view_pager, container, false);
        ButterKnife.bind(this, view);

        questionTextView.setText(question.getQuestion());
        if (question.getImage() != null) {
            questionImageView.setImageResource(question.getImage());
        }
        progressTextView.setText(App.getINSTANCE().getString(R.string.mark_template, position + 1, test.getMaxMark()));

        finishButton.setOnClickListener((v) -> mListener.OnFinishClick());
        finishButton.setVisibility(position == (test.getQuestions().size() - 1) ? View.VISIBLE : View.GONE);

        recyclerView.setAdapter(new RadioButtonAdapter(question.getAnswers(), (answer -> mListener.OnClick(answer, position)), getCorrectPosition(question)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentRadioButtonClickListener) {
            mListener = (OnFragmentRadioButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private Integer getCorrectPosition(Question question) {
        if (answer != null) {
            for (int i = 0; i < question.getAnswers().size(); i++) {
                if (answer.equals(question.getAnswers().get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public interface OnFragmentRadioButtonClickListener {
        void OnClick(String answer, Integer position);

        void OnFinishClick();
    }
}

