package application.controller;

import application.api.GPT;
import application.helper.ChatMessage;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChatGPTController implements Initializable {
    @FXML
    private VBox chatBox;
    @FXML
    private TextField inputField;

    @FXML
    private ScrollPane scrollPane;
    private static final int MAX_PROMPTS = 5;
    private int promptCount = 0;

    public void handleUserMessage(ActionEvent event) {
        promptCount++;
        String promptCountText = promptCount + "/" + MAX_PROMPTS;

        if (promptCount > MAX_PROMPTS) {
            ChatMessage limitMessage = new ChatMessage("You have reached the limit for today's chat.", Pos.CENTER_LEFT, "", "gpt");
            chatBox.getChildren().add(limitMessage);
            inputField.setDisable(true); // Disable the input field
            return;
        }

        String userMessage = inputField.getText();
        inputField.clear();

        // User message
        ChatMessage userMessageControl = new ChatMessage(userMessage, Pos.CENTER_RIGHT, promptCountText, "user");
        chatBox.getChildren().add(userMessageControl);

        // Display a loading message
        ChatMessage loadingMessage = new ChatMessage("Processing...", Pos.CENTER_LEFT, promptCountText, "gpt");
        chatBox.getChildren().add(loadingMessage);

        // Create a new task to get response from GPT model
        Task<String> gptTask = new Task<>() {
            @Override
            protected String call() {
                return GPT.chatGPT(userMessage);
            }
        };

        // Update the chat with the GPT response when the task is done
        gptTask.setOnSucceeded(ev -> {
            // Remove the loading message
            chatBox.getChildren().remove(loadingMessage);

            String gptResponse = gptTask.getValue();
            ChatMessage gptMessageControl = new ChatMessage(gptResponse, Pos.CENTER_LEFT, promptCountText, "gpt");
            chatBox.getChildren().add(gptMessageControl);
            scrollPane.setVvalue(1.0);  // Scroll to bottom

            try (BufferedWriter writer = new BufferedWriter
                    (new FileWriter("src/main/resources/data/chat_log.txt", true))) {
                writer.write("User: " + userMessage + "\n");
                writer.write("GPT: " + gptResponse + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the task in a new thread
        new Thread(gptTask).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}
