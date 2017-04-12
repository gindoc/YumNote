package com.cwenhui.yumnote.dagger.modules;

import com.cwenhui.data.remote.repository.impl.NoteBookDataRepository;
import com.cwenhui.data.remote.repository.impl.SampleDataRepository;
import com.cwenhui.data.remote.repository.impl.UserDataRepository;
import com.cwenhui.domain.repository.NoteBookRepository;
import com.cwenhui.domain.repository.SampleRepository;
import com.cwenhui.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 作者: GIndoc
 * 日期: 2017/1/19 10:00
 * 作用:
 */
@Module
public class RepositoryModule {

    @Provides
    public SampleRepository providesSampleRepository(SampleDataRepository repository) {
        return repository;
    }

    @Provides
    public NoteBookRepository providesNoteBookRepository(NoteBookDataRepository repository) {
        return repository;
    }

    @Provides
    public UserRepository providesUserRepository(UserDataRepository repository) {
        return repository;
    }
}
