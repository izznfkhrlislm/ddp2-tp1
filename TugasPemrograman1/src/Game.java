/*
 * Created by Izzan Fakhril Islam (1606875806)
 * Kelas	: DDP 2 -A
 * Project	: TugasPemrograman1
 * Date		: 23/03/2017
 * Time		: 16:32 PM
 * 
 * Class yang digunakan untuk mengatur Game (Permainan) dari League
 */

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private League liga;
	public static boolean isFinished = false;
	public static boolean manualMode = false;
	private  ArrayList<Team[]> listOfMatches;
	private Team[] playingTeams;
	private int gol1, yellCard1, redCard1, foul1, gol2, yellCard2, redCard2, foul2;
	
	/*
	 * Main Constructor dari kelas Game
	 * Fungsi: Untuk membentuk sebuah Game dengan jumlah Tim yang berpartisipasi sebanyak numInit
	 * @param numInit: banyaknya tim yang berpartisipasi dalam Game
	 */
	
	public Game(int numInit){
		liga = new League(numInit);
		//Menggunakan rumus kombinasi untuk menghitung banyaknya pertandingan yang terjadi
		int numberOfMatches = (numInit*(numInit-1))/2;  
		//Membentuk ArrayList berisi Array of Team
		this.listOfMatches = new ArrayList<Team[]>();  
		for (int i = 0; i < numberOfMatches; i++){
			this.listOfMatches.add(new Team[2]);
		}
		
		int counter = 0;
		for (int i = 0; i < numInit; i++){
			for (int j = i+1; j < numInit; j++){
				this.listOfMatches.get(counter)[0] = liga.getLeagueTable().get(i);
				this.listOfMatches.get(counter)[1] = liga.getLeagueTable().get(j);
				counter++;
			}
		}
		
		//Flag isFinished dimana penanda berakhirnya Liga (jika jumlah permainan mencapai 0)
		if (this.listOfMatches.size() == 0){
			isFinished = true;
		}
	}
	
	/*
	 * Show League Fixtures
	 * Fungsi: Untuk menampilkan daftar pertandingan Liga
	 */
	
	public void showFixtures(){
		System.out.println("DAFTAR PERTANDINGAN:");
		for (int i = 0; i < listOfMatches.size(); i++){
			System.out.println(this.listOfMatches.get(i)[0].getNamaTeam()+" vs "+this.listOfMatches.get(i)[1].getNamaTeam());
		}
	}
	
	/* Show League Final Screen
	 * Fungsi: Untuk menampilkan ringkasan (summary) dari Liga yang telah berjalan, dan menampilkan klasemen akhir dan
	 * 		   daftar top skorer akhir 
	 */
	
	public void showFinalScreen(){
		liga.updateLeague();
		liga.updateScorerList();
		System.out.println("LIGA FASILKOM MUSIM INI TELAH USAI!");
		System.out.println("CHAMPION: "+liga.getLeagueTable().get(0).getNamaTeam());
		System.out.println("TOP SCORER: "+League.getScorerList().get(0).getNamaPemain()+" ("+League.getScorerList().get(0).getTeamName()+")");
		System.out.println();
		System.out.println("KLASEMEN");
		liga.showKlasemen();
		System.out.println();
		System.out.println("TOP SCORER");
		League.showTopScorer();
		System.out.println();
		System.out.println("GOODBYE...");
	}
	
	/*
	 *  Show League Next Game
	 *  Fungsi: Menampilkan Pertandingan yang akan berlangsung
	 */
	
	public void showNextGame(){
		Team[] nextMatch = this.listOfMatches.get(0);
		System.out.println("Pertandingan Selanjutnya: "+nextMatch[0].getNamaTeam()+" vs "+nextMatch[1].getNamaTeam());
	}
	
	/*
	 * Manual Mode Next Game
	 * Fungsi: Untuk memproses pertandingan yang dilakukan dalam mode Manual
	 */
	
	public void manualNextGame(){
		playingTeams = this.listOfMatches.get(0);
	}
	
	public void manualNextGameRemove(){
		playingTeams = this.listOfMatches.remove(0);
	}
	
	/*
	 * Automatic Mode Next Game
	 * Fungsi: Memproses pertandingan dalam mode Otomatis
	 */
	
	public void nextGame(){
		Random r = new Random();
		playingTeams = this.listOfMatches.remove(0);
		
		Team tim1 = playingTeams[0];
		gol1 = r.nextInt(6);
		yellCard1 = r.nextInt(4);
		redCard1 = r.nextInt(3);
		foul1 = r.nextInt(6);
		
		Team tim2 = playingTeams[1];
		gol2 = r.nextInt(6);
		yellCard2 = r.nextInt(4);
		redCard2 = r.nextInt(3);
		foul2 = r.nextInt(6);
		
		matchResult(tim1, tim2, gol1, gol2, foul1, foul2, yellCard1, yellCard2, redCard1, redCard2);
		
		processGoals(gol1, tim1);
		processYellCard(yellCard1, tim1);
		processRedCard(redCard1, tim1);
		processFoul(foul1, tim1);
		
		processGoals(gol2, tim2);
		processYellCard(yellCard2, tim2);
		processRedCard(redCard2, tim2);
		processFoul(foul2, tim2);
		
		liga.updateLeague();
		liga.updateScorerList();
	}
	
	/*
	 * Match Result Process
	 * Fungsi: Memproses hasil pertandingan, dan memasukkan ke statistik setiap Tim dan Pemain
	 */
	
	public void matchResult(Team tim1, Team tim2, int gol1, int gol2, int foul1, int foul2, int yellCard1, int yellCard2, int redCard1, int redCard2){
		System.out.println("Statistik pertandingan Tim "+tim1.getNamaTeam()+" vs Tim "+tim2.getNamaTeam());
		System.out.println(tim1.getNamaTeam()+" "+gol1+" - "+gol2+" "+tim2.getNamaTeam());
		System.out.println("Tim: "+tim1.getNamaTeam());
		System.out.println("	Gol: "+gol1);
		System.out.println("	Pelanggaran: "+foul1);
		System.out.println("	Kartu kuning: "+yellCard1);
		System.out.println("	Kartu Merah: "+redCard1);
		
		System.out.println("Tim: "+tim2.getNamaTeam());
		System.out.println("	Gol: "+gol2);
		System.out.println("	Pelanggaran: "+foul2);
		System.out.println("	Kartu kuning: "+yellCard2);
		System.out.println("	Kartu Merah: "+redCard2);
		System.out.println();
		
		//Tim 1 menang
		if (gol1 > gol2){
			tim1.menang();
			tim1.goal(gol1);
			tim1.kebobolan(gol2);
			tim2.kalah();
			tim2.goal(gol2);
			tim2.kebobolan(gol1);
		}
		
		//Tim 1 kalah
		else if (gol1 < gol2){
			tim2.menang();
			tim2.goal(gol2);
			tim2.kebobolan(gol1);
			tim1.kalah();
			tim1.goal(gol1);
			tim1.kebobolan(gol2);
		}
		
		//Pertandingan Seri
		else if (gol1 == gol2) {
			tim1.seri();
			tim1.goal(gol1);
			tim1.kebobolan(gol2);
			tim2.seri();
			tim2.goal(gol2);
			tim2.kebobolan(gol1);
		}
		else {}
	}
	
	/*
	 * Match Attribute Process
	 * Fungsi: Memproses berbagai hal dalam sebuah pertandingan (Gol, Kartu Kuning, Kartu Merah, Pelanggaran) berdasarkan
	 * 	       nama tim
	 */
	
	public void processGoals(int jmlGol, Team tim){
		Random r = new Random();
		int container = 0;
		while ((jmlGol - container) != 0){
			for (int i = 0; i < tim.getListPemain().size(); i++){
				int gol = r.nextInt((jmlGol - container)+1);
				tim.getListPemain().get(i).addGol(gol);
				container += gol;
				if (tim.getListPemain().get(i).getJumlahGol() != 0){
					liga.addScorer(tim.getListPemain().get(i), gol);	
				}
			}
		}
	}
	
	public void processYellCard(int jmlKK, Team tim){
		Random r = new Random();
		int container = 0;
		while ((jmlKK - container) != 0){
			for (int i = 0; i < tim.getListPemain().size(); i++){
				int kk = r.nextInt((jmlKK - container)+1);
				tim.getListPemain().get(i).addKartuKuning(kk);
				container += kk;
			}
		}
	}
	
	public void processRedCard(int jmlKM, Team tim){
		Random r = new Random();
		int container = 0;
		while ((jmlKM - container) != 0){
			for (int i = 0; i < tim.getListPemain().size(); i++){
				int km = r.nextInt((jmlKM - container)+1);
				tim.getListPemain().get(i).addKartuMerah(km);
				container += km;
			}
		}
	}
	
	public void processFoul(int jmlFoul, Team tim){
		Random r = new Random();
		int container = 0;
		while ((jmlFoul - container) != 0){
			for (int i = 0; i < tim.getListPemain().size(); i++){
				int foul = r.nextInt((jmlFoul - container)+1);
				tim.getListPemain().get(i).addPelanggaran(foul);
				container += foul;
			}
		}
	}
	
	/*
	 * Attribute getter for Game object
	 */
	
	public League getLiga(){
		return liga;
	}
	
	public ArrayList<Team[]> getMatches() {
		return this.listOfMatches;
	}
	
	public Team[] getPlayingTeam() {
		return playingTeams;
	}
	
}