package mongodbServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teclado {

    private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    public static String getString() throws Exception {
        String ret = null;
        try{
            ret = teclado.readLine();
        }catch(Exception e){}
        return ret;
    }

    public static byte getByte() throws Exception {
        byte ret = (byte)0;
        try{
            ret = Byte.parseByte(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e){
            throw new Exception("Byte invalido");
        }
        return ret;
    }

    public static short getShort() throws Exception {
        short ret = (short)0;
        try{
            ret = Short.parseShort(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e) {
            throw new Exception("Short invalido");
        }
        return ret;
    }

    public static int getInt() throws Exception {
        int ret = 0;
        try{
            ret = Integer.parseInt(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e) {
            throw new Exception("Int invalido");
        }
        return ret;
    }

    public static long getLong() throws Exception {
        long ret = 0L;
        try {
            ret = Long.parseLong(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e) {
            throw new Exception("Long invalido");
        }
        return ret;
    }

    public static float getFloat() throws Exception {
        float ret = 0.0F;
        try{
            ret = Float.parseFloat(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e) {
            throw new Exception("Float invalido");
        }
        return ret;
    }

    public static double getDouble() throws Exception {
        double ret = 0.0;
        try{
            ret = Double.parseDouble(teclado.readLine());
        }catch(IOException e){}
        catch (NumberFormatException e) {
            throw new Exception("Double invalido");
        }
        return ret;
    }

    public static boolean getBoolean() throws Exception {
        boolean ret = false;
        try{
            String ax = teclado.readLine();
            if(ax == null) {
                throw new Exception("Boolean invalido");
            }
            if(!ax.equals("true") && !ax.equals("false")){
                throw new Exception("Boolean invalido");
            }
            ret = Boolean.parseBoolean(ax);
        }catch(IOException e){}
        return ret;
    }

    public static char getChar() throws Exception {
        char ret = ' ';
        try {
            String ax = teclado.readLine();
            if(ax == null) {
                throw new Exception("Char invalido");
            }
            if(ax.length() != 1){
                throw new Exception("Char invalido");
            }
            ret = ax.charAt(0);
        }catch (IOException e) {}
        return ret;
    }
}
