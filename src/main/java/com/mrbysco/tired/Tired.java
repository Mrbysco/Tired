package com.mrbysco.tired;

import com.mojang.logging.LogUtils;
import com.mrbysco.tired.handler.SleepHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Tired.MOD_ID)
public class Tired {
	public static final String MOD_ID = "tired";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Tired(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SleepSchedule.commonSpec);
		eventBus.register(SleepSchedule.class);

		NeoForge.EVENT_BUS.register(new SleepHandler());
	}
}
