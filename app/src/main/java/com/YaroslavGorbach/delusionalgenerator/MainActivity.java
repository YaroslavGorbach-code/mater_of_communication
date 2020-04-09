package com.YaroslavGorbach.delusionalgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements DialogChooseTheme.ChooseThemesListener {

    private Toolbar mToolbar;

    private ImageButton mStartButton_ex1;
    private ImageButton mStartButton_ex2;
    private ImageButton mStartButton_ex3;
    private ImageButton mStartButton_ex4;
    private ImageButton mStartButton_ex5;
    private ImageButton mStartButton_ex6;
    private ImageButton mStartButton_ex7;
    private ImageButton mStartButton_ex8;

    private ImageButton mStatisticsButton_ex1;
    private ImageButton mStatisticsButton_ex2;
    private ImageButton mStatisticsButton_ex3;
    private ImageButton mStatisticsButton_ex4;
    private ImageButton mStatisticsButton_ex5;
    private ImageButton mStatisticsButton_ex6;
    private ImageButton mStatisticsButton_ex7;
    private ImageButton mStatisticsButton_ex8;

    private ImageButton mNotificationButton_ex1;
    private ImageButton mNotificationButton_ex2;
    private ImageButton mNotificationButton_ex3;
    private ImageButton mNotificationButton_ex4;
    private ImageButton mNotificationButton_ex5;
    private ImageButton mNotificationButton_ex6;
    private ImageButton mNotificationButton_ex7;
    private ImageButton mNotificationButton_ex8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

        mStartButton_ex1.setOnClickListener(v->{
            startActivity(new Intent(this, ExercisesActivity.class).
                    putExtra(ExercisesActivity.EXTRA_ID_EX, 1));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex2.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 2));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex3.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 3));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex4.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 4));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex5.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 5));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex6.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 6));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex7.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 7));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStartButton_ex8.setOnClickListener(v-> {startActivity(new Intent(this, ExercisesActivity.class).
                putExtra(ExercisesActivity.EXTRA_ID_EX, 8));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });



        mStatisticsButton_ex1.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 1));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex2.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 2));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex3.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 3));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex4.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 4));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex5.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 5));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex6.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 6));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex7.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 7));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mStatisticsButton_ex8.setOnClickListener(v-> {startActivity(new Intent(this, Statistics_activity.class).
                putExtra(Statistics_activity.EXTRA_ID_EX, 8));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });




        mNotificationButton_ex1.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 1));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex2.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 2));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex3.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 3));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex4.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 4));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex5.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 5));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex6.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 6));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex7.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 7));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mNotificationButton_ex8.setOnClickListener(v-> {startActivity(new Intent(this, HelpActivity.class).
                putExtra(HelpActivity.EXTRA_ID, 8));
            overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
        });

        mToolbar.setOnMenuItemClickListener(v->{

           switch (v.getItemId()){
               case R.id.theme:

                   DialogChooseTheme dialog = new DialogChooseTheme();
                   dialog.show(getSupportFragmentManager(),"Выбор темы");
                   overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);

                   break;
               case R.id.rate:


                   break;
               case R.id.share:

                   break;
               case R.id.aboutApp:

                   DialogAboutApp dialog2 = new DialogAboutApp();
                   dialog2.show(getSupportFragmentManager(),"AboutApp");
                   overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
                   break;
           }

            return true;
        });

    }




    private void initializeComponents(){

        mToolbar = findViewById(R.id.toolbar_main);

        mStartButton_ex1 = findViewById(R.id.start_ex1);
        mStartButton_ex2 = findViewById(R.id.start_ex2);
        mStartButton_ex3 = findViewById(R.id.start_ex3);
        mStartButton_ex4 = findViewById(R.id.start_ex4);
        mStartButton_ex5 = findViewById(R.id.start_ex5);
        mStartButton_ex6 = findViewById(R.id.start_ex6);
        mStartButton_ex7 = findViewById(R.id.start_ex7);
        mStartButton_ex8 = findViewById(R.id.start_ex8);

        mStatisticsButton_ex1 = findViewById(R.id.statistics_ex1);
        mStatisticsButton_ex2 = findViewById(R.id.statistics_ex2);
        mStatisticsButton_ex3 = findViewById(R.id.statistics_ex3);
        mStatisticsButton_ex4 = findViewById(R.id.statistics_ex4);
        mStatisticsButton_ex5 = findViewById(R.id.statistics_ex5);
        mStatisticsButton_ex6 = findViewById(R.id.statistics_ex6);
        mStatisticsButton_ex7 = findViewById(R.id.statistics_ex7);
        mStatisticsButton_ex8 = findViewById(R.id.statistics_ex8);

        mNotificationButton_ex1 = findViewById(R.id.help_ex1);
        mNotificationButton_ex2 = findViewById(R.id.help_ex2);
        mNotificationButton_ex3 = findViewById(R.id.help_ex3);
        mNotificationButton_ex4 = findViewById(R.id.help_ex4);
        mNotificationButton_ex5 = findViewById(R.id.help_ex5);
        mNotificationButton_ex6 = findViewById(R.id.help_ex6);
        mNotificationButton_ex7 = findViewById(R.id.help_ex7);
        mNotificationButton_ex8 = findViewById(R.id.help_ex8);

    }

    private void setTheme(){
        String color = Repo.getInstance(MainActivity.this).getThemeState();
        switch (color){

            case "blue":

                setTheme(R.style.AppTheme_blue);

                break;

            case "green":

                setTheme(R.style.AppTheme_green);
                break;

            case "orange":

                setTheme(R.style.AppTheme_orange);
                break;

            case "red":

                setTheme(R.style.AppTheme_red);
                break;

            case "purple":

                setTheme(R.style.AppTheme_purple);
                break;

        }
    }


    @Override
    public void onClickTheme(DialogFragment dialog) {
        recreate();
    }
}

