package com.blueprint.managers;

public interface Manager {
    public <T> T create(T data);
    public <T> T update(T data);
    public <T> void delete (T data);
}
