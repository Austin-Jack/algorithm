package algorithm;

public class Decimalism2NScale {
    private static final String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    /**
     * 十进制转n进制
     *
     * @param n        十进制数
     * @param scale    目标进制 (2-62)
     * @return         转换结果
     */
    private static String toN(int n, int scale) {
        // 处理特殊情况：0
        if (n == 0) {
            return "0";
        }
        
        // 检查进制范围
        if (scale < 2 || scale > str.length()) {
            throw new IllegalArgumentException("进制必须在2-" + str.length() + "之间");
        }
        
        StringBuilder sb = new StringBuilder();
        boolean negative = n < 0;
        n = Math.abs(n);
        
        while (n > 0) {
            int index = n % scale;
            sb.append(str.charAt(index));
            n /= scale;
        }
        
        // 关键修正：反转字符串
        String result = sb.reverse().toString();
        return negative ? "-" + result : result;
    }
    
    public static void main(String[] args) {
        // 测试用例
        System.out.println(toN(2000, 62));  // 正确结果
        System.out.println(toN(10, 2));     // 应该输出 "1010"
        System.out.println(toN(255, 16));   // 应该输出 "FF"
        System.out.println(toN(0, 16));     // 应该输出 "0"
    }
}