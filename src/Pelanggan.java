import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Pelanggan {
    // Atribut
    private String kodePelanggan;
    private String namaPelanggan;
    private String noTelpPelanggan;
    private int umurPelanggan;
    private String emailPelanggan;
    private String statusPelanggan; 

    //Constructor

    public Pelanggan() {
    }

    public Pelanggan(String kodePelanggan, String namaPelanggan, String noTelpPelanggan, int umurPelanggan, String emailPelanggan, String statusPelanggan) {
        this.kodePelanggan = kodePelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noTelpPelanggan = noTelpPelanggan;
        this.umurPelanggan = umurPelanggan;
        this.emailPelanggan = emailPelanggan;
        this.statusPelanggan = statusPelanggan;
    }


    //Getter Setter

    public String getNamaPelanggan() {
        return this.namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNoTelpPelanggan() {
        return this.noTelpPelanggan;
    }

    public void setNoTelpPelanggan(String noTelpPelanggan) {
        this.noTelpPelanggan = noTelpPelanggan;
    }

    public int getUmurPelanggan() {
        return this.umurPelanggan;
    }

    public void setUmurPelanggan(int umurPelanggan) {
        this.umurPelanggan = umurPelanggan;
    }

    public String getEmailPelanggan() {
        return this.emailPelanggan;
    }

    public void setEmailPelanggan(String emailPelanggan) {
        this.emailPelanggan = emailPelanggan;
    }


    public String getKodePelanggan() {
        return this.kodePelanggan;
    }

    public void setKodePelanggan(String kodePelanggan) {
        this.kodePelanggan = kodePelanggan;
    }

    public String getStatusPelanggan() {
        return this.statusPelanggan;
    }

    public void setStatusPelanggan(String statusPelanggan) {
        this.statusPelanggan = statusPelanggan;
    }


    @Override
    public String toString() {
        return "{" +
            " namaPelanggan='" + getNamaPelanggan() + "'" +
            ", noTelpPelanggan='" + getNoTelpPelanggan() + "'" +
            ", umurPelanggan='" + getUmurPelanggan() + "'" +
            ", emailPelanggan='" + getEmailPelanggan() + "'" +
            "}";
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : berfungsi untuk memasukkan data dari file pelanggan.txt ke arraylist pelanggan
    public static ArrayList<Pelanggan> updatePelanggan (ArrayList<Pelanggan> pelanggans) throws FileNotFoundException, IOException {
        try (BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pelanggan.txt"))) {
            String s = "";
            while ((s = read.readLine()) != null) {
                String data[] = s.split(",");
                pelanggans.add(new Pelanggan(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], data[5]));
            }
        }
        return pelanggans;
    }

    //Nama         : Jodie Ivana Salim
    //NIM          : 03081210005
    //Deskripsi    : - Berfungsi untuk mengupdate textfile dan arraylist jika ada 1 data yang perlu diganti
    //               - Contoh : Menggantikan status pelanggan "Meminjam" ke "Lunas" atau sebaliknya yang ada di file txt
    //               - OverLoading dengan method diatas karena nama yang sama tetapi parameter berbeda
    public static void updatePelanggan (String kodePelanggan, String status) throws IOException{
        String FilePath = "C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pelanggan.txt";
        File oldFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pelanggan.txt");
        File newFile = new File ("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\temp.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jodie\\OneDrive - Universitas Pelita Harapan\\3. Semester Akselerasi 1\\2. PBO\\ProjectRentCar\\src\\data\\pelanggan.txt"))) {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String s = "";
            int i = 0;
            while ((s = br.readLine()) != null) {
                String data[] = s.split(",");
                if (i == 0) {
                    if (data[0].equalsIgnoreCase(kodePelanggan)) {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + ",";
                        row = row + status;
                        pw.print(row);
                    } else {
                        String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5];
                        pw.print(row);
                    }
                } else {
                    if (data[0].equalsIgnoreCase(kodePelanggan)) {
                        String row =  "\n" + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + ",";
                        row = row + status;
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

}