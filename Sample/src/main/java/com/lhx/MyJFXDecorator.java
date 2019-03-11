package com.lhx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MyJFXDecorator extends JFXDecorator {

    public MyJFXDecorator(Stage stage, Node node) {
        this(stage, node, true, true, true);
    }

    /**
     *  控制全屏，最大化，最小化的构造方法
     * @param stage
     * @param node
     * @param fullScreen
     * @param max
     * @param min
     */
    public MyJFXDecorator(Stage stage, Node node, boolean fullScreen, boolean max, boolean min) {
        super(stage, node, fullScreen, max, min);

        // decorator中包含的顶部Btn对应的css选择器
        // [对应的ClassName是 .jfx-decorator-buttons-container]
        Node btnContainer = this.lookup(".jfx-decorator-buttons-container");
        if(btnContainer != null) {
            // btnContainer实际上 是一个HBox
            final HBox btnContainerHBox = (HBox)btnContainer;
            ObservableList<Node> btns = btnContainerHBox.getChildren();
            int btnMaxIdx = 0;
            if(fullScreen) {
                btnMaxIdx ++;
            }
            if(min) {
                btnMaxIdx ++;
            }
            if(btns.size() >= btnMaxIdx) {
                // 找到最大化按钮
                final JFXButton btnMax = (JFXButton) btns.get(btnMaxIdx);
                btnContainerHBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        // 当双击btnContainerHBox的时候，就调用最大化功能
                        if (event.getClickCount() == 2) {
                            btnMax.fire();
                        }
                    }
                });
            } // end if(btns.size() >= btnMaxIdx)

            // 给btnContainerHBox左侧添加一个icon图标
            HBox leftHBox = new HBox();
            leftHBox.setAlignment(Pos.CENTER_LEFT);
            leftHBox.setPadding(new Insets(0, 0, 0, 10));
            leftHBox.setSpacing(10);

            final HBox iconBox = new HBox();
            iconBox.setAlignment(Pos.CENTER_LEFT);
            iconBox.setSpacing(5);

            // bind icon
            stage.getIcons().addListener(new ListChangeListener<Image>() {
                @Override
                public void onChanged(Change<? extends Image> c) {
                    while (c.next()) {
                        iconBox.getChildren().clear();
                        ObservableList<? extends Image> icons = c.getList();
                        if(icons != null && !icons.isEmpty()) {
                            ImageView imageView;
                            for (Image icon : icons) {
                                imageView = new ImageView();
                                imageView.setFitWidth(20);
                                imageView.setFitHeight(20);
                                imageView.setImage(icon);
                                iconBox.getChildren().add(imageView);
                            } // end for
                        }
                    } // end while
                } // end onChanged
            });
            // 把iconBox加到LeftBox上
            leftHBox.getChildren().addAll(iconBox);
            // 再把leftBox加到BtnContainerHBox上
            HBox.setHgrow(leftHBox, Priority.ALWAYS);
            btnContainerHBox.getChildren().add(0, leftHBox);

        } // end if(btnContainer != null)

    }


}
