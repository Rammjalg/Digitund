package com.digitund;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseX {

  public static final char[] DICTIONARY_16 =
      new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  public static final char[] DICTIONARY_62 =
      new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
          'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
          'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

  protected char[] dictionary;


  public BaseX(char[] dictionary) {
    this.dictionary = dictionary;
  }

  public BaseX() {
    this.dictionary = DICTIONARY_62;
  }

  public String encode(BigInteger value) {

    List<Character> result = new ArrayList<Character>();
    BigInteger base = new BigInteger("" + dictionary.length);
    int exponent = 1;
    BigInteger remaining = value;
    while (true) {
      BigInteger a = base.pow(exponent); //16^1 = 16
      BigInteger b = remaining.mod(a); //119 % 16 = 7 | 112 % 256 = 112
      BigInteger c = base.pow(exponent - 1);
      BigInteger d = b.divide(c);

      //if d > dictionary.length, we have a problem. but BigInteger doesnt have
      //a greater than method :-(  hope for the best. theoretically, d is always
      //an index of the dictionary!
      result.add(dictionary[d.intValue()]);
      remaining = remaining.subtract(b); //119 - 7 = 112 | 112 - 112 = 0

      //finished?
      if (remaining.equals(BigInteger.ZERO)) {
        break;
      }

      exponent++;
    }

    //need to reverse it, since the start of the list contains the least significant values
    StringBuffer sb = new StringBuffer();
    for (int i = result.size() - 1; i >= 0; i--) {
      sb.append(result.get(i));
    }
    return sb.toString();
  }

  /**
   * decodes the given string from the base of the dictionary provided in the constructor.
   *
   * @param str the string to decode.
   * @return the decoded number.
   */
  public BigInteger decode(String str) {

    //reverse it, coz its already reversed!
    char[] chars = new char[str.length()];
    str.getChars(0, str.length(), chars, 0);

    char[] chars2 = new char[str.length()];
    int i = chars2.length - 1;
    for (char c : chars) {
      chars2[i--] = c;
    }

    //for efficiency, make a map
    Map<Character, BigInteger> dictMap = new HashMap<Character, BigInteger>();
    int j = 0;
    for (char c : dictionary) {
      dictMap.put(c, new BigInteger("" + j++));
    }

    BigInteger bi = BigInteger.ZERO;
    BigInteger base = new BigInteger("" + dictionary.length);
    int exponent = 0;
    for (char c : chars2) {
      BigInteger a = dictMap.get(c);
      BigInteger b = base.pow(exponent).multiply(a);
      bi = bi.add(new BigInteger("" + b));
      exponent++;
    }

    return bi;

  }
}
