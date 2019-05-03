package tr.com.gokhan.kilic.engcards.models;

import java.util.List;

public class UserModel {

    String userName;
    int userAvatarImage;
    int userProgress;
    List<Integer> chapterProgress;

    public UserModel(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAvatarImage() {
        return userAvatarImage;
    }

    public void setUserAvatarImage(int userAvatarImage) {
        this.userAvatarImage = userAvatarImage;
    }

    public int getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(int userProgress) {
        this.userProgress = userProgress;
    }

    public List<Integer> getChapterProgress() {
        return chapterProgress;
    }

    public void setChapterProgress(List<Integer> chapterProgress) {
        this.chapterProgress = chapterProgress;
    }






}
