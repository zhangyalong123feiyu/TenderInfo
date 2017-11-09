package com.zyl_android.tenderinfo.project.builder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zyl_android.tenderinfo.R;

import java.util.List;


public class HistoryTagShowView extends ViewGroup {

	private Context context;
	
	private int viewWidth;
	private int viewHeight;
	
	//�������
	private int maxRows = 3;
	
	//��� dp
	private int marignWidth = 11;
	private int marignHeight = 11;

	private int viewCount = 0;

	private int childViewHeight = 0;
	
	private int currentRows = 0;
	
	//�������
	private int maxViewCount = 50;
	
	private int layoutId = R.layout.searchresult_item;
	
	//��ͬ���͵����ݿ����ж������� ������tagview��˵ ֻ��Ҫ����String
	public void setDataList(final List<String> dataList){
		//��������Ѿ��е�view ��������µ�view
		removeAllViews();
		//ÿ�ζ�Ҫ�������init���� ���²���currentRows��ֵ currentRows��Ҫ��0 ��Ȼ�ᵼ��currentRows�ۼ�
		init = true;
		currentRows=0;

		//ָ����һ�������ֵ
		viewCount = dataList.size()>=maxViewCount? maxViewCount : dataList.size();
		for(int i=0 ; i<viewCount ; i++){
			View v = LayoutInflater.from(context).inflate(layoutId, null, false);
			TextView txt = (TextView)v.findViewById(R.id.v_tag_show_txt);
			txt.setText(dataList.get(i));

			final int pos = i;
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//�ص������λ��
					if(onClickTagShowViewListener!=null){
						onClickTagShowViewListener.onTagShowViewClick(pos);
					}
				}
			});
			v.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					if(onClickTagShowViewListener!=null){
						onClickTagShowViewListener.onTagShowViewLongClick(pos);
					}
					return true;
				}
			});
			//׼������view�Ŀ��
			int w = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			int h = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			v.measure(w, h);

			childViewHeight = v.getMeasuredHeight();
			addView(v);
		}
	}
	
	public HistoryTagShowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		//��dpתΪpx
		marignWidth = dip2px(context, marignWidth);
		marignHeight = dip2px(context, marignHeight);
	}

	public static int dip2px(Context context, float dipValue) {
		float fontScale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * fontScale + 0.5f);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//����setDataList()�����е�addView��ִ����Ϻ� �������θ÷���
		viewWidth = getMeasuredWidth();
		viewHeight = getMeasuredHeight();
		//�õ��м���
		updateCurrentRows();
		//���ݼ������ø߶�
		int meaH = 0;
		if(currentRows == 0){
			//һ�����ݶ�û�� ��Ĭ�ϵľ��� dpתpx
			meaH = dip2px(context, 80);
		}else{
			//��currentRows�еľ��� currentRows+1���ļ��
			meaH = currentRows * childViewHeight + marignHeight * (currentRows + 1);
		}
		
		//�������ø߶�
		setMeasuredDimension(viewWidth, meaH);
	}
	
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		int childCount = getChildCount();
		int needleWidth = marignWidth;
		int needleHeight = marignHeight;
		
		int cnt = 0;
		for(int i=0 ; i<childCount ; i++){
			View v = getChildAt(i);
			if(needleWidth + v.getMeasuredWidth() + marignWidth>viewWidth){
				needleHeight += childViewHeight + marignHeight;
				needleWidth = marignWidth;
				cnt++;
				if(cnt>=maxRows){
					return;
				}
			}
			v.layout(needleWidth, needleHeight, needleWidth+v.getMeasuredWidth(), needleHeight+childViewHeight);
			needleWidth += marignWidth + v.getMeasuredWidth();
		}
	}
	
	
	//ִֻ��һ�� ȷ������
	private boolean init = true;
	private void updateCurrentRows(){
		if(!init){
			return;
		}
		init = false;
		
		int childCount = getChildCount();
		int needleWidth = marignWidth;
		int needleHeight = marignHeight;
		if(childCount>0){
			currentRows++;
		}
		for(int i=0 ; i<childCount ; i++){
			View v = getChildAt(i);
			//��ʱ������� �߶���һ�µ� ���ö�β���
			int childViewWidth = v.getMeasuredWidth();
			
			//�������� ��Խ��������� ��������� currentRows���ж��߶ȵĹؼ� �������
			if(needleWidth + childViewWidth + marignWidth > viewWidth){
				needleHeight += childViewHeight + marignHeight;
				needleWidth = marignWidth;
				currentRows++;
				if(currentRows>=maxRows){
					return;
				}
			}
//			v.layout(needleWidth, needleHeight, needleWidth + childViewWidth, needleHeight+childViewHeight);
			needleWidth += marignWidth + childViewWidth;
		}
	}
	
	private OnClickTagShowViewListener onClickTagShowViewListener;
	
	public interface OnClickTagShowViewListener{
		void onTagShowViewClick(int pos);
		void onTagShowViewLongClick(int pos);
	}
	public OnClickTagShowViewListener getOnClickTagShowViewListener() {
		return onClickTagShowViewListener;
	}

	public void setOnClickTagShowViewListener(OnClickTagShowViewListener onClickTagShowViewListener) {
		this.onClickTagShowViewListener = onClickTagShowViewListener;
	}

}
