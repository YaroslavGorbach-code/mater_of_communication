package com.YaroslavGorbach.delusionalgenerator.component.speaking_ex;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.data.WordType;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.Chronometer;
import com.YaroslavGorbach.delusionalgenerator.feature.chronometer.ChronometerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.voicerecorder.VoiceRecorder;
import com.YaroslavGorbach.delusionalgenerator.feature.voicerecorder.VoiceRecorderImp;

import java.util.List;
import java.util.Random;

public class SpeakingExImp implements SpeakingEx {
    private final MutableLiveData<String> _word = new MutableLiveData<>("test");
    private final MutableLiveData<Boolean> mIsRecording = new MutableLiveData<>();
    private final Chronometer mChronometer;
    private final Chronometer mChronometerOneWord;
    private final VoiceRecorder mVoiceRecorder;

    private final ExModel mExModel;
    private final Repo mRepo;
    private final Resources mResources;
    private final Random mRandom = new Random();

    public SpeakingExImp(
            ExModel exModel,
            Repo repo,
            Resources resources,
            android.widget.Chronometer chronometer,
            android.widget.Chronometer chronometerOneWord,
            String recordPath
    ){
        mExModel = exModel;
        mRepo = repo;
        mResources = resources;
        mChronometer = new ChronometerImp(chronometer);
        mChronometerOneWord = new ChronometerImp(chronometerOneWord);
        mVoiceRecorder = new VoiceRecorderImp(recordPath);
        // init immediately
        nextWord();
    }

    @Override
    public void nextWord() {
        mChronometerOneWord.reset();
        switch (mExModel.name){
            case LINGUISTIC_PYRAMIDS:
                List<String> words = mRepo.getWords(WordType.NOT_ALIVE, mResources);
                _word.postValue(words.get(mRandom.nextInt(words.size())));
                break;
        }
    }

    @Override
    public String getShortDesc() {
        return mExModel.shortDesc[0];
    }

    @Override
    public LiveData<String> getWord() {
        return _word;
    }

    @Override
    public void startPauseChronometer() {
        if (mChronometer.getState() && mChronometerOneWord.getState()){
            mChronometer.pause();
            mChronometerOneWord.pause();
        }else {
            mChronometer.start();
            mChronometerOneWord.start();
        }
    }

    @Override
    public void startStopRecord() {
        if (mVoiceRecorder.getState()){
            mVoiceRecorder.stop();
            mIsRecording.postValue(false);
        }else {
            mVoiceRecorder.start();
            mIsRecording.postValue(true);
        }
    }

