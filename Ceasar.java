import java.util.Scanner;
public class Ceasar
{
    public static String MaHoa(String a, int key)
     {
        // Tạo ra một Builder chuỗi với dung lượng ban đầu là 16
        //dùng StringBuilder để hiệu quả hơn trong việc xây dựng chuỗi.
        StringBuilder KetQua = new StringBuilder();
        a = a.toLowerCase(); // về dạng chữ thường.
            // phương thức length() trả về độ dài chuỗi.
        for (int i = 0; i < a.length(); i++) {
            // charAT(i), trả lại ký tự tại vị trí i.
            char c = a.charAt(i);
            // Kiểm tra xem ký tự có phải là chữ cái hay khôncong
            if (Character.isLetter(c))
             {
               c=(char)((c-'a'+key+26)%26+'a');
               // xác định c là chữ thường vì gán 'a';
               // để tìm điểm xác định bảng ascii.
            }
            KetQua.append(c);
        }
        // c là đoạn mã , s là key
        return KetQua.toString();
    }
    public static String GiaiMa(String a, int key)
    {
       return MaHoa(a,26-key);
    }
    public static void main (String[] args)
    {
        //nhap ma
       try(  Scanner scanner  = new Scanner(System.in);)
        {
                System.out.print(" Nhap Doan Ma ");

                String a = scanner.nextLine();
                // nhap key
                System.out.print("Nhập độ dịch chuyển (0-25): ");
                    int s = scanner.nextInt();
                // kiem tra do hop le 
            if ( s <0 || s > 25 )
            {
                System.out.println("Độ dịch chuyển không hợp lệ! Phải nằm trong khoảng 0-25.");
            } 
            else 
            {
                // Mã hóa
                String doanma = MaHoa(a, s);
                System.out.println("Văn bản mã hóa: " + doanma);

                // Giải mã
                String vanban = GiaiMa(doanma, s);
                System.out.println("Văn bản giải mã: " + vanban);
            }
        }
    }
}
