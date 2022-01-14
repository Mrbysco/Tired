package com.mrbysco.tired;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class SleepSchedule {
	public static class Common {
		public final IntValue sleepDivision;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Sleep Schedule")
					.push("Schedule");

			sleepDivision = builder
					.comment("Defines the division of the day to sleep towards (4 = quarter, 2 = half, 1 = full day) [Default: 4]")
					.defineInRange("sleepDivision", 4, 1, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		Tired.LOGGER.debug("Loaded Tired's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		Tired.LOGGER.debug("Tired's config just got changed on the file system!");
	}
}
