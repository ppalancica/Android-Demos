package com.idevwizard.sharedprefsobjectserializationdemo;

public class Foo<T> {

    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
