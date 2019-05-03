package tr.com.gokhan.kilic.engcards.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseActivity;
import tr.com.gokhan.kilic.engcards.activities.selectAvatar.SelectAvatarAdapter;
import tr.com.gokhan.kilic.engcards.activities.selectAvatar.SelectAvatarFragment;

public class HomeActivity extends BaseActivity {

    private TextView mTextMessage;
    FrameLayout contentFrame;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_cards);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_info);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        contentFrame = findViewById(R.id.contentFrame);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);



        SelectAvatarFragment selectAvatarFragment = SelectAvatarFragment.newInstance();
        inflateFragment(selectAvatarFragment,R.id.fragmentInsideContent,SelectAvatarFragment.TAG);




    }






}
