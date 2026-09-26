public class Movie {
    private String title;
    private int duration;
    private double price;
    private boolean nowShowing;

    public Movie(String title, int duration, double price, boolean nowShowing) {
        this.title = title;
        this.duration = duration;
        this.price = price;
        this.nowShowing = nowShowing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public boolean isNowShowing() {
        return nowShowing;
    }

    public void setNowShowing(boolean nowShowing) {
        this.nowShowing = nowShowing;
    }

    public void printDetails() {
        System.out.println(title + " | " + duration + " min | Rs " + price
                + " | " + (nowShowing ? "Now Showing" : "Not Showing"));
    }
}