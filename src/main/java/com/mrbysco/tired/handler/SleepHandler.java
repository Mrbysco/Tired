package com.mrbysco.tired.handler;

import com.mrbysco.tired.SleepSchedule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;

public class SleepHandler {
	@SubscribeEvent
	public void onCanSleep(CanPlayerSleepEvent event) {
		if (event.getProblem() == Player.BedSleepingProblem.NOT_POSSIBLE_NOW) {
			event.setProblem(null);
		}
	}

	@SubscribeEvent
	public void onCanContinueSleeping(CanContinueSleepingEvent event) {
		if (event.getProblem() == Player.BedSleepingProblem.NOT_POSSIBLE_NOW) {
			event.setContinueSleeping(true);
		}
	}

	@SubscribeEvent
	public void onSleepFinished(SleepFinishedTimeEvent event) {
		final LevelAccessor level = event.getLevel();
		long dividedTime = 24000L / SleepSchedule.COMMON.sleepDivision.get();
		long j = level.getLevelData().getDayTime() + dividedTime;
		long newTime = (j - j % dividedTime);
		System.out.println(newTime);
		event.setTimeAddition(newTime);
	}
}
