package tr.com.gokhan.kilic.engcards.models;

public class Word {

    int id;
    String turkishMean;
    String englishMean;
    boolean isInList;
    boolean isLearned;
    int wordClass;




    public Word(int id, String turkishMean, String englishMean, boolean isInList, boolean isLearned, int wordClass) {
        this.id = id;
        this.turkishMean = turkishMean;
        this.englishMean = englishMean;
        this.isInList = isInList;
        this.isLearned = isLearned;
        this.wordClass = wordClass;
    }

    public Word(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isInList() {
        return isInList;
    }

    public void setInList(boolean inList) {
        isInList = inList;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public int getWordClass() {
        return wordClass;
    }

    public void setWordClass(int wordClass) {
        this.wordClass = wordClass;
    }

}
