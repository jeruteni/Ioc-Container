package com.ioc;

import com.ioc.annotation.Component;
import com.ioc.container.PackageScanner;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PackageScannerTest {
    private PackageScanner packageScanner = new PackageScanner("com.ioc");

    @Test
    public void getAnnotatedClasses() throws IOException, ClassNotFoundException {
        List<Class> classes = packageScanner.getAnnotationClasses();
        Assert.assertEquals(1,classes.size());
    }


}

@Component
class Service {

}
