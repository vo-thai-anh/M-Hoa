import java.util.HashMap;
import java.util.Scanner;

public class ChuDon
{

    // khởi tạo lưu trữ bảng mã hóa
    private static HashMap<Character, Character> BangMaHoa = new HashMap<>();

    // Phương thức kiểm tra tính hợp lệ của khóa
    public static boolean ktra(String khoa) 
    {
        if (khoa.length() != 26) {
            return false; 
            // Nhập quá 26 đánh liền 
        }
        // kiểm tra hợp lệ của khóa nè !
        boolean[] Cp = new boolean[26];
        for (char c : khoa.toCharArray())
        // từng vòng lập chuỗi khóa sẽ chuyển sang mảng ký tự 
         {
            if (c < 'a' || c > 'z')
            {
                return false; //  chỉ từ a tới z thôi 
            }
            if (Cp[c - 'a']) //( cp[c-'a'] xác định vị trí trong mảng)
            {
                return false; // Ký tự trùng lặp
            }
            Cp[c - 'a'] = true; // Đánh dấu ký tự đã có
        }
        return true;
    }

    // Phương thức mã hóa
    public static String MaHoa(String x)
    {
        StringBuilder Kq = new StringBuilder();
        for (char c : x.toCharArray()) //duyệt chuỗi x bằng chuyển từng mảng ký tự 
        {
            if (BangMaHoa.containsKey(Character.toLowerCase(c)))//kiểm tra 'a' và 'A'
            {
                char KTTW = BangMaHoa.get(Character.toLowerCase(c));// 
                Kq.append(Character.isUpperCase(c) ? Character.toUpperCase(KTTW) : KTTW); // ktra và chuyển đổi ký tự từ 
            } 
            else 
            {
                Kq.append(c); // Giữ nguyên ký tự không phải chữ cái
            }

        }
        return Kq.toString();
    }

    public static void main(String[] args) 
    {
     
     try(Scanner sc = new Scanner(System.in))
        {
            String k;
            do 
            {
                // Nhập khóa từ người dùng
                System.out.print("Nhập khóa mã hóa (26 ký tự từ a-z): ");
                k = sc.nextLine();
                // Kiểm tra tính hợp lệ của khóa
                if (!ktra(k)) 
                {
                    System.out.println("Khóa không hợp lệ! Vui lòng nhập 26 ký tự khác nhau từ a-z.");
                    return;
                }
            }while(!ktra(k));

            // Tạo bảng mã hóa từ khóa
            TaoKhoa(k);

            // Nhập văn bản từ người dùng
            System.out.print("Nhập văn bản cần mã hóa: ");
            String Doanma = sc.nextLine();

            // Mã hóa văn bản
            String VBMH = MaHoa(Doanma);
            System.out.println("Văn bản đã mã hóa: " + VBMH);
            
        }
    }
    // Phương thức tạo bảng mã hóa từ khóa
    public static void TaoKhoa(String khoa)
    {
        for (int i = 0; i < 26; i++) 
        {
            char KTgoc = (char) ('a' + i);// cộng thứ tự i vào a=61 ;
            char KyTuKhoa = khoa.charAt(i);
            BangMaHoa.put(KTgoc, KyTuKhoa);// chỗ này giống hàng nha thầy ơi thầy 
        }
    }
}
