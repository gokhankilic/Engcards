package tr.com.gokhan.kilic.engcards.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;

public class UserInfoFragment extends BaseFragment {

    public static final String TAG = UserInfoFragment.class.getSimpleName();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info, container, false);


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView userImage = view.findViewById(R.id.userAvatarImage);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        ProgressBar userProgressBar = view.findViewById(R.id.userProgressBar);



    }

}






