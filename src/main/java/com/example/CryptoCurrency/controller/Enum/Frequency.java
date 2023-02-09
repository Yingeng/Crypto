package com.example.CryptoCurrency.controller.Enum;

public enum Frequency {
    MIN1(1),
    MIN5(5),
    MIN10(10),
    DAY1(1440);

    private Integer num;

    Frequency(Integer num) {
        this.num = num;
    }

    public Integer getValue() {
        return this.num;
    }

}
