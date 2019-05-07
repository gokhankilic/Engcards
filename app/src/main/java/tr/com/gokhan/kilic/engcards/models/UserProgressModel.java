package tr.com.gokhan.kilic.engcards.models;

import java.util.List;

public class UserProgressModel {

    int numberOfCardsLearned;
    List<Integer> chaptersProgress;

    public UserProgressModel() {
    }

    public int getNumberOfCardsLearned() {
        return numberOfCardsLearned;
    }

    public void setNumberOfCardsLearned(int numberOfCardsLearned) {
        this.numberOfCardsLearned = numberOfCardsLearned;
    }

    public List<Integer> getChaptersProgress() {
        return chaptersProgress;
    }

    public void setChaptersProgress(List<Integer> chaptersProgress) {
        this.chaptersProgress = chaptersProgress;
    }
}
