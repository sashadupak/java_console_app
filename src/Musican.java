public class Musican extends AbsPerson implements Skills {
    public Musican(String name, String skill){
        super(name, skill);
    }
    @Override
    public void Sound(String msg) {
        System.out.println(this.name + " sound: " + msg);
        Wait.delay();
    }
    @Override
    public void Play() {
        System.out.println(this.name + " " + skill);
        Wait.delay();
    }
}