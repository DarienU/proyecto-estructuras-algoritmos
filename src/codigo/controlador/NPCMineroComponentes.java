package codigo.controlador;

import static com.almasb.fxgl.dsl.FXGL.image;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NPCMineroComponentes extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animacionIzquierda;

        
    public NPCMineroComponentes() {
        Image npcImageIzquierda = image("pc.png");

        animacionIzquierda = new AnimationChannel(npcImageIzquierda, 9, 80, 89, Duration.seconds(1), 0, 7);

        texture = new AnimatedTexture(animacionIzquierda);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

}
