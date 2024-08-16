package codigo.vista;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.texture;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ZeldaaMainMenu extends FXGLMenu {

    private static final int SIZE = 150;
    private static final Color HOVER_COLOR = Color.PINK;
    private Animation<?> animation; // puede referirse a una instancia de cualquier tipo de Animation

    public ZeldaaMainMenu() {
        super(MenuType.MAIN_MENU); //se utiliza para iniciar la clase base (FXGLMenu) con una configuración específica

        
        // Botón "Iniciar Juego"
        StackPane startGameButton = createButton("Iniciar Juego", e -> fireNewGame());
        
        /* el " e -> " se refiera a una expresion lambda, o sea
        acción que se llevará a cabo cuando se haga clic en el botón.
        */
        

        // Botón "Cerrar Juego"
        StackPane exitGameButton = createButton("Cerrar Juego", e -> FXGL.getPrimaryStage().close());

        // Contenedor para los botones
        HBox buttonsContainer = new HBox(20);
        buttonsContainer.setTranslateX(getAppWidth() / 2 - 200);
        buttonsContainer.setTranslateY(getAppHeight() - 100);
        buttonsContainer.getChildren().addAll(startGameButton, exitGameButton);

        // Fondo
        Image backgroundImage = texture("FONDO.png").getImage();
        ImageView bgView = new ImageView(backgroundImage);
        bgView.setFitWidth(getAppWidth());
        bgView.setFitHeight(getAppHeight());

        getContentRoot().getChildren().addAll(bgView, buttonsContainer);

        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();
    }

    private StackPane createButton(String text, javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> handler) {
        Rectangle buttonShape = new Rectangle(150, 30);
        buttonShape.fillProperty().bind(
            Bindings.when(buttonShape.hoverProperty()).then(HOVER_COLOR).otherwise(Color.color(0.1, 0.05, 0.0, 0.45))
        );

        Text buttonText = FXGL.getUIFactoryService().newText(text, Color.WHITE, FontType.GAME, 24.0);

        StackPane button = new StackPane(buttonShape, buttonText);
        button.setPadding(new Insets(5));
        button.setAlignment(Pos.CENTER);
        button.setOnMouseClicked(handler);

        return button;
    }
    
}