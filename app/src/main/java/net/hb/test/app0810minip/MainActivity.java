package net.hb.test.app0810minip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    TextView tv[] = new TextView[2];
    ViewPager vp1;
    ViewPagerAdapter vpAdapter;
    Fragment[] fragment = new Fragment[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv[0]=findViewById(R.id.text_r);
        tv[1]=findViewById(R.id.text_w);

        vp1=findViewById(R.id.vp1);

        this.fragment[0]=new ReadFragment();
        this.fragment[1]=new WriteFragment();

        vpAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        vp1.setAdapter(vpAdapter);

        for (int i=0; i<2; i++){
            tv[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_r: vp1.setCurrentItem(0); break;
            case R.id.text_w: vp1.setCurrentItem(1); break;
        }//switch
    }//onClick

    public void call (int number) {
        vp1.setCurrentItem(number);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {super(fm);}
        public Fragment getItem(int position) {return fragment[position];}
        public int getCount() {return fragment.length;}

    }//VPA class end

}//class end
