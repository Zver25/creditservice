package com.mfo.creditservice.payloads;

public class CreateRequestRequest {

    private long sum; // Сумма с копейками
    private int interval; // Количество месяцев
    private long userId; // Идентификатор пользователя

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
