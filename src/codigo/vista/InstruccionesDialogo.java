package codigo.vista;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class InstruccionesDialogo {

    public void mostrarInstrucciones() {
        VBox content = new VBox(
                getUIFactoryService().newText(
                        "¡Bienvenido a \"The Legend of Zelda: The Minish Cap\"!\n" +
                        "Resuelve puzzles interconectados para salvar a la princesa.\n" +
                        "Obtén pistas para el siguiente desafío.\n" +
                        "Explora en busca de puzzles items ocultos.\n" +
                        "Utiliza tu ingenio y conocimientos para avanzar.\n" +
                        "Guarda todas las letras dadas en los puzzles.\n" +
                        "¡Buena suerte en tu aventura!")
        );

        Button btnClose = getUIFactoryService().newButton("Preparado?");
        btnClose.setPrefWidth(300);

        // Ajustar el ancho del cuadro de texto
        content.setPrefWidth(300);

        getDialogService().showBox("Instrucciones del Juego", content, btnClose);
    }
}
