package com.cwenhui.domain.usecase;

import com.cwenhui.domain.repository.SampleRepository;

import javax.inject.Inject;

/**
 * Author: GIndoc on 2017/1/18 23:35
 * email : 735506583@qq.com
 * FOR   :
 */

public class TestCase {
    private SampleRepository sampleRepository;

    @Inject
    public TestCase(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public void test() {
        sampleRepository.test();
    }
}
