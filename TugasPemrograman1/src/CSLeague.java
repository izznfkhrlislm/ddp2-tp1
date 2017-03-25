/*
 * Created by Izzan Fakhril Islam (1606875806)
 * Kelas	: DDP 2 - A
 * Project	: TugasPemrograman1
 * Date		: 24/03/2017
 * Time		: 16:48 PM
 * 
 * Kelas Utama (Main Class)
 */

import java.util.Scanner;

public class CSLeague {
	private static Scanner scan;
	private static int gol1;
	private static int gol2;
	private static int yellcard1;
	private static int yellcard2;
	private static int redcard1;
	private static int redcard2;
	private static int foul1;
	private static int foul2;
	private static Team tim1, tim2;
	
	/*
	 * Main Method dari project TugasPemrograman1
	 */
	
	public static void main(String[] args){
		boolean hasInitiated = false;
		scan = new Scanner(System.in);
		
		Game game = null;
		while (!hasInitiated){
			try{
				//Perintah Init (Inisialisasi jumlah Tim)
				System.out.print("WELCOME!\n[CSL2017] > ");
				String input = scan.nextLine();
				String[] input1 = input.split(" ");
				if (input1[0].equalsIgnoreCase("INIT")){
					int numInit = Integer.parseInt(input1[1]);
					game = new Game(numInit);
					System.out.println("LIGA FASILKOM MUSIM INI TELAH DIMULAI");
					//game.showFixtures();
					hasInitiated = true;
				}
				else {
					System.out.println("ERROR: Game belum di init, silakan init terlebih dahulu dengan perintah: init [jumlahTim]");
				}	
			}
			catch (Exception e){}
			
		}
		//Setelah Inisialisasi jumlah Tim
		while (!Game.isFinished){
			try {
				System.out.print("[CSL2017] > ");
				String input = scan.nextLine();
				String[] input1 = input.split(" ");
				String[] input2 = input.split(" -");
				
				if (input1[0].equalsIgnoreCase("INIT")){
					System.out.println("ERROR : Game sudah di-init, init gagal!");
				}
				
				//Perintah untuk menampilkan Informasi terkait Liga
				else if(input1[0].equalsIgnoreCase("show")){
					try{
						//Menampilkan Klasemen
						if(input1[1].equalsIgnoreCase("klasemen")){  
							game.getLiga().showKlasemen();
						}
						//Menampilkan daftar Pencetak Gol
						else if(input1[1].equalsIgnoreCase("pencetakGol")){
							League.showTopScorer();
						}
						//Menampilkan Pertandingan Selanjutnya
						else if(input1[1].equalsIgnoreCase("nextGame")){
							game.showNextGame();
						}
						//Menampilkan Informasi Tim
						else if ("tim".equalsIgnoreCase(input1[1])){ 
							try {
								int team = game.getLiga().getLeagueTable().indexOf(game.getLiga().getTeamWithName(input1[2]));
								game.getLiga().getLeagueTable().get(team).showTeam();	
							}
							//Exception handling: Jika tim yang dicari tidak ada dalam Liga
							catch (Exception e){
								System.out.println("ERROR: Tim "+input1[2]+" tidak ada dalam liga!");
							}
						}
						
						//Menampilkan Informasi Pemain
						else if ("pemain".equalsIgnoreCase(input1[1])){  
							try{
								int team = game.getLiga().getLeagueTable().indexOf(game.getLiga().getTeamWithName(input1[2]));
								int player = game.getLiga().getLeagueTable().get(team).getListPemain().indexOf(game.getLiga().getPlayerWithName(input1[3], input1[2]));
								game.getLiga().getLeagueTable().get(team).getListPemain().get(player).showPemain();
							}
							//Exception handling: Jika Pemain yang dicari tidak ada dalam Tim
							catch (Exception e){  
								System.out.println("ERROR: Pemain dengan nama"+input1[3]+" bukan anggota dari Tim "+input1[2]+"!");
							}
						}
						
						else {
							System.out.println("ERROR: Perintah tidak dikenali!");
						}
					}
					catch (Exception e) {
						System.out.println("ERROR: Perintah tidak dikenali!");
					}
				}
				
				//Perintah Next Game secara Random
				else if("nextGame".equalsIgnoreCase(input1[0])){
					if (input1.length == 1){
						game.nextGame();
						if (game.getMatches().size() == 0){
							game.showFinalScreen();
							Game.isFinished = true;
						}
					}
					
					//Menjalankan pertandingan secara otomatis hingga Liga selesai
					else {
						if ("-all".equalsIgnoreCase(input1[1])){
							System.out.println(game.getMatches().size());
							int n = game.getMatches().size();
							for (int i = 0; i < n; i++){
								game.nextGame();
							}
							game.showFinalScreen();
							Game.isFinished = true;
						}
						
						//Manual mode
						else {
							
							//Flag tidakAdaPemain bernilai true jika terjadi Error
							boolean tidakAdaPemain = false;
							game.manualNextGame(); 
							for (int i = 1; i < input2.length; i++){
								String[] manual = input2[i].split(" ");
								tim1 = game.getPlayingTeam()[0];
								tim2 = game.getPlayingTeam()[1];
								int noPemain = Integer.parseInt(manual[2]);
								
								//Perintah -g (Memproses gol)
								if ("g".equalsIgnoreCase(manual[0])){
									try {
										if (manual[1].equalsIgnoreCase(tim1.getNamaTeam())){
											int playerNameIndex = tim1.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim1.getNamaTeam()));
											tim1.getListPemain().get(playerNameIndex).addGol(1);
											gol1++;
										}
										
										else if (manual[1].equalsIgnoreCase(tim2.getNamaTeam())){
											int playerNum = tim2.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim2.getNamaTeam()));
											tim2.getListPemain().get(playerNum).addGol(1);
											gol2++;
										}
										
										else {
											System.out.println("ERROR: Tim "+manual[1]+" sedang tidak bertanding atau salah tim!");
											tidakAdaPemain = true;
										}
									}
									
									catch (Exception e) {
										System.out.println("ERROR: Tidak ada pemain nomor "+manual[2]+" di Tim "+manual[1]+"!");
										tidakAdaPemain = true;
									}
								}
								
								//Perintah -kk (Memproses kartu kuning)
								else if ("kk".equalsIgnoreCase(manual[0])){
									try {
										if (manual[1].equalsIgnoreCase(tim1.getNamaTeam())){
											int playerNameIndex = tim1.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim1.getNamaTeam()));
											tim1.getListPemain().get(playerNameIndex).addKartuKuning(1);
											yellcard1++;
										}
										
										else if (manual[1].equalsIgnoreCase(tim2.getNamaTeam())){
											int playerNum = tim2.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim2.getNamaTeam()));
											tim2.getListPemain().get(playerNum).addKartuKuning(1);
											yellcard2++;
										}
										
										else {
											System.out.println("ERROR: Pemain dengan nomor "+manual[2]+" sedang tidak bertanding atau salah tim!");
											tidakAdaPemain = true;
										}
									}
									
