package codigo.vista;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.DialogService;


public class Puzzle3 {

    private DialogService dialogService = FXGL.getDialogService();

    public void resolverPuzzle3() {
        // Mostrar un cuadro de diálogo de entrada para la pregunta
        dialogService.showInputBox("¿Qué estructura de datos utiliza un esquema LIFO (Last In, First Out)?\n" +"\n"
                + "1) Cola\n"
                + "2) Pila\n"
                + "3) Árbol\n", answer -> {
                    if (answer != null) {
                        if (answer.equalsIgnoreCase("2")) {
                            dialogService.showMessageBox("¡Respuesta correcta!\n"
                                    + "Ubicación del siguiente puzzle: Siguiente mapa, parte inferior\n"
                                    + "Letra a guardar: Z");
                        } else {
                            dialogService.showMessageBox("Respuesta incorrecta. Vuelve a intentarlo.");
                        }
                    }
                });

    }

}
