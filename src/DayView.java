public class DayView<T> {

    private T data ;
    private boolean isActive = true;
    Key key;

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
