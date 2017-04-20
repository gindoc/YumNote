package com.cwenhui.yumnote.modules.note;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.cwenhui.domain.model.Note;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityNoteBinding;
import com.trello.rxlifecycle.LifecycleTransformer;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/19 15:55
 * email : 735506583@qq.com
 * FOR   :
 */

public class NoteActivity extends BaseActivity<NoteContract.View, NotePresenter> implements NoteContract
        .View {
    private static final String NOTE = "NOTE";
    @Inject
    NotePresenter mPresenter;
    private ActivityNoteBinding mBinding;

    @Override
    protected NotePresenter createPresent() {
        return mPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_note);
        mBinding.setView(this);
        Note note = (Note) getIntent().getSerializableExtra(NOTE);
        initToolbar(note.getNoteTitle());
        initNote(note.getNoteContent());
    }

    private void initNote(String noteContent) {
        mBinding.tvNoteContent.setText(noteContent);
    }

    private void initToolbar(String noteTitle) {
        mBinding.toolbar.setTitleTextColor(Color.WHITE);
        mBinding.toolbar.setTitle(noteTitle);
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mBinding.toolbar);
        processStatusBar(mBinding.statusBar, true, false);
    }

    @Override
    public <T> LifecycleTransformer<T> getBindToLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showError(String error) {

    }

    public static Intent getStartIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NOTE, note);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
