public class Main {
    public static void main(String[] args) {
        MyCalendar<DataTest> calendar = new MyCalendar<>(DataTest.class);
        System.out.println("Hello world");
        DataTest data = new DataTest();
        data.setSteps(1250);
        data.setDistance(23);
        data.setSpeed(15.8);

    }
}