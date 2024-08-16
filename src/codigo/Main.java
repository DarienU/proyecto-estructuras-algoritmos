package codigo;

import codigo.modelo.ZeldaFactory;
import codigo.controlador.PlayerComponentes;
import codigo.modelo.OperacionesCorazon;
import codigo.modelo.Inventario;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.onCollisionOneTimeOnly;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;
import static com.almasb.fxgl.dsl.FXGL.showMessage;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import static codigo.modelo.EntityType.*;
import codigo.vista.InstruccionesDialogo;
import codigo.vista.InteracionPrincesa;
import codigo.vista.ZeldaaMainMenu;
import codigo.vista.Puzzle1;
import codigo.vista.Puzzle2;
import codigo.vista.Puzzle3;
import codigo.vista.Puzzle4;
import codigo.vista.Puzzle5;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.removeUINode;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends GameApplication {

    private static final int nivelMaximo = 7;
    private static final int nivelInicial = 1;

    private Entity player;
    private Puzzle1 puzzle1;
    private Puzzle2 puzzle2;
    private Puzzle3 puzzle3;
    private Puzzle4 puzzle4;
    private Puzzle5 puzzle5;
    private InteracionPrincesa InteracionPrincesa;

    private boolean tieneEspada = false;
    private boolean tieneEscudo = false;
    
    private ImageView espadaImage;
    private ImageView escudoImage;

    private static final Inventario inventario = new Inventario();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(672);// 16*15 = 240-------- 32*15= 480
        settings.setHeight(432);// 16*10 = 160------- 32*10= 320
        settings.setTitle("Zelda-Minish-Cap");
        settings.setVersion("beta");
        settings.setMainMenuEnabled(true);
        settings.setDeveloperMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new ZeldaaMainMenu();
            }
        });

    }

    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(0.3);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponentes.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponentes.class).stop();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponentes.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponentes.class).stop();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponentes.class).up();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponentes.class).stopVerticalMovement();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponentes.class).down();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponentes.class).stopVerticalMovement();
            }
        }, KeyCode.DOWN);

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("mapa", nivelInicial);
        vars.put("partidasJugadas", 1);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new ZeldaFactory());
        getGameScene().setBackgroundColor(Color.LIGHTGREY);

        player = null;

        establecerMapa(nivelInicial);

        player = spawn("player", 200, 200);

        set("player", player);

        puzzle1 = new Puzzle1();
        puzzle2 = new Puzzle2();
        puzzle3 = new Puzzle3();
        puzzle4 = new Puzzle4();
        puzzle5 = new Puzzle5();
        InteracionPrincesa = new InteracionPrincesa();
        
        Viewport viewport = getGameScene().getViewport();
        viewport.setBounds(0, 0, getAppWidth(), getAppHeight());
        viewport.bindToEntity(player, getAppWidth(), getAppHeight());

    }

    @Override
    protected void initUI() {

        spawnVidas("/assets/textures/vida.png");

    }

    @Override
    protected void initPhysics() {

        getPhysicsWorld().setGravity(0, 0);

        onCollisionBegin(PLAYER, PUZ1, (player, puz1) -> {
            puzzle1();
        });

        onCollisionBegin(PLAYER, NPCPUZLE2, (player, npcpuzzle2) -> {
            puzzle2();
        });

        onCollisionBegin(PLAYER, PUZ3, (player, puz3) -> {
            puzzle3();
        });

        onCollisionBegin(PLAYER, NPCMINERO, (player, npcminero) -> {
            puzzle4();
        });

        onCollisionBegin(PLAYER, PUZ5, (player, puz5) -> {
            puzzle5();
        });
        onCollisionBegin(PLAYER, PRINCESA, (player, prin) -> {
            interaccion();
        });

        onCollisionBegin(PLAYER, WALL, (player, wall) -> {
            chocarParedes();
        });

        onCollisionOneTimeOnly(PLAYER, EXIT, (player, exit) -> {

            getGameScene().getViewport().fade(() -> {
                nextLevel();
            });
        });
        
        onCollisionBegin(PLAYER, NPCBUSQUEDA, (player, npcBusqueda) -> {
            busquedaSecuencial();
        });

        onCollisionBegin(PLAYER, INSTRUCCIONES, (player, instrucciones) -> {
            handleInstruccionCollision();
        });

        onCollisionBegin(PLAYER, PUZD, (player, puzd) -> {
            puzzleFinal();
        });

        onCollisionBegin(PLAYER, ESPADA, (player, espada) -> {
            ColisionEspada();
        });

        onCollisionBegin(PLAYER, ESCUDO, (player, escudo) -> {
            ColisionEscudo();
        });

        onCollisionBegin(PLAYER, ROTULO, (player, rotulo) -> {
            rotulo();
        });

    }

    public void puzzle1() {
        puzzle1.resolverPuzzle1();
    }

    public void puzzle2() {
        puzzle2.resolverPuzzle2();
    }

    public void puzzle3() {
        puzzle3.resolverPuzzle3();
    }

    public void puzzle4() {
        puzzle4.resolverPuzzle4();
    }

    public void puzzle5() {
        puzzle5.resolverPuzzle5();
    }

    public void interaccion() {
        InteracionPrincesa.intereccion();
    }

    public void puzzleFinal() {
        FXGL.getDialogService().showInputBox("¿Con las letras dadas, que palabra forma?", answer -> {
            if (answer != null && answer.equalsIgnoreCase("pizza")) {
                juegoGanado();
                spawn("prin", 430, 112);
            } else {
                juegoPerdido();
            }
        });
    }

    public void juegoGanado() {
        Platform.runLater(() -> {
            showMessage("¡Felicidades! ¡Has ganado en un total de " + geti("partidasJugadas") + " partidas!", () -> {
            });
        });
    }

    public void juegoPerdido() {
        inc("partidasJugadas", +1);

        Platform.runLater(() -> {
            showMessage("Perdistes, vuelve a intentarlo!!.", () -> {
                set("mapa", nivelInicial); // Volver al primer nivel
                establecerMapa(nivelInicial);
                inventario.eliminarInicio(1);
                inventario.eliminarInicio(2);
                
                if (tieneEspada) {
                    removeUINode(espadaImage);
                    tieneEspada = false;
                }

                // Eliminar la imagen del escudo si existe
                if (tieneEscudo) {
                    removeUINode(escudoImage);
                    tieneEscudo = false;
                }


            });
        });
    }

    public void rotulo() {
        int mapaActual = geti("mapa");

        if (mapaActual == 3) {
            Platform.runLater(() -> {
                showMessage("Bienvendio a la Pradera de Hyrule Sur", () -> {
                });
            });
        } else if (mapaActual == 4) {
            Platform.runLater(() -> {
                showMessage("Bienvenido al Rancho Lon Lon", () -> {
                });
            });
        }
    }

    public void handleInstruccionCollision() {
        Platform.runLater(() -> {
            InstruccionesDialogo instruccionesDialog = new InstruccionesDialogo();
            instruccionesDialog.mostrarInstrucciones();
        });

    }
    
    public void busquedaSecuencial(){
        boolean tieneEspada = inventario.buscarNumero(1);

        if (tieneEspada) {
            showMessage("Ya tienes la espada.");
        } else {
            showMessage("No tienes la espada. Debes buscarla.");
        }
    }

    public void ColisionEspada() {
        int espadaNumero = 1; // Número que representa la espada en tu inventario
        inventario.insertarInicio(espadaNumero); // Insertar el número de la espada en el inventario

        if (inventario.obtenerLista() != null && inventario.obtenerLista().obtenerNumero() == espadaNumero) {
            double gameWidth = getAppWidth();
            double gameHeight = getAppHeight();

            double tamanioImagen = 32; // Tamaño de la imagen de la espada (ajusta según sea necesario)
            double padding = 10; // Espacio entre las imágenes de espada (ajusta según sea necesario)

            double posicionEscudoX = gameWidth - (2 * (padding + tamanioImagen));

            double posicionEscudoY = 30;

            String espadaImagenPath = "/assets/textures/Sword.png";

            espadaImage = new ImageView(new Image(espadaImagenPath));
            espadaImage.setTranslateX(posicionEscudoX);
            espadaImage.setTranslateY(posicionEscudoY);

            // Agregar la imagen de la espada a la interfaz de usuario
            addUINode(espadaImage);

            tieneEspada = true;
        }

        inventario.ordenarLista();

        // Para corroborar
        inventario.imprimir();
    }

    public void ColisionEscudo() {
        int escudoNumero = 2; // Número que representa el escudo en tu inventario
        inventario.insertarInicio(escudoNumero); // Insertar el número del escudo en el inventario

        if (inventario.obtenerLista() != null && inventario.obtenerLista().obtenerNumero() == escudoNumero) {
            double gameWidth = getAppWidth();
            double gameHeight = getAppHeight();

            double tamanioImagen = 32; // Tamaño de la imagen del escudo (ajusta según sea necesario)
            double padding = 60; // Espacio entre las imágenes de espada y escudo (ajusta según sea necesario)

            double posicionEscudoX = gameWidth - 2 * (padding + tamanioImagen);

            double posicionEscudoY = 30;

            String escudoImagenPath = "/assets/textures/ESCUDO.png";

            // Crear una imagen del escudo
            escudoImage = new ImageView(new Image(escudoImagenPath));
            escudoImage.setTranslateX(posicionEscudoX);
            escudoImage.setTranslateY(posicionEscudoY);

            // Agregar la imagen del escudo a la interfaz de usuario
            addUINode(escudoImage);

            // Indicar que se ha obtenido el escudo
            tieneEscudo = true;
        }

        inventario.ordenarLista();

        // Para corroborar
        inventario.imprimir();
    }

    public void nextLevel() {

        String lvl2 = new String("mapa1-2.mp3");
        Music gameMusic1 = FXGL.getAssetLoader().loadMusic(lvl2);
        String lvl3 = new String("mapa32.mp3");
        Music gameMusic3 = FXGL.getAssetLoader().loadMusic(lvl3);
        String lvl4 = new String("mapa4.mp3");
        Music gameMusic4 = FXGL.getAssetLoader().loadMusic(lvl4);
        String lvl56 = new String("mapa5-6.mp3");
        Music gameMusic56 = FXGL.getAssetLoader().loadMusic(lvl56);

        int nivelParalelo = geti("mapa");

        set("mapa", nivelParalelo + 1); // Avanzar al siguiente nivel
        establecerMapa(nivelParalelo + 1);

        if ((nivelParalelo == 2))  {
            FXGL.getAudioPlayer().loopMusic(gameMusic1);

        } 
        if (geti("mapa") == 3) {
            FXGL.getAudioPlayer().stopMusic(gameMusic1);
            FXGL.getAudioPlayer().loopMusic(gameMusic3);
            crearNpcAleatoriamente(0);
            spawn("npcpuzzle2", 139, 1182);
            spawn("npcBusqueda", 1504, 142);

            
        } 
        if (geti("mapa") == 4) {
            FXGL.getAudioPlayer().stopMusic(gameMusic3);
            FXGL.getAudioPlayer().loopMusic(gameMusic4);
            spawn("npcminero", 880, 1730);
            crearNpcAleatoriamente(0);

        }
        
        if (geti("mapa") == 5 || geti("mapa") == 6) {
            FXGL.getAudioPlayer().stopMusic(gameMusic4);
            FXGL.getAudioPlayer().loopMusic(gameMusic56);

        }
        

    }

    public void establecerMapa(int numeroMapa) {
        if (player != null) {
            Point2D puntoAparicion = generadorUbicacionJugador(numeroMapa);
            player.getComponent(PhysicsComponent.class).overwritePosition(puntoAparicion);
            player.setZIndex(Integer.MAX_VALUE);
        }

        if (geti("mapa") == 2) {
            getGameScene().getViewport().setBounds(0, 0, getAppWidth(), getAppHeight());
            getGameScene().getViewport().bindToEntity(player, getAppWidth(), getAppHeight());//centra al bicho
        } else if (geti("mapa") == 3) {
            getGameScene().getViewport().setBounds(0, 0, getAppWidth() * 3, getAppHeight() * 3);
            getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
        } else if (geti("mapa") == 4) {
            getGameScene().getViewport().setBounds(0, 0, (int) (672 * 2.1), (int) (432 * 4.4));
            getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);//centra al bicho
        } else if (geti("mapa") == 5) {
            getGameScene().getViewport().setBounds(0, 0, (int) (672 * 1.4), (int) (432 * 2.4));
            getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);//centra al bicho
        } else if (geti("mapa") == 6) {
            getGameScene().getViewport().setBounds(0, 0, getAppWidth(), (int) (432 * 1.5));
            getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);//centra al bicho
        }

        setLevelFromMap("tmx/mapa" + numeroMapa + ".tmx");
    }

    public Point2D generadorUbicacionJugador(int numeroMapa) {

        /* este metodo basicamente se encarga de ubicar bien al jugador
        despues de avanzar al siguiente nivel
         */
        if (numeroMapa == 1) {
            return new Point2D(200, 200);
        } else if (numeroMapa == 2) {
            return new Point2D(226, 38);
        } else if (numeroMapa == 3) {
            return new Point2D(1339, 803); //Ubicacion para el primer mapa grande
        } else if (numeroMapa == 4) {
            return new Point2D(30, 281);
        } else if (numeroMapa == 5) {
            return new Point2D(492, 899);
        } else {
            return new Point2D(372, 455);
        }
    }

    public void chocarParedes() {

        var wall = getGameWorld().getSingleton(WALL);

        // valida si hay una pared 
        if (wall != null) {
            wall.getComponent(CollidableComponent.class).setValue(true);
        } else {
            wall.getComponent(CollidableComponent.class).setValue(false);
        }
    }

    public void crearNpcAleatoriamente(int cantidadGatosGenerados) {
        if (cantidadGatosGenerados < 5) {
            // Generar gato aleatorio
            double x = Math.random() * 2022;
            double y = Math.random() * 1344;
            spawn("npc", x, y);
            // Llamar recursivamente para generar el siguiente gato
            crearNpcAleatoriamente(cantidadGatosGenerados + 1);
        } else {

        }
    }

    public void spawnVidas(String rutaImagenCorazon) {
        OperacionesCorazon operaciones = new OperacionesCorazon(); // clase Operaciones

        for (int i = 1; i < 4; i++) {
            operaciones.insertarCorazones(i);

            ImageView imagenCorazon = new ImageView(new Image(rutaImagenCorazon)); // imagen del corazón

            imagenCorazon.setX(40 * i + -10);
            imagenCorazon.setY(10);

            addUINode(imagenCorazon);
        }

        operaciones.imprimirLista(); // por la dudas sino me sirve

    }

    public static void main(String[] args) {
        launch(args);
    }
}
