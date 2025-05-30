public class DataTest {
    private int steps;
    private int distance;
    private double speed;
    public DataTest(){
        steps = 120;
        distance = 0;
    }
    public int getDistance(){
        return distance;
    }
    public DataTest(int steps){
        this.steps = steps;
    }
    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public double getSpeed(){
        return speed;
    }
}
