public class Key {
    private int month;
    private int day;

    public Key(int month,int day){
        this.day = day;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    @Override
    public boolean equals(Object aKey){
        Key k = (Key) aKey;
        return (this.month == k.getMonth()) && (this.day == k.getDay());
    }
    @Override
    public int hashCode(){
        return 707;
    }
}
