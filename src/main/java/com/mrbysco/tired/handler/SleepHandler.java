package com.mrbysco.tired.handler;

import com.mrbysco.tired.SleepSchedule;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SleepHandler {
	@SubscribeEvent
	public void onCheckSleepingTime(SleepingTimeCheckEvent event) {
		event.setResult(Result.ALLOW);
	}

	@SubscribeEvent
	public void onSleepFinished(SleepFinishedTimeEvent event) {
		final LevelAccessor level = event.getLevel();
		long dividedTime = 24000L / SleepSchedule.COMMON.sleepDivision.get();
		long j = level.getLevelData().getDayTime() + dividedTime;
		long newTime = (j - j % dividedTime);
		event.setTimeAddition(newTime);
	}
}
