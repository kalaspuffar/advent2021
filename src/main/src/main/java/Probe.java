public class Probe {
    private int x = 0;
    private int y = 0;
    private int vx;
    private int vy;
    private int highestY = 0;

    private int targetLeft;
    private int targetTop;
    private int targetRight;
    private int targetBottom;


    public Probe(int vx, int vy, int targetLeft, int targetTop, int targetRight, int targetBottom) {
        this.vx = vx;
        this.vy = vy;

        this.targetLeft = targetLeft;
        this.targetTop = targetTop;
        this.targetRight = targetRight;
        this.targetBottom = targetBottom;
    }

    /*
The probe's x position increases by its x velocity.
The probe's y position increases by its y velocity.
Due to drag, the probe's x velocity changes by 1 toward the value 0; that is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0, or does not change if it is already 0.
Due to gravity, the probe's y velocity decreases by 1.
     */
    public void step() {
        this.x += vx;
        this.y += vy;
        if (this.vx > 0) {
            this.vx--;
        } else if (this.vx < 0) {
            this.vx++;
        }
        this.vy--;
        if (highestY < y) {
            highestY = y;
        }
    }

    public int getHighestY() {
        return highestY;
    }

    public boolean aboveBottomTarget() {
        return y > targetBottom;
    }

    public boolean withinTarget() {
        return x >= targetLeft && x <= targetRight && y >= targetBottom && y <= targetTop;
    }

    @Override
    public String toString() {
        return "Probe{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                '}';
    }
}
