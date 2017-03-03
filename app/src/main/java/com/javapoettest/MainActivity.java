package com.javapoettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Test;

import java.lang.reflect.Method;

@Test("测试")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            Class clazz=Class.forName("com.example.helloworld.HelloWorld");
            Method method=clazz.getMethod("main");
            method.invoke(clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
