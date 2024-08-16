package codigo.vista;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.DialogService;

public class Puzzle5 {

    private DialogService dialogService = FXGL.getDialogService();

    public void resolverPuzzle5() {
        dialogService.showInputBox("¿Cuál de las siguientes estructuras de datos utiliza una política \n"
                + "de \"primero en entrar, primero en salir\" (FIFO)?\n"
                + "1) Pila\n"
                + "2) Cola\n"
                + "3) Lista enlazada\n", answer -> {
                    if (answer != null) {
                        if (answer.equalsIgnoreCase("2")) {
                            dialogService.showMessageBox("¡Respuesta correcta!\n"
                                    + "Ubicación del siguiente puzzle: Entra a la casa y rescata a la princesa\n"
                                    + "Letra a guardar: A");
                        } else {
                            dialogService.showMessageBox("Respuesta incorrecta. Vuelve a intentarlo.");
                        }
                    }
                });

    }

}