    @Override
    public LiveData<Boolean> getRecordingState() {
        return mIsRecording;
    }


//    private final RepoImpOLD mRepo_Imp_;
//    private final Repo_SQLite mRepoSQLite;
//    private final int mIdEx;
//    private MediaRecorder mediaRecorder;
//    private String recordFile;
//    private final Random r = new Random();
//
//
//    private String [] mArrayTextUnderThumb = {};
//    private List<String> mWorlds_ex1 = new ArrayList<>();
//    private List<String> mWorlds_ex2_living = new ArrayList<>();
//    private List<String> mWorlds_ex2_not_living = new ArrayList<>();
//    private List<String> mWorlds_ex3_filings = new ArrayList<>();
//    private List<String> mWorlds_ex4 = new ArrayList<>();
//    private List<String> mWorlds_ex5 = new ArrayList<>();
//    private List<String> mWorlds_ex6 = new ArrayList<>();
//    private List<String> mWorlds_ex7 = new ArrayList<>();
//    private List<String> mWorlds_ex8 = new ArrayList<>();
//    private List<String> mWorlds_ex9 = new ArrayList<>();
//    private List<String> mWorlds_ex10 = new ArrayList<>();
//    private List<String> mWorlds_ex11 = new ArrayList<>();
//    private List<String> mWorlds_ex12 = new ArrayList<>();
//    private List<String> mWorlds_ex13 = new ArrayList<>();
//
//    private final MutableLiveData<Boolean> _isRecording = new MutableLiveData<>(false);
//    public LiveData<Boolean> isRecording = _isRecording;
//
//    private final MutableLiveData<String> _exShortDesc = new MutableLiveData<>();
//    public LiveData<String> exShortDesc = _exShortDesc;
//
//    private final MutableLiveData<String> _buttonNextText = new MutableLiveData<>();
//    public LiveData<String> buttonNextText = _buttonNextText;
//
//    public SpeakingVm(@NonNull Application application, int exId) {
//        super(application);
//        mRepo_Imp_ = new RepoImpOLD(application);
//        mRepoSQLite = Repo_SQLite.getInstance(application);
//        mIdEx = exId;
//
//        switch (exId){
//            case 1:
//                mWorlds_ex1 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                mArrayTextUnderThumb = application.getResources().getStringArray(R.array.TextUnderThumb_ex1);
//                _exShortDesc.setValue("Обобщайте, разобобщайте, переходите по аналогиям");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 2:
//                mWorlds_ex2_living = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_Alive));
//                mWorlds_ex2_not_living = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Найдите сходство");
//                _buttonNextText.setValue("следующие слова");
//                break;
//            case 3:
//                mWorlds_ex2_not_living = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                mWorlds_ex3_filings = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_filings));
//                _exShortDesc.setValue("Найдите сходство");
//                _buttonNextText.setValue("следующие слова");
//                break;
//            case 4:
//                mWorlds_ex4 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Найдите сходства");
//                _buttonNextText.setValue("следующие слова");
//                break;
//            case 5:
//                mWorlds_ex5 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Описывайте максимально долго данный предмет");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 6:
//                mWorlds_ex6 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_abbreviations));
//                _exShortDesc.setValue("Придумайте необычную расшифровку аббревиатуры");
//                _buttonNextText.setValue("следующая аббревиатура");
//
//                break;
//            case 7:
//                mWorlds_ex7 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Придумайте к этому слову 5 или больше смешных прилагательных");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 8:
//                mWorlds_ex8 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Продайте это");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 9:
//                mWorlds_ex9 = Arrays.asList(application.getResources().getStringArray(R.array.letters));
//                _exShortDesc.setValue("Назовите 15 слов на эту букву");
//                _buttonNextText.setValue("следующая буква");
//                break;
//            case 10:
//                mWorlds_ex10 = Arrays.asList(application.getResources().getStringArray(R.array.Terms));
//                _exShortDesc.setValue("Дайте определение слову");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 11:
//                mWorlds_ex11 = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                _exShortDesc.setValue("Придумайте чем это может быть еще");
//                _buttonNextText.setValue("следующее слово");
//                break;
//            case 12:
//                mWorlds_ex12 = Arrays.asList(application.getResources().getStringArray(R.array.professions));
//                _exShortDesc.setValue("Придумайте ситуацию или фразу худшего в мире");
//                _buttonNextText.setValue("следующая профессия");
//                break;
//            case 13:
//                mWorlds_ex13 = Arrays.asList(application.getResources().getStringArray(R.array.questions));
//                _exShortDesc.setValue("Дайте развернутый ответ на вопрос");
//                _buttonNextText.setValue("следующий вопрос");
//                break;
//            case 14:
//                _exShortDesc.setValue("Составьте рассказ, используя данные слова");
//                _buttonNextText.setValue("следующие слова");
//                mWorlds_ex2_living = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_Alive));
//                mWorlds_ex2_not_living = Arrays.asList(application.getResources().getStringArray(R.array.Worlds_items_notAlive));
//                break;
//        }
//    }
//
//    /*Остановка записи*/
//    public void stopRecording(Context context) {
//        _isRecording.setValue(false);
//        Toast.makeText(context, "Запись сохранена: " + recordFile, Toast.LENGTH_SHORT).show();
//        mediaRecorder.stop();
//        mediaRecorder.release();
//        mediaRecorder = null;
//    }
//
//    /*Старт записи*/
//    public void startRecording(String exName, Activity activity) {
//        _isRecording.setValue(true);
//
//        //Get app external directory path
//        String recordPath = activity.getExternalFilesDir("/").getAbsolutePath();
//
//        //Get current date and time
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.getDefault());
//        Date now = new Date();
//
//        //initialize filename variable with date and time at the end to ensure the new file wont overwrite previous file
//        recordFile = exName + " " + formatter.format(now) + ".3gp";
//
//        //filenameText.setText("Recording, File Name : " + recordFile);
//
//        //Setup Media Recorder for recording
//        mediaRecorder = new MediaRecorder();
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mediaRecorder.setOutputFile(recordPath + "/" + recordFile);
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        try {
//            mediaRecorder.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Start Recording
//        mediaRecorder.start();
//    }
//
//    public String getWord() {
//        switch (mIdEx) {
//            case 1:
//                return mWorlds_ex1.get(r.nextInt(mWorlds_ex1.size()));
//            case 2:
//                return String.format("%s - %s", mWorlds_ex2_living.get(r.nextInt(mWorlds_ex2_living.size())),
//                        mWorlds_ex2_not_living.get(r.nextInt(mWorlds_ex2_not_living.size())));
//            case 3:
//                return String.format("%s - %s", mWorlds_ex3_filings.get(r.nextInt(mWorlds_ex3_filings.size())),
//                        mWorlds_ex2_not_living.get(r.nextInt(mWorlds_ex2_not_living.size())));
//            case 4:
//                String word_1 = mWorlds_ex4.get(r.nextInt(mWorlds_ex4.size()));
//                String word_2 = mWorlds_ex4.get(r.nextInt(mWorlds_ex4.size()));
//                if (word_1.equals(word_2)) {
//                    word_1 = mWorlds_ex4.get(r.nextInt(mWorlds_ex4.size()));
//                }
//                return String.format("%s - %s", word_1, word_2);
//            case 5:
//                return mWorlds_ex5.get(r.nextInt(mWorlds_ex5.size()));
//            case 6:
//                return mWorlds_ex6.get(r.nextInt(mWorlds_ex6.size()));
//            case 7:
//                return mWorlds_ex7.get(r.nextInt(mWorlds_ex7.size()));
//            case 8:
//                return mWorlds_ex8.get(r.nextInt(mWorlds_ex8.size()));
//            case 9:
//                return mWorlds_ex9.get(r.nextInt(mWorlds_ex9.size()));
//            case 10:
//                return mWorlds_ex10.get(r.nextInt(mWorlds_ex10.size()));
//            case 11:
//                return mWorlds_ex11.get(r.nextInt(mWorlds_ex11.size()));
//            case 12:
//                return mWorlds_ex12.get(r.nextInt(mWorlds_ex12.size()));
//            case 13:
//                return mWorlds_ex13.get(r.nextInt(mWorlds_ex13.size()));
//            case 14:
//                return String.format("%s, %s,  %s,  %s",
//                        mWorlds_ex2_living.get(r.nextInt(mWorlds_ex2_living.size())),
//                        mWorlds_ex2_not_living.get(r.nextInt(mWorlds_ex2_not_living.size())),
//                        mWorlds_ex2_living.get(r.nextInt(mWorlds_ex2_living.size())),
//                        mWorlds_ex2_not_living.get(r.nextInt(mWorlds_ex2_not_living.size())));
//        }
//        throw new IllegalArgumentException("Incorrect exercise id");
//    }
//
//    public String getThumbWord(){
//        return mArrayTextUnderThumb[r.nextInt(mArrayTextUnderThumb.length)];
//    }
//
//    public LiveData<Exercise> getExerciseById(int mIdEx) {
//        return mRepo_Imp_.getExerciseById(mIdEx);
//    }
//
//    public int getMaxWorldCount(int exId){
//        try {
//            return  mRepoSQLite.getMaxWorldCount(exId);
//        }catch (Exception e){
//            return 0;
//        }
//    }
}
