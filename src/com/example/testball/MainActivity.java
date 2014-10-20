package com.example.testball;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private List<String> mDataList;
	private ListView listView;
	private ShopItemAdapter adapter;
	private ImageView buyCarImg;
	
	private int[] buyCarLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.shop_listview);
		buyCarImg = (ImageView)findViewById(R.id.buy_car);
		getDataList();
		adapter = new ShopItemAdapter(this, mDataList);
		listView.setAdapter(adapter);
	}

	private void getDataList(){
		mDataList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			mDataList.add("第" + i + "个title");
		}
		
	}
	
	public int[] getLocation(){
		buyCarLocation = new int[2];
		buyCarImg.getLocationInWindow(buyCarLocation);
		return buyCarLocation;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
