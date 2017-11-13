package com.zyl_android.tenderinfo.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyl_android.tenderinfo.R;
import com.zyl_android.tenderinfo.mvp.presenter.SearchActivityPresenter;
import com.zyl_android.tenderinfo.mvp.view.SearchActivityView;
import com.zyl_android.tenderinfo.project.builder.HistoryTagShowView;
import com.zyl_android.tenderinfo.project.ui.baseui.BaseActivity;
import com.zyl_android.tenderinfo.project.utils.SearchHistoryUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bibinet on 2017-11-9.
 */

public class SearchActivity extends BaseActivity implements SearchActivityView{
    @BindView(R.id.act_search_exit)
    ImageView actSearchExit;
    @BindView(R.id.searchEdit)
    EditText searchEdit;
    @BindView(R.id.doSearch)
    TextView doSearch;
    @BindView(R.id.hotOne)
    TextView hotOne;
    @BindView(R.id.hotTwo)
    TextView hotTwo;
    @BindView(R.id.hotThree)
    TextView hotThree;
    @BindView(R.id.hotFour)
    TextView hotFour;
    @BindView(R.id.hotFive)
    TextView hotFive;
    @BindView(R.id.hotSix)
    TextView hotSix;
    @BindView(R.id.act_search_delete_history)
    ImageView actSearchDeleteHistory;
    @BindView(R.id.act_search_history_tag)
    HistoryTagShowView actSearchHistoryTag;
    @BindView(R.id.act_search_not_history)
    TextView actSearchNotHistory;
    private SearchActivityPresenter searchActivityPresenter;

    @Override
    protected void onrefresh() {

    }

    @Override
    protected void onloadMore() {

    }

    @Override
    protected int getChildlayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        titleLayout.setVisibility(View.GONE);
        searchActivityPresenter=new SearchActivityPresenter(this);
    }

    @Override
    protected void loadData(boolean isLoadMore) {
        searchActivityPresenter.getHotWords();
    }
    @OnClick({R.id.act_search_exit, R.id.doSearch, R.id.hotOne, R.id.hotTwo, R.id.hotThree, R.id.hotFour, R.id.hotFive, R.id.hotSix, R.id.act_search_delete_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_search_exit:
                finish();
                break;
            case R.id.doSearch:
                String editContent = searchEdit.getText().toString().trim();
                if (TextUtils.isEmpty(editContent)) {
                    toast("请确保您要收索的内容不为空");
                		}else {
                    hotWordsSearch(editContent);
                }
                break;
            case R.id.hotOne:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.hotTwo:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.hotThree:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.hotFour:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.hotFive:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.hotSix:
                hotWordsSearch(hotOne.getText().toString());
                break;
            case R.id.act_search_delete_history:
                break;
        }
    }
    @Override
    public void onGetHotWordsSucess(List<String> hotMsg) {
        TextView[] tvHot = new TextView[6];
        tvHot[0] = hotOne;
        tvHot[1] = hotTwo;
        tvHot[2] = hotThree;
        tvHot[3] = hotFour;
        tvHot[4] = hotFive;
        tvHot[5] = hotSix;

        for(int i=0 ; i<tvHot.length ; i++){
            String hot = hotMsg.get(i);
            if(!hot.equals("")){
                tvHot[i].setText(hot);
                tvHot[i].setVisibility(View.VISIBLE);
            }else{
                tvHot[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onGetHotWordsFailed(String msg) {

    }
    private void hotWordsSearch(String content) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("content", content);
        log("TAG","传递内容+"+content);
        startActivity(intent);
        //写入数据
        SearchHistoryUtils.writeData(this, content);
//        searchDataList.add(0, content);
//        updateSearchData();
    }

}
