package com.tgbot32.tgbot32.service;

import com.tgbot32.tgbot32.config.BotConfig;
import com.tgbot32.tgbot32.src.Methods;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    public void BotGiveResponse(String response, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        Methods methods = new Methods();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start") || messageText.equals("/help")) {
                String response = "поиск свободных комнат /getFreeRooms\n" +
                        "поиск свободных комнат с условием /getFreeRooms 1000-2000 2 bar conditioner\n" +
                        "бронирование комнаты /reserve 32\n" +
                        "выселение /goOut 32";
                BotGiveResponse(response, chatId);
            } else if (messageText.equals("/getFreeRooms")) {
                BotGiveResponse(methods.PrintFreeRooms(), chatId);
            } else if (messageText.contains("/getFreeRooms")) {

            } else if (messageText.equals("/reserve")) {

            } else if (messageText.equals("/goOut")) {

            } else {
                String response = "Нет такой команды, для получения списка команд используйте /help";
                BotGiveResponse(response, chatId);
            }
        }


    }
}
