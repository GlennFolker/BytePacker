package packer;

import java.util.*;

public class BytePacker {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            String line = null;

            System.out.print("Bytes: ");
            while((line = scanner.nextLine()) != null) {
                line = line.trim();
                if(line.equals("exit")) break;

                String[] str = line.split("\\s+");
                byte[] bytes = new byte[str.length];

                for(int i = 0; i < str.length; i++) {
                    bytes[i] = Byte.parseByte(str[i]);
                }

                String packed = pack(bytes);
                byte[] unpacked = unpack(packed);

                String format = String.format("%s %s %s", Arrays.toString(bytes), packed, Arrays.toString(unpacked));
                System.out.println(format);

                System.out.print("Bytes: ");
            }
        }
    }

    public static String pack(byte[] bytes) {
        String str = "";
        for(int i = 0; i < bytes.length; i += 2) {
            int c = bytes[i] << 8;
            if(i+1 < bytes.length) c |= bytes[i + 1];

            str += Character.toString(c);
        }

        return str;
    }

    public static byte[] unpack(String str) {
        int len = str.length();
        byte[] bytes = new byte[len * 2];

        for(int i = 0; i < len; i++) {
            int code = Character.codePointAt(str, i);

            bytes[i*2] = (byte)(code >>> 8);
            bytes[(i*2) + 1] = (byte)(code & 0xff);
        }

        return bytes;
    }
}
