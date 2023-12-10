package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ChatUIController {

    @FXML
    private BorderPane root;

    @FXML
    private VBox userList;

    @FXML
    private VBox chatArea;

    @FXML
    private TextArea chatHistory;

    @FXML
    private TextField messageInput;

    public void initialize() {
        for (int i = 1; i <= 10; i++) {
            Button userButton = new Button("User " + i);
            userButton.setMinWidth(150);
            userList.getChildren().add(userButton);
        }

        messageInput.setPromptText("Type your message...");

        // Set padding for the chatArea VBox
        chatArea.setPadding(new javafx.geometry.Insets(10));
    }

    @FXML
    private void sendMessage() {
        String message = messageInput.getText();
        if (!message.isEmpty()) {
            chatHistory.appendText("You: " + message + "\n");
            messageInput.clear();
        }
    }
}
