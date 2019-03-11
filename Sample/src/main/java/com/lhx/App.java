package com.lhx;


import com.jfoenix.controls.JFXDecorator;
import com.lhx.uicontroller.AppController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * 使用datafx管理视图切换和共享数据
     */
    private ViewFlowContext flowContext;

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage firstStage) throws Exception {
        // 初始化datafx的ViewFlowContext来管理数据
        flowContext = new ViewFlowContext();
        // 将stage注入到ViewFlowContext[实现方式：将container的View视图通过装饰器绑定到stage上]
        flowContext.register("FirstStage", firstStage);
        // Flow的初始化需要一个默认的视图View和视图控制器ViewController
        Flow flow = new Flow(AppController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flow.createHandler(flowContext).start(container);
        // 顶部是一个黑色的边框，上面有最小化、最大化和关闭三个通用的控件，
        // 这些控件的控制是在JFXDecorator初始化时定义的
        // 如果需要修改，就要定义Decorator
//        JFXDecorator decorator = new JFXDecorator(firstStage, container.getView(),false, true, true);
        MyJFXDecorator decorator = new MyJFXDecorator(firstStage, container.getView(), false, true, true);

        // 初始化scene
        Scene scene = new Scene(decorator, 700, 500);

        // 添加样式
        final ObservableList<String> styleSheets = scene.getStylesheets();
        styleSheets.add(App.class.getResource("/css/jfoenix-components.css").toExternalForm());

        firstStage.setMinWidth(500);
        firstStage.setMinHeight(400);
        // 代码添加icon
        firstStage.getIcons().add(new Image("/images/test.png"));
        firstStage.setTitle("Demo");
        firstStage.setScene(scene);
        firstStage.show();
    }
}
