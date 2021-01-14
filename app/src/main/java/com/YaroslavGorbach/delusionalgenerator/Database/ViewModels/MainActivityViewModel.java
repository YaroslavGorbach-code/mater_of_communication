package com.YaroslavGorbach.delusionalgenerator.Database.ViewModels;
import androidx.lifecycle.ViewModel;

import java.io.File;

public class MainActivityViewModel extends ViewModel {

    public void deleteRecords(String path) {
        /*Получаем файлы из деректории*/
        File directory = new File(path);
        File[] allFiles = directory.listFiles();

        if (allFiles != null) {
            for (File f : allFiles) {
                f.delete();
            }
        }
    }
}
