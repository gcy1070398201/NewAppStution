package com.example.min.newappstution.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by min on 2017/9/23.
 */
public class RemarkText extends AppCompatActivity{
    //
//    @Override : 1、检查是否正确的重写了父类中的方法。2、标明代码，这是一个重写的方法。


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //元注解 包括（Retention，Target，Inherited，Documented）

//    @Retention ：注解保留的生命周期(RUNTIME,CLASS,SOURCE)
//    @Target：注解对象的作用范围。(类、接口、枚举、注解类型、类成员（构造方法、方法、成员变量）)
//    @Inherited ：@Inherited标明所修饰的注解，在所作用的类上，是否可以被继承。
//    @Documented ：如其名，javadoc的工具文档化，一般不关心。

    //自定义注解
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public @interface Text{

        int value() default -1;
    }

}
