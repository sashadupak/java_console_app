public class Painter extends AbsPerson {
    public Painter(String name, String skill){
        super(name, skill);
    }
    @Override
    public void Sound(String msg) {
        System.out.println(name + " sound: " + msg);
        Wait.delay();
    }
}
