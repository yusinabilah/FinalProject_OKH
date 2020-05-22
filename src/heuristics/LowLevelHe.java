/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

/**
 *
 * Author by Muslikh
  Modified by Mursyidatun Nabilah*/ 

import java.util.Arrays;
import java.util.Random;
import proyekokh.Conflict;
import proyekokh.Schedule;

public class LowLevelHe {

	static int[][] conflictMatrix;
	LowLevelHe(int[][] confMatrix) {
		conflictMatrix = confMatrix;
	}
	
	public static int[][] move(int[][] timeslot, int jumlahmove) {
		int[][] timeslotTemp = timeslot;
		int[] slot = new int[timeslotTemp.length];
		
		for (int i=0 ; i < timeslotTemp.length; i++) {
			slot[i] = timeslotTemp[i][1];
		}
		
		for (int i = 0; i < jumlahmove; i++) {
//			int randomCourse = randomNumber(1, timeslot.length);
//			int randomTimeSlot = randomNumber(1, Arrays.stream(slot).max().getAsInt());
			int randomCourse = random(timeslot.length);
			int randomTimeSlot = random(Arrays.stream(slot).max().getAsInt());
			System.out.println("number to random: " + Arrays.stream(slot).max().getAsInt());
			
			timeslotTemp[randomCourse][1] = randomTimeSlot;
		}
		
		return timeslotTemp;
	}
	public int[][] move1(int[][] timeslot) {
		int[][] timeslotTemp = timeslot;
		int[] slot = new int[timeslotTemp.length];
		
		for (int i=0 ; i < timeslotTemp.length; i++) {
			slot[i] = timeslotTemp[i][1];
		}
		
		
		int randomCourse = random(timeslot.length);
		int randomTimeSlot = random(Arrays.stream(slot).max().getAsInt());
//		System.out.println("number timeslot to random: " + randomTimeSlot);
		if (Schedule.checkRandomTimeslot(randomCourse, randomTimeSlot, conflictMatrix, timeslot))
			timeslotTemp[randomCourse][1] = randomTimeSlot;
		
		return timeslotTemp;
	}
	public int[][] move2(int[][] timeslot) {
		int[][] timeslotTemp = timeslot;
		int[] slot = new int[timeslotTemp.length];
		
		for (int i=0 ; i < timeslotTemp.length; i++) {
			slot[i] = timeslotTemp[i][1];
		}
		
		int randomCourse1 = random(timeslot.length);
		int randomCourse2 = random(timeslot.length);
		int randomTimeSlot1 = random(Arrays.stream(slot).max().getAsInt());
		int randomTimeSlot2 = random(Arrays.stream(slot).max().getAsInt());
//		System.out.println("number timeslot to random: " + randomTimeSlot1 + " , " + randomTimeSlot2);
		if (Schedule.checkRandomTimeslot(randomCourse1, randomTimeSlot1, conflictMatrix, timeslot))
			timeslotTemp[randomCourse1][1] = randomTimeSlot1;
	
		if (Schedule.checkRandomTimeslot(randomCourse2, randomTimeSlot2, conflictMatrix, timeslot))
			timeslotTemp[randomCourse2][1] = randomTimeSlot2;
		
		return timeslotTemp;
	}
	public int[][] move3(int[][] timeslot) {
		int[][] timeslotTemp = timeslot;
		int[] slot = new int[timeslotTemp.length];
		
		for (int i=0 ; i < timeslotTemp.length; i++) {
			slot[i] = timeslotTemp[i][1];
		}
		
		int randomCourse1 = random(timeslot.length);
		int randomCourse2 = random(timeslot.length);
		int randomCourse3 = random(timeslot.length);
		int randomTimeSlot1 = random(Arrays.stream(slot).max().getAsInt());
		int randomTimeSlot2 = random(Arrays.stream(slot).max().getAsInt());
		int randomTimeSlot3 = random(Arrays.stream(slot).max().getAsInt());
//		System.out.println("number timeslot to random: " + randomTimeSlot1 + " , " + randomTimeSlot2 + " , " + randomTimeSlot3);
		if (Schedule.checkRandomTimeslot(randomCourse1, randomTimeSlot1, conflictMatrix, timeslot))
			timeslotTemp[randomCourse1][1] = randomTimeSlot1;
		
		if (Schedule.checkRandomTimeslot(randomCourse2, randomTimeSlot2, conflictMatrix, timeslot))
			timeslotTemp[randomCourse2][1] = randomTimeSlot2;
		
		if (Schedule.checkRandomTimeslot(randomCourse3, randomTimeSlot3, conflictMatrix, timeslot))
			timeslotTemp[randomCourse3][1] = randomTimeSlot3;
		
		return timeslotTemp;
	}
	
