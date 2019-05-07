package tr.com.gokhan.kilic.engcards.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.activities.game.GameActivity;
import tr.com.gokhan.kilic.engcards.models.UserProgressModel;
import tr.com.gokhan.kilic.engcards.models.Word;

public class GameChaptersFragment extends BaseFragment implements GameChaptersAdapter.SelectChapterListener {
    public static final String TAG = GameChaptersFragment.class.getSimpleName();

    //VARIABLES
    List<UserProgressModel> userProgressModels = new ArrayList<>();
    int selectedChapter = 0;


    //VIEWS
    RecyclerView chaptersRecyclerView ;
    private Button playEngcards;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_chapters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chaptersRecyclerView = view.findViewById(R.id.chaptersRecyclerView);
        playEngcards = view.findViewById(R.id.playEngcardsButton);

        playEngcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GameActivity.class);
                intent.putExtra("chapterLevel",selectedChapter);
                startActivity(intent);
            }
        });

        UserProgressModel model1 = new UserProgressModel();

        List<Integer> progress = new ArrayList<>();
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);
        progress.add(1);



        model1.setChaptersProgress(progress);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        chaptersRecyclerView.setLayoutManager(mLayoutManager);
        chaptersRecyclerView.setItemAnimator(new DefaultItemAnimator());

        GameChaptersAdapter gameChaptersAdapter = new GameChaptersAdapter(progress,this);
        chaptersRecyclerView.setAdapter(gameChaptersAdapter);
    }

    @Override
    public void selectedChapter(int selectedChapter) {
        this.selectedChapter =  selectedChapter;
        Log.i(TAG, "selectedChapter: " + selectedChapter);
    }
}
