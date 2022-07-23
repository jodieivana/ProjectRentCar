import java.util.Scanner;

public abstract class Transportasi{

    //Attribut
    String kodeTransport;
    String namaTransport;
    String PlatTransportasi;
    int JumlahPenumpang;
    String StatusTransport;
    int HargaSewa;

    static Scanner input = new Scanner(System.in);

    //Constructor
    public Transportasi() {
    }

    public Transportasi(String kodeTransportasi, String namaTransportasi, String PlatTransportasi, int JumlahPenumpang, String StatusTransportasi, int HargaSewa) {
        this.kodeTransport = kodeTransportasi;
        this.namaTransport = namaTransportasi;
        this.PlatTransportasi = PlatTransportasi;
        this.JumlahPenumpang = JumlahPenumpang;
        this.StatusTransport = StatusTransportasi;
        this.HargaSewa = HargaSewa;
    }
    

    //Getter Setter

    public String getKodeTransport() {
        return this.kodeTransport;
    }

    public void setKodeTransport(String kodeTransport) {
        this.kodeTransport = kodeTransport;
    }

    public String getNamaTransport() {
        return this.namaTransport;
    }

    public void setNamaTransport(String namaTransport) {
        this.namaTransport = namaTransport;
    }

    public String getPlatTransportasi() {
        return this.PlatTransportasi;
    }

    public void setPlatTransportasi(String PlatTransportasi) {
        this.PlatTransportasi = PlatTransportasi;
    }

    public int getJumlahPenumpang() {
        return this.JumlahPenumpang;
    }

    public void setJumlahPenumpang(int JumlahPenumpang) {
        this.JumlahPenumpang = JumlahPenumpang;
    }

    public String getStatusTransport() {
        return this.StatusTransport;
    }

    public void setStatusTransport(String StatusTransport) {
        this.StatusTransport = StatusTransport;
    }

    public int getHargaSewa() {
        return this.HargaSewa;
    }

    public void setHargaSewa(int HargaSewa) {
        this.HargaSewa = HargaSewa;
    }
    

    //Cek transportasi
    public static int cekTransport (){
        int total = 0;
        int data[] = new int[4];
        System.out.println("Cek Transport");
        System.out.println("---------");
        System.out.println("0 jika terpenuhi, 1 jika tidak");
        System.out.print("Minyak di atas 50% : ");
        data[0] = input.nextInt();
        System.out.print("Mesin jalan lancar : ");
        data[1] = input.nextInt()*3;
        System.out.print("Bodi tidak tergores : ");
        data[2] = input.nextInt()*3;
        System.out.print("Interior bersih : ");
        data[3] = input.nextInt();

        for (int i : data) {
            total += i;
        }
        return total;
    }
    
}