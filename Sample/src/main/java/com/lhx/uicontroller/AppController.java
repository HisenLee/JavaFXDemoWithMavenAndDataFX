package com.lhx.uicontroller;

import com.lhx.ExtendedAnimatedFlowContainer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 用java注射机制来映射fxml以及fxml中的UI组件
 */
@ViewController(value = "/fxml/Main.fxml")
public final class AppController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private BorderPane root;

    // 变量名需要对应Main.fxml中的控件id
    @FXML
    private MenuItem homeMenu;
    @FXML
    private MenuItem demoMenu;
    @FXML
    private MenuItem saveMenu;

    @PostConstruct
    public void init() throws FlowException {
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(".......");
            }
        });
    }


}
