/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

/**
 *
 * @author Lenovo
 */


/*kodingan gagal*/



import proyekokh.Schedule;
import proyekokh.Conflict;
import proyekokh.CourseSet;
import proyekokh.Optimizer;
import proyekokh.Utils;

public class TabuSearch {
    public void getTimeslotByTabuSearch() {
        String crs="";
        String stu="";
        CourseSet cs = new CourseSet(crs);
        Conflict cm = new Conflict(stu, cs.getSize());
        int [][] confMat = cm.getConflictMatrix();
        int jumlahSiswa = cm.getJumlahStudent();
        
        int[][] solution = Schedule.getSaturationSchedule(cs.getSize(), cm.getDegree(), confMat);
        
		timeslot = schedule.schedulingByDegree(course_sorted);
		
		// initial solution
		timeslotTabuSearch = schedule.getSchedule();
		initialPenalty = Evaluator.getPenalty(conflict_matrix, timeslotTabuSearch, jumlahmurid);
		
		int[][] bestTimeslot = Utils.copySolution(timeslotTabuSearch); // handle current best timeslot
		int[][] bestcandidate  = Evaluator.getTimeslot(timeslotTabuSearch);
		int[][] timeslotTabuSearchSementara = Evaluator.getTimeslot(timeslotTabuSearch);
    }
        
		
//		int timeslot_dibutuhkan = Arrays.stream(timeslot).max().getAsInt();
		
		//inisiasi tabulist
        LinkedList<int[][]> tabulist = new LinkedList<int[][]>();
        int maxtabusize = 10;
        tabulist.addLast(Evaluator.getTimeslot(timeslotTabuSearch));
        
      //inisiasi iterasi
        int maxiteration = 1000;
        int iteration=0;
        
      //inisasi itung penalty
        double penalty1 = 0;
        double penalty2 = 0;
        double penalty3 = 0;
        
        boolean terminate = false;
        
        while(!terminate){
            iteration++;
            
//            search candidate solution / search neighbor
//            sneighborhood = getneighbor(bestcandidate)
           ArrayList<int[][]> sneighborhood = new ArrayList<>();
//           ArrayList<Double> listPenalty = new ArrayList<>();     
              
//        		int[][] timeslotLLH;
        /*	LowLevelHeuristics lowLevelHeuristics = new LowLevelHeuristics(conflict_matrix);
        	timeslotTabuSearchSementara = lowLevelHeuristics.move1(timeslotTabuSearchSementara);
			sneighborhood.add(timeslotTabuSearchSementara);
			timeslotTabuSearchSementara = lowLevelHeuristics.swap2(timeslotTabuSearchSementara);
			sneighborhood.add(timeslotTabuSearchSementara);
			timeslotTabuSearchSementara = lowLevelHeuristics.move2(timeslotTabuSearchSementara);
			sneighborhood.add(timeslotTabuSearchSementara);
			timeslotTabuSearchSementara = lowLevelHeuristics.swap3(timeslotTabuSearchSementara);
			sneighborhood.add(timeslotTabuSearchSementara);
			timeslotTabuSearchSementara = lowLevelHeuristics.move3(timeslotTabuSearchSementara);
			sneighborhood.add(timeslotTabuSearchSementara);*/
				
        		
        		//membandingkan neighbor, pilih best neighbor, membandingkan juga apa ada di tabu list
           int j = 0;
           while (sneighborhood.size() > j) {
//        	   penalty2 = Evaluator.getPenalty(conflict_matrix, sneighborhood.get(j), jumlahmurid);
//               penalty1 = Evaluator.getPenalty(conflict_matrix, bestcandidate, jumlahmurid);
               if( !(tabulist.contains(sneighborhood.get(j))) && 
            		   Evaluator.getPenalty(conflict_matrix, sneighborhood.get(j), jumlahmurid) < Evaluator.getPenalty(conflict_matrix, bestcandidate, jumlahmurid))
                 bestcandidate = sneighborhood.get(j);
                	
               j++;
           }
                
           sneighborhood.clear();
                
           //bandingkan best neighbor dengan best best solution
           if(Evaluator.getPenalty(conflict_matrix, bestcandidate, jumlahmurid) < Evaluator.getPenalty(conflict_matrix, timeslotTabuSearch, jumlahmurid))
              timeslotTabuSearch = Evaluator.getTimeslot(bestcandidate);
                
           //masukkan best neighbor tadi ke tabu
           tabulist.addLast(bestcandidate);
           if(tabulist.size() > maxtabusize)
              tabulist.removeFirst();
                
           //return sbest;
           tabuSearchPenaltyList1 = new double[100];
           if ((iteration+1)%10 == 0)
               System.out.println("Iterasi: " + (iteration+1) + " memiliki penalty " + Evaluator.getPenalty(conflict_matrix, timeslotTabuSearch, jumlahmurid));

//           for (int i = 0 ; i < iteration; i ++) {
//        	   tabuSearchPenaltyList1[i] = listPenalty.get(i);
//           }
           
           if (iteration == maxiteration) 
        	   terminate = true;
        }
        bestPenalty = Evaluator.getPenalty(conflict_matrix, timeslotTabuSearch, jumlahmurid);
        deltaPenalty = ((initialPenalty-bestPenalty)/initialPenalty)*100;
        
        System.out.println("=============================================================");
		System.out.println("		Metode TABU SEARCH						 			 "); // print best penalty
		System.out.println("\nPenalty Initial : "+ initialPenalty); // print initial penalty
		System.out.println("Penalty Terbaik : " + bestPenalty); // print best penalty
		System.out.println("Terjadi Peningkatan Penalti : " + deltaPenalty + " % dari inisial solusi");
		System.out.println("Timeslot yang dibutuhkan : " + schedule.getJumlahTimeSlot(timeslotTabuSearch) + "\n");
		System.out.println("=============================================================");
	}
	