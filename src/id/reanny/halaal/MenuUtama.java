package id.reanny.halaal;

import id.reanny.halaal.R;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


public class MenuUtama extends Fragment
{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.awal_menu_utama, container, false);
         
        return rootView;
        
    }
}

