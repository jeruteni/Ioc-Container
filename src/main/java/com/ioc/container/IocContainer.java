package com.ioc.container;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IocContainer {
    private Map<String, Object> beans = new HashMap();

    final static Logger logger = Logger.getLogger(IocContainer.class);

    private PackageScanner packageScanner;

    public IocContainer(String basePackage) {
        packageScanner = new PackageScanner(basePackage);
        createBeans();
    }

    private void createBeans() {
        try {
            List<Class> classes = packageScanner.getAnnotationClasses();
        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.fillInStackTrace());
        }
    }

}
