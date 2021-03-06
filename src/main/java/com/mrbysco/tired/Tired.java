package com.mrbysco.tired;

import com.mojang.logging.LogUtils;
import com.mrbysco.tired.handler.SleepHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Tired.MOD_ID)
public class Tired {
	public static final String MOD_ID = "tired";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Tired() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(Type.COMMON, SleepSchedule.commonSpec);
		eventBus.register(SleepSchedule.class);

		MinecraftForge.EVENT_BUS.register(new SleepHandler());
	}
}
