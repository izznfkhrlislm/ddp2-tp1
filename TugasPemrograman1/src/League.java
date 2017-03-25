/*
 * Created by Izzan Fakhril Islam (1606875806)
 * Kelas	: DDP 2 - A
 * Project	: TugasPemrograman1
 * Date		: 23/03/2017
 * Time		: 16:05 PM
 * 
 * Class yang digunakan untuk membentuk League (kumpulan dari objek Tim dan Pemain)
 */

import java.util.ArrayList;
import java.util.Random;
public class League {
	private int numberOfTeams;
	private ArrayList<Team> listTeam;
	private static ArrayList<Pemain> scorerList;
	private ArrayList<String> listTemp;
	private Team team;
	private Random r;
	
	/*
	 * Main Constructor dari kelas League
	 * Fungsi: Membentuk kelas League dengan jumlah Tim sebanyak numberOfTeams
	 * @param numberOfTeams jumlah objek Tim yang akan berpartisipasi di League
	 */
	
	public League(int numberOfTeams){
		this.numberOfTeams = numberOfTeams;
		
		//Membuat ArrayList berisi objek Pemain sebanyak jumlah user input
		if (this.numberOfTeams >= 4 && this.numberOfTeams <= 10){
			this.listTeam = new ArrayList<Team>();
		}
		
		else {
			if (this.numberOfTeams > 10){
				System.out.println("Jumlah tim minimal 4 tim dan maksimal 10 tim!");
			}
			else if (this.numberOfTeams < 4){
				System.out.println("Jumlah tim minimal 4 tim dan maksimal 10 tim!");
			}
		}
		
		//Membuat nama objek Tim secara acak dan tidak ada yang sama.
		this.listTemp = new ArrayList<>();
		League.scorerList = new ArrayList<Pemain>();
		r = new Random();
		
		for (int i = 0; i < numberOfTeams; i++){
			int randint = r.nextInt(18);
			while (exists(randint)){
				randint = r.nextInt(18);
			}
			this.team = new Team(Team.getListOfTeamNames()[randint]);
			this.listTeam.add(this.team);
		}
	}
	
	/*
	 * Unique Checker
	 * Fungsi: Menmeriksa apakah objek di Array sama atau tidak
	 */
	
		public boolean exists(int number){
				if (listTemp.contains(Team.getListOfTeamNames()[number])){	
					return true;
				}
				else {
					listTemp.add(Team.getListOfTeamNames()[number]);
					return false;
				}
		}
	
	/*
	 * Scorer Add
	 * Fungsi: Menambah Gol ke dalam Pemain
	 */
		
	public void addScorer(Pemain player, int jmlGol){
		if (!League.scorerList.contains(player)){
			League.scorerList.add(player);
		}
		else {
			League.scorerList.get(League.scorerList.indexOf(player)).addGol(jmlGol);
		}
	}
	
	/*
	 * Scorer List Update
	 * Fungsi: Memperbarui (update) daftar top skorer berdasarkan urutan gol terbanyak
	 */
	
	public void updateScorerList() {
		for (int i = League.scorerList.size()-1; i >= 0; i--){
			boolean swapped = false;
			for (int j = 0; j < i; j++){
				if (League.scorerList.get(j).getJumlahGol() < League.scorerList.get(j+1).getJumlahGol()) {
					Pemain temp = League.scorerList.get(j);
					League.scorerList.set(j, League.scorerList.get(j+1));
					League.scorerList.set(j+1, temp);
					swapped = true;
				}
			}
			if (!swapped) {
				return;
			}
		}
	}
	
