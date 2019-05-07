package tr.com.gokhan.kilic.engcards.activities.home;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.models.Word;


public class CardCollectionAdapter extends RecyclerView.Adapter<CardCollectionAdapter.MyViewHolder> {


    public interface TextToSpeechListener{
        public void selectedCard(String englishMean);
    }

    public String USER_AVATAR = "userAvatar";

    //VARIABLES
    List<Word>cards;
    List<Integer> cardColors = new ArrayList<>();
    TextToSpeechListener listener;


    public CardCollectionAdapter(List<Word> cards,TextToSpeechListener listener) {

      this.cards = cards;
      this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView turkishMean;
        TextView englishMean;
        CardView cardContainer;
        ImageButton speakerImageButton;

        public MyViewHolder(View view) {
            super(view);

            turkishMean = view.findViewById(R.id.englishMeanTextView);
            englishMean = view.findViewById(R.id.turkishMeanTextView);
            speakerImageButton = view.findViewById(R.id.speakerImageButton);

            cardContainer = view.findViewById(R.id.card);

            cardColors.add(R.color.color1);
            cardColors.add(R.color.color2);
            cardColors.add(R.color.color3);
            cardColors.add(R.color.color4);
            cardColors.add(R.color.color5);
            cardColors.add(R.color.color6);
            cardColors.add(R.color.color7);
            cardColors.add(R.color.color8);
            cardColors.add(R.color.color9);
            cardColors.add(R.color.color10);

        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_card_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {



        myViewHolder.englishMean.setText(cards.get(i).getEnglishMean());
        myViewHolder.turkishMean.setText(cards.get(i).getTurkishMean());
        myViewHolder.cardContainer.setCardBackgroundColor(myViewHolder.itemView.getResources().getColor(cardColors.get(i%8)));
        myViewHolder.speakerImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.selectedCard(cards.get(i).getEnglishMean());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


}
