package testclasses;

public class Books {
    private String title;
    private boolean inPrint;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isInPrint() {
        return inPrint;
    }

    public void setInPrint(boolean inPrint) {
        this.inPrint = inPrint;
    }

    @Override
    public String toString() {
        String result = "Book title: " + title;
        if (inPrint) {
            result += "\nStatus: is in print";
        } else {
            result += "\nStatus: is not in print";
        }

        return result;
    }
}
