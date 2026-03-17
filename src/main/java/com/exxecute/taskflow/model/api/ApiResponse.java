package com.exxecute.taskflow.model.api;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private final LocalDateTime date;
    private final long count;
    private final T data;

    public ApiResponse(T data, long count) {
        this.date = LocalDateTime.now();
        this.count = count;
        this.data = data;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public long getCount() {
        return this.count;
    }

    public T getData() {
        return this.data;
    }
}
