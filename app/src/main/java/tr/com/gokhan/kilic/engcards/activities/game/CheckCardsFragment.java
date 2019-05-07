package tr.com.gokhan.kilic.engcards.activities.game;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.activities.home.CardCollectionAdapter;
import tr.com.gokhan.kilic.engcards.models.Word;

public class CheckCardsFragment extends BaseFragment implements CardCollectionAdapter.TextToSpeechListener {
    public static String TAG = CheckCardsFragment.class.getSimpleName();

    //VARIABLES
    int chapterLevel;
    int gameType;
    List<Word> words = new ArrayList<>();
    ArrayList<Integer> selectedWords = new ArrayList<>();
    CardCollectionAdapter adapter;
    TextToSpeech textToSpeech;

    //VIEWS
    RecyclerView checkCardsRecyclerView;
    Button readyButton;
    ImageView stepbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_check_cards,container,false);
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stepbar = view.findViewById(R.id.stepBar1);
        checkCardsRecyclerView = (RecyclerView) view.findViewById(R.id.checkCardRecyclerView);
        readyButton = view.findViewById(R.id.readyButton);

        initTextToSpeech();
        initCards();
        initView();

    }


    public void initView(){

        final Context context = checkCardsRecyclerView.getContext();
        LayoutAnimationController controller = null;
        controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_slide_from_right);


        adapter = new CardCollectionAdapter(words,this);

        RecyclerView.LayoutManager mLayoutManager;

        if(getBaseActivity().getResources().getConfiguration().orientation == 1) {

            mLayoutManager = new GridLayoutManager(context, 2);
        }else{
            mLayoutManager = new GridLayoutManager(context, 3);
        }


        checkCardsRecyclerView.setLayoutManager(mLayoutManager);
        checkCardsRecyclerView.setAdapter(adapter);
        checkCardsRecyclerView.setLayoutAnimation(controller);
        checkCardsRecyclerView.getAdapter().notifyDataSetChanged();
        checkCardsRecyclerView.scheduleLayoutAnimation();

        readyButton.startAnimation(AnimationUtils.loadAnimation(context,R.anim.move_from_left));
        readyButton.setOnClickListener(readyButtonClickListener);
    }




    public void generateWordsWillBeAsked(int chapterLevel){

        if(gameType != -1){

            Random random = new Random();

            while (selectedWords.size() < 10){

                int randomWord = random.nextInt(words.size());

                if(!selectedWords.contains(words.get(randomWord).getId())) {
                    selectedWords.add(words.get(randomWord).getId());
                }

            }


        }else{ //ENDGAME


        }

    }


    public void initTextToSpeech(){
        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }


    public void initCards(){
        chapterLevel = getArguments().getInt("chapterLevel",-1);
        gameType = getArguments().getInt("gameType",-1);

        if(gameType == -1){
            //USER CLICKED CHAPTER GAME BUTTON
            words = BaseActivity.mDBHelper.getWords(true);
            stepbar.setImageResource(R.drawable.ic_stap_bar_progress_1);
        }else{
            //USER CLICKED PLAY CARDS BUTTON
            stepbar.setImageResource(R.drawable.ic_progress_bar_two_1);
            switch (gameType){
                case 0: //ALL CARDS
                    words = BaseActivity.mDBHelper.getWords(true);
                    break;
                case 1: //COLLECTION CARDS
                    words = BaseActivity.mDBHelper.getWords(true);
                    break;
                case 2: //FAVORITES CARDS
                    words = BaseActivity.mDBHelper.getWords(true);
                    break;
            }

        }
    }



    View.OnClickListener readyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            generateWordsWillBeAsked(chapterLevel);
            GamePlayFragment fragment = new GamePlayFragment();
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("selectedWordIds",selectedWords);
            bundle.putInt("gameType",gameType);
            fragment.setArguments(bundle);
            FragmentTransaction ft = getBaseActivity().getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            ft.replace(R.id.gameContainer, fragment, "fragment");
            // Start the animated transition.
            ft.commit();
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume: ");
    }

    @Override
    public void selectedCard(String englishMean) {
        textToSpeech.speak(englishMean, TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }
}
