package codigo.controlador;

import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;

public class PlayerComponentes extends Component {

    private PhysicsComponent fisica;

    private AnimatedTexture textura ;

    private AnimationChannel animIdle2, animIzquierda, animArriba, animAbajo,animDerecha;

    public PlayerComponentes() {

        Image imagenIzquierda = image("sprits1.png");
        Image imagenAbajo = image("sprits2.png");
        Image imagenArriba = image("sprits3.png");
        Image imagenDerecha = image("sprits4.png");


        animIzquierda = new AnimationChannel(imagenIzquierda, 10, 85, 75, Duration.seconds(1), 0, 9);

        animArriba = new AnimationChannel(imagenArriba, 10, 85, 75, Duration.seconds(1), 0, 9); // Aquí se corrige
        
        animDerecha = new AnimationChannel(imagenDerecha, 10, 85, 75, Duration.seconds(1), 0, 9); // Aquí se corrige
        
        animIdle2 = new AnimationChannel(imagenAbajo, 10, 85, 75, Duration.seconds(1), 0, 0);
        animAbajo = new AnimationChannel(imagenAbajo, 10, 85, 75, Duration.seconds(1), 0, 9); // Aquí se corrige

        textura = new AnimatedTexture(animIdle2);
        textura.loop();

    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(textura);
        
    }

    @Override
    public void onUpdate(double tpf) {
        if (fisica.isMovingX()) {
            if (fisica.getVelocityX() < 0) { // Si se está moviendo hacia arriba
                if (textura.getAnimationChannel() != animIzquierda) {
                    textura.loopAnimationChannel(animIzquierda);
                }
            } else { // Si se está moviendo hacia abajo
                if (textura.getAnimationChannel() != animDerecha) {
                    textura.loopAnimationChannel(animDerecha);
                }
            }
            fisica.setVelocityX(fisica.getVelocityX() * tpf);
            
        } else if (fisica.isMovingY()) {
            if (fisica.getVelocityY() < 0) { // Si se está moviendo hacia arriba
                if (textura.getAnimationChannel() != animArriba) {
                    textura.loopAnimationChannel(animArriba);
                }
            } else { // Si se está moviendo hacia abajo
                if (textura.getAnimationChannel() != animAbajo) {
                    textura.loopAnimationChannel(animAbajo);
                }
            }
            fisica.setVelocityY(fisica.getVelocityY() * tpf);
        } else {
            // Cuando no se mueve en ninguna dirección, mostrar la animación de idle
            if (textura.getAnimationChannel() != animIdle2) {
                textura.loopAnimationChannel(animIdle2);
            }
        }
    }

    public void right() {
        fisica.setVelocityX(8000);
        fisica.setVelocityY(0);
    }

    public void left() {
        fisica.setVelocityX(-8000);
        fisica.setVelocityY(0);
    }

    public void stop() {
        fisica.setVelocityX(0);
        fisica.setVelocityY(0);
    }

    public void up() {
        fisica.setVelocityY(-8000);
        fisica.setVelocityX(0);

    }

    public void down() {
        fisica.setVelocityY(8000);
        fisica.setVelocityX(0);

    }

    public void stopVerticalMovement() {
        fisica.setVelocityY(0);
    }

}
