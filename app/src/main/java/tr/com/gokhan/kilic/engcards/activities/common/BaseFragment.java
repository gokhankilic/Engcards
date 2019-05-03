package tr.com.gokhan.kilic.engcards.activities.common;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    @Nullable
    public BaseActivity getBaseActivity() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        } else {
            return null;
        }
    }


    public void showLoadingLayout(){
        if(getBaseActivity() != null){
            getBaseActivity().showLoadingLayout();
        }
    }

    public void hideLoadingLayout(){
        if(getBaseActivity() != null){
            getBaseActivity().hideLayoutLoading();
        }
    }

}
