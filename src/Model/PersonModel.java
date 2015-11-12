package Model;

/**
 * Created by jonathan on 12/11/15.
 */
public abstract class PersonModel extends Model {

    protected String name;

    protected boolean sex;

    protected int age;


    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

}
