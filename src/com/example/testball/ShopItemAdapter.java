package com.example.testball;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ShopItemAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private ImageView ballImage;
	private ViewGroup animationLayer;
	
	
	public ShopItemAdapter(Context context, List<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.listitem_layout, null);
			viewHolder.titleText = (TextView)convertView.findViewById(R.id.listitem_title);
			viewHolder.buyBtn = (Button)convertView.findViewById(R.id.listitem_btn);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.titleText.setText(list.get(position));
		viewHolder.buyBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int[] startLocation = new int[2];
				v.getLocationInWindow(startLocation);
				ballImage = new ImageView(context);
				ballImage.setBackgroundResource(R.drawable.addshopcart_sign);
				setAnimation(ballImage,startLocation);
			}
		});
		return convertView;
	}

	private void setAnimation(final View v,int[] startLoc){
//		animationLayer = null;
		animationLayer = createAnimationLayer();
		animationLayer.addView(v);
		final View view = addBallToLayer(v,startLoc);
		int[] endLoc = ((MainActivity)context).getLocation();
		int xDelt = startLoc[0] - endLoc[0];
		int yDelt = startLoc[1] -endLoc[1];
		TranslateAnimation transformationX = new TranslateAnimation(0, -xDelt, 0, 0);
		transformationX.setInterpolator(new LinearInterpolator());
		transformationX.setRepeatCount(0);
		transformationX.setFillAfter(true);
		TranslateAnimation transformationY = new TranslateAnimation(0, 0, 0, -yDelt);
		transformationY.setInterpolator(new AccelerateInterpolator());
		transformationY.setRepeatCount(0);
		transformationY.setFillAfter(true);
		
		AnimationSet animationSet = new AnimationSet(false);
		animationSet.addAnimation(transformationX);
		animationSet.addAnimation(transformationY);
		animationSet.setDuration(800);
		animationSet.setFillAfter(true);
		view.startAnimation(animationSet);
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				v.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Log.v("mickey", "--onAnimationEnd---isShown:" + v.isShown());
				view.clearAnimation();
				v.setVisibility(View.GONE);
			}
		});
	}
	
	private ViewGroup createAnimationLayer(){
		ViewGroup rootView = (ViewGroup) ((Activity)context).getWindow().getDecorView();
		LinearLayout animationLayer = new LinearLayout(context);
		LinearLayout.LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		animationLayer.setLayoutParams(lParams);
		rootView.addView(animationLayer);
		return animationLayer;
	}
	
	private View addBallToLayer(View view,int[] ballLoc){
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lParams.leftMargin = ballLoc[0];
		lParams.topMargin = ballLoc[1];
		view.setLayoutParams(lParams);
		return view;
	}
	
	static class ViewHolder{
		TextView titleText;
		Button buyBtn;
		
	}
}
