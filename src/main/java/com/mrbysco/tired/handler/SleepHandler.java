package com.mrbysco.tired.handler;

import com.mrbysco.tired.SleepSchedule;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.ClockAdjustment;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.level.SleepFinishedTimeEvent;

public class SleepHandler {
	@SubscribeEvent
	public void onCanSleep(CanPlayerSleepEvent event) {
		Player.BedSleepingProblem problem = event.getProblem();
		if (problem == null) return;
		Component message = problem.message();
		if (message != null && message.equals(Component.translatable("block.minecraft.bed.no_sleep"))) {
			event.setProblem(null);
		}
	}

	@SubscribeEvent
	public void onCanContinueSleeping(CanContinueSleepingEvent event) {
		Player.BedSleepingProblem problem = event.getProblem();
		if (!event.mayContinueSleeping() && problem == Player.BedSleepingProblem.OTHER_PROBLEM) {
			event.setContinueSleeping(true);
		}
	}

	@SubscribeEvent
	public void onSleepFinished(SleepFinishedTimeEvent event) {
		long dividedTime = 24000L / SleepSchedule.COMMON.sleepDivision.get();
		ClockAdjustment newAdjustment = new ClockAdjustment.Relative(dividedTime);
		event.setAdjustment(newAdjustment);
	}
}
