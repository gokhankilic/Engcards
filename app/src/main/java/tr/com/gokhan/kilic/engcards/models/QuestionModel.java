package tr.com.gokhan.kilic.engcards.models;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel {
    int type;



    String turkishMean;
    String englishMean;
    String answer;

    public QuestionModel(int type, String turkishMean, String englishMean, String answer) {
        this.type = type;
        this.turkishMean = turkishMean;
        this.englishMean = englishMean;
        this.answer = answer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTurkishMean() {
        return turkishMean;
    }

    public void setTurkishMean(String turkishMean) {
        this.turkishMean = turkishMean;
    }

    public String getEnglishMean() {
        return englishMean;
    }

    public void setEnglishMean(String englishMean) {
        this.englishMean = englishMean;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
