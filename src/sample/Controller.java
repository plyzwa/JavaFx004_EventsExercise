package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;
    @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }
    @FXML
    public void firstButtonClicked(ActionEvent e) {
        if (e.getSource().equals(helloButton)) {
            System.out.println("Hello " + nameField.getText());
        } else if (e.getSource().equals(byeButton)) {
            System.out.println("Bye " + nameField.getText());
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background threat";
                    System.out.println("I sleep on the " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background threat";
                            System.out.println("I am updating the label on  " + s);
                            ourLabel.setText("We dit something");
                        }
                    });

                } catch (InterruptedException event) {
                    System.out.println("Sleep doesnt work");
                }
            }
        };
        new Thread(task).start();
        if(ourCheckBox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(false);
        }
    }
    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disabledButton = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disabledButton);
        byeButton.setDisable(disabledButton);
    }
    public void handleChanged() {
        System.out.println("CheckBox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }

}
