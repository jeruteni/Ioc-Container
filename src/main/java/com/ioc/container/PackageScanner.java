package com.ioc.container;

import com.ioc.annotation.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PackageScanner {
    private String basePackages;


    public PackageScanner(String basePackages) {
        this.basePackages = basePackages;

    }

    public List<Class> getAnnotationClasses() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = basePackages.replace(".", "/");
        Enumeration<URL> enumeration = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (enumeration.hasMoreElements()) {
            URL resource = enumeration.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class> classes = new ArrayList<>();
        for (File file : dirs) {
            classes.addAll(findClasses(file, basePackages));
        }

        return classes;
    }

    private List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (clazz.isAnnotationPresent(Component.class)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }
}
