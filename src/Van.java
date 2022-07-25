import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


//Merupakan inheritance dari Van
public class Van extends Transportasi{
    
    static Scanner input = new Scanner(System.in);
    
    //Constructor
    public Van() {
    }

    public Van(String kodeVan, String namaVan, String PlatTransportasi, int JumlahPenumpang, String StatusVan, int HargaSewa) {
        this.kodeTransport = kodeVan;
        this.namaTransport = namaVan;
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusTransport = StatusVan;
        this.HargaSewa = HargaSewa;
    }

    //Getter Setter
    public int getJumlahPenumpang() {
        return this.JumlahPenumpang;
    }

    public void setJumlahPenumpang(int JumlahPenumpang) {
        this.JumlahPenumpang = JumlahPenumpang;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk memasukkan data dari file van.txt ke arraylist van
    public static ArrayList<Van> updateVan (ArrayList<Van> van) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                van.add(new Van(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5])));
            }
        }
        return van;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : - Berfungsi untuk mengupdate textfile dan arraylist jika ada 1 data yang perlu diganti
    //               - Contoh : Menggantikan status van "Tersedia" ke "Dipinjam" atau sebaliknya yang ada di file txt
    //               - OverLoading dengan method diatas karena nama yang sama tetapi parameter berbeda
    public static void updateVan (String kodeVan, String status) throws IOException{
        String FilePath = "C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt";
        File oldFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt");
        File newFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeVan)) {
                        String row = data[0] + "," + data[1] + "," + data[2] +  "," + data[3] + "," ;
                        row = row + status + "," + data[5];
                        pw.print(row);
                    } else {
                        String row =  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeVan)) {
                        String row = "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," ;
                        row = row + status + "," + data[5];
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                }
                i++;
            }
            br.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(FilePath);
            newFile.renameTo(dump);
        }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : - Berfungsi untuk mencetak informasi mengenai van dengan kriteria tertentu
    //               - Contoh : Yang dibawah hanya mencetak Kode, Jenis, Transimisi, Penumpang, dan juga Harga.
    public static void displayAturanVan (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt"))) {
            String s = "";
            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    for (int i = 0; i < 6; i++) {
                        if ((i == 1) || (i == 5)) {
                            System.out.print(data[i] + "\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t|"); 
                        } else if ((i==3) || (i==1) ){
                            System.out.print(data[i] + "\t\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mendaftar van baru ke arraylist dan juga txt file
    public static void DaftarVan (ArrayList<Van> vans) throws Exception {
        System.out.println("Daftar Van Baru");
                System.out.println("-----------------");
                System.out.print("Nama van : ");
                String namaVan = input.next();
                System.out.print("Masukkan jumlah penumpang : ");
                int penumpang = input.nextInt();
                System.out.print("Masukkan harga sewa : ");
                int harga = input.nextInt();
                System.out.print("Masukkan plat transportasi : ");
                String plat1 = input.next();
                String plat2 = input.next();
                String plat3 = input.next();
                String plat = plat1 + " " + plat2 + " " + plat3;

                String kodeVan = "V0" + Integer.toString(vans.size()+1);

                //Data pelanggan masuk ke ArrayList
                vans.add(new Van(kodeVan, namaVan, plat, penumpang, "Tersedia", harga));
                //Data pelanggan dicetak ke file txt
                try (FileWriter pwVan = new FileWriter("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt", true)) {
                    pwVan.append("\n" + kodeVan + "," + namaVan + "," + plat + "," + penumpang + "," + "Tersedia" + "," + harga);
                }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk sewa van, sekaligus mengupdate status van jika van terlah disewa serta menambahkannya ke transaksi pinjam dan pelanggan
    public static void SewaVan (ArrayList<Van> vans, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans) throws Exception {
        
        String tanggalPinjam = util.inputTanggal("peminjaman");
        int durasi = util.inputDurasi("peminjaman");
        String lokasiPinjam = util.inputLokasi("peminjaman");
        util.clearScreen();

        //Print van yang tersedia
        Van.displayAturanVan( "Tersedia");

        ///Input van yang ingin disewa
        System.out.print("Masukkan kode van yang ingin disewakan : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();

        //Cetak detail van kode inputan
        for (Van van : vans) {
            if (van.getKodeTransport().equalsIgnoreCase(kodeInput) && van.getStatusTransport().equalsIgnoreCase("Tersedia")) {
                System.out.println("Kode Van : " + van.getKodeTransport());
                System.out.println("Nama Van : " + van.getNamaTransport());
                System.out.println("Plat Van : " + van.getPlatTransportasi());
                System.out.println("Harga Sewa per Hari : Rp" + van.getHargaSewa());
                int deposit = van.getHargaSewa()/10;
                System.out.println("Harga Deposito : Rp" + deposit);
                System.out.println("-----------------------------------");
                int hargaTotal =  van.getHargaSewa()*durasi + deposit;
                System.out.println("Harga total : Rp" + hargaTotal);
                System.out.print("Apakah anda yakin?(y/n) : ");
                String pilyakin = input.next();

                //Data pelanggan akan di-input jika sudah yakin
                if (pilyakin.equalsIgnoreCase("y")) {
                    util.clearScreen();
                    System.out.println("Masukkan detail pelanggan");
                    System.out.println("-------------------------");
                    System.out.print("Nama Pelanggan : ");
                    String namaPelanggan = input.next();
                    System.out.print("Nomor Telepon : ");
                    String noTelp = input.next();
                    System.out.print("Umur : ");
                    int umurPelanggan = input.nextInt();
                    if (umurPelanggan < 18) {
                        throw new Exception("Umur pelanggan tidak mencukupi");
                    }
                    System.out.print("Email : ");
                    String emailPelanggan = input.next();

                    String kodePelanggan = "P0" + Integer.toString(pelanggans.size()+1);
                    String kodePinjam = "T0" + Integer.toString(pinjams.size()+1);

                    //Data pelanggan masuk ke ArrayList
                    pelanggans.add(new Pelanggan(kodePelanggan, namaPelanggan, noTelp, umurPelanggan, emailPelanggan, "meminjam"));
                    //Data pelanggan dicetak ke file txt
                    try (FileWriter pwPelanggan = new FileWriter("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pelanggan.txt", true)) {
                        pwPelanggan.append("\n" + kodePelanggan + "," + namaPelanggan + "," + noTelp + "," + umurPelanggan + "," + emailPelanggan + "," + "meminjam");
                    }

                    //Data peminjaman masuk ke ArrayList
                    pinjams.add(new TransaksiPeminjaman(kodePinjam, kodeInput, kodePelanggan, lokasiPinjam, tanggalPinjam, deposit, hargaTotal, durasi, "Meminjam"));
                    //Data pinjam dicetak ke file txt
                    try (FileWriter pwPinjam = new FileWriter("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt", true)) {
                        pwPinjam.append( "\n" +kodePinjam + "," + kodeInput + "," + kodePelanggan + "," + lokasiPinjam + "," + tanggalPinjam + "," + deposit + "," + hargaTotal + "," + durasi + ",Meminjam");
                    }
                    Van.updateVan(kodeInput, "Dipinjam");

                    //Cetak reciept 
                    util.clearScreen();
                    TransaksiPeminjaman.cetakRecieptPinjam(kodePinjam,pinjams);
                }
                break;
            }
        }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk pengembalian van, sekaligus mengupdate status van jika van terlah disewa serta menambahkannya ke transaksi pinjam dan pelanggan
    public static void kembaliVan (ArrayList<Van> vans, ArrayList<TransaksiPeminjaman> pinjams, ArrayList<Pelanggan> pelanggans, ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException{
        TransaksiPeminjaman.displayAturanPinjam ("Meminjam", "v");
        //Input nomor peminjaman yang mau dikembalikan
        System.out.print("Masukkan kode transaksi : ");
        String kodeInput = input.next();
        kodeInput = kodeInput.toUpperCase();
        util.clearScreen();
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeInput) && pinjam.getStatusPinjam().equalsIgnoreCase("Meminjam")) {
                System.out.println("Nomor Transaksi : " + pinjam.getNomorTransaksi());
                System.out.println("Nama Van : " + pinjam.getVanPinjam().getNamaTransport());
                System.out.println("Plat Van : " + pinjam.getVanPinjam().getPlatTransportasi());
                System.out.println("Peminjam : " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("-----------------------");
                String tanggalKembali = util.inputTanggal("pengembalian");
                String lokasiKembali = util.inputLokasi("pengembalian");

                String tanggalKembaliSeharusnya = util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa());
                int dendaHari = 0;
                int bedaHari;
                if (tanggalKembali.equalsIgnoreCase(tanggalKembaliSeharusnya)) {
                    System.out.println("Van dikembalikan tepat waktu");
                    dendaHari += 0;
                } else {
                    if ((bedaHari = util.getDifferenceDays(tanggalKembali, tanggalKembaliSeharusnya)) > 0) {
                        System.out.printf("Van dikembalikan %d hari terlambat\n", bedaHari);
                        dendaHari = bedaHari * 50000;
                    } else {
                        System.out.println("Van dikembalikan lebih awal");
                        dendaHari += 0;
                    }
                }
                //Cek van dari class van
                int dendaCek = Van.cekTransport() * 50000;
                //Hitung total denda pelanggan
                int totalDenda = dendaHari + dendaCek - pinjam.getDeposit();

                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeInput, pinjams);
                Pelanggan.updatePelanggan(peminjaman.getPelangganPinjam().getKodePelanggan(), "lunas");
                Van.updateVan(peminjaman.getVanPinjam().getKodeTransport(), "Tersedia");
                TransaksiPeminjaman.updatePinjam(kodeInput, "Berhasil");

                //Data masuk kembali ke ArrayList
                kembalis.add(new TransaksiPengembalian(kodeInput, lokasiKembali, tanggalKembali, totalDenda));
                //Data dicetak kembali ke file
                try (FileWriter pwKembali = new FileWriter("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pengembalian.txt", true)) {
                    pwKembali.append("\n" + kodeInput + "," + lokasiKembali + "," + tanggalKembali + "," + totalDenda);
                }
                util.clearScreen();
                TransaksiPengembalian.cetakRecieptKembali(kodeInput, kembalis, pinjams);
                break;
            }
        }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengurutkan dan menampilkan van berdasarkan harga terendah ke tertinggi menggunakan shell sort
    public static void displayAturanVanAsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt"))) {
            String s = "";
            ArrayList <Van> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    asc.add (new Van(data[0], data[1], data[2], Integer.parseInt(data[3]),data[4], Integer.parseInt(data[5])));
                }
            }
            for (int gap = asc.size()/2; gap > 0; gap /= 2) {
                for (int i = gap; i < asc.size(); i += 1) {
                    Van temp = new Van();
                    temp = asc.get(i);
                    int j;
                    for (j = i; j >= gap && asc.get(j-gap).getHargaSewa() > temp.getHargaSewa(); j -= gap) {
                        asc.set(j, asc.get(j-gap));
                    }
                    asc.set(j, temp);
                }
            }

            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            for (Van van : asc) {
                System.out.println(van);
            }
        }
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengurutkan dan menampilkan van berdasarkan harga tertinggi ke terendah menggunakan shell sort
    public static void displayAturanVanDsc (String equals) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\van.txt"))) {
            String s = "";
            ArrayList <Van> asc = new ArrayList<>();
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                
                if (data[4].equalsIgnoreCase(equals)) {
                    asc.add (new Van(data[0], data[1], data[2], Integer.parseInt(data[3]),data[4], Integer.parseInt(data[5])));
                }
            }
            for (int gap = asc.size()/2; gap > 0; gap /= 2) {
                for (int i = gap; i < asc.size(); i += 1) {
                    Van temp = new Van();
                    temp = asc.get(i);
                    int j;
                    for (j = i; j >= gap && asc.get(j-gap).getHargaSewa() < temp.getHargaSewa(); j -= gap) {
                        asc.set(j, asc.get(j-gap));
                    }
                    asc.set(j, temp);
                }
            }

            System.out.println("|Kode\t|Jenis\t|Penumpang\t|Harga\t|");
            for (Van van : asc) {
                System.out.println(van);
            }
        }
    }

    @Override
    public String toString() {
        return "|" +  getKodeTransport() + "\t|"
        + getNamaTransport() + "\t|" 
        + getJumlahPenumpang() + "\t\t|"
        + getHargaSewa() + "\t|";
    }

}
