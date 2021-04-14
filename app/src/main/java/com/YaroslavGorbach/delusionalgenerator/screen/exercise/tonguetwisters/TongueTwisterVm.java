package com.YaroslavGorbach.delusionalgenerator.screen.exercise.tonguetwisters;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.component.tonguetwisters.TongueTwisterEx;
import com.YaroslavGorbach.delusionalgenerator.component.tonguetwisters.TongueTwisterExImp;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class TongueTwisterVm extends ViewModel {
    public final TongueTwisterEx tongueTwisterEx;
    public TongueTwisterVm(int exId, Repo repo, Resources resources) {
        tongueTwisterEx = new TongueTwisterExImp(repo.getExercises().get(exId), repo, resources);
    }

    public static class TongueTwisterVmFactory extends ViewModelProvider.NewInstanceFactory {
        private final int exId;
        private final Repo repo;
        private final Resources resources;

        public TongueTwisterVmFactory(int exId, Repo repo, Resources resources){
            super();
            this.exId = exId;
            this.repo = repo;
            this.resources = resources;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(TongueTwisterVm.class)) {
                return (T)  new TongueTwisterVm(exId, repo, resources);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }



//    private String[] mTwists = {};
//    private final Random r = new Random();
//
//
//    public TongueTwisterVm(@NonNull Application application, int exId) {
//        super(application);
//
//        switch (exId) {
//            case 30:
//                mTwists = application.getResources().getStringArray(R.array.twisters_easy);
//                break;
//            case 31:
//                mTwists = application.getResources().getStringArray(R.array.twisters_hard);
//                break;
//            case 32:
//                mTwists = application.getResources().getStringArray(R.array.twisters_very_hard);
//                break;
//        }
//    }
//
//    private final MutableLiveData<Integer> _clickCounter = new MutableLiveData<>(0);
//    public LiveData<Integer> clickCounter = _clickCounter;
//
//    private final MutableLiveData<Integer> _numberOfTwisters = new MutableLiveData<>(1);
//    public LiveData<Integer> numberOfTwisters = _numberOfTwisters;
//
//    public void onClick(){
//        if (_clickCounter.getValue() !=5){
//            _clickCounter.setValue(_clickCounter.getValue() + 1);
//        }else {
//            _clickCounter.setValue(1);
//            _numberOfTwisters.setValue(numberOfTwisters.getValue() + 1);
//        }
//    }
//
//    public String getTwist(){
//        if (mTwists!=null){
//            return mTwists[r.nextInt(mTwists.length)];
//        }
//        throw new IllegalArgumentException("Array is empty");
//    }
}
