package com.kjellvos.os.gridHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

import java.util.ArrayList;

/**
 * Created by kjevo on 2/21/17.
 */
public class GridHandler {
    private Scene scene;
    private ArrayList<GridItem> itemsInGrid;

    private double width = 0D, height = 0D;

    public GridHandler(){
        Pane pane = new Pane();
        itemsInGrid = new ArrayList<GridItem>();
        pane.setPrefWidth(800D);
        pane.setPrefHeight(600D);
    }

    public Vec2d getMaxXAndMaxYFromItemsInGrid(){
        int maxY = 0, maxX = 0;
        itemsInGrid.sort(new GridSorter());
        for (int i = 0; i < itemsInGrid.size(); i++){
            GridItem itemInGrid = itemsInGrid.get(i);
            if ((itemInGrid.getxPos()+itemInGrid.getColSpan()) > maxX) {
                maxX = (itemInGrid.getxPos()+itemInGrid.getColSpan());
            }
            if ((itemInGrid.getyPos()+itemInGrid.getRowSpan()) > maxY) {
                maxY = (itemInGrid.getyPos()+itemInGrid.getRowSpan());
            }
        }
        return new Vec2d(maxX, maxY);
    }

    public Scene getGridAsScene(){
        Pane pane = new Pane();
        scene = new Scene(pane);
        setupWidthAndHeightChangeListeners();
        width = scene.getWidth();
        height = scene.getHeight();
        itemsInGrid.sort(new GridSorter());

        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                pane.getChildren().add(itemsInGrid.get(i).getUINode());
            }
        }

        return scene;
    }

    public void calculateAllPositionAndSize(){
        itemsInGrid.sort(new GridSorter());
        Vec2d maxXY = getMaxXAndMaxYFromItemsInGrid();

        for (int i = 0; i < itemsInGrid.size(); i++){
            if (itemsInGrid.get(i).getUINode() != null) {
                calculatePositionAndSize(itemsInGrid.get(i).getUINode(), itemsInGrid.get(i).getxPos(), ((int) maxXY.x), itemsInGrid.get(i).getyPos(), ((int) maxXY.y), 10, itemsInGrid.get(i).getRowSpan(), itemsInGrid.get(i).getColSpan());
            }
        }
    }

    public void calculatePositionAndSize(Node UINode, int xPos, int maxX, int yPos, int maxY, double padding, int rowSpan, int colSpan) {
        Class tempClass = UINode.getClass();
        Font font = new Font("Verdana", height/40);

        double height2 = height - padding;
        double width2 = width - padding;
        double relocateX = padding + (width2/maxX*xPos);
        double relocateY = padding + (height2/maxY*yPos);
        double width = (((width2-padding)/maxX)*colSpan)-padding;
        double height = (((height2-padding)/maxY)*rowSpan)-padding;
        double leftRightPadding, topBottomPadding;
        if (tempClass == Label.class) {

        }else if (tempClass == Button.class){
            Button button = (Button) UINode;
            Text buttonText = new Text(button.getText().toString());

            button.setFont(font);
            buttonText.setFont(font);
            topBottomPadding = (((height2/maxY)/2)*rowSpan)-(buttonText.getLayoutBounds().getHeight()/2)-(padding/2);
            leftRightPadding = (((width2/maxX)/2)*colSpan)-(buttonText.getLayoutBounds().getWidth()/2)-(padding/2);
            button.setPadding(new Insets(topBottomPadding, leftRightPadding, topBottomPadding, leftRightPadding));
            button.relocate(relocateX, relocateY);
        }else if (tempClass == RadioButton.class){
            RadioButton radioButton = (RadioButton) UINode;
            radioButton.setPrefSize(width, height);
            radioButton.relocate(relocateX, relocateY);
        }else if (tempClass == ToggleButton.class){
            ToggleButton toggleButton = (ToggleButton) UINode;
            toggleButton.setPrefSize(width, height);
            toggleButton.relocate(relocateX, relocateY);
        }else if (tempClass == CheckBox.class){
            CheckBox checkBox = (CheckBox) UINode;
            checkBox.setPrefSize(width, height);
            checkBox.relocate(relocateX, relocateY);
        }else if (tempClass == ChoiceBox.class){
            ChoiceBox choiceBox = (ChoiceBox) UINode;
            choiceBox.setPrefSize(width, height);
            choiceBox.relocate(relocateX, relocateY);
        }else if (tempClass == TextField.class){
            TextField textField = (TextField) UINode;
            textField.setPrefSize(width, height);
            textField.relocate(relocateX, relocateY);
        }else if (tempClass == ScrollPane.class){
            ScrollPane scrollPane = (ScrollPane) UINode;
            scrollPane.setPrefSize(width, height);
            scrollPane.relocate(relocateX, relocateY);
        }else if (tempClass == TreeView.class){
            TreeView treeView = (TreeView) UINode;
            treeView.setPrefSize(width, height);
            treeView.relocate(relocateX, relocateY);
        }else if (tempClass == ComboBox.class){
            ComboBox comboBox = (ComboBox) UINode;
            comboBox.setPrefSize(width, height);
            comboBox.relocate(relocateX, relocateY);
        }else if (tempClass == Separator.class){
            Separator separator = (Separator) UINode;
            separator.setPrefSize(width, height);
            separator.relocate(relocateX, relocateY);
        }else if (tempClass == Slider.class){
            Slider slider = (Slider) UINode;
            slider.setPrefSize(width, height);
            slider.relocate(relocateX, relocateY);
        }else if (tempClass == ProgressBar.class){
            ProgressBar progressBar = (ProgressBar) UINode;
            progressBar.setPrefSize(width, height);
            progressBar.relocate(relocateX, relocateY);
        }else if (tempClass == ProgressIndicator.class){
            ProgressIndicator progressIndicator = (ProgressIndicator) UINode;
            progressIndicator.setPrefSize(width, height);
            progressIndicator.relocate(relocateX, relocateY);
        }else if (tempClass == Hyperlink.class){
            Hyperlink hyperlink = (Hyperlink) UINode;
            hyperlink.setPrefSize(width, height);
            hyperlink.relocate(relocateX, relocateY);
        }else if (tempClass == HTMLEditor.class){
            HTMLEditor htmlEditor = (HTMLEditor) UINode;
            htmlEditor.setPrefSize(width, height);
            htmlEditor.relocate(relocateX, relocateY);
        }else if (tempClass == TitledPane.class){
            TitledPane titledPane = (TitledPane) UINode;
            titledPane.setPrefSize(width, height);
            titledPane.relocate(relocateX, relocateY);
        }else if (tempClass == PasswordField.class){
            PasswordField passwordField = (PasswordField) UINode;
            passwordField.setPrefSize(width, height);
            passwordField.relocate(relocateX, relocateY);
        }else if (tempClass == ColorPicker.class){
            ColorPicker colorPicker = (ColorPicker) UINode;
            colorPicker.setPrefSize(width, height);
            colorPicker.relocate(relocateX, relocateY);
        }else if (tempClass == Pagination.class){
            Pagination pagination = (Pagination) UINode;
            pagination.setPrefSize(width, height);
            pagination.relocate(relocateX, relocateY);
        }else if (tempClass == TableView.class){
            TableView tableView = (TableView) UINode;
            tableView.setPrefSize(width, height);
            tableView.relocate(relocateX, relocateY);
        }else if (tempClass == Text.class){
            Text text = (Text) UINode;
            text.setFont(font);
            text.prefHeight(height);
            text.prefWidth(width);
            text.setWrappingWidth(width);
            text.relocate(relocateX, relocateY);
        }else if (tempClass == DatePicker.class) {
            DatePicker datePicker = (DatePicker) UINode;
            datePicker.setPrefSize(width, height);
            datePicker.relocate(relocateX, relocateY);
        }else if (tempClass == ListView.class){
            ListView listView = (ListView) UINode;
            listView.setPrefSize(width, height);
            listView.relocate(relocateX, relocateY);
        }
    }

    public void add(int xPos, int yPos, Node UINode) {
        add(xPos, yPos, UINode, 1, 1);
    }

    public void add(int xPos, int yPos, Node UINode, int rowSpan, int colSpan){
        itemsInGrid.sort(new GridSorter());
        Vec2d maxXY = getMaxXAndMaxYFromItemsInGrid();
        if (((int) maxXY.x) > xPos+colSpan && ((int) maxXY.y) > yPos+rowSpan) {
            itemsInGrid.remove(xPos + (yPos*maxXY.x));
            itemsInGrid.add(new GridItem(xPos, yPos, colSpan, rowSpan).setUINode(UINode));
        }else{
            for (int i = 0; i < yPos; i++) {
                for (int i2 = 0; i2 < xPos; i2++){
                    itemsInGrid.add(new GridItem(i2, i, 1, 1).setUINode(null));
                }
            }
            itemsInGrid.add(new GridItem(xPos, yPos, colSpan, rowSpan).setUINode(UINode));
        }
    }

    private void setupWidthAndHeightChangeListeners() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = newValue.doubleValue();
            calculateAllPositionAndSize();
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = newValue.doubleValue();
            calculateAllPositionAndSize();
        });
    }
}
