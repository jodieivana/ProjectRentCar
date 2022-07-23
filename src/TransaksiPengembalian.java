import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

//TransaksiPengembalian merupakan inheritance dari Transaksi
public class TransaksiPengembalian extends Transaksi{

    //Attribut
    String nomorTransaksi;
    private int denda;
    private String lokasiKembali;
    private String tanggalKembali;

    //Constructor
    public TransaksiPengembalian() {
    }

    public TransaksiPengembalian(String nomorTransaksi, String lokasiKembali, String tanggalKembali, int denda) {
        this.nomorTransaksi = nomorTransaksi;
        this.denda = denda;
        this.lokasiKembali = lokasiKembali;
        this.tanggalKembali = tanggalKembali;
    }


    public TransaksiPengembalian(String lokasiKembali, String tanggalKembali, int denda) {
        this.denda = denda;
        this.lokasiKembali = lokasiKembali;
        this.tanggalKembali = tanggalKembali;
    }

    //Getter Setter
    public int getDenda() {
        return this.denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public String getLokasiKembali() {
        return this.lokasiKembali;
    }

    public void setLokasiKembali(String lokasiKembali) {
        this.lokasiKembali = lokasiKembali;
    }

    public String getTanggalKembali() {
        return this.tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getNomorTransaksi() {
        return this.nomorTransaksi;
    }

    public void setNomorTransaksi(String nomorTransaksi) {
        this.nomorTransaksi = nomorTransaksi;
    }

    @Override
    public String toString() {
        return "{" +
            " denda='" + getDenda() + "'" +
            ", lokasiKembali='" + getLokasiKembali() + "'" +
            ", tanggalKembali='" + getTanggalKembali() + "'" +
            "}";
    }

    public static ArrayList<TransaksiPengembalian> updateKembali (ArrayList<TransaksiPengembalian> kembalis) throws FileNotFoundException, IOException, ParseException {
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pengembalian.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                kembalis.add(new TransaksiPengembalian(data[0], data[1], data[2], Integer.parseInt(data[3])));
            }
        }
        return kembalis;
    }

    public static void cetakRecieptKembali (String kodeTransaksi, ArrayList<TransaksiPengembalian> kembalis,  ArrayList<TransaksiPeminjaman> pinjams) {
        for (TransaksiPengembalian kembali : kembalis) {
            if (kembali.getNomorTransaksi().equalsIgnoreCase(kodeTransaksi)) {
                TransaksiPeminjaman peminjaman = TransaksiPeminjaman.cariTransaksiPinjam(kodeTransaksi, pinjams);
                System.out.println("Kelompok 4 Rental");
                System.out.println("---------------------");
                System.out.println("Transaksi " + kodeTransaksi);

                if (peminjaman.getMobilPinjam() != null) {
                    System.out.println("Sewa mobil " + peminjaman.getMobilPinjam().getNamaTransport() + " " + peminjaman.getMobilPinjam().getPlatTransportasi());
                } else if (peminjaman.getVanPinjam() != null) {
                    System.out.println("Sewa van " + peminjaman.getVanPinjam().getNamaTransport() + " " + peminjaman.getVanPinjam().getPlatTransportasi());
                }
                System.out.println("---------------------");
                //Jika denda>deposit, maka bayar denda; jika deposit<denda, maka sisah deposit dikembalikan 
                if (kembali.getDenda() > 0) {
                    System.out.println("Denda yang harus yang dibayar : " + kembali.getDenda());
                } else if (kembali.getDenda() == 0) {
                    System.out.println("Tidak ada denda dan deposit");
                } else {
                    System.out.println("Deposit yang harus dikembalikan : " + kembali.getDenda()*(-1) );
                }
                break;
            }
        }
    }
}