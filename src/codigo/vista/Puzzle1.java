package codigo.vista;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.DialogService;

public class Puzzle1 {

    private DialogService dialogService = FXGL.getDialogService();

    public void resolverPuzzle1() {
        // Mostrar un cuadro de diálogo de entrada para la pregunta
        dialogService.showInputBox("¿Cuál de las siguientes estructuras de datos \n"
                +"utiliza punteros para enlazar nodos?\n" +
                "1) Array\n" +
                "2) Lista enlazada\n" +
                "3) Árbol binario", answer -> {
            if (answer != null) {
                // Verificar la respuesta ingresada por el usuario
                if (answer.equalsIgnoreCase("2")) {
                    // Mostrar un mensaje de respuesta correcta y la ubicación del siguiente puzzle
                    dialogService.showMessageBox("¡Respuesta correcta!\n" +
                            "Ubicación del siguiente puzzle: Esquina izquierda del siguiente mapa\n" +
                            "Letra a guardar: P");
                } else {
                    // Mostrar un mensaje si la respuesta es incorrecta
                    dialogService.showMessageBox("Respuesta incorrecta. Vuelve a intentarlo.");
                }
            }
        });
    }
}
