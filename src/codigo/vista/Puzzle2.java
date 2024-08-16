package codigo.vista;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.DialogService;

public class Puzzle2 {

    private DialogService dialogService = FXGL.getDialogService();

    public void resolverPuzzle2() {
        // Mostrar un cuadro de diálogo de entrada para la pregunta
        dialogService.showInputBox("¿Qué estructura de datos permite la inserción\n"
                + "y eliminación eficiente tanto al inicio como al final?\n" 
                + "1) Pila\n"
                + "2) Cola\n"
                + "3) Lista doblemente enlazada\n", answer -> {
                    if (answer != null) {
                        if (answer.equalsIgnoreCase("3")) {
                            dialogService.showMessageBox("¡Respuesta correcta!\n"
                                    + "Ubicación del siguiente puzzle: Detras de la casa, parche café\n"
                                    + "Letra a guardar: I");
                        } else {
                            dialogService.showMessageBox("Respuesta incorrecta. Vuelve a intentarlo.");
                        }
                    }
                });
    }

}