	public static int[][] swap(int[][] timeslot, int jumlahswap) {
		int[][] timeslotTemp= timeslot;
		
		for(int i=0; i < jumlahswap; i++) {
			int exam1 = randomNumber(0, timeslot.length);
			int exam2 = randomNumber(0, timeslot.length);
			
			int slot1 = timeslot[exam1][1];
			int slot2 = timeslot[exam2][1];
			
			timeslotTemp[exam1][1] = slot2;
			timeslotTemp[exam2][1] = slot1;
		}
		
		return timeslotTemp;
	}
	public int[][] swap2(int[][] timeslot) {
		int[][] timeslotSementara = timeslot;
		
		
		int randomcourse1 = random(timeslot.length);
		int randomcourse2 = random(timeslot.length);
		while (randomcourse2 == randomcourse1) {
			randomcourse2 = random(timeslot.length);
		}
		
		int slot1 = timeslot[randomcourse1][1];
		int slot2 = timeslot[randomcourse2][1];
		
		if (Schedule.checkRandomTimeslot(randomcourse1, slot2, conflictMatrix, timeslot)) {
			timeslotSementara[randomcourse1][1] = slot2;
		}
		if (Schedule.checkRandomTimeslot(randomcourse2, slot1, conflictMatrix, timeslot)) {
			timeslotSementara[randomcourse2][1] = slot1;
		}
		return timeslotSementara;
	}
	public int[][] swap3(int[][] timeslot) {
		int[][] timeslotSementara = timeslot;
		
		int randomcourse1, randomcourse2, randomcourse3;
		do {
			randomcourse1 = random(timeslot.length);
			randomcourse2 = random(timeslot.length);
			randomcourse3 = random(timeslot.length);
		} while (randomcourse2 == randomcourse1 || randomcourse2 == randomcourse3 || randomcourse1 == randomcourse3);
		
		int slot1 = timeslot[randomcourse1][1];
		int slot2 = timeslot[randomcourse2][1];
		int slot3 = timeslot[randomcourse3][1];
			
//		timeslotSementara[randomcourse1][1] = slot2;
//		timeslotSementara[randomcourse2][1] = slot1;
		
		if (Schedule.checkRandomTimeslot(randomcourse1, slot2, conflictMatrix, timeslot)) {
			timeslotSementara[randomcourse1][1] = slot2;
		}
		if (Schedule.checkRandomTimeslot(randomcourse2, slot3, conflictMatrix, timeslot)) {
			timeslotSementara[randomcourse2][1] = slot3;
		}
		if (Schedule.checkRandomTimeslot(randomcourse3, slot1, conflictMatrix, timeslot)) {
			timeslotSementara[randomcourse3][1] = slot1;
		}
		return timeslotSementara;
	}
	private static int randomNumber(int min, int max) {
		Random random = new Random();
		try {
			return random.nextInt(max - min) + min;	
		}
			catch(Exception e) {
//				System.out.println("ERROR di nextInt: " + (max-min));
				//return random.nextInt(Math.abs(max - min)) + min;
				if (Math.abs(max - min) == 0) {
					return random.nextInt(Math.abs(max - min)+1) + min;
				}
					else
						return random.nextInt(Math.abs(max - min)) + min;
			}
	}
	
	private static int random(int number) {
		Random random = new Random();
		return random.nextInt(number);
	}
}

