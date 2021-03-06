package com.lyl.utils;

import java.net.InetAddress;

/**
 * Created by 潘淮  on 2018/12/26.<br>
 */
public class RandomString {

    private String sep = "";

    private static final int IP;

    private static short counter = (short) 0;

    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    private static RandomString uuidgen = new RandomString();

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }


    public static RandomString getInstance() {
        return uuidgen;
    }


    public static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }


    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }


    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }


    protected int getJVM() {
        return JVM;
    }


    protected synchronized short getCount() {
        if (counter < 0) {
            counter = 0;
        }
        return counter++;
    }


    protected int getIP() {
        return IP;
    }


    protected short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }


    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }


    public String generate() {
        return new StringBuffer(36)
                .append(format(getIP()))
                .append(sep)
                .append(format(getJVM()))
                .append(sep)
                .append(format(getHiTime()))
                .append(sep)
                .append(format(getLoTime()))
                .append(sep)
                .append(format(getCount()))
                .toString();
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        String id;
        RandomString uuid = RandomString.getInstance();

        for(int i = 0;i<100;i++){
            id = uuid.generate();
            System.out.println(id +":长度"+id.length());
        }
    }
}
