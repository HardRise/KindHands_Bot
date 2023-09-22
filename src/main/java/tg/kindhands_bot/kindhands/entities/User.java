package tg.kindhands_bot.kindhands.entities;

import tg.kindhands_bot.kindhands.enums.BotState;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName = "";
    private String patronymic;
    private Boolean blocked;
    private String denialReason;
    private BotState botState = BotState.NULL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getDenialReason() {
        return denialReason;
    }

    public void setDenialReason(String denialReason) {
        this.denialReason = denialReason;
    }

    public BotState getBotState() {
        return botState;
    }

    public void setBotState(BotState botState) {
        this.botState = botState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(chatId, user.chatId) && Objects.equals(firstName, user.firstName) && Objects.equals(blocked, user.blocked) && Objects.equals(denialReason, user.denialReason) && botState == user.botState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, firstName, blocked, denialReason, botState);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", chatId=" + chatId +
                ", name='" + firstName +
                ", blocked=" + blocked +
                ", denialReason='" + denialReason +
                ", botState=" + botState;
    }
}
