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
    final Methods methods = new Methods();

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
        //Methods methods = new Methods();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start")) {
                BotGiveResponse(methods.StartMessage(), chatId);
            } else if (messageText.equals("/help")) {
                BotGiveResponse(methods.HelpMessage(), chatId);
            } else if (messageText.equals("/getFreeRooms")) {
                BotGiveResponse(methods.PrintFreeRooms(), chatId);
            } else if (messageText.equals("/getFreeRoomsWithAllInfo")) {
                BotGiveResponse(methods.PrintFreeRoomsWithAllInfo(), chatId);
            } else if (messageText.contains("/getFreeRooms")) {
                BotGiveResponse(methods.PrintFreeRoomsWithTerms(messageText), chatId);
            } else if (messageText.contains("/reserve")) {
                BotGiveResponse(methods.ReserveRoom(messageText), chatId);
            } else if (messageText.contains("/goOut")) {
                BotGiveResponse(methods.GoOutRoom(messageText), chatId);
            } else {
                BotGiveResponse(methods.ErrorNotFoundCommand(), chatId);
            }
        }
    }
}