									catch (Exception e){
										System.out.println("ERROR: Tidak ada pemain nomor "+manual[2]+" di Tim "+manual[1]+"!");
										tidakAdaPemain = true;
									}
									
								}
								
								//Perintah -km (Memproses kartu merah)
								else if ("km".equalsIgnoreCase(manual[0])){
									try {
										if (manual[1].equalsIgnoreCase(tim1.getNamaTeam())){
											int playerNameIndex = tim1.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim1.getNamaTeam()));
											tim1.getListPemain().get(playerNameIndex).addKartuMerah(1);
											redcard1++;
										}
										
										else if (manual[1].equalsIgnoreCase(tim2.getNamaTeam())){
											int playerNameIndex = tim2.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim2.getNamaTeam()));
											tim2.getListPemain().get(playerNameIndex).addKartuMerah(1);
											redcard2++;
										}
										
										else {
											System.out.println("ERROR: Tim "+manual[1]+" sedang tidak bertanding atau salah tim!");
											tidakAdaPemain = true;
										}
									}
									catch (Exception e){
										tidakAdaPemain = true;
									}
								}
								
								//Perintah -p (Memproses pelanggaran)
								else if ("p".equalsIgnoreCase(manual[0])){
									try {
										if (manual[1].equalsIgnoreCase(tim1.getNamaTeam())){
											int playerNameIndex = tim1.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim1.getNamaTeam()));
											tim1.getListPemain().get(playerNameIndex).addPelanggaran(1);
											foul1++;
										}
										
										else if (manual[1].equalsIgnoreCase(tim2.getNamaTeam())){
											int playerNameIndex = tim2.getListPemain().indexOf(game.getLiga().getPlayerNum(noPemain, tim2.getNamaTeam()));
											tim2.getListPemain().get(playerNameIndex).addPelanggaran(1);
											foul2++;
										}
										
										else {
											System.out.println("ERROR: Tim "+manual[1]+" sedang tidak bertanding atau salah tim!");
											tidakAdaPemain = true;
										}
									}
									catch (Exception e){
										tidakAdaPemain = true;
									}
								}
								
								else{
									System.out.println("ERROR: Perintah tidak dikenali!");
									tidakAdaPemain = true;
								}
							}
							
							//Jika kartu kuning mendapatkan 2, akan mendapatkan 1 kartu merah
							if (yellcard1 > 1){
								for (int i = 0; i < (yellcard1 / 2); i++){
									redcard1++;
								}
							}
							
							if (yellcard2 > 1){
								for (int i = 0; i < (yellcard2 / 2); i++){
									redcard2++;
								}
							}
							
							if (!tidakAdaPemain){
								game.manualNextGameRemove();
								game.matchResult(tim1, tim2, gol1, gol2, foul1, foul2, yellcard1, yellcard2, redcard1, redcard2);
								game.getLiga().updateLeague();
								game.getLiga().updateScorerList();
							}
							
							//Me-reset static variable kembali ke 0
							gol1 = 0; gol2 = 0; foul1 = 0; foul2 = 0; yellcard1 = 0; yellcard2 = 0; redcard1 = 0; redcard2 = 0;
						}
					}
				}
				
				//Keluar secara paksa dari program
				else if("quit".equalsIgnoreCase(input1[0])){
					System.out.println("Liga dibatalkan di tengah jalan.");
					break;
				}
				
				else {
					System.out.println("ERROR: Perintah tidak dikenali!");
				}
			}
			catch (Exception e){
				System.out.println("ERROR: Perintah tidak dikenali!");
			}
		}
	}
}