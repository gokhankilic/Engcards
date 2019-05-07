package tr.com.gokhan.kilic.engcards.activities.selectAvatar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tr.com.gokhan.kilic.engcards.R;
import tr.com.gokhan.kilic.engcards.shared.UserDefaults;


public class SelectAvatarAdapter extends RecyclerView.Adapter<SelectAvatarAdapter.MyViewHolder> {

        public static String TAG = SelectAvatarFragment.class.getSimpleName();
        public String USER_AVATAR = "userAvatar";

        //VARIABLES
        SelectAvatarListener listener;
        List<Integer> avatars;
        List<MyViewHolder> myViewHolders = new ArrayList<>();

        public interface SelectAvatarListener {
            public void getIdSelectedAvatar(int selectedIdAvatar);
        }

        public SelectAvatarAdapter(List<Integer> avatars,SelectAvatarListener listener) {
            this.listener = listener;
            this.avatars = avatars;

        }


     public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImageView;
        ImageView checkImage;

        public MyViewHolder(View view) {
            super(view);

            avatarImageView = view.findViewById(R.id.avatarImageView);
            checkImage = view.findViewById(R.id.checkImage);

        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_avatar_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolders.add(myViewHolder);
        myViewHolder.avatarImageView.setImageResource(avatars.get(i));
        myViewHolder.avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i<myViewHolders.size(); i++){
                    myViewHolders.get(i).checkImage.setVisibility(View.INVISIBLE);
                }
                listener.getIdSelectedAvatar(i);
                myViewHolder.checkImage.setVisibility(View.VISIBLE);

                UserDefaults.getEditor().putInt(USER_AVATAR,avatars.get(i)).commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return avatars.size();
    }


}
