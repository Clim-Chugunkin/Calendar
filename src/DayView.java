public class DayView<T> {
    private Key key;
    private T data ;
    private boolean isActive = true;

    public void setKey(Key key){
        this.key = key;
    }

    public Key getKey(){
        return this.key;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void resetActive(){
        isActive = false;
    }
    public boolean isActive(){
        return isActive;
    }
}
