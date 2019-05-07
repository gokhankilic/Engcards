package tr.com.gokhan.kilic.engcards.activities.game;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.cards.CardsFragment;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;

public class GameActivity extends BaseActivity {
    int chapterLevel;
    int gameType;
    Bundle bundle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_game);

        Intent intent = getIntent();

        chapterLevel = intent.getIntExtra("chapterLevel",-1);
        gameType = intent.getIntExtra("gameType",-1);

        bundle = new Bundle();

        bundle.putInt("chapterLevel",chapterLevel);
        bundle.putInt("gameType",gameType);

        FragmentTransaction ftAdd;
        CheckCardsFragment checkCardsFragment = new CheckCardsFragment();
        checkCardsFragment.setArguments(bundle);
        ftAdd = getSupportFragmentManager().beginTransaction();
        removeAllFragments();
        ftAdd.add(R.id.gameContainer, checkCardsFragment, "gokhan");
        ftAdd.commitAllowingStateLoss();




    }


}
