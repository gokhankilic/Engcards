package tr.com.gokhan.kilic.engcards.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.cards.CardsFragment;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.home.CardCollectionFragment;
import tr.com.gokhan.kilic.engcards.activities.home.UserInfoFragment;
import tr.com.gokhan.kilic.engcards.activities.selectAvatar.SelectAvatarFragment;
import tr.com.gokhan.kilic.engcards.shared.UserDefaults;

public class HomeActivity extends BaseActivity {


    FrameLayout contentFrame;
    private UserInfoFragment userInfoFragment;
    private CardCollectionFragment cardCollectionFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    removeAllFragments();
                    updateHomeFragments();

                    return true;
                case R.id.navigation_dashboard:
                    FragmentTransaction  ftAdd;
                    CardsFragment cardsFragment = new CardsFragment();
                    ftAdd = getSupportFragmentManager().beginTransaction();
                    removeAllFragments();
                    ftAdd.add(R.id.container, cardsFragment, "gokhan");
                    ftAdd.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_home);

        contentFrame = findViewById(R.id.contentFrame);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);




        if(UserDefaults.getUserAccount() == null) {
            SelectAvatarFragment selectAvatarFragment = SelectAvatarFragment.newInstance();
            inflateFragment(selectAvatarFragment, R.id.contentFrame, SelectAvatarFragment.TAG);
        }else{

            if(savedInstanceState == null) {
                updateHomeFragments();
            }
        }

    }







}
