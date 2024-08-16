
package codigo.controlador;

import static com.almasb.fxgl.dsl.FXGL.image;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class NPCPuzzle1Componentes extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animacion;


    public NPCPuzzle1Componentes() {
        Image npcImage = image("npcpuzle1.png"); 

        animacion = new AnimationChannel(npcImage, 3, 53, 70, Duration.seconds(1.30), 0, 2);

        texture = new AnimatedTexture(animacion);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }
}
