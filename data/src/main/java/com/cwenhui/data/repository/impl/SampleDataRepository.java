package com.cwenhui.data.repository.impl;

import com.cwenhui.data.BaseRepository;
import com.cwenhui.domain.repository.SampleRepository;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.HttpUrl;
import timber.log.Timber;

/**
 * Author: GIndoc on 2017/1/19 0:10
 * email : 735506583@qq.com
 * FOR   :
 */

public class SampleDataRepository extends BaseRepository implements SampleRepository {

    @Inject
    @Named("api_url")
    HttpUrl url;

    @Inject
    public SampleDataRepository() {
        super();
    }

    @Override
    public void test() {
        Timber.e(url.toString());
    }
}
