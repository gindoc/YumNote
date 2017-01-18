package com.cwenhui.data.dagger;

import com.cwenhui.data.repository.impl.SampleDataRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: GIndoc on 2017/1/18 21:29
 * email : 735506583@qq.com
 * FOR   :
 */
@Singleton
@Component(modules = {DataModule.class})
public interface DataComponent {
    void inject(SampleDataRepository repository);
//    void inject(BaseRepository baseRepository);
}
