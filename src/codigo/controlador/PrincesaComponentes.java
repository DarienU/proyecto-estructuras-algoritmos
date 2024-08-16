
package codigo.controlador;

import static com.almasb.fxgl.dsl.FXGL.image;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class PrincesaComponentes extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animacion;


    public PrincesaComponentes() {
        Image npcImage = image("prin.png");

        animacion = new AnimationChannel(npcImage, 13, 75, 74, Duration.seconds(1), 0, 12);

        texture = new AnimatedTexture(animacion);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }


}
