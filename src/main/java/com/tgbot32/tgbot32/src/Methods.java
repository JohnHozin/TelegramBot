package com.tgbot32.tgbot32.src;

public class Methods {
    Room[] rooms = {
            new Room(11, 4, 2000, true, false),
            new Room(12, 2, 2500, false, true),
            new Room(13, 1, 1000, false, false),
            new Room(21, 3, 4000, true, true),
            new Room(22, 2, 3000, false, true),
            new Room(23, 5, 2000, true, false),
            new Room(31, 1, 4000, false, true),
            new Room(32, 2, 2500, false, true),
            new Room(33, 3, 5000, true, true),
    };
    //}

    public String ErrorMassage(int value,String response) {
        if (value == 0) {
            response ="Такой комнаты нет!";
        } else {
            response = (response.replaceFirst(".$","")).replaceFirst(".$","");
        }
        return response;
    }


    public String PrintFreeRooms() {
        String response = "";
        int value = 0;
        for (Room r : rooms) {
            if (!r.isReserved()) {
                response = response + r.getNum() + ", ";
                value++;
            }
        }
        return ErrorMassage(value, response);
    }

//    public void PrintFreeRoomsWithTerms(String command) {
//        String[] splitted = command.split(" ");
//        boolean bar = false;
//        boolean conditioner = false;
//        int min = 0;
//        //int max = (int) Double.POSITIVE_INFINITY;
//        int max = 0;
//        int place = -1;
//        for (String s : splitted) {
//            if (s.equals("bar")) {
//                bar = true;
//            } else if (s.equals("conditioner")) {
//                conditioner = true;
//            } else if (s.contains("-")) {
//                min = Integer.parseInt(s.split("-")[0]);
//                max = Integer.parseInt(s.split("-")[1]);
//            } else if (s.matches("\\d")) {
//                place = Integer.parseInt(s);
//            }
//        }
//
//        int value = 0;
//        for (Room r : rooms) {
//            int mark = 0;
//            if (r.isReserved()) continue;
//            if (bar && r.isBar()) mark++;
//            if (conditioner && r.isConditioner()) mark++;
//            if (r.getPrice() >= min && r.getPrice() <= max) mark++;
//            if (r.getPlace() == place || place == 0) mark++;
//
//            if (splitted.length - 1 == mark) {
//                System.out.print(r.getNum() + ", ");
//                value++;
//            }
//        }
//        ErrorMassage(value);
//    }
//
//    public void ReserveRoom(String command) {
//        int number = Integer.parseInt(command.split(" ")[1]);
//        int value = 0;
//        for (Room r : rooms) {
//            if (r.getNum() == number) {
//                if (r.isReserved()) {
//                    System.out.println("Комната занята!");
//                    return;
//                }
//                r.setReserved(true);
//                System.out.print("Выполнено!!!");
//                value++;
//            }
//        }
//        ErrorMassage(value);
//    }
//
//    public void GoOutRoom(String command) {
//        int number = Integer.parseInt(command.split(" ")[1]);
//        int value = 0;
//        for (Room r : rooms) {
//            if (r.getNum() == number) {
//                if (!r.isReserved()) {
//                    System.out.println("Комната свободна!");
//                    return;
//                }
//                r.setReserved(false);
//                System.out.print("Выполнено!!!");
//                value++;
//            }
//        }
//        ErrorMassage(value);
//    }
}
