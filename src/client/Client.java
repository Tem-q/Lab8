package client;

import data.*;
import dragon.*;
import data.User;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

/**
 * @author Kurovskiy Artem R3136
 * This is the main class
 */
public class Client {
    private String host;
    private int port;
    private static DatagramChannel clientChannel;
    private static SocketAddress address;
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(16384);
    private static Scanner scanner = new Scanner(System.in);

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static String init(User user, String status) {
        DataForClient  message = null;
        try {
            clientChannel = DatagramChannel.open();
            address = new InetSocketAddress("localhost", 5000);
            clientChannel.connect(address);

            if (status.equals("newUser")) {
                DataForServer<User> newUser = new DataForServer<>("newUser", user);
                send(newUser);
                message = receive();
            } if (status.equals("loginUser")) {
                DataForServer<User> loginUser = new DataForServer<>("loginUser", user);
                send(loginUser);
                message = receive();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message.getMessage();
    }

    public static String getCollection() {
        DataForServer<String> getCollection = new DataForServer<>("getCollection", "");
        send(getCollection);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String fillUsers() {
        DataForServer<String> fillUsers = new DataForServer<>("fillUsers", "");
        send(fillUsers);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String help() {
        DataForServer<String> help = new DataForServer<>("help", "");
        send(help);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String info() {
        DataForServer<String> info = new DataForServer<>("info", "");
        send(info);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String add(Dragon dragon) {
        DataForServer<Dragon> add = new DataForServer<>("add", dragon);
        send(add);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String addIfMax(Dragon dragon) {
        DataForServer<Dragon> addIfMax = new DataForServer<>("add_if_max", dragon);
        send(addIfMax);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String update(Dragon dragon) {
        DataForServer<Dragon> update = new DataForServer<>("update", dragon);
        send(update);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String removeById(int id) {
        DataForServer<Integer> removeById = new DataForServer<>("remove_by_id", id);
        send(removeById);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String clear() {
        DataForServer<String> clear = new DataForServer<>("clear", "");
        send(clear);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String checkId(int id) {
        DataForServer<Integer> checkId = new DataForServer<>("check_id", id);
        send(checkId);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String head() {
        DataForServer<String> head = new DataForServer<>("head", "");
        send(head);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String removeHead() {
        DataForServer<String> removeHead = new DataForServer<>("remove_head", "");
        send(removeHead);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String sumOfAge() {
        DataForServer<String> sumOfAge = new DataForServer<>("sum_of_age", "");
        send(sumOfAge);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String filterLessThanAge(Long age) {
        DataForServer<Long> filterLessThanAge = new DataForServer<>("filter_less_than_age", age);
        send(filterLessThanAge);
        DataForClient message = receive();
        return message.getMessage();
    }

    public static String filterContainsName(String name) {
        DataForServer<String> filterContainsName = new DataForServer<>("filter_contains_name", name);
        send(filterContainsName);
        DataForClient message = receive();
        return message.getMessage();
    }

    public void run() {
        try {
            clientChannel = DatagramChannel.open();
            address = new InetSocketAddress("localhost", this.port);
            clientChannel.connect(address);

            String username;
            String password;
            User user;
            DataForClient message;
            while (true) {
                System.out.println("Enter the login");
                username = scanner.nextLine();
                System.out.println("Enter the password");
                Console console = System.console();
                if (console != null) {
                    char[] symbols = console.readPassword();
                    if (symbols == null) continue;
                    password = String.valueOf(symbols);
                } else password = scanner.nextLine();
                user = new User(username, password);
                DataForServer<User> newUser = new DataForServer<>("newUser", user);
                send(newUser);
                message = receive();
                System.out.println(message.getMessage());
                if ((message.getMessage().equals("User signed in successfully")) || (message.getMessage().equals("New user is successfully added"))) {
                    break;
                } else {
                    System.out.println("Try again");
                }
            }

            System.out.println("Enter the command please");
            boolean param = true;
            try {
                String[] parametrs = new String[]{"",""};
                String task = scanner.nextLine();
                parametrs = checkTask(task, parametrs);
                task = parametrs[0];
                while (param) {
                    switch (task) {
                        case "help":
                            DataForServer<String> help = new DataForServer<>("help", "");
                            send(help);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "info":
                            DataForServer<String> info = new DataForServer<>("info", "");
                            send(info);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "show":
                            DataForServer<String> show = new DataForServer<>("show", "");
                            send(show);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "add":
                            DataForServer<Dragon> add = new DataForServer<>("add", add(task,0));
                            send(add);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "update":
                            if (!parametrs[1].equals("")) {
                                try {
                                    int id = Integer.parseInt(parametrs[1]);
                                    DataForServer<Dragon> update = new DataForServer<>("update", add(task, id));
                                    send(update);
                                    message = receive();
                                    System.out.println(message.getMessage());
                                } catch (NumberFormatException e) {
                                    System.out.println("The id must be int type. Try again");
                                }
                            } else {
                                System.out.println("You didn't enter the id");
                            }
                            break;
                        case "remove_by_id":
                            if (!parametrs[1].equals("")) {
                                try {
                                    int id = Integer.parseInt(parametrs[1]);
                                    DataForServer<Integer> removeById = new DataForServer<>("remove_by_id", id);
                                    send(removeById);
                                    message = receive();
                                    System.out.println(message.getMessage());
                                } catch (NumberFormatException e) {
                                    System.out.println("The id must be int type. Try again");
                                }
                            } else {
                                System.out.println("You didn't enter the id");
                            }
                            break;
                        case "clear":
                            DataForServer<String> clear = new DataForServer<>("clear", "");
                            send(clear);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "exit":
                            param = false;
                            break;
                        case "head":
                            DataForServer<String> head = new DataForServer<>("head", "");
                            send(head);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "remove_head":
                            DataForServer<String> removeHead = new DataForServer<>("remove_head", "");
                            send(removeHead);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "add_if_max":
                            DataForServer<Dragon> addIfMax = new DataForServer<>("add_if_max", add(task,0));
                            send(addIfMax);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "sum_of_age":
                            DataForServer<String> sumOfAge = new DataForServer<>("sum_of_age", "");
                            send(sumOfAge);
                            message = receive();
                            System.out.println(message.getMessage());
                            break;
                        case "filter_contains_name":
                            if (!parametrs[1].equals("")) {
                                String name = parametrs[1];
                                DataForServer<String> filterContainsName = new DataForServer<>("filter_contains_name", name);
                                send(filterContainsName);
                                message = receive();
                                System.out.println(message.getMessage());
                            } else {
                                System.out.println("You didn't enter the specified substring");
                            }
                            break;
                        case "filter_less_than_age":
                            if (!parametrs[1].equals("")) {
                                try {
                                    long age = Long.parseLong(parametrs[1]);
                                    if (age <= 0) {
                                        System.out.println("The age must be greater than 0. Try again");
                                    }
                                    DataForServer<Long> filterLessThanAge = new DataForServer<>("filter_less_than_age", age);
                                    send(filterLessThanAge);
                                    message = receive();
                                    System.out.println(message.getMessage());
                                } catch (NumberFormatException e) {
                                    System.out.println("The age must be long type. Try again");
                                }
                            } else {
                                System.out.println("You didn't enter the age");
                            }
                            break;
                        default:
                            System.out.println("Unknown command. Please try again");
                    }
                    if (param) {
                        System.out.println("Enter a command please");
                        task = scanner.nextLine();
                        parametrs = checkTask(task, parametrs);
                        task = parametrs[0];
                    }
                }
            } catch (NoSuchElementException e) {
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void send(DataForServer<?> command) {
        try {
            byteBuffer.put(serialize(command));
            byteBuffer.flip();
            clientChannel.write(byteBuffer);
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DataForClient receive() {
        try {
            clientChannel.read(byteBuffer);
            byteBuffer.flip();
            return deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] serialize(DataForServer<?> command) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = null;
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(command);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            objectOutputStream.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DataForClient deserialize() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            DataForClient message = (DataForClient) objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
            byteBuffer.clear();
            return message;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method finds a command from the list in the input line
     * @param task
     * @return parametrs[]
     */
    public String[] checkTask(String task, String[] parametrs) {
        String[] command = task.split(" ");
        for (int i = 0; i<command.length; i++) {
            if ((command[i].equals("help")) || (command[i].equals("info")) || (command[i].equals("show")) || (command[i].equals("add")) ||
                    (command[i].equals("clear")) || (command[i].equals("exit")) || (command[i].equals("head")) ||
                    (command[i].equals("remove_head")) || (command[i].equals("add_if_max")) || (command[i].equals("sum_of_age"))) {
                parametrs[0] = command[i];
                break;
            }
            try {
                if ((command[i].equals("update")) || (command[i].equals("remove_by_id")) || (command[i].equals("execute_script")) ||
                        (command[i].equals("filter_contains_name")) || (command[i].equals("filter_less_than_age"))) {
                    parametrs[0] = command[i];
                    parametrs[1] = command[i+1];
                    break;
                } else {
                    parametrs[0] = "";
                }
            } catch (IndexOutOfBoundsException e) {
                parametrs[0] = command[i];
                parametrs[1] = "";

            }
        }
        return parametrs;
    }

    private Dragon add(String task, int id) {
        long xcor = 0;
        double ycor = 0;
        float x = 0;
        int y = 0;
        float z = 0;
        String nameLocation = null;
        String personName = null;
        float personHeight = 0;
        long personWeight = 0;
        String dragonName = null;
        long dragonAge = 0;
        int dragonWeight = 0;
        DragonType dragonType = null;
        Dragon dragon = null;

        System.out.println("Enter the x coordinate. The x coordinate must be long type");
        while (true) {
            try {
                xcor = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("The x coordinate must be long type. Try again");
                scanner.nextLine();
            }
        }

        System.out.println("Enter the y coordinate. The y coordinate must be double type and less than or equal to 67");
        while (true) {
            try {
                double ycheck = scanner.nextDouble();
                if (ycheck <= 67) {
                    ycor = ycheck;
                    break;
                } else {
                    System.out.println("The y coordinate must be less than or equal to 67. Try again");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("The y coordinate must be double type. Try again");
                scanner.nextLine();
            }
        }
        Coordinates coordinates = new Coordinates(xcor, ycor);

        System.out.println("Enter the x location coordinate. The x location coordinate must be float type");
        while (true) {
            try {
                x = scanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("The x location coordinate must be float type. Try again");
                scanner.nextLine();
            }
        }
        System.out.println("Enter the y location coordinate. The y location coordinate must be int type");
        while (true) {
            try {
                y = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("The y location coordinate must be int type. Try again");
                scanner.nextLine();
            }
        }
        System.out.println("Enter the z location coordinate. The z location coordinate must be float type");
        while (true) {
            try {
                z = scanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("The z location coordinate must be float type. Try again");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.println("Enter the name of location. The name of location can't be null or empty");
        while (true) {
            String checkEmpty = scanner.nextLine();
            if(!checkEmpty.equals("")) {
                nameLocation = checkEmpty;
                break;
            } else {
                System.out.println("The name of location can't be null or empty. Try again");
            }
        }
        Location location = new Location(x, y, z, nameLocation);

        System.out.println("Enter the name of killer. The name of killer can't be null or empty");
        while (true) {
            String checkEmpty = scanner.nextLine();
            if(!checkEmpty.equals("")) {
                personName = checkEmpty;
                break;
            } else {
                System.out.println("The name of killer can't be null or empty. Try again");
            }
        }
        System.out.println("Enter the height of killer. The height of killer must be float type and greater than 0");
        while (true) {
            try {
                float personHeightCheck = scanner.nextFloat();
                if (personHeightCheck > 0) {
                    personHeight = personHeightCheck;
                    break;
                } else {
                    System.out.println("The height of killer must be greater than 0. Try again");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("The height of killer must be float type. Try again");
                scanner.nextLine();
            }
        }
        System.out.println("Enter the weight of killer. The weight of killer must be long type and greater than 0");
        while (true) {
            try {
                long personWeightCheck = scanner.nextLong();
                if (personWeightCheck > 0) {
                    personWeight = personWeightCheck;
                    break;
                } else {
                    System.out.println("The weight of killer must be greater than 0. Try again");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("The weight of killer must be long type. Try again");
                scanner.nextLine();
            }
        }
        Person killer = new Person(personName, personHeight, personWeight, location);

        scanner.nextLine();
        System.out.println("Enter the name of dragon. The name of dragon can't be null or empty");
        while (true) {
            String checkEmpty = scanner.nextLine();
            if(!checkEmpty.equals("")) {
                dragonName = checkEmpty;
                break;
            } else {
                System.out.println("The name of dragon can't be null or empty. Try again");
            }
        }
        System.out.println("Enter the age of dragon. The age of dragon must be long type and greater than 0");
        while (true) {
            try {
                long dragonAgeCheck = scanner.nextLong();
                if (dragonAgeCheck > 0) {
                    dragonAge = dragonAgeCheck;
                    break;
                } else {
                    System.out.println("The age of dragon must be greater than 0. Try again");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("The age of dragon must be long type. Try again");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.println("Enter the description of dragon");
        String dragonDescription = scanner.nextLine();

        System.out.println("Enter the weight of dragon. The weight of dragon must be int type and greater than 0");
        while (true) {
            try {
                int dragonWeightCheck = scanner.nextInt();
                if (dragonWeightCheck > 0) {
                    dragonWeight = dragonWeightCheck;
                    break;
                } else {
                    System.out.println("The weight of dragon must be greater than 0. Try again");
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("The weight of dragon must be int type. Try again");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.println("Choose the type of dragon: " + DragonType.AIR + " " + DragonType.FIRE + " " + DragonType.UNDERGROUND);
            try {
                dragonType = DragonType.valueOf(scanner.next());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("There is no such type of dragon");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        if (task.equals("add") || task.equals("add_if_max")) {
            dragon = new Dragon(dragonName, coordinates, dragonAge, dragonDescription, dragonWeight, dragonType, killer);
        } if (task.equals("update")) {
            dragon = new Dragon(id, dragonName, coordinates, dragonAge, dragonDescription, dragonWeight, dragonType, killer);
        }

        return dragon;
    }
}
