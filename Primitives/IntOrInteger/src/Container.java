public class Container {
    private int count;

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }

    public int increment(int a){
        return ++a;
    }

}
