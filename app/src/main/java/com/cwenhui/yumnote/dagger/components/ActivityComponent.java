package com.cwenhui.yumnote.dagger.components;

import com.cwenhui.yumnote.modules.guide.GuideActivity;
import com.cwenhui.yumnote.modules.main.MainActivity;
import com.cwenhui.yumnote.dagger.modules.ActivityModule;
import com.cwenhui.yumnote.dagger.modules.FragmentModule;
import com.cwenhui.yumnote.modules.note.NoteActivity;
import com.cwenhui.yumnote.modules.notes.NotesActivity;
import com.cwenhui.yumnote.modules.welcome.WelcomeActivity;

import dagger.Subcomponent;

/**
 * 作者: 陈文辉
 * 日期: 2017/1/17 23:39
 * 作用:
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    FragmentComponent plus(FragmentModule module);


    void inject(GuideActivity guideActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(NotesActivity notesActivity);

    void inject(NoteActivity noteActivity);
}
