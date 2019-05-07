package tr.com.gokhan.kilic.engcards.activities.cards;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.activities.home.CardCollectionAdapter;
import tr.com.gokhan.kilic.engcards.models.Word;

public class CardsFragment extends BaseFragment implements CardCollectionAdapter.TextToSpeechListener {
    RecyclerView favoritesRecyclerView;
    RecyclerView endcardsRecyclerView;
    TextToSpeech textToSpeech;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cards,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView);
        endcardsRecyclerView = view.findViewById(R.id.engcardsRecyclerView);

        List<Word>cards = BaseActivity.mDBHelper.getWords(false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        favoritesRecyclerView.setLayoutManager(mLayoutManager);
        favoritesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        CardCollectionAdapter favoritesCardCollectionAdapter = new CardCollectionAdapter(cards,this);
        favoritesRecyclerView.setAdapter(favoritesCardCollectionAdapter);

        RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        endcardsRecyclerView.setLayoutManager(mmLayoutManager);
        endcardsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        CardCollectionAdapter endcardsCardCollectionAdapter = new CardCollectionAdapter(cards,this);
        endcardsRecyclerView.setAdapter(endcardsCardCollectionAdapter);


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
}
