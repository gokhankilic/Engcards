package tr.com.gokhan.kilic.engcards.activities.selectAvatar;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.activities.common.BaseFragment;
import tr.com.gokhan.kilic.engcards.activities.shared.UserDefaults;

public class SelectAvatarFragment extends BaseFragment implements SelectAvatarAdapter.SelectAvatarListener{

    public static final String TAG = SelectAvatarFragment.class.getSimpleName();
    public String USER_AVATAR = "userAvatar";

    //VARIABLES
    SelectAvatarAdapter selectAvatarAdapter;
    String userName;


    //VIEWS
    RecyclerView recyclerView;
    ImageButton doneButton;
    EditText userNameEditText;





    public static SelectAvatarFragment newInstance(){ return new SelectAvatarFragment(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_avatar,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.avatarsRecyclerView);
        doneButton = view.findViewById(R.id.doneButton);
        userNameEditText = view.findViewById(R.id.userNameEditText);

        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int selectedAvatar = UserDefaults.getSharedPreferences().getInt(USER_AVATAR,100);

                if(selectedAvatar != 100 && userName != null && userName != ""){
                    getActivity().getSupportFragmentManager().beginTransaction().remove(SelectAvatarFragment.this).commit();

                }else{
                    getBaseActivity().displayWarningMessage(R.string.title_home);
                }

            }
        });
        getAvatarDrawables();
    }

    public void getAvatarDrawables(){

        List<Integer> avatarDrawables = new ArrayList<>();
        avatarDrawables.add(R.drawable.ic_avatar_1);
        avatarDrawables.add(R.drawable.ic_avatar_2);
        avatarDrawables.add(R.drawable.ic_avatar_3);
        avatarDrawables.add(R.drawable.ic_avatar_4);
        avatarDrawables.add(R.drawable.ic_avatar_5);
        avatarDrawables.add(R.drawable.ic_avatar_6);
        avatarDrawables.add(R.drawable.ic_avatar_7);
        avatarDrawables.add(R.drawable.ic_avatar_8);
        avatarDrawables.add(R.drawable.ic_avatar_9);
        avatarDrawables.add(R.drawable.ic_avatar_3);
        avatarDrawables.add(R.drawable.ic_avatar_4);
        avatarDrawables.add(R.drawable.ic_avatar_5);
        avatarDrawables.add(R.drawable.ic_avatar_6);
        avatarDrawables.add(R.drawable.ic_avatar_7);
        avatarDrawables.add(R.drawable.ic_avatar_8);


        selectAvatarAdapter = new SelectAvatarAdapter(avatarDrawables,this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(selectAvatarAdapter);

    }

    @Override
    public void getIdSelectedAvatar(int selectedIdAvatar) {
        Log.i(TAG, "getIdSelectedAvatar: " + selectedIdAvatar);
    }
}
