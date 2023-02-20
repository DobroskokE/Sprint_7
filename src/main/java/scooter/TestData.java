package scooter;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {
    static int year = (int) (Math.random() * 2023);
    static int month = (int) (Math.random() * 12);
    static int day = (int) (Math.random() * 31);
    public static String randomFirstName = RandomStringUtils.randomAlphabetic(10);
    public static String randomLastName = RandomStringUtils.randomAlphabetic(10);
    public static String randomAddress = RandomStringUtils.randomAlphabetic(30);
    public static int randomMetroStation = (int) (Math.random() * 10);
    public static String randomPhone = RandomStringUtils.randomAlphabetic(10);
    public static int randomRentTime = (int) (Math.random() * 10);
    public static String randomDeliveryDate = year + "-" + month + "-" + day;
    public static String randomComment = RandomStringUtils.randomAlphabetic(50);


}
