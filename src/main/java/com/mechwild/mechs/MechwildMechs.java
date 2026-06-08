package com.mechwild.mechs;

import com.mechwild.mechs.entity.RaptorMechEntity;
import com.mechwild.mechs.item.MechDeploymentCoreItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MechwildMechs.MODID)
public class MechwildMechs {
    public static final String MODID = "mechwildmechs";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final RegistryObject<Item> MECH_DEPLOYMENT_CORE = ITEMS.register("mech_deployment_core", () -> new MechDeploymentCoreItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<EntityType<RaptorMechEntity>> RAPTOR_MECH = ENTITY_TYPES.register("raptor_mech", () -> EntityType.Builder.of(RaptorMechEntity::new, MobCategory.CREATURE).sized(4.2F, 5.4F).clientTrackingRange(12).build("raptor_mech"));
    public MechwildMechs() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus); ENTITY_TYPES.register(bus); bus.addListener(this::entityAttributes); bus.addListener(this::creativeTab); MinecraftForge.EVENT_BUS.register(this);
    }
    private void entityAttributes(EntityAttributeCreationEvent event) { AttributeSupplier.Builder attrs = RaptorMechEntity.createAttributes(); event.put(RAPTOR_MECH.get(), attrs.build()); }
    private void creativeTab(BuildCreativeModeTabContentsEvent event) { if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(MECH_DEPLOYMENT_CORE.get()); }
}
