public class Town {
	class Finishing {
	public void F() {
		System.out.println("The story ends here.");
		}
	}
	public void End()
	{
		Finishing end = new Finishing();
		end.F();
	}
    private String town_name = "Green";
    private String sqr_name = "Apple";
    public void Start() {
        System.out.println("In the town <" + this.town_name + ">");
        Wait.delay();
    }
    public String Town_Name() {
        return ("In the town <" + this.town_name + ">");
    }
    public String Square() {
        return ("On the square <" + this.sqr_name + ">");
    }
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Town other = (Town) obj;
        if ((this.town_name == null) ? (other.town_name != null) : !this.town_name.equals(other.town_name))
        {
            return false;
        }
        if ((this.sqr_name == null) ? (other.sqr_name != null) : !this.sqr_name.equals(other.sqr_name))
        {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 19*hash + (this.town_name != null ? this.town_name.hashCode() : 0);
        hash = 19*hash + (this.sqr_name != null ? this.sqr_name.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString()
    {
        return "Town{" + "Name=" + this.town_name + " Square=" + this.sqr_name + "}";
    }
}
