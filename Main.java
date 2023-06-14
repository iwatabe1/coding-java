import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class FastScanner {

  private final InputStream in = System.in;
  private final byte[] buffer = new byte[1024];
  private int ptr = 0;
  private int buflen = 0;

  private boolean hasNextByte() {
    if (ptr < buflen) {
      return true;
    } else {
      ptr = 0;
      try {
        buflen = in.read(buffer);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (buflen <= 0) {
        return false;
      }
    }
    return true;
  }

  private int readByte() {
    if (hasNextByte()) return buffer[ptr++]; else return -1;
  }

  private static boolean isPrintableChar(int c) {
    return 33 <= c && c <= 126;
  }

  public boolean hasNext() {
    while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
    return hasNextByte();
  }

  public String next() {
    if (!hasNext()) throw new NoSuchElementException();
    StringBuilder sb = new StringBuilder();
    int b = readByte();
    while (isPrintableChar(b)) {
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }

  public long nextLong() {
    if (!hasNext()) throw new NoSuchElementException();
    long n = 0;
    boolean minus = false;
    int b = readByte();
    if (b == '-') {
      minus = true;
      b = readByte();
    }
    if (b < '0' || '9' < b) {
      throw new NumberFormatException();
    }
    while (true) {
      if ('0' <= b && b <= '9') {
        n *= 10;
        n += b - '0';
      } else if (b == -1 || !isPrintableChar(b)) {
        return minus ? -n : n;
      } else {
        throw new NumberFormatException();
      }
      b = readByte();
    }
  }

  public int nextInt() {
    long nl = nextLong();
    if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
    return (int) nl;
  }

  public double nextDouble() {
    return Double.parseDouble(next());
  }
}

public class Main {

  public static void main(String[] args) {
    try {
      FastScanner fs = new FastScanner();

      String S = fs.next();

      HashMap<String, Integer> mp = new HashMap<>();

      mp.put(S, 0);

      Queue<String> queue = new LinkedList<>();
      int ans = 0;

      queue.add(S);

      while (!queue.isEmpty()) {
        String current = queue.poll();

        if (current.equals("atcoder")) {
          ans = mp.get(current);
          break;
        }

        for (int i = 1; i < 7; ++i) {
          StringBuilder next = new StringBuilder(current);

          char one = next.charAt(i);
          char two = next.charAt(i - 1);

          next.setCharAt(i, two);
          next.setCharAt(i - 1, one);

          String nextStr = next.toString();

          if (!mp.containsKey(nextStr)) {
            queue.add(nextStr);
            int num = mp.get(current);
            ++num;
            mp.put(nextStr, num);
          }
        }
      }

      System.out.println(ans);
    } catch (RuntimeException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
