package com.cwenhui.yumnote.modules.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityMainBinding;
import com.cwenhui.yumnote.widgets.recyclerview.SingleTypeAdapter;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract
        .View {

    @Inject
    MainPresenter mPresenter;
    private ActivityMainBinding mBinding;
    private SingleTypeAdapter<MainViewModel> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolBar();
        setupNavigation();
        processStatusBar(mBinding.contentMain.toolbar, true, false);

        adapter = new SingleTypeAdapter(this, R.layout.item_activity_main);
        mBinding.contentMain.recyclerView.setAdapter(adapter);

        mPresenter.requestNoteBooks();
    }

    private void setupNavigation() {
        mBinding.navigation.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mBinding.drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initToolBar() {
        mBinding.contentMain.toolbar.setTitle("个人笔记");
        mBinding.contentMain.toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mBinding.contentMain.toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected MainPresenter createPresent() {
        return mPresenter;
    }

    public void test(View view) {
        mPresenter.test();
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mBinding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void loadNoteBookList(List<NoteBook> data) {
//        adapter.addAll(data);
    }
}
