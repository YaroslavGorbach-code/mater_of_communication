//package com.YaroslavGorbach.delusionalgenerator.Fragments;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//
//import com.YaroslavGorbach.delusionalgenerator.Activityes.HelpActivity;
//import com.YaroslavGorbach.delusionalgenerator.R;
//import com.YaroslavGorbach.delusionalgenerator.Activityes.Statistics_activity;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ExercisesFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class ExercisesFragment extends Fragment {
//
//    private ImageButton mStartButton_ex1;
//    private ImageButton mStartButton_ex2;
//    private ImageButton mStartButton_ex3;
//    private ImageButton mStartButton_ex4;
//    private ImageButton mStartButton_ex5;
//    private ImageButton mStartButton_ex6;
//    private ImageButton mStartButton_ex7;
//    private ImageButton mStartButton_ex8;
//    private ImageButton mStartButton_ex9;
//    private ImageButton mStartButton_ex10;
//    private ImageButton mStartButton_ex11;
//    private ImageButton mStartButton_ex12;
//
//    private ImageButton mStatisticsButton_ex1;
//    private ImageButton mStatisticsButton_ex2;
//    private ImageButton mStatisticsButton_ex3;
//    private ImageButton mStatisticsButton_ex4;
//    private ImageButton mStatisticsButton_ex5;
//    private ImageButton mStatisticsButton_ex6;
//    private ImageButton mStatisticsButton_ex7;
//    private ImageButton mStatisticsButton_ex8;
//    private ImageButton mStatisticsButton_ex9;
//    private ImageButton mStatisticsButton_ex10;
//    private ImageButton mStatisticsButton_ex11;
//    private ImageButton mStatisticsButton_ex12;
//
//    private ImageButton mNotificationButton_ex1;
//    private ImageButton mNotificationButton_ex2;
//    private ImageButton mNotificationButton_ex3;
//    private ImageButton mNotificationButton_ex4;
//    private ImageButton mNotificationButton_ex5;
//    private ImageButton mNotificationButton_ex6;
//    private ImageButton mNotificationButton_ex7;
//    private ImageButton mNotificationButton_ex8;
//    private ImageButton mNotificationButton_ex9;
//    private ImageButton mNotificationButton_ex10;
//    private ImageButton mNotificationButton_ex11;
//    private ImageButton mNotificationButton_ex12;
//
//    public ExercisesFragment() {
//        // Required empty public constructor
//    }
//
//    // TODO: Rename and change types and number of parameters
//    public static ExercisesFragment newInstance() {
//        return new ExercisesFragment();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_exercises_version_2, container, false);
//
//        mStartButton_ex1 = view.findViewById(R.id.start_ex1);
//        mStartButton_ex2 = view.findViewById(R.id.start_ex2);
//        mStartButton_ex3 = view.findViewById(R.id.start_ex3);
//        mStartButton_ex4 = view.findViewById(R.id.start_ex4);
//        mStartButton_ex5 = view.findViewById(R.id.start_ex5);
//        mStartButton_ex6 = view.findViewById(R.id.start_ex6);
//        mStartButton_ex7 = view.findViewById(R.id.start_ex7);
//        mStartButton_ex8 = view.findViewById(R.id.start_ex8);
//        mStartButton_ex9 = view.findViewById(R.id.start_ex9);
//        mStartButton_ex10 = view.findViewById(R.id.start_ex10);
//        mStartButton_ex11 = view.findViewById(R.id.start_ex11);
//        mStartButton_ex12 = view.findViewById(R.id.start_ex12);
//
//        mStatisticsButton_ex1 = view.findViewById(R.id.statistics_ex1);
//        mStatisticsButton_ex2 = view.findViewById(R.id.statistics_ex2);
//        mStatisticsButton_ex3 = view.findViewById(R.id.statistics_ex3);
//        mStatisticsButton_ex4 = view.findViewById(R.id.statistics_ex4);
//        mStatisticsButton_ex5 = view.findViewById(R.id.statistics_ex5);
//        mStatisticsButton_ex6 = view.findViewById(R.id.statistics_ex6);
//        mStatisticsButton_ex7 = view.findViewById(R.id.statistics_ex7);
//        mStatisticsButton_ex8 = view.findViewById(R.id.statistics_ex8);
//        mStatisticsButton_ex9 = view.findViewById(R.id.statistics_ex9);
//        mStatisticsButton_ex10 = view.findViewById(R.id.statistics_ex10);
//        mStatisticsButton_ex11 = view.findViewById(R.id.statistics_ex11);
//        mStatisticsButton_ex12 = view.findViewById(R.id.statistics_ex12);
//
//        mNotificationButton_ex1 = view.findViewById(R.id.help_ex1);
//        mNotificationButton_ex2 = view.findViewById(R.id.help_ex2);
//        mNotificationButton_ex3 = view.findViewById(R.id.help_ex3);
//        mNotificationButton_ex4 = view.findViewById(R.id.help_ex4);
//        mNotificationButton_ex5 = view.findViewById(R.id.help_ex5);
//        mNotificationButton_ex6 = view.findViewById(R.id.help_ex6);
//        mNotificationButton_ex7 = view.findViewById(R.id.help_ex7);
//        mNotificationButton_ex8 = view.findViewById(R.id.help_ex8);
//        mNotificationButton_ex9 = view.findViewById(R.id.help_ex9);
//        mNotificationButton_ex10 = view.findViewById(R.id.help_ex10);
//        mNotificationButton_ex11 = view.findViewById(R.id.help_ex11);
//        mNotificationButton_ex12 = view.findViewById(R.id.help_ex12);
//        // Inflate the layout for this fragment
//
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        /*Оброботка нажатий для кнопок которые запускают упражнения*/
//        mStartButton_ex1.setOnClickListener(v->{ startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                    putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 1));
//        });
//
//        mStartButton_ex2.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 2));
//
//        });
//
//        mStartButton_ex3.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 3));
//
//        });
//
//        mStartButton_ex4.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 4));
//
//        });
//
//        mStartButton_ex5.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 5));
//
//        });
//
//        mStartButton_ex6.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 6));
//
//        });
//
//        mStartButton_ex7.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 7));
//
//        });
//
//        mStartButton_ex8.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 8));
//
//        });
//
//        mStartButton_ex9.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 9));
//
//        });
//
//        mStartButton_ex10.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 10));
//
//        });
//
//        mStartButton_ex11.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 11));
//
//        });
//
//        mStartButton_ex12.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), ExercisesCategory1Fragment.class).
//                putExtra(ExercisesCategory1Fragment.EXTRA_ID_EX, 12));
//
//        });
//
//
//        /*Оброботка нажатий на кнопки которые отвечают за открытие статистики упражнения*/
//        mStatisticsButton_ex1.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 1));
//
//        });
//
//        mStatisticsButton_ex2.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 2));
//
//        });
//        mStatisticsButton_ex3.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 3));
//
//        });
//
//        mStatisticsButton_ex4.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 4));
//
//        });
//
//        mStatisticsButton_ex5.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 5));
//
//        });
//
//        mStatisticsButton_ex6.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 6));
//
//        });
//
//        mStatisticsButton_ex7.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 7));
//
//        });
//
//        mStatisticsButton_ex8.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 8));
//
//        });
//
//        mStatisticsButton_ex9.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 9));
//
//        });
//
//        mStatisticsButton_ex10.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 10));
//
//        });
//
//        mStatisticsButton_ex11.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 11));
//
//        });
//
//        mStatisticsButton_ex12.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), Statistics_activity.class).
//                putExtra(Statistics_activity.EXTRA_ID_EX, 12));
//
//        });
//
//
//        /*Оброботка нажатий на кнопки которые отвечают за открытие помощи по упражнению*/
//        mNotificationButton_ex1.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 1));
//
//        });
//
//        mNotificationButton_ex2.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 2));
//
//        });
//
//        mNotificationButton_ex3.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 3));
//
//        });
//
//        mNotificationButton_ex4.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 4));
//
//        });
//
//        mNotificationButton_ex5.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 5));
//
//        });
//
//        mNotificationButton_ex6.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 6));
//
//        });
//
//        mNotificationButton_ex7.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 7));
//
//        });
//
//        mNotificationButton_ex8.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 8));
//
//        });
//
//        mNotificationButton_ex9.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 9));
//
//        });
//
//        mNotificationButton_ex10.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 10));
//
//        });
//
//        mNotificationButton_ex11.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 11));
//
//        });
//
//        mNotificationButton_ex12.setOnClickListener(v-> {startActivity(new Intent(view.getContext(), HelpActivity.class).
//                putExtra(HelpActivity.EXTRA_ID, 12));
//
//        });
//
//    }
//}