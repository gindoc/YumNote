package com.cwenhui.yumnote.modules.editor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.cwenhui.domain.model.Note;
import com.cwenhui.yumnote.R;
import com.cwenhui.yumnote.base.BaseActivity;
import com.cwenhui.yumnote.databinding.ActivityNoteEditBinding;
import com.cwenhui.yumnote.databinding.LayoutDialogChooseColorBinding;
import com.cwenhui.yumnote.utils.ConstUtils;
import com.cwenhui.yumnote.utils.FileUtils;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author: GIndoc on 2017/4/20 14:53
 * email : 735506583@qq.com
 * FOR   :
 */

public class NoteEditActivity extends BaseActivity<NoteEditContract.View, NoteEditPresenter>
        implements NoteEditContract.View {
    private static final String NOTE = "NOTE";
    public static final int RESUME_TEXT_SIZE = 0;
    public static final int INCREASE_TEXT_SIZE = 1;
    public static final int REDUCE_TEXT_SIZE = -1;
    private static final int DEFAULT_TEXT_SIZE = 5;
    private static final String BOOK_ID = "BOOK_ID";
    private int currentTextSize = DEFAULT_TEXT_SIZE;
    private ActivityNoteEditBinding mBinding;

    @Inject
    @Named("upload_img_path")
    String UPLOAD_IMAGE_PATH;

    @Inject
    NoteEditPresenter mPresenter;
    private LayoutDialogChooseColorBinding binding;
    private AlertDialog dialog;
    private String mImageCapturePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_note_edit);
        mBinding.setView(this);
        binding = LayoutDialogChooseColorBinding.inflate(LayoutInflater.from(this), (ViewGroup) mBinding
                .getRoot(), false);
        binding.setView(this);
        initToolbar();
        initEditor();
    }


    private void initEditor() {
        mBinding.editor.setEditorHeight(200);
        mBinding.editor.setFontSize(DEFAULT_TEXT_SIZE);
        mBinding.editor.setEditorFontColor(Color.BLACK);
        mBinding.editor.setPadding(10, 10, 10, 10);
        mBinding.editor.setPlaceholder("请在此处开始你的笔记吧...");
        dialog = new AlertDialog.Builder(this)
                .setView(binding.getRoot())
                .create();

        Note note = (Note) getIntent().getSerializableExtra(NOTE);
        if (note != null) {
            mBinding.tvTitle.setText(note.getNoteTitle());
            mBinding.editor.setHtml(note.getNoteContent());
        }
    }

    private void initToolbar() {
        mBinding.toolbar.setTitleTextColor(Color.WHITE);
        mBinding.toolbar.setTitle("");
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_check_black_20dp);
        setSupportActionBar(mBinding.toolbar);
        processStatusBar(mBinding.toolbar, true, false);
    }

    public void changeTextColor(String color) {
        mBinding.editor.setTextColor(Color.parseColor(color));
        dialog.dismiss();
    }

    public void changeTextSize(int action) {
        if (action == RESUME_TEXT_SIZE) {
            mBinding.editor.setFontSize(DEFAULT_TEXT_SIZE);
            currentTextSize = DEFAULT_TEXT_SIZE;
        } else if (action == INCREASE_TEXT_SIZE && currentTextSize < 7) {
            mBinding.editor.setFontSize(++currentTextSize);
        } else if (action == REDUCE_TEXT_SIZE && currentTextSize > 1) {
            mBinding.editor.setFontSize(--currentTextSize);
        }
    }

    public void bold() {
        mBinding.editor.setBold();
    }

    public void strikeThrough() {
        mBinding.editor.setStrikeThrough();
    }

    public void colorText() {
        dialog.show();
    }

    public void openTextSizePanel() {
        int visible = mBinding.llTextSizePanel.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;
        mBinding.llTextSizePanel.setVisibility(visible);
    }

    public void listBulleted() {
        mBinding.editor.setBullets();
    }

    public void listNumbered() {
        mBinding.editor.setNumbers();
    }

    @Override
    protected NoteEditPresenter createPresent() {
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
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Note note = (Note) getIntent().getSerializableExtra(NOTE);
                if (note != null) {
                    mPresenter.updateNote(note.getNoteId(),
                            mBinding.tvTitle.getText().toString(),
                            mBinding.editor.getHtml());
                } else {
                    int bookId = getIntent().getIntExtra(BOOK_ID, -1);
                    mPresenter.addNote(bookId, mBinding.tvTitle.getText().toString(),
                            mBinding.editor.getHtml());
                }
                break;
            case R.id.undo:
                mBinding.editor.undo();
                break;
            case R.id.redo:
                mBinding.editor.redo();
                break;
            case R.id.rich_editor:
                int visible = mBinding.llRichEditor.getVisibility();
                if (visible == View.VISIBLE) {
                    mBinding.llRichEditor.setVisibility(View.GONE);
                    mBinding.llRichEditor.setAnimation(AnimationUtils
                            .loadAnimation(this, R.anim.zoomout));
                    item.setIcon(R.drawable.ic_sort_white_20dp);
                    mBinding.llTextSizePanel.setVisibility(View.GONE);
                } else {
                    mBinding.llRichEditor.setVisibility(View.VISIBLE);
                    mBinding.llRichEditor.setAnimation(AnimationUtils
                            .loadAnimation(this, R.anim.zoomin));
                    item.setIcon(R.drawable.ic_sort_dark_20dp);
                }
                break;
            case R.id.take_pic:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = FileUtils.createTempImage();
                mImageCapturePath = file.toString();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, ConstUtils.TAKE_PIC_REQUEST_CODE);
                break;
            case R.id.album:
                Intent intent1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media
                        .EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, ConstUtils.OPEN_ALBUM);
                break;
            case R.id.cancel:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) return;
        if (requestCode == ConstUtils.TAKE_PIC_REQUEST_CODE) {
            mPresenter.compressImg(mImageCapturePath);
        } else if (requestCode == ConstUtils.OPEN_ALBUM && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            mPresenter.compressImg(picturePath);
        }
    }

    @Override
    public void loadImg(List<String> urls) {
        if (urls == null || urls.size() == 0) return;
        mBinding.editor.insertImage(UPLOAD_IMAGE_PATH + urls.get(0), "图片显示故障了");
    }

    @Override
    public void updateNoteSuccessful() {
        Intent intent = new Intent();
        Note noteSrc = (Note) getIntent().getSerializableExtra(NOTE);
        try {
            Note noteDes = noteSrc.clone();
            noteDes.setNoteTitle(mBinding.tvTitle.getText().toString());
            noteDes.setNoteContent(mBinding.editor.getHtml());
            intent.putExtra(NOTE, noteDes);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNoteSuccessful(Note note) {
        Intent intent = new Intent();
        intent.putExtra(NOTE, note);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public static Intent getStartIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteEditActivity.class);
        intent.putExtra(NOTE, note);
        return intent;
    }

    public static Intent getStartIntent(Context context, int bookId) {
        Intent intent = new Intent(context, NoteEditActivity.class);
        intent.putExtra(BOOK_ID, bookId);
        return intent;
    }

    @Override
    protected void onDestroy() {
        mBinding.editor.removeAllViews();
        mBinding.editor.destroy();
        super.onDestroy();

    }
}
