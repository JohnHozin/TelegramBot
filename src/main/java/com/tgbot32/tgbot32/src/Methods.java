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

    public String ErrorMessage(int value, String response, boolean needCut) {
        if (value == 0) {
            response = "Такой комнаты нет!";
        } else if (needCut) {
            response = (response.replaceFirst(".$", "")).replaceFirst(".$", "");
        }
        return response;
    }

    public String ErrorNotFoundCommand(){
        return "Нет такой команды, для получения списка команд используйте /help";
    }

    public String StartMessage(){
        return "Добро пожаловать в бота по управлению бронированием в отеле.\n" +
                "Для получения справки введите команду /help";
    }

    public String HelpMessage(){
        return """
                Поиск свободных комнат:
                /getFreeRooms
                Поиск свободных комнат с условием:
                /getFreeRooms 1000-2000 2 bar conditioner
                Получение полной информации по всем комнатам:
                /getFreeRoomsWithAllInfo
                Бронирование комнаты:
                /reserve 32
                Выселение:
                /goOut 32""";
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
        return ErrorMessage(value, response, true);
    }

    public String PrintFreeRoomsWithAllInfo(){
        String response = "";
        int value = 0;
        for (Room r : rooms) {
            if (!r.isReserved()) {
                value++;
                response = response + "Номер комнаты: " + r.getNum() + "\n";
                response = response + "Количество спальных мест: " + r.getPlace() + "\n";
                response = response + "Цена: " + r.getPrice() + "\n";
                response = response + "Кондиционер: " + (r.isConditioner()?"есть":"нет") + "\n";
                response = response + "Бар: " + (r.isBar()?"есть":"нет") + "\n\n";
            }
        }
        return ErrorMessage(value, response, true);
    }

        public String PrintFreeRoomsWithTerms(String response) {
        String[] splitted = response.split(" ");
        boolean bar = false;
        boolean conditioner = false;
        int min = 0;
        //int max = (int) Double.POSITIVE_INFINITY;
        int max = 0;
        int place = -1;
        for (String s : splitted) {
            if (s.equals("bar")) {
                bar = true;
            } else if (s.equals("conditioner")) {
                conditioner = true;
            } else if (s.contains("-")) {
                min = Integer.parseInt(s.split("-")[0]);
                max = Integer.parseInt(s.split("-")[1]);
            } else if (s.matches("\\d")) {
                place = Integer.parseInt(s);
            }
        }

        int value = 0;
        response = "";
        for (Room r : rooms) {
            int mark = 0;
            if (r.isReserved()) continue;
            if (bar && r.isBar()) mark++;
            if (conditioner && r.isConditioner()) mark++;
            if (r.getPrice() >= min && r.getPrice() <= max) mark++;
            if (r.getPlace() == place || place == 0) mark++;

            if (splitted.length - 1 == mark) {
                System.out.print(r.getNum() + ", ");
                response = response + r.getNum() + ", ";
                value++;
            }
        }
        return ErrorMessage(value, response, true);
    }

    public String ReserveRoom(String response) {
        int number = Integer.parseInt(response.split(" ")[1]);
        int value = 0;
        for (Room r : rooms) {
            if (r.getNum() == number) {
                if (r.isReserved()) {
                    return "Комната занята!";
                }
                r.setReserved(true);
                response = "Выполнено!!!";
                value++;
            }
        }
        return ErrorMessage(value, response, false);
    }

    public String GoOutRoom(String response) {
        int number = Integer.parseInt(response.split(" ")[1]);
        int value = 0;
        for (Room r : rooms) {
            if (r.getNum() == number) {
                if (!r.isReserved()) {
                    return "Комната свободна!";
                }
                r.setReserved(false);
                response = "Выполнено!!!";
                value++;
            }
        }
        return ErrorMessage(value, response, false);
    }
}
