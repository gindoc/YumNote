package com.cwenhui.yumnote.modules.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cwenhui.domain.model.NoteBook;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityMainBinding;
import com.cwenhui.yumnote.modules.notes.NotesActivity;
import com.cwenhui.yumnote.utils.StringUtils;
import com.cwenhui.yumnote.utils.ToastUtil;
import com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView.ItemClickListener;
import com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView.Section;
import com.cwenhui.yumnote.widgets.SectionedExpandableGridRecyclerView.SectionedExpandableLayoutHelper;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract
        .View, ItemClickListener {
    private final String[] OPERATIONS = {"删除", "重命名"};

    @Inject
    MainPresenter mPresenter;
    private ActivityMainBinding mBinding;
    private SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolBar();
        setupNavigation();
        addNoteBook();

        sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(this,
                mBinding.contentMain.recyclerView, this, 1);
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
        processStatusBar(mBinding.contentMain.toolbar, true, false);
    }

    @Override
    protected MainPresenter createPresent() {
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
        for (NoteBook noteBook : data) {
            if (noteBook.getSubNoteBooks() != null && noteBook.getSubNoteBooks().size() > 0) {
                sectionedExpandableLayoutHelper.addSection(noteBook,
                        (ArrayList<NoteBook>) noteBook.getSubNoteBooks());
            }else {
                sectionedExpandableLayoutHelper.addSection(noteBook, null);
            }
        }
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override
    public void addSuccessful(NoteBook book) {
        sectionedExpandableLayoutHelper.addSection(book, null);
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override
    public void deleteSuccessful(NoteBook noteBook) {
        sectionedExpandableLayoutHelper.removeSection(noteBook.getName());
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override
    public void renameSuccessful(String oldName, NoteBook noteBook) {
        sectionedExpandableLayoutHelper.rename(oldName, noteBook);
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override
    public void itemClicked(NoteBook item) {

    }

    @Override
    public void itemClicked(Section section) {
        startActivity(NotesActivity.getStartIntent(this, section.getNoteBook()));
    }

    @Override
    public void itemLongClicked(final Section section) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setItems(OPERATIONS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                ToastUtil.show(MainActivity.this, OPERATIONS[i]);
                        switch (i) {
                            case 0:
                                mPresenter.deleteNoteBook(section.getNoteBook());
                                break;
                            case 1:
                                rename(section);
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void rename(final Section section) {
        final EditText et = new EditText(this);
        et.setHint("请输入新笔记本名");
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("重命名")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input = et.getText().toString();
                        if (StringUtils.isSpace(input)) {
                            ToastUtil.show(MainActivity.this,"笔记本名不能为空");
                            dialogInterface.dismiss();
                            return;
                        }
                        try {
                            Section clonedSection = section.clone();
                            mPresenter.renameNoteBook(clonedSection, input);
                            dialogInterface.dismiss();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();
    }

    @Override
    public void itemLongClicked(NoteBook item) {

    }

    private void addNoteBook() {
        final EditText et = new EditText(this);
        et.setHint("请输入笔记本名");
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("新建笔记本")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input = et.getText().toString();
                        if (StringUtils.isSpace(input)) {
                            ToastUtil.show(MainActivity.this,"笔记本名不能为空");
                            dialogInterface.dismiss();
                            return;
                        }
                        mPresenter.addNoteBook(input);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        final AlertDialog ad = builder.create();
        mBinding.contentMain.fabAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.show();
            }
        });
    }
}
