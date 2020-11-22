package com.batch.springbatch.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class NoOpItemWriter implements ItemWriter {

    @Override
    public void write(List items) throws Exception {
        // ignore
    }
}
