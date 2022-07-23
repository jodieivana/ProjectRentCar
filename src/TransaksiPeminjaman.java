import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

//TransaksiPeminjaman merupakan inheritance dari Transaksi
public class TransaksiPeminjaman extends Transaksi{

    private ArrayList <Mobil> mobils = new ArrayList<>();
    private ArrayList <Van> vans = new ArrayList<>();
    private ArrayList <Pelanggan> pelanggans = new ArrayList<>();
    
    private int deposit;
    private String lokasiPinjam;
    private String tanggalPinjam;
    private Mobil mobilPinjam;
    private Van vanPinjam;
    private Pelanggan pelangganPinjam;
    private String statusPinjam;

    //Constructor
    public TransaksiPeminjaman() {
    }


    public TransaksiPeminjaman( String nomorTransaksi, String kodeTransport, String kodePelanggan, String lokasiPinjam, String tanggalPinjam, int deposit, int biaya, int lamaSewa, String statusPinjam) throws FileNotFoundException, IOException {
        this.nomorTransaksi = nomorTransaksi;
        if (kodeTransport.substring(0, 1).equalsIgnoreCase("m")) {
            Mobil.updateMobil(mobils);
            for (Mobil mobil : mobils) {
                if (mobil.getKodeTransport().equalsIgnoreCase(kodeTransport)) {
                    this.mobilPinjam = mobil;
                    this.vanPinjam = null;
                    break;
                }
            }
        } else if (kodeTransport.substring(0, 1).equalsIgnoreCase("v")) {
            Van.updateVan(vans);
            for (Van van : vans) {
                if (van.getKodeTransport().equalsIgnoreCase(kodeTransport)) {
                    this.vanPinjam = van;
                    this.mobilPinjam = null;
                    break;
                }
            }
        }
        Pelanggan.updatePelanggan(pelanggans);
        for (Pelanggan pelanggan : pelanggans) {
            if (pelanggan.getKodePelanggan().equalsIgnoreCase(kodePelanggan)) {
                this.pelangganPinjam = pelanggan;
                break;
            }
        }
        this.lokasiPinjam = lokasiPinjam;
        this.tanggalPinjam = tanggalPinjam;
        this.deposit = deposit;
        this.biaya = biaya;
        this.lamaSewa = lamaSewa;
        this.statusPinjam = statusPinjam;
    }

    
    //Getter Setter
    public int getDeposit() {
        return this.deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getLokasiPinjam() {
        return this.lokasiPinjam;
    }

    public void setLokasiPinjam(String lokasiPinjam) {
        this.lokasiPinjam = lokasiPinjam;
    }

    public String getTanggalPinjam() {
        return this.tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Mobil getMobilPinjam() {
        return this.mobilPinjam;
    }

    public void setMobilPinjam(Mobil mobilPinjam) {
        this.mobilPinjam = mobilPinjam;
    }

    public Pelanggan getPelangganPinjam() {
        return this.pelangganPinjam;
    }

    public void setPelangganPinjam(Pelanggan pelangganPinjam) {
        this.pelangganPinjam = pelangganPinjam;
    }

    public String getStatusPinjam() {
        return this.statusPinjam;
    }

    public void setStatusPinjam(String statusPinjam) {
        this.statusPinjam = statusPinjam;
    }

    public Van getVanPinjam() {
        return this.vanPinjam;
    }

    public void setVanPinjam(Van vanPinjam) {
        this.vanPinjam = vanPinjam;
    }



    @Override
    public String toString() {
        return "{" +
            " mobils='" + "'" +
            ", pelanggans='"  + "'" +
            ", deposit='" + getDeposit() + "'" +
            ", lokasiPinjam='" + getLokasiPinjam() + "'" +
            ", tanggalPinjam='" + getTanggalPinjam() + "'" +
            ", mobilPinjam='"  + "'" +
            ", pelangganPinjam='" + "'" +
            ", nomorTransaksi='" + getNomorTransaksi() + "'" +
            ", biaya='" + getBiaya() + "'" +
            ", lamaSewa='" + getLamaSewa() + "'" +
            "}";
    }
    
    public static ArrayList<TransaksiPeminjaman> updatePinjam (ArrayList<TransaksiPeminjaman> pinjams) throws FileNotFoundException, IOException, ParseException {
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                pinjams.add(new TransaksiPeminjaman(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]), data[8]));
            }
        }
        return pinjams;
    }

    public static void updatePinjam (String kodeTransaksi, String status) throws IOException{
        String FilePath = "C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt";
        File oldFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt");
        File newFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodeTransaksi)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + "," + data[8] ;
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodeTransaksi)) {
                        String row = "\n" +  data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[6] + "," + data[7] + "," + data[8] ;
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

    public static void cetakRecieptPinjam (String kodeTransaksi, ArrayList<TransaksiPeminjaman> pinjams) throws ParseException {
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kodeTransaksi)) {
                System.out.println("Kelompok 4 Rental");
                System.out.println("---------------------");
                System.out.println("Transaksi " + kodeTransaksi);
                if (pinjam.getMobilPinjam() != null) {
                    System.out.println("Sewa mobil " + pinjam.getMobilPinjam().getNamaTransport() + " " + pinjam.getMobilPinjam().getPlatTransportasi());
                    System.out.println("\t\tRp" + pinjam.getMobilPinjam().getHargaSewa() + " x " + pinjam.getLamaSewa());
                } else if (pinjam.getVanPinjam() != null) {
                    System.out.println("Sewa van " + pinjam.getVanPinjam().getNamaTransport() + " " + pinjam.getVanPinjam().getPlatTransportasi());
                    System.out.println("\t\tRp" + pinjam.getVanPinjam().getHargaSewa() + " x " + pinjam.getLamaSewa());
                }
                System.out.println("Deposit");
                System.out.println("\t\tRp" + pinjam.getDeposit());
                System.out.println("---------------------");
                System.out.println("Total : " + pinjam.getBiaya());
                System.out.println("Peminjaman atas nama " + pinjam.getPelangganPinjam().getNamaPelanggan());
                System.out.println("Harap dikembalikan sebelum " + util.addToDate(pinjam.getTanggalPinjam(), pinjam.getLamaSewa()));
                break;
            }
        }
    }

    public static TransaksiPeminjaman cariTransaksiPinjam (String kode, ArrayList<TransaksiPeminjaman> pinjams){
        int i = 0;
        for (TransaksiPeminjaman pinjam : pinjams) {
            if (pinjam.getNomorTransaksi().equalsIgnoreCase(kode)) {
                break;
            }
            i++;
        }
        return pinjams.get(i);
    }

    public static void displayAturanPinjam (String equals1, String kode) throws FileNotFoundException, IOException{
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\peminjaman.txt"))) {
            String s = "";
            System.out.println("|Kode Transaksi\t|Kode Transport\t|Kode Penumpang\t|Tanggal Pinjam\t|");
            while ((s = read.readLine()) != null) {
                
                String data[] = s.split(",");
                String nm = data[1].substring(0,1);
                if (data[8].equalsIgnoreCase(equals1) && nm.equalsIgnoreCase(kode)) {
                    for (int i = 0; i < 8; i++) {
                        if ((i == 1) || (i == 2)) {
                            System.out.print(data[i] + "\t\t|"); 
                        } else if (i == 0){
                            System.out.print("|" +data[i] + "\t\t|"); 
                        } else if (i == 4) {
                            System.out.print(data[i] + "\t|"); 
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

}