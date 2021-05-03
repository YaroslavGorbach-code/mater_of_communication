package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import androidx.fragment.app.Fragment;


public class NavFragment extends Fragment {
//    private final Fragment mExsFragment = new ExercisesFragment();
//    private final Fragment mRecordsFragment = new RecordsFragment();
//    private Fragment mActive = mExsFragment;
//
//    public NavFragment(){
//        super(R.layout.fragment_nav);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getChildFragmentManager().beginTransaction().replace(R.id.nav_container, mExsFragment, "1").commit();
//        getChildFragmentManager().beginTransaction().add(R.id.nav_container, mRecordsFragment, "2").hide(mRecordsFragment).commit();
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        FragmentNavBinding binding = FragmentNavBinding.bind(view);
//
//        binding.bottomNav.setOnNavigationItemSelectedListener(nav -> {
//            if (binding.bottomNav.getSelectedItemId()!=nav.getItemId()
//                    || getChildFragmentManager().getFragments().isEmpty()){
//                switch (nav.getItemId()){
//                    case R.id.menu_nav_exercises:
//                        getChildFragmentManager().beginTransaction().hide(mActive).show(mExsFragment).commit();
//                        mActive = mExsFragment;
//                        return true;
//                    case R.id.menu_nav_records:
//                        getChildFragmentManager().beginTransaction().hide(mActive).show(mRecordsFragment).commit();
//                        mActive = mRecordsFragment;
//                        return true;
//                }
//            }
//            return false;
//        });
//        binding.bottomNav.setSelectedItemId(R.id.menu_nav_exercises);
//    }
}
