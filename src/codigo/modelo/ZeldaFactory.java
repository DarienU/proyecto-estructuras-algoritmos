package codigo.modelo;

import codigo.controlador.NPCBusquedaComponentes;
import codigo.controlador.PrincesaComponentes;
import codigo.controlador.NPCComponentes;
import codigo.controlador.NPCPuzzle1Componentes;
import codigo.controlador.NPCMineroComponentes;
import codigo.controlador.PlayerComponentes;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import static codigo.modelo.EntityType.*;

public class ZeldaFactory implements EntityFactory {

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        return entityBuilder(data)
                .type(WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();

    }

    @Spawns("puz1")
    public Entity newPuz1(SpawnData data) {
        return entityBuilder(data)
                .type(PUZ1)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();

    }
    @Spawns("puz3")
    public Entity newPuz3(SpawnData data) {
        return entityBuilder(data)
                .type(PUZ3)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();

    }
    
    @Spawns("puz5")
    public Entity newPuz5(SpawnData data) {
        return entityBuilder(data)
                .type(PUZ5)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();

    }

    @Spawns("instrucciones")
    public Entity newIntrucciones(SpawnData data) {
        return entityBuilder(data)
                .type(INSTRUCCIONES)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("escudo")
    public Entity newEscudo(SpawnData data) {
        return entityBuilder(data)
                .type(ESCUDO)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("espada")
    public Entity newEspada(SpawnData data) {
        return entityBuilder(data)
                .type(ESPADA)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        HitBox playerHitBox = new HitBox(BoundingShape.box(44, 75));

        return entityBuilder(data)
                .type(PLAYER)
                .bbox(playerHitBox)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new PlayerComponentes())
                .build();
    }

    // salidas
    @Spawns("exit")
    public Entity newExit(SpawnData data) {
        return entityBuilder(data)
                .type(EXIT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("rotulo")
    public Entity newRotulo(SpawnData data) {
        return entityBuilder(data)
                .type(ROTULO)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("puzd")
    public Entity newPuzd(SpawnData data) {
        return entityBuilder(data)
                .type(PUZD)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("npc")
    public Entity newNPC(SpawnData data) {

        return entityBuilder(data)
                .type(NPC)
                .with(new IrremovableComponent())
                .with(new NPCComponentes())
                .build();
    }

    @Spawns("prin")
    public Entity newPrincesa(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        HitBox npcHitBox = new HitBox(BoundingShape.box(50, 30));

        return entityBuilder(data)
                .type(PRINCESA)
                .bbox(npcHitBox)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new PrincesaComponentes())
                .build();
    }
    
    @Spawns("npcpuzzle2")
    public Entity newNpcpuzzle2(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        HitBox npcHitBox = new HitBox(BoundingShape.box(40, 30));

        return entityBuilder(data)
                .type(NPCPUZLE2)
                .bbox(npcHitBox)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new NPCPuzzle1Componentes())
                .build();
    }
    
    @Spawns("npcBusqueda")
    public Entity newNpcbusqueda(SpawnData data) {

        HitBox npcHitBox = new HitBox(BoundingShape.box(40, 30));

        return entityBuilder(data)
                .type(NPCBUSQUEDA)
                .bbox(npcHitBox)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new NPCBusquedaComponentes())
                .build();
    }
    
    @Spawns("npcminero")
    public Entity newNpcminero(SpawnData data) {
        
        HitBox npcHitBox = new HitBox(BoundingShape.box(40, 30));

        return entityBuilder(data)
                .type(NPCMINERO)
                .bbox(npcHitBox)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new NPCMineroComponentes())
                .build();
    }

}
