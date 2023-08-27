package tg.kindhands_bot.kindhands.service;

import liquibase.pro.packaged.S;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.kindhands_bot.kindhands.components.NavigationMenu;
import tg.kindhands_bot.kindhands.components.ProcessingBotMessages;
import tg.kindhands_bot.kindhands.config.BotConfig;

@Component
public class KindHandsBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public KindHandsBot(BotConfig config) {
        super(config.getToken());
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    /**
     * Основной метод для работы бота.
     * -----||-----
     * The main method for the bot to work.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            ProcessingBotMessages botMessages = new ProcessingBotMessages(update);

            switch (messageText) {
                case "/start": {
                    sendMessage(botMessages.startCommand());
                    sendMessage(NavigationMenu.choosingShelter(chatId));
                    break;
                }
                default: sendMessage(botMessages.defaultMessage());
            }
        } else if (update.hasCallbackQuery()) {

            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            ProcessingBotMessages botMessages = new ProcessingBotMessages(update);

            switch (callbackData) {
                case "DOG_SH": {
                    sendMessage(botMessages.editExistMessage("Вы выбрали собачий приют."));
                    break;
                }
                case "CAT_SH": {
                    sendMessage(botMessages.editExistMessage("Вы выбрали кошачий приют."));
                    break;
                }
            }
        }
    }

    /**
     * Метод для отправки ответа пользователю.
     * -----||-----
     * A method for sending a response to the user.
     */
    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
