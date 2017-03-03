package com.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class MyClass extends AbstractProcessor{

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Test.class.getCanonicalName());
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements=roundEnvironment.getElementsAnnotatedWith(Test.class);
        for(Element element:elements){
            if(element.getKind()!= ElementKind.CLASS){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,"only support class");
            }
        }
        MethodSpec main = MethodSpec.methodBuilder("main") //main代表方法名
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)//Modifier 修饰的关键字
                .returns(void.class)
                .addStatement("$T.out.println($S)", System.class,"Hello World")//添加代码，这里$T和$S后面会讲，这里其实就是添加了System,out.println("Hello World");
                .build();
        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")//HelloWorld是类名
                .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
                .addMethod(main)  //在类中添加方法
                .build();
        JavaFile javaFile = JavaFile.builder("com.example.helloworld", typeSpec)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
