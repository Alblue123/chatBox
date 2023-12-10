package application.helper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ChatMessage extends VBox {
    public ChatMessage(String message, Pos alignment,
                       String limitText, String messageType) {
        super();

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.JUSTIFY);
        messageLabel.setMaxWidth(300);
        messageLabel.setPadding(new Insets(5));
        if ("user".equals(messageType)) {
            messageLabel.setStyle("-fx-background-color: lightblue; -fx-background-radius: 5;");
            messageLabel.setMaxWidth(300);
        } else if ("gpt".equals(messageType)) {
            messageLabel.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        }
        Label limitLabel = new Label(limitText);
        limitLabel.setAlignment(Pos.BOTTOM_RIGHT);

        this.setAlignment(alignment);
        this.getChildren().addAll(messageLabel, limitLabel);
        this.setMargin(messageLabel, new Insets(5));
    }
}
