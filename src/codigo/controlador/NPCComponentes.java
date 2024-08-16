package codigo.controlador;

import static com.almasb.fxgl.dsl.FXGL.image;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NPCComponentes extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animacion;


    public NPCComponentes() {
        Image npcImage = image("gato3.png"); 

        animacion = new AnimationChannel(npcImage, 8, 58, 50, Duration.seconds(1), 0, 7);

        texture = new AnimatedTexture(animacion);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

}
