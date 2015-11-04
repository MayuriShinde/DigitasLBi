package com.example.digitaslbi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class activityAttachFragment extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animal_family);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			switch (pos) {

			case 0:
				return Fragment_AnimalFav
						.newInstance("FirstFragment, Instance 1");
			case 1:
				return Fragment_Animalother
						.newInstance("SecondFragment, Instance 1");
			default:
				return Fragment_AnimalFav
						.newInstance("FirstFragment, Instance 1");

			}

		}

		@Override
		public int getCount() {
			return 2;
		}
	}
}
