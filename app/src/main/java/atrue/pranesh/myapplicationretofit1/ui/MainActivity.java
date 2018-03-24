package atrue.pranesh.myapplicationretofit1.ui;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.widget.FrameLayout;
import atrue.pranesh.myapplicationretofit1.R;
import atrue.pranesh.myapplicationretofit1.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements Communicator{
    FrameLayout base_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base_container=findViewById(R.id.base_container);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,new HomeFragment(),"HomeFragment");
        fragmentTransaction.commit();
    }


    @Override
    public void sendData(Bundle bundle) {
    HomeFragment fragment=(HomeFragment)getFragmentManager().findFragmentByTag("HomeFragment");
    fragment.refershAdapter(bundle);

    }
}
interface Communicator{
    void sendData(Bundle bundle);
}
