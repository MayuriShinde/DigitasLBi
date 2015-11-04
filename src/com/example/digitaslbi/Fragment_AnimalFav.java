package com.example.digitaslbi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_AnimalFav extends Fragment {
	List<Animal_Feature_class> lsCustomerDetails;
	ListAdapter adpter;
	ListView ls;
	Animal_Feature_class model;
	DBHelper db;
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragmentanimalfav, container, false);
		db = new DBHelper(getActivity());
		display();
		return v;
	}

	public static Fragment_AnimalFav newInstance(String text) {

		Fragment_AnimalFav f = new Fragment_AnimalFav();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}

	public void display() {
		lsCustomerDetails = new ArrayList<Animal_Feature_class>();
		lsCustomerDetails = db.getCustomerDetail();
		adpter = new ListAdapter(getActivity(), lsCustomerDetails);
		ls = (ListView) v.findViewById(R.id.listView1);

		ls.setAdapter(adpter);

	}

}
