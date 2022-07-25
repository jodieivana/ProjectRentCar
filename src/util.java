import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class util {

    static Scanner input = new Scanner(System.in);

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengubah tanggal yang salah input
    public static String changeToDate (String tanggal) throws ParseException{
        Calendar cal = Calendar.getInstance();
            //String diubah menjadi date
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(date.parse(tanggal));
            //Date diubah kembali menjadi string
        tanggal = date.format(cal.getTime());
        return tanggal;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk menambah hari pada tanggal
    public static String addToDate (String tanggal, int durasi) throws ParseException{
        Calendar cal = Calendar.getInstance();
            //String diubah menjadi date
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(date.parse(tanggal));
        //Tambah tanggal
        cal.add(Calendar.DAY_OF_MONTH, durasi);
            //Date diubah kembali menjadi string
        tanggal = date.format(cal.getTime());
        return tanggal;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk membuat 2 tanggal dengan perbedaan dalam hari
    public static int getDifferenceDays(String date1, String date2) throws ParseException {
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        long diff = d1.getTime() - d2.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk update arraylist dari file
    public static void updateAll ( ArrayList <Mobil> mobils,ArrayList <TransaksiPeminjaman> pinjams, ArrayList <TransaksiPengembalian> kembalis, ArrayList <Pelanggan> pelanggans, ArrayList<Van> vans) throws FileNotFoundException, IOException, ParseException{
        mobils.removeAll(mobils);
        Mobil.updateMobil(mobils);
        pelanggans.removeAll(pelanggans);
        Pelanggan.updatePelanggan(pelanggans);
        pinjams.removeAll(pinjams);
        TransaksiPeminjaman.updatePinjam(pinjams);
        kembalis.removeAll(kembalis);
        TransaksiPengembalian.updateKembali(kembalis);
        vans.removeAll(vans);
        Van.updateVan(vans);
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk membersihkan layar
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengembalikan inputan tanggal dari user
    public static String inputTanggal (String status) throws ParseException {
        //Input tanggal pinjam
        System.out.println("Masukkan tanggal " + status.toLowerCase());
        System.out.print("Tanggal (1-31) : ");
        int day = input.nextInt();
        System.out.print("Bulan (1-12): ");
        int month = input.nextInt();
        System.out.print("Tahun : ");
        int year = input.nextInt();
        //Hari, bulan, dan tahun digabung menjadi satu tanggal
        String tanggal = Integer.toString(day) + "/" + Integer.toString(month) + "/" +Integer.toString(year);
        tanggal = util.changeToDate(tanggal);
        return tanggal;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengembalikan inputan durasi dari user
    public static int inputDurasi (String status) {
        //Input durasi pinjam
        System.out.print("Masukkan durasi " + status.toLowerCase() + " anda : ");
        int durasi = input.nextInt();
        return durasi;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk mengembalikan inputan lokasi dari user
    public static String inputLokasi (String status ){
        //Input lokasi pinjam
        System.out.print("Masukkan lokasi " + status + " anda : ");
        String lokasi = input.next();
        return lokasi;
    }
}