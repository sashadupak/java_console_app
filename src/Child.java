public class Child extends AbsPerson {
    public Child(String name, String skill){
        super(name, skill);
    }
    @Override
    public void Sound(String msg) {
        System.out.println(this.name + " sound: " + msg);
        Wait.delay();
    }
    String get_name() {
        return name;
    }
}
