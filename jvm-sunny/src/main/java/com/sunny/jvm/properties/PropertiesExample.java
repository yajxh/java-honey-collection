package com.sunny.jvm.properties;

import java.io.*;
import java.util.Properties;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 18:10 <br>
 * @see com.sunny.jvm.properties <br>
 */
public class PropertiesExample {
    public static void main(String[] s) {

        /////////////////////////saving properties into example.properties file/////////////////////////
        try (OutputStream out = new FileOutputStream("propertiesExample.properties")) {
            Properties properties = new Properties();
            properties.setProperty("name", "sunnyCodes");
            properties.setProperty("article", "JavaProperties");
            properties.setProperty("version", "1.0");
            properties.setProperty("ide", "idea");
            properties.store(out, "This is a sample for java properties");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////Reading properties////////////////////////////////////////////////////
        try (InputStream in = new FileInputStream("propertiesExample.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            System.out.println("####Properties.getProperty usage####");
            System.out.println(prop.getProperty("name"));
            System.out.println();

            System.out.println("####Properties.stringPropertyNames usage####");
            for (String property : prop.stringPropertyNames()) {
                String value = prop.getProperty(property);
                System.out.println(property + "=" + value);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();

        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////writing and reading fromxml///////////////////////////////////////////////
        try (OutputStream out = new FileOutputStream("propertiesExample.xml")) {
            Properties properties = new Properties();
            properties.setProperty("name", "sunnyCodes");
            properties.setProperty("article", "JavaProperties");
            properties.setProperty("version", "1.0");
            properties.setProperty("ide", "idea");
            properties.storeToXML(out,
                    "This is how we can have properties as xml");

        } catch (IOException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////Reading properties from xml////////////////////////////////////////////////////
        try (InputStream in = new FileInputStream("propertiesExample.xml")) {
            Properties prop = new Properties();
            prop.loadFromXML(in);

            System.out.println("####Properties.load from xml usage####");
            for (String property : prop.stringPropertyNames()) {
                String value = prop.getProperty(property);
                System.out.println(property + "=" + value);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();

        /////////////////////////////////////////////////////////////////////////////////////////////////

    }
}
