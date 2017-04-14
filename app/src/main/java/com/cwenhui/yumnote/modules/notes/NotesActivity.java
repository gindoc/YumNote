package com.cwenhui.yumnote.modules.notes;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityNotesBinding;
import com.cwenhui.yumnote.utils.ToastUtil;
import com.cwenhui.yumnote.widgets.recyclerview.BaseViewAdapter;
import com.cwenhui.yumnote.widgets.recyclerview.SingleTypeAdapter;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/4/14 19:33
 * email : 735506583@qq.com
 * FOR   :
 */
public class NotesActivity extends BaseActivity<NotesContract.View, NotesPresenter>
        implements NotesContract.View {
    private static final String NOTEBOOK = "NOTEBOOK";
    private ActivityNotesBinding mBinding;

    @Inject
    NotesPresenter mPresenter;
    SingleTypeAdapter<Note> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        NoteBook noteBook = (NoteBook) getIntent().getSerializableExtra(NOTEBOOK);
        initToolbar(noteBook.getName());
        initRecyclerView();
        mPresenter.requestNotes(noteBook.getId());
    }

    private void initRecyclerView() {
        adapter = new SingleTypeAdapter<Note>(this, R.layout.item_activity_notes);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        adapter.setPresenter(new AdapterPresenter());
    }

    private void initToolbar(String name) {
        mBinding.toolbar.setTitleTextColor(Color.WHITE);
        mBinding.toolbar.setTitle(name);
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mBinding.toolbar);
        processStatusBar(mBinding.toolbar, true, false);
    }

    @Override
    protected NotesPresenter createPresent() {
        return mPresenter;
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
        getMenuInflater().inflate(R.menu.menu_notes, menu);
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

    public static Intent getStartIntent(Context context, NoteBook noteBook) {
        Intent intent = new Intent(context, NotesActivity.class);
        intent.putExtra(NOTEBOOK, noteBook);
        return intent;
    }

    @Override
    public void loadNotes(List<Note> notes) {
        adapter.addAll(notes);
    }


    public class AdapterPresenter implements BaseViewAdapter.Presenter {
        public void onItemClick(Note note) {
            ToastUtil.show(NotesActivity.this, note.getNoteTitle());
        }
    }

}
