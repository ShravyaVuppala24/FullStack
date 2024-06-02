package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class SingletonTest extends TestCase {

    @Test
    public void testGetObject() {
        Singleton singleton = Singleton.getObject();
        System.out.println(singleton.toString());

        Singleton singleton1 = Singleton.getObject();
        System.out.println(singleton1.toString());
    }
}