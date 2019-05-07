package tr.com.gokhan.kilic.engcards.activities.game;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.models.QuestionModel;
import tr.com.gokhan.kilic.engcards.models.Word;

public class GamePlayFragment extends BaseFragment {
    public static String TAG = GamePlayFragment.class.getSimpleName();

    List<Integer> selectedWordIds;
    List<Word> selectedWords = new ArrayList<>();
    List<QuestionModel> questions = new ArrayList<>();
    int gameType;
    int currentQuestion = 0;

    //VIEWS
    ImageView stepBar;
    LinearLayout answerContainer;
    ImageButton nextQuestion;
    TextView questionTurkishMean,questionEnglishMean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_play,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stepBar = view.findViewById(R.id.stepBar2);
        answerContainer = view.findViewById(R.id.answerContainer);
        nextQuestion = view.findViewById(R.id.nextQuestionButton);
        questionTurkishMean = view.findViewById(R.id.questionTurkishMean);
        questionEnglishMean = view.findViewById(R.id.questionEnglishMean);

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion(questions.get(currentQuestion));
                currentQuestion++;
            }
        });

        initView();
        getWordsToBeAsked();
        generateQuestions();

        askQuestion(questions.get(0));


    }



    public void getWordsToBeAsked(){
        selectedWordIds = getArguments().getIntegerArrayList("selectedWordIds");

        for (int i = 0; i<selectedWordIds.size(); i++){
            Word word = BaseActivity.mDBHelper.getWordById(selectedWordIds.get(i));
            selectedWords.add(word);
        }
    }

    public void initView(){
        gameType = getArguments().getInt("gameType");

        if(gameType == -1){ //CHAPTER GAME
            stepBar.setImageResource(R.drawable.ic_step_bar_progress_2);
        }else{
            stepBar.setImageResource(R.drawable.ic_progress_bar_two_2);
        }
    }


    public void generateQuestions(){
        for(int i = 0; i<selectedWords.size(); i++){
            for(int j = 0; j<3; j++){
                QuestionModel  questionModel = new QuestionModel(j,
                        selectedWords.get(i).getTurkishMean(),
                        selectedWords.get(i).getEnglishMean(),
                        selectedWords.get(i).getEnglishMean());
                questions.add(questionModel);
            }


        }
    }


    public void askQuestion(QuestionModel question){

        questionEnglishMean.setText(question.getEnglishMean());
        questionTurkishMean.setText(question.getTurkishMean());

        switch (question.getType()){
            case 0:
                initAnswerType1(question.getAnswer());
                break;
            case 1:
                initAnswerType2(question.getAnswer());
                break;
            case 2:
                initAnswerType3(question.getAnswer());
                break;
        }

    }


    public void initAnswerType1(final String answer){
        answerContainer.removeAllViews();
        View view = null;
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.component_answer_type_1,null);
        answerContainer.addView(view);

        EditText answerEditText = view.findViewById(R.id.answerTypeEditText);
        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(answer.toLowerCase().equals(s.toString())){
                    currentQuestion++;
                    askQuestion(questions.get(currentQuestion));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void initAnswerType2(String answer){
        View view = null;
        answerContainer.removeAllViews();
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.component_answer_type_2,null);
        answerContainer.addView(view);

    }

    public void initAnswerType3(String answer){
        View view = null;
        answerContainer.removeAllViews();
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.component_answer_type_3,null);
        answerContainer.addView(view);
    }



}