	/*
	 * League Table Updater
	 * Fungsi: Mengurutkan Tim berdasarkan jumlah poin, dan jika jumlah poin sama, akan
	 * 		   diurut berdasarkan selisih gol (gol masuk - gol keluar); 
	 */
	public void updateLeague() { 
		for (int i = this.listTeam.size()-1; i >= 0; i--){
			boolean swapped = false;
			for (int j = 0; j < i; j++){
				if (this.listTeam.get(j).getJumlahPoin() < this.listTeam.get(j+1).getJumlahPoin()){
					Team temp = this.listTeam.get(j);
					this.listTeam.set(j, this.listTeam.get(j+1));
					this.listTeam.set(j+1, temp);
					swapped = true;
				}
				
				//Jika poin sama, menggunakan Selisih Gol;
				else if (this.listTeam.get(j).getJumlahPoin() == this.listTeam.get(j+1).getJumlahPoin()) { 
					if ((this.listTeam.get(j).getJumlahGol() - this.listTeam.get(j).getJumlahKebobolan()) < 
							((this.listTeam.get(j+1).getJumlahGol() - this.listTeam.get(j+1).getJumlahKebobolan()))){
						Team temp = this.listTeam.get(j);
						this.listTeam.set(j, this.listTeam.get(j+1));
						this.listTeam.set(j+1, temp);
						swapped = true;
					}
				}
			}
			if (!swapped){
				return;
			}
		}
	}
	
	/*
	 * Show League Table
	 * Fungsi: Menampilkan Klasemen Liga
	 */
	
	public void showKlasemen() {
		System.out.println("Peringkat | Nama Tim | Jumlah Gol | Jumlah Kebobolan | Menang | Kalah | Seri | Poin");
		System.out.println("-----------------------------------------------------------------------------------");
		for (int i = 0; i < this.listTeam.size(); i++){
			System.out.printf("%10d|%10s|%12d|%18d|%8d|%7d|%6d|%5d\n", 
					i+1, this.listTeam.get(i).getNamaTeam(), this.listTeam.get(i).getJumlahGol(), this.listTeam.get(i).getJumlahKebobolan(),
					this.listTeam.get(i).getJumlahMenang(), this.listTeam.get(i).getJumlahKalah(), this.listTeam.get(i).getJumlahSeri(),
					this.listTeam.get(i).getJumlahPoin());
		}
	}
	
	/*
	 * Show Top Scorer
	 * Fungsi: Menampilkan daftar pencetak gol (10 terbanyak)
	 */
	
	public static void showTopScorer() {
		System.out.println("Peringkat | Nama Pemain | Nama Tim | Jumlah Gol");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < 10; i++){
			System.out.printf("%10d|%13s|%10s|%11d\n", i+1, League.scorerList.get(i).getNamaPemain(),
					League.scorerList.get(i).getTeamName(), League.scorerList.get(i).getJumlahGol());
		}
	}
	
	/*
	 * Team Getter based on Team Name
	 * Mengambil objek Tim berdasarkan nama dari objek Tim itu
	 * @param teamName nama Tim yang akan dicari
	 */
	
	public Team getTeamWithName(String teamName){
		for (Team tim: this.getLeagueTable()){
			if (tim.getNamaTeam().equals(teamName)){
				return tim;
			}
		}
		return null;
	}
	
	/*
	 * Player Getter based on Player Name
	 * Mengambil objek Pemain berdasarkan nama dari objek Pemain itu
	 * @param playerName nama Pemain yang akan dicari
	 * @param teamName nama Tim dimana objek Pemain itu berada
	 */
	
	public Pemain getPlayerWithName(String playerName, String teamName){
		Team tim = getTeamWithName(teamName);
		if (tim != null){
			for (Pemain pemain: tim.getListPemain()){
				if (pemain.getNamaPemain().equals(playerName)){
					return pemain;
				}
			}
			return null;
		}
		return null;
	}
	
	/*
	 * Player Getter based on Player Number
	 * Mengambil objek Pemain berdasarkan nomor dari objek Pemain itu
	 * @param playerNum Sebuah integer nomor pemain yang akan dicari
	 * @param teamName Sebuah String nama Tim dimana objek Pemain itu berada
	 */
	
	public Pemain getPlayerNum(int playerNum, String teamName){
		Team tim = getTeamWithName(teamName);
		if (tim != null){
			for (Pemain pemain: tim.getListPemain()){
				if (pemain.getNoPemain() == playerNum){
					return pemain;
				}
			}			
		}
		return null;
	}
	
	/*
	 * Attribute getter untuk objek League
	 */
	
	public ArrayList<Team> getLeagueTable() {
		return this.listTeam;
	}
	
	public int getNumberOfTeams() {
		return numberOfTeams;
	}
	
	public static ArrayList<Pemain> getScorerList() {
		return League.scorerList;
	}
}