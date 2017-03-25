/*
 * Created by Izzan Fakhril Islam (1606875806)
 * Project	: TugasPemrograman1
 * Date		: 23/03/2017
 * Time		: 10:44 AM
 * 
 * Class yang digunakan untuk membentuk objek Pemain
 */

public class Pemain {
	private String namaPemain;
	private int noPemain;
	private int jumlahGol;
	private int jumlahPelanggaran;
	private int jumlahKartuKuning;
	private int jumlahKartuMerah;
	private String teamName;
	
	//Daftar nama Pemain, untuk diambil secara acak
	private static String[] listOfPlayerNames = 
		{"Arnold", "Kaidou", "Chopper", "Pica", "Enel", "Zoro", "Pedro", "Beckman", "Ace",
	     "Shiryu", "Sakazuki", "Marco", "Garp", "Dadan", "Sengoku", "Sanji", "Magellan",
	     "Dragon", "Sabo", "Smoker", "Luffy", "Franky", "Borsalino", "Buggy", "Crocodile",
	     "Shanks", "Yasopp", "Coby", "Burgess", "Usopp", "Law", "Kid", "Bege", "Yonji",
	     "Doffy", "Edward", "Mihawk", "Shanks", "Jinbei", "Killer", "Robin", "Roger",
	     "Shiki", "Rayleigh", "Robb", "Kuma", "Moriah", "Teach", "Pagaya", "Conis", "Hachi",
	     "Brook", "Kinemon", "Vergo", "Caesar", "Momo", "Mohji", "Cabaji", "Jozu", "Vista",
	     "Doma", "Augur", "Drake", "Ivankov", "Charlotte", "Bellamy", "Demaro", "Dorry",
	     "Brogy", "Kuro", "Zeff", "Gin", "Pearl", "Alvide", "Apoo", "Kuzan", "Nami", "SoulKing", "Hancock", "Koala"};
	
	/*
	 * Main Constructor dari class Pemain
	 * Fungsi: untuk membentuk objek Pemain
	 * @param namaPemain sebuah String nama Pemain
	 * @param noPemain sebuah angka nomor punggung Pemain
	 * @param teamName sebuah String nama tim dimana objek Pemain berada
	 */
	
	public Pemain(String namaPemain, int noPemain, String teamName){
		this.namaPemain = namaPemain;
		this.noPemain = noPemain;
		this.teamName = teamName;
		this.jumlahGol = 0;
		this.jumlahPelanggaran = 0;
		this.jumlahKartuKuning = 0;
		this.jumlahKartuMerah = 0;
	}
	
	/*
	 * Attribute Adder untuk objek Pemain
	 * Fungsi: Menambahkan atribut pemain (jumlah gol, jumlah kartu kuning, jumlah kartu merah, dan jumlah pelanggaran)
	 * @param jmlGol banyaknya jumlah gol yang dicetak oleh Pemain
	 * @param jmlKK banyaknya kartu kuning yang diberikan kepada Pemain
	 * @param jmlKM banyaknya kartu merah yang diberikan kepada Pemain
	 * @param jmlFoul banyaknya pelanggaran yang dilakukan oleh Pemain
	 */
	
	public void addGol(int jmlGol){
		this.jumlahGol += jmlGol;
	}
	
	public void addKartuKuning(int jmlKK){
		this.jumlahKartuKuning += jmlKK;
		this.jumlahPelanggaran += jmlKK;
		
		//jika mendapatkan 2 kartu kuning dalam satu pertandingan, Pemain akan mendapatkan 1 kartu merah
		if (this.jumlahKartuKuning > 0){
			if (this.jumlahKartuKuning % 2 == 0){
				addKartuMerah(this.jumlahKartuKuning/2);
			}
		}
	}
	
	public void addKartuMerah(int jmlKM){
		this.jumlahKartuMerah += jmlKM;
		this.jumlahPelanggaran += jmlKM;
	}
	
	public void addPelanggaran(int jmlFoul){
		this.jumlahPelanggaran += jmlFoul;
	}
	
	/*
	 * Menampilkan Informasi terkait dengan objek Pemain
	 * Jenis Informasi: Nomor punggung, Nama pemain, jumlah Gol, jumlah Pelanggaran, jumlah Kartu Kuning, jumlah Kartu Merah
	 */
	
	public void showPemain(){
		System.out.printf("Nomor: %s\n", this.noPemain);
		System.out.printf("Nama: %s\n", this.namaPemain);
		System.out.printf("Gol: %s\n", this.jumlahGol);
		System.out.printf("Pelanggaran: %s\n", this.jumlahPelanggaran);
		System.out.printf("Kartu Kuning: %s\n", this.jumlahKartuKuning);
		System.out.printf("Kartu Merah: %s\n", this.jumlahKartuMerah);
	}
	
	/*
	 * Attribute getter
	 * Fungsi: Mengambil nilai dari atribut yang ada di objek Pemain (untuk digunakan oleh class lain)
	 */
	
	//Mengambil sebuah Array of String berisi daftar nama Pemain
	public static String[] getListOfPlayerNames() {
		return Pemain.listOfPlayerNames;
	}
	
	//Mengambil sebuah String berisi nama Pemain
	public String getNamaPemain() {
		return namaPemain;
	}
	
	//Mengambil sebuah integer berisi nomor punggung Pemain
	public int getNoPemain() {
		return noPemain;
	}
	
	//Mengambil sebuah string berisi nama Tim
	public String getTeamName() {
		return teamName;
	}
	
	//Mengambil sebuah integer berisi jumlah gol yang dicetak oleh Pemain
	public int getJumlahGol() {
		return jumlahGol;
	}
	
	//Mengambil sebuah integer berisi jumlah pelanggaran yang dibuat oleh Pemain
	public int getJumlahPelanggaran() {
		return jumlahPelanggaran;
	}
	
	//Mengambil sebuah integer berisi jumlah kartu kuning yang diberikan kepada Pemain
	public int getJumlahKartuKuning() {
		return jumlahKartuKuning;
	}
	
	//Mengambil sebuah integer berisi jumlah kartu merah yang diberikan kepada Pemain
	public int getJumlahKartuMerah() {
		return jumlahKartuMerah;
	}
}