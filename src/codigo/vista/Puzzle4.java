package codigo.vista;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.DialogService;

public class Puzzle4 {

    private DialogService dialogService = FXGL.getDialogService();

    public void resolverPuzzle4() {
        dialogService.showInputBox("¿Cuál de las siguientes estructuras de datos \n"
                + "garantiza un acceso rápido aleatorio a sus elementos?\n"
                + "1) Pila\n"
                + "2) Cola\n"
                + "3) Array\n", answer -> {
                    if (answer != null) {
                        if (answer.equalsIgnoreCase("3")) {
                            dialogService.showMessageBox("¡Respuesta correcta!\n"
                                    + "Ubicación del siguiente puzzle: Detras de la casa, justo en el tronco\n"
                                    + "Letra a guardar: Z");
                        } else {
                            dialogService.showMessageBox("Respuesta incorrecta. Vuelve a intentarlo.");
                        }
                    }
                });

    }

}
