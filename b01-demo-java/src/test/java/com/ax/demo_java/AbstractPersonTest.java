package com.ax.demo_java;

import com.ax.demo_java.testinterface.AbstractPersonImpl;
import org.junit.jupiter.api.Test;

public class AbstractPersonTest {


    @Test
    public void test1() {
        AbstractPersonImpl person = new AbstractPersonImpl();

        person.mothod1();;
        person.name = "jim";
        System.out.println(" person.name2 = " +  person.name2);

        person.mothod5();;
        person.mothod6();;

    }
}