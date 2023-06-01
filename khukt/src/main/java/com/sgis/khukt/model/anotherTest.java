
package com.sgis.khukt.model;
import org.springframework.beans.factory.annotation.Value;

public class anotherTest {
    @Value("${definehostname}")
    static String hostname;

    @Value("${rptdesignpath}")
    static String rptdesignpath;

    public static void main(String[] args) {
        System.out.println(rptdesignpath);
    }
}
