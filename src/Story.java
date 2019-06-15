import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Scanner;

public class Story {
    public static void main(String[] args) {
        Musican Guslya = new Musican("Guslya", "playing the flute.");
        Painter Tubick = new Painter("Tubick", "");
        Town myTown = new Town();
        Child Pugovka = new Child("Pugovka", "");
        anonymous.Sound("Once.");
        try {
            myTown.Start();
            Guslya.Play();
            Tubick.Sound("What a " + Types.answer.Wonderful.toString() + " musician!");
            Pugovka.Sound("I hear such musical instrument for the first time.");
        }
        catch(NoLinkException e15) {
            throw new NoLinkException();
        }
        double a = Math.random()*2-1;
        if (a > 0)
        {
            Pugovka.Sound("I, like many, do not know such instrument.");
        }
        else
        {
            Pugovka.Sound("I, unlike many, know what a flute is.");
        }
        if (a < 0)
        {
            Tubick.Sound("I also know how to play the flute.");
        }
        else
        {
            Tubick.Sound("And I can't play the flute.");
        }
        Tubick.Sound("I and " + Guslya.name + "live together " + myTown.Town_Name() + " " + myTown.Square());
        anonymous.Sound("In the house of baby " + Pugovka.name);
        try {
            a = Math.random() * 2 - 1;
            if (a > 0) {
                Tubick.Sound("The room in which we settled is " + Types.answer.Spacious.toString() + "!");
            } else if (a < 0) {
                Tubick.Sound("The room in which we settled is " + Types.answer.Bright.toString() + "!");
            } else throw new WordException(Types.answer.Boring.toString());
        }
        catch(WordException e59) {
            Tubick.Sound("The room in which we settled is " + Types.answer.Boring.toString() + "...");
        }
        myTown.End();

        System.out.println();
        Start enter = new Start();
        System.out.println("Data import...");
        final String file_name;
        if (args[0]!="" && args[0]!=null) {
            file_name = args[0];
        }
        else {
            file_name = "simple_data.json";
        }
        enter.load(file_name);
        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new Thread() {
            public void run() {
                enter.save(file_name);
            }
        });
        System.out.println("Interactive mode. Enter 'help' to see all supported commands.");
        boolean exit = false;
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        do {
            System.out.print("->");
            Scanner scan = new Scanner(System.in);
            String command = scan.next();
            String str_arguments = scan.nextLine();
            String[] arguments = str_arguments.split("}");
            Map<String, String> arg1, arg2;
            String key = null, path = null, name = null, skill = null;
            try {
                arg1 = gson.fromJson((arguments[0] + "}"), type);
                key = arg1.get("key");
                path = arg1.get("path");
                arg2 = gson.fromJson((arguments[1] + "}"), type);
                name = arg2.get("name");
                skill = arg2.get("skill");
            }
            catch (Exception e) {}
            switch (command) {
                case "help": enter.help();
                    break;
                case "insert":
                    if (key != null && name != null && skill != null && key != "") {
                        enter.insert(key, new Child(name, skill));
                    }
                    else {
                        enter.arguments_error();
                    }
                    break;
                case "remove":
                    if (key != null && key != "") {
                        enter.remove(key);
                    }
                    else {
                        enter.arguments_error();
                    }
                    break;
                case "remove_greater_key":
                    if (key != null && key != "") {
                        enter.remove_greater_key(key);
                    }
                    else {
                        enter.arguments_error();
                    }
                    break;
                case "show": enter.show();
                    break;
                case "info":
                    enter.info();
                    break;
                case "import":
                    if (path != null && path != "") {
                        enter.load(path);
                    }
                    else {
                        enter.arguments_error();
                    }
                    break;
                case "save":
                    enter.save(file_name);
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    enter.command_error();
            }
        } while (!exit);
        enter.save(file_name);
    }
    static Sounds anonymous = new Sounds() {
        public void Sound(String message) {
            System.out.println(message);
            Wait.delay();
        }
    };
}