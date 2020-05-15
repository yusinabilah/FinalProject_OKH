/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekokh;

/**
 *
 * @author Lenovo
 */
public class Solution {
	private int[][] solution;
	private Double penalty;
	private int jumlahTimeslot = 0;
	
	public Solution(int size) {
		solution = new int[size][2];
	}
	
	public Solution(int[][] solution) {
		this.solution = solution;
	}
	
	public int[][] getSolution() {
		return this.solution;
	}
	
	public void setSolution(int[][] solution) {
		this.solution = solution;
	}
	
	public int getSize() {
		return this.solution.length;
	}
	
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
	public Double getPenalty() {
		return penalty;
	}
	
	public int getJumlahTimeslot() {
		for(int i = 0; i < solution.length; i++) {
			if(solution[i][1] > jumlahTimeslot)
				jumlahTimeslot = solution[i][1];
		}
		
		return jumlahTimeslot;
	}
}

