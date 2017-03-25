/*
 * Created by Izzan Fakhril Islam (1606875806)
 * Kelas	: DDP 2 - A
 * Project	: TugasPemrograman1
 * Date		: 23/03/2017
 * Time		: 10:56 AM
 * 
 * Class yang digunakan untuk membentuk objek Tim berdasarkan informasi dari objek Pemain
 */

import java.util.Random;
import java.util.ArrayList;

public class Team {
	private String namaTeam;
	private int peringkatTeam;
	private int jumlahMenang;
	private int jumlahKalah;
	private int jumlahSeri;
	private int jumlahPoin;
	private int jumlahGol;
	private int jumlahKebobolan;
	private Pemain pemain;
	private ArrayList<Pemain> listPemain;
	private ArrayList<Integer> listTemp;
	private Random r;
	
	//Daftar nama tim untuk diambil secara acak
	private static String[] listOfTeamNames =
		{"Gajah", "Rusa", "Belalang", "Kodok", "Kucing", "Tupai", "Rajawali", "Siput", "Kumbang", "Semut", 
		  "Ular", "Harimau", "Kuda", "Serigala", "Panda", "Kadal", "Ayam", "Bebek"};
	
	/*
	 * Main Constructor dari class Tim
	 * Fungsi: untuk membentuk objek Tim
	 * @param namaTeam sebuah String nama Tim
	 */
	
	public Team(String namaTeam){
		this.namaTeam = namaTeam;
		this.listPemain	 = new ArrayList<Pemain>();
		this.jumlahMenang = 0;
		this.jumlahKalah = 0;
		this.jumlahSeri = 0;
		this.jumlahPoin = 0;
		this.jumlahGol = 0;
		this.jumlahKebobolan = 0;
		r = new Random();
		this.listTemp = new ArrayList<>();
		
		//mengambil nomor random untuk nomor punggung pemain
		for (int i = 0; i < 5; i++){
			int randint = r.nextInt(99);
			while (exists(randint)){
				randint = r.nextInt(99);
			}
			this.pemain = new Pemain(Pemain.getListOfPlayerNames()[r.nextInt(Pemain.getListOfPlayerNames().length)], randint, this.namaTeam);
			this.listPemain.add(this.pemain);
		}
	}
	
	/*
	 * Unique Checker
	 * Fungsi: Untuk memastikan bahwa tidak ada nilai yang sama dalam sebuah objek (unique)
	 */
	
	public boolean exists(int number){
		if (listTemp.contains(number)){
			return true;
		}
		else {
			listTemp.add(number);
			return false;
		}
	}
	
	/*
	 * Menampilkan daftar objek Pemain yang berada di objek Tim
	 * Info yang ditampilkan: Nomor punggung, Nama, Gol, Pelanggaran, Kartu Kuning, Kartu Merah
	 */
	
	public void showTeam(){
		System.out.println("Nomor |   Nama  | Gol | Pelanggaran | Kartu Kuning | Kartu Merah ");
		System.out.println("-----------------------------------------------------------------");
		for (int i = 0; i < this.listPemain.size(); i++){
			System.out.printf("%6d|%9s|%5s|%13s|%14s|%13s\n", this.listPemain.get(i).getNoPemain(),
					this.listPemain.get(i).getNamaPemain(), this.listPemain.get(i).getJumlahGol(), this.listPemain.get(i).getJumlahPelanggaran(),
					this.listPemain.get(i).getJumlahKartuKuning(), this.listPemain.get(i).getJumlahKartuMerah());
		}
	}
	
	/*
	 * Attribute modifier untuk objek Tim
	 * Fungsi: menambahkan atribut untuk objek Tim (Jumlah Menang, Jumlah Kalah, Jumlah Seri, Jumlah Gol yang dicetak, Jumlah Gol kemasukan)
	 * @param jmlGol banyaknya gol yang dicetak oleh tim
	 * @param jmlMasuk banyaknya gol kemasukan yang dialami oleh tim
	 */
	
	public void menang(){
		this.jumlahMenang++;
		this.jumlahPoin += 3;
	}
	
	public void kalah(){
		this.jumlahKalah++;
	}
	
	public void seri(){
		this.jumlahSeri++;
		this.jumlahPoin++;
	}
	
	public void goal(int jmlGol){
		this.jumlahGol += jmlGol;
	}
	
	public void kebobolan(int jmlMasuk){
		this.jumlahKebobolan += jmlMasuk;
	}
	
	/*
	 * Attribute getter untuk objek Tim
	 */
	
	public static String[] getListOfTeamNames(){
		return Team.listOfTeamNames;
	}
	
	public String getNamaTeam() {
		return namaTeam;
	}

	public int getPeringkatTeam() {
		return peringkatTeam;
	}

	public int getJumlahMenang() {
		return jumlahMenang;
	}

	public int getJumlahKalah() {
		return jumlahKalah;
	}

	public int getJumlahSeri() {
		return jumlahSeri;
	}

	public int getJumlahPoin() {
		return jumlahPoin;
	}

	public int getJumlahGol() {
		return jumlahGol;
	}

	public int getJumlahKebobolan() {
		return jumlahKebobolan;
	}

	public ArrayList<Pemain> getListPemain() {
		return this.listPemain;
	}
}