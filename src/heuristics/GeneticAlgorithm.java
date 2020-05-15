/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;


import proyekokh.Conflict;
import proyekokh.CourseSet;
import proyekokh.Schedule;
import proyekokh.Utils;
import proyekokh.Solution;
import java.util.ArrayList;

public class GeneticAlgorithm {
	private static ArrayList<Solution> recombination(Solution s1, Solution s2) {
		int[][] sol1 = s1.getSolution();
		int[][] sol2 = s2.getSolution();
		
		int n = 1;
		
		int[][] temp1 = Utils.copySolution(sol1);
		int[][] temp2 = Utils.copySolution(sol2);
				
		for(int i=0; i < n; i++) {
			sol1[i][1] = temp2[temp2.length-n+i][1];
			sol2[temp2.length-n+i][1] = temp1[i][1];
		}
		
		Solution solution1 = new Solution(sol1);
		Solution solution2 = new Solution(sol2);
		
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		solutions.add(solution1);
		solutions.add(solution2);
		
		return solutions;
	}
	
	private static Solution mutation(Solution solution) {
		int[][] arrSol = solution.getSolution();
		
		int step = (int)0.1 * arrSol.length;
		
		for(int i = 0; i < step; i++) {
			int randomExam = Utils.getRandomNumber(1, arrSol.length-1);
			int randomTimeslot = Utils.getRandomNumber(1, solution.getJumlahTimeslot());
			
			arrSol[randomExam][1] = randomTimeslot;
		}
		
		
		Solution mutationSolution = new Solution(arrSol);
		
		return mutationSolution;
	}
	
	private static ArrayList<Solution> generatePopulation(CourseSet cs, Conflict cm, int populationSize) {
		int jumlahStudent = cm.getJumlahStudent();		
		ArrayList<Solution> population = new ArrayList<Solution>();
		
		for(int i = 0; i < populationSize; i++) {
			int[][] matrix = cm.getLargestDegree();
			int[][] confMat = cm.getConflictMatrix();
			
			Schedule scheduler = new Schedule(cs.getSize());
			scheduler.timesloting(matrix, 100);
			scheduler.printSchedule(cm.getDegree());
			int[][] solution = Utils.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat);
			
			Solution s = new Solution(solution);
			population.add(s);
			
			double penalty = Utils.getPenalty(confMat, solution, jumlahStudent);
			s.setPenalty(penalty);
		}
		
		return population;
	}
	
	public static void run(String dir_stu, String dir_crs, int populationSize, int iterasi) {
		CourseSet cs = new CourseSet(dir_crs);
		Conflict cm = new Conflict(dir_stu, cs.getSize());
		
		int jumlahStudent = cm.getJumlahStudent();
		int[][] conflictMatrix = cm.getConflictMatrix();
		
		ArrayList<Solution> population = generatePopulation(cs, cm, populationSize);
		
		int iter = 0;
				
		Solution bestSolution = new Solution(cs.getSize());
		
		bestSolution.setSolution(Utils.copySolution(population.get(0).getSolution()));
		bestSolution.setPenalty(Utils.getPenalty(conflictMatrix, bestSolution.getSolution(), jumlahStudent));
		
		while(iter < iterasi) {
			population.sort((o1, o2) -> o1.getPenalty().compareTo(o2.getPenalty()));
			
			// Recombination
			Solution solution1 = population.get(0);
			Solution solution2 = population.get(1);
			
			ArrayList<Solution> solRecombination = recombination(solution1, solution2);
			solution1 = solRecombination.get(0);
			solution2 = solRecombination.get(1);
			
			if(Utils.isNotTabrakan(conflictMatrix, solution1.getSolution()))
				solution1.setPenalty(Utils.getPenalty(conflictMatrix, solution1.getSolution(), jumlahStudent));
			else
				solution1.setPenalty(1000);
			
			if(Utils.isNotTabrakan(conflictMatrix, solution2.getSolution()))
				solution2.setPenalty(Utils.getPenalty(conflictMatrix, solution2.getSolution(), jumlahStudent));
			else
				solution2.setPenalty(1000);
						
			// Mutation
			solution1 = mutation(solution1);
			solution2 = mutation(solution2);
			
			if(Utils.isNotTabrakan(conflictMatrix, solution1.getSolution()))
				solution1.setPenalty(Utils.getPenalty(conflictMatrix, solution1.getSolution(), jumlahStudent));
			else
				solution1.setPenalty(1000);
			
			if(Utils.isNotTabrakan(conflictMatrix, solution2.getSolution()))
				solution2.setPenalty(Utils.getPenalty(conflictMatrix, solution2.getSolution(), jumlahStudent));
			else
				solution2.setPenalty(1000);
			
			
			if(Utils.isNotTabrakan(conflictMatrix, solution1.getSolution()) && Utils.isNotTabrakan(conflictMatrix, solution2.getSolution())) {
				if(solution1.getPenalty() < solution2.getPenalty() && solution1.getPenalty() < bestSolution.getPenalty())
					bestSolution = solution1;
				else if(solution2.getPenalty() < solution1.getPenalty() && solution2.getPenalty() < bestSolution.getPenalty())
					bestSolution = solution2;
			}
			
			if(Utils.isNotTabrakan(conflictMatrix, solution1.getSolution()) && Utils.isNotTabrakan(conflictMatrix, solution2.getSolution())) {
				if((iter+1) % 10 == 0) {
					if(solution1.getPenalty() < solution2.getPenalty())
						System.out.println("Iterasi ke-" + (iter+1) + " " + solution1.getPenalty());
					else
						System.out.println("Iterasi ke-" + (iter+1) + " " + solution2.getPenalty());
				}
			} else {
				if((iter+1) % 10 == 0)
					System.out.println("Iterasi ke-" + (iter+1) + " " + bestSolution.getPenalty());
			}
			
			population = generatePopulation(cs, cm, populationSize);
			
			iter++;
			
		}	
		
		System.out.println();
		System.out.println("Best solution : " + bestSolution.getPenalty());
		System.out.println("Jumlah timeslot : " + bestSolution.getJumlahTimeslot());
	}
	
}
