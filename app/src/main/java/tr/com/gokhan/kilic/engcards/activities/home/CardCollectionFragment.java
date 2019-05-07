package tr.com.gokhan.kilic.engcards.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.activities.game.GameActivity;
import tr.com.gokhan.kilic.engcards.models.Word;

public class CardCollectionFragment extends BaseFragment implements CardCollectionAdapter.TextToSpeechListener {

    public static final String TAG = CardCollectionFragment.class.getSimpleName();

    //VARIABLES
    List<Word> cards = new ArrayList<>();
    TextToSpeech textToSpeech;

    //VIEWS
    RecyclerView cardCollectionRecyclerView;
    Button playCollectionButton,playAllButton,playFavoritesButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_collection, container, false);


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textToSpeech = new TextToSpeech(view.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        cardCollectionRecyclerView = view.findViewById(R.id.cardCollectionRecyclerView);
        playCollectionButton = view.findViewById(R.id.playCollectionButton);
        playAllButton = view.findViewById(R.id.playAllCardsButton);
        playFavoritesButton = view.findViewById(R.id.playFavoritesButton);

        playCollectionButton.setOnClickListener(onClickListener);
        playAllButton.setOnClickListener(onClickListener);
        playFavoritesButton.setOnClickListener(onClickListener);

        cards = BaseActivity.mDBHelper.getWords(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        cardCollectionRecyclerView.setLayoutManager(mLayoutManager);
        cardCollectionRecyclerView.setItemAnimator(new DefaultItemAnimator());

        CardCollectionAdapter cardCollectionAdapter = new CardCollectionAdapter(cards,this);
        cardCollectionRecyclerView.setAdapter(cardCollectionAdapter);

    }

    @Override
    public void selectedCard(String englishMean) {
        textToSpeech.speak(englishMean,TextToSpeech.QUEUE_FLUSH,null);
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


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int gameType = 0;
            switch (v.getId()){
                case R.id.playAllCardsButton:
                    gameType = 0 ;
                    break;
                case R.id.playCollectionButton:
                    gameType = 1;
                    break;
                case R.id.playFavoritesButton:
                    gameType = 2;
                    break;
            }

            Intent intent = new Intent(getContext(), GameActivity.class);
            intent.putExtra("chapterLevel",-1);
            intent.putExtra("gameType",gameType);
            startActivity(intent);
        }
    };
}
