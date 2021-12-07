public class Fish {
    int daysToReproduce = 8;

    public void incDay() {
        daysToReproduce--;
    }

    public void init(int days) {
        daysToReproduce = days;
    }

    public Fish hasChild() {
        if (daysToReproduce == 0) {
            daysToReproduce = 7;
            return new Fish();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "days=" + daysToReproduce +
                '}';
    }
}
