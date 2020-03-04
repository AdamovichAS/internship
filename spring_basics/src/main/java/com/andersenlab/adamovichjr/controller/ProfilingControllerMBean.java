package com.andersenlab.adamovichjr.controller;

//прописываем методы, которые хотим чтобы были доступны в jmx console(jvisualvm)
public interface ProfilingControllerMBean {
    void setEnabled(boolean enabled);
}
