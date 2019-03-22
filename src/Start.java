/**
 * User interaction class
 * @autor Aleksandr Dupak
 * @version 5.3
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class Start {
    Map<String, Child> children = new LinkedHashMap<>();
    Date date = new Date();
    /** <p> Print all commands supported in interactive mode. </p> */
    public void help() {
        System.out.println("insert {String key} {element}: add a new element with a given key.");
        System.out.println("show: show all the elements of the collection.");
        System.out.println("import {String path}: add to the collection all the data from the file.");
        System.out.println("save: save current collection to file.");
        System.out.println("info: show information about the collection.");
        System.out.println("remove {String key}: remove element from the collection by its key.");
        System.out.println("remove_greater_key {String key}: remove from the collection all elements whose key exceeds the specified.");
        System.out.println("exit: exit interactive mode.");
        System.out.println("Example: ->insert {\"key\":\"a1\"} {\"name\":\"Alex\",\"skill\":\"learn\"}");
    }

    /** <p> Add a new element with a given key. </p>
     *
     * @param key unique key for given element.
     * @param element specific item matching the key.
     */
    public void insert(String key, Child element) {
        children.put(key, element);
        System.out.printf("Inserted. %d elements in the collection now. %n", children.size());
    }

    /** <p> Remove element from the collection by its key. </p>
     *
     * @param key the key corresponding to the element.
     */
    public void remove(String key) {
        Child dead = children.remove(key);
        if (dead == null) {
            System.out.println("No such key in the collection.");
        } else {
            String form = "{\"name\": \"%s\", \"skill\": \"%s\"} has been removed. %n";
            System.out.printf(form, dead.name, dead.skill);
        }
    }

    /** <p> Show all the elements of the collection sorted by their keys. </p> */
    public void show() {
        sort();
        if (children.isEmpty()) {
            System.out.println("Collection is empty.");
        }
        else {
            String form = "{\"key\": \"%s\", \"element\": {\"name\": \"%s\", \"skill\": \"%s\"}} %n";
            children.forEach((k, v) -> System.out.printf(form, k, children.get(k).name, children.get(k).skill));
        }
    }

    /** <p> Remove from the collection all elements whose key exceeds the specified. </p>
     *
     * @param desired_key comparison key.
     */
    public void remove_greater_key(String desired_key) {
        int start_size = children.size();
        Set<String> keys = children.keySet();
        List<String> trash = new ArrayList<>();
        for (String key : keys) {
            if (key.compareTo(desired_key) > 0) {
                trash.add(key);
            }
        }
        for (String key : trash) {
            children.remove(key);
        }
        if (children.size() < start_size) {
            System.out.printf("%d collection items have been removed. %n", (start_size - children.size()));
        }
        else {
            System.out.printf("There are no any keys in the collection greater than \"%s\". %n", desired_key);
        }
    }
    /** <p> Bubble sort. </p> */
    public void sort() {
        List<Map.Entry<String, Child>> list = new ArrayList<>(children.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Child>>() {
            @Override
            public int compare(Map.Entry<String, Child> stringChildEntry, Map.Entry<String, Child> t1) {
                return stringChildEntry.getKey().compareTo(t1.getKey());
            }
        });
        Map<String, Child> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Child> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        children = sortedMap;
    }
    /** <p> Show information about the collection: type, initialisation date, number of elements. </p> */
    public void info() {
        System.out.printf("collection type: %s%n", children.getClass());
        System.out.printf("initialisation date: %s%n", date.toString());
        System.out.printf("number of elements: %s%n", children.size());
    }
    /** <p> Report an arguments error. </p> */
    public void arguments_error() {
        System.out.println("Input error. See 'help'");
    }
    /** <p> Report a command error. </p> */
    public void command_error() {
        System.out.println("No such command. See 'help'");
    }
    /** <p> Add to the collection all the data from the file. </p>
     *
     * @param path full path to the file with the extension.
     */
    public void load(String path) {
        Gson gson = new Gson();
        StringBuilder jsonStr = new StringBuilder();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                jsonStr.append(scan.next());
            }
            try {
                Type type = new TypeToken<Map<String, Child>>() {}.getType();
                if (jsonStr.toString().length()>0) {
                    Map<String, Child> imported = gson.fromJson(jsonStr.toString(), type);
                    children.putAll(imported);
                    System.out.println("The file was imported successfully.");
                }
                else {
                    System.out.println("The file is empty.");
                }
            }
            catch (Exception e) {
                System.out.println("Incorrect file or format.");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No such file or directory.");
        }
    }

    /** <p> Save current collection to file. </p>
     *
     * @param file_name full path to the file with the extension.
     */
    public void save(String file_name) {
        Gson gson = new Gson();
        sort();
        try {
            FileOutputStream output = new FileOutputStream(file_name);
            String packed = gson.toJson(children);
            byte[] buffer = packed.getBytes();
            output.write(buffer);
            System.out.println("The collection has been saved.");
        }
        catch (IOException e) {
            System.out.println("Output error.");
        }
    }
}