package tr.com.gokhan.kilic.engcards.activities.home;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.models.UserProgressModel;
import tr.com.gokhan.kilic.engcards.models.Word;

public class GameChaptersAdapter extends RecyclerView.Adapter<GameChaptersAdapter.MyViewHolder>  {

    //VARIABLES
    List<Integer> chapters;
    List<Drawable> cardBackgrounds = new ArrayList<>();
    List<MyViewHolder> myViewHolders = new ArrayList<>();
    int currentChapter;
    SelectChapterListener listener;

    public interface SelectChapterListener {
        public void selectedChapter(int selectedChapter);
    }

    public GameChaptersAdapter(List<Integer> chapters,SelectChapterListener listener) {
        this.listener = listener;
        this.chapters = chapters;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView chapterName;
        CardView chapterCard;
        ImageView chapterStatus;
        LinearLayout cardBackgroundContainer;


        public MyViewHolder(View view) {
            super(view);

            chapterName = view.findViewById(R.id.chapterNameTextView);
            chapterCard = view.findViewById(R.id.chapterCard);
            chapterStatus = view.findViewById(R.id.chapterStatus);
            cardBackgroundContainer = view.findViewById(R.id.cardBackgroundContainer);

            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_1));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_2));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_3));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_4));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_5));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_6));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_7));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_8));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_9));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_10));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_11));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_12));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_13));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_14));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_15));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_16));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_17));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_18));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_19));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_20));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_21));
            cardBackgrounds.add(view.getResources().getDrawable(R.drawable.ic_chapter_background_22));



        }


    }

    @Override
    public GameChaptersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_game_chapter_list_item, parent, false);

        return new GameChaptersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolders.add(myViewHolder);

        myViewHolder.chapterName.setText("Chapter " + i);

        myViewHolder.cardBackgroundContainer.setBackground(cardBackgrounds.get(i%22));

        if(currentChapter == i){
            myViewHolder.chapterStatus.setImageResource(R.drawable.ic_current_chapter);
        }else{
            myViewHolder.chapterStatus.setImageResource(R.drawable.ic_completed_chapter);
        }

        myViewHolder.cardBackgroundContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i<myViewHolders.size(); i++){
                    myViewHolders.get(i).chapterStatus.setImageResource(R.drawable.ic_completed_chapter);
                }
                myViewHolder.chapterStatus.setImageResource(R.drawable.ic_current_chapter);
                currentChapter = i;
                listener.selectedChapter(currentChapter);
            }
        });

    }


    @Override
    public int getItemCount() {
        return chapters.size();
    }

}
