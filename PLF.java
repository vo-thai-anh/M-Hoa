import java.util.Scanner;
import java.util.LinkedHashSet;

public class PLF 
{
    private char[][] table;// mảng 2 chiều 
    public void MaHoa(String key) 
    {
        this.table = new char[5][5];// tạo bảng bằng phương thức
        creatable(key);
    }
    private void creatable(String key)
    {
        LinkedHashSet<Character> set = new LinkedHashSet<>();// linkedhashset không cho trùng lập ký tự 
        for (char c : key.toCharArray()) 
        {
            if (c >= 'a' && c <= 'z' && c != 'j') 
            {
                set.add(c);
            }
        }
        for (char c = 'a'; c <= 'z'; c++) 
        {
            if (c == 'j') continue;
            if (!set.contains(c)) // ktra ky tu chưa có thì thêm vào 
            {
                set.add(c);
            }
        }

        int KS = 0; //khởi tạo chỉ số kiểm soát bảng
        for (Character c : set) // duyệt qua từng ký tự trong set
        {
            table[KS / 5][KS % 5] = c;// ví dụ " Thaianhzdo" ; t[0][0],h[0][1],a[0][2],i[0][4],n[0][5]
            KS++;                     //                       z[1][0],d[1][1],0[1][2],
        }
    }

    public String preprocess(String a) // tao chuỗi a
    {
        StringBuilder Kq = new StringBuilder(); // tạo StringBD kq
        for (char c : a.toCharArray())  // duyệt từng ký tự của a và lưu vào c ( chuyển chuỗi thanhf mảng )
        {
            if (c >= 'a' && c <= 'z') 
            {
                Kq.append(c);// thỏa điều kiện thì thêm vào kq , kq là một chuối của builder
            }
        }
        String processed = Kq.toString().replace("j", "i");// thay the j = i
        // tạo chuối processed , gáng kq vào pro; 
        Kq.setLength(0); // khởi tạo lại builder
        for (int i = 0; i < processed.length(); i += 2) // xử lý cuối process theo từng cặp 
        {
            if (i + 1 == processed.length()) //nếu ký tự lẽ thì cộng x 
            {
                Kq.append(processed.charAt(i)).append('x');
            } 
            else if (processed.charAt(i) == processed.charAt(i + 1)) // kiểm tra 2 cặp ký tự có giống nhau không thì thêm x ở giữa
            {
                Kq.append(processed.charAt(i)).append('x');
                i--;// giảm 1 để không bỏ sót ký tự 
            } 
            else 
            {
                Kq.append(processed.charAt(i)).append(processed.charAt(i + 1));
            }
        }
        return Kq.toString();
    }

    public String mh (String vb) 
    {
        String vbmh = preprocess(vb);
        StringBuilder mhd = new StringBuilder();

        for (int i = 0; i < vbmh.length(); i += 2) // chạy vòng lập 
        {
            char first = vbmh.charAt(i);//khởi tạo first gáng và kiểm tra từng ký tự của mã vừa nhập 
            char second =(i + 1 < vbmh.length()) ? vbmh.charAt(i + 1) : 'x'; // Thêm 'x' nếu thiếu ký tự// ký tự theo sau 
            int[] firstPos = getPosition(first);// lấy vị trí đâu tiên 
            int[] secondPos = getPosition(second);// thứ 2
                // *quy ước x[0][3]
                //fp(0)=0;hàng
                //fp(1)=3;cột
            if (firstPos[0] == secondPos[0]) // kiểm tra có cùng hàng không 
            {
                mhd.append(table[firstPos[0]][(firstPos[1] + 1) % 5]);
                mhd.append(table[secondPos[0]][(secondPos[1] + 1) % 5]);
                // nếu có thêm ký tự bên phải vào mhd, và hàng cuối cùng nhờ %5 sẽ quay lại 1 vì 5%5=1 dư 0;
            } 
            else if (firstPos[1] == secondPos[1]) // kiểm tra xem có cùng cột không 
            {
                mhd.append(table[(firstPos[0] + 1) % 5][firstPos[1]]);
                mhd.append(table[(secondPos[0] + 1) % 5][secondPos[1]]);
            }
            else 
            {
                mhd.append(table[firstPos[0]][secondPos[1]]);
                mhd.append(table[secondPos[0]][firstPos[1]]);
            }
        }
        return mhd.toString();
    }
    private int[] getPosition(char c) { // Lấy vị trí của ký tự trong bảng
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j] == c) {
                    return new int[]{i, j}; // Trả về hàng và cột
                }
            }
        }
        return null; // Nếu không tìm thấy ký tự
    }




    public static void main(String[] args) 
    {
     try 
        ( Scanner scanner = new Scanner(System.in))
            {  
                System.out.print("Nhập từ khóa (key): ");
                String k = scanner.nextLine();
                
                PLF cipher = new PLF();
                cipher.MaHoa(k);
                
                System.out.print("Nhập văn bản cần mã hóa: ");
                String message = scanner.nextLine();
                
                String mhd = cipher.mh(message);
                System.out.println("Văn bản đã mã hóa: " + mhd);
            }
     }
}
