public class Agent {
    protected String type;
    protected int x;
    protected int y;
    public Agent(String type){
        this.type=type;
        x = -1;
        y = -1;
    }

    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
}