public abstract class AbsPerson implements Sounds {
    protected String name, skill;
    public AbsPerson(String name, String skill){
        this.name = name;
        this.skill = skill;
    }
    public void Sound(String msg) {
        System.out.println(this.name + " sound: " + msg);
    }
}
