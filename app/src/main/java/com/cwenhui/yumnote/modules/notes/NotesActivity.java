package com.cwenhui.yumnote.modules.notes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cwenhui.domain.model.Note;
import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityNotesBinding;
import com.cwenhui.yumnote.modules.note.NoteActivity;
import com.cwenhui.yumnote.widgets.recyclerview.BaseViewAdapter;
import com.cwenhui.yumnote.widgets.recyclerview.BindingViewHolder;
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
    private static final String NOTE = "NOTE";
    private static final String NOTEBOOK = "NOTEBOOK";
    private static final int TO_VIEW_PAGE = 99;
    private static final String POSITION = "POSITION";
    private ActivityNotesBinding mBinding;
    private int BOOK_ID;

    @Inject
    NotesPresenter mPresenter;
    SingleTypeAdapter<Note> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        mBinding.setView(this);
        NoteBook noteBook = (NoteBook) getIntent().getSerializableExtra(NOTEBOOK);
        BOOK_ID = noteBook.getId();
        initToolbar(noteBook.getName());
        initRecyclerView();
        mPresenter.requestNotes(noteBook.getId());
    }

    private void initRecyclerView() {
        adapter = new SingleTypeAdapter<Note>(this, R.layout.item_activity_notes);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        adapter.setDecorator(new AdapterDecorator());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == TO_VIEW_PAGE) {
            int pos = data.getIntExtra(POSITION, -1);
            adapter.remove(pos);
            adapter.add(pos, (Note) data.getSerializableExtra(NOTE));
            adapter.notifyDataSetChanged();
        }
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

    @Override
    public void deleteSuccessful(int pos) {
        adapter.remove(pos);
        adapter.notifyDataSetChanged();
    }

    public void addNote() {

    }

    public class AdapterDecorator implements BaseViewAdapter.Decorator{

        @Override
        public void decorator(BindingViewHolder holder, final int position, int viewType) {
            holder.getBinding().getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this)
                            .setTitle("确定删除该笔记，删除后不可恢复？")
                            .setPositiveButton("狠心删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mPresenter.deleteNote(BOOK_ID, adapter.get(position).getNoteId(), position);
                                }
                            })
                            .setNegativeButton("算了吧", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.show();
                    return true;
                }
            });
            holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(NoteActivity.getStartIntent(NotesActivity.this,
                            position, adapter.get(position)), TO_VIEW_PAGE);
                }
            });
        }
    }


}
