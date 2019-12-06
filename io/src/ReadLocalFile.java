import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReadLocalFile {
    public static void main(String[] args) throws IOException {

        // String str1 = readByLine("C:\\Users\\COMPUTER\\IdeaProjects\\io\\src\\cadao.txt");
        // System.out.println(str1);

        //String str2 = readUTF8("C:\\Users\\COMPUTER\\IdeaProjects\\io\\src\\cadao.txt");
        //System.out.println(str2);
        byte[] data = readBytesOnlineResource("https://vnkings.com/wp-content/uploads/2018/03/maxresdefault-2.jpg");
        savefile("D:\\dong.jpg",data);
        String str3 = downloadResource("https://raw.githubusercontent.com/nam-long/learning-java/master/resources/cadao.txt");
        System.out.println(str3);
        dowloadResource("https://raw.githubusercontent.com/nam-long/learning-java/master/resources/cadao.txt","C:\\Users\\COMPUTER\\IdeaProjects\\io\\src\\cadao.txt");
        listFile("");
    }

    public static String read(String filename) throws IOException {

        String str = "";

        InputStream is = new FileInputStream(filename);
        int c;
        while ((c = is.read()) != -1) {
            str += (char) c;
        }

        is.close();
        return str;
    }

    public static String readUTF8(String filename) throws IOException {

        String str = "";

        Reader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            str += line + '\n';
        }
        reader.close();

        return str;
    }

    public static String readByLine(String filename) throws IOException {
        String str = "";

        InputStream is = new FileInputStream(filename);
        DataInputStream br = new DataInputStream(is);
        String line;
        while ((line = br.readLine()) != null) {
            str += line + '\n';
        }
        is.close();

        return str;

    }

    public static String readBuffer(String filename) throws IOException {

        String str = null;
        // 0123456789
        byte[] buffer = new byte[10]; //[0][1][2]

        InputStream is = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int count;
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        str = new String(baos.toByteArray());
        baos.close();
        is.close();

        return str;
    }

    public static String readOnlineResource(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = new String(baos.toByteArray());

            is.close();
        }

        return str;
    }
    public static byte[] readBytesOnlineResource(String strUrl) throws IOException {

        byte[] str = null;

        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count;
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = baos.toByteArray();

            is.close();
        }

        return str;
    }
    public static void savefile(String filename, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }
    public static String downloadResourceByHttps(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count; /* So byte doc vao buffer */
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            str = new String(baos.toByteArray());

            is.close();
        }

        return str;
    }

    public static String downloadResource(String strUrl) throws IOException {

        String str = null;

        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[100];
        int count; /* So byte doc vao buffer */
        while ((count = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        str = new String(baos.toByteArray());

        is.close();

        return str;
    }
    public static void dowloadResource(String strUrl, String filename) throws IOException {
        String str = null;
        URL url = new URL(strUrl);
        InputStream is = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fis = new FileOutputStream(filename);

        byte[] buffere = new byte[100];
        int count;
        while ((count = bis.read(buffere)) != -1){
            fis.write(buffere, 0, count);

        }
        fis.close();
        is.close();
    }

    public static void downloadImage(String imageUrl, String filename) throws IOException {

        byte[] imageData = downloadImageData(imageUrl);
        savefile(filename, imageData);
    }

    public static byte[] downloadImageData(String strUrl) throws IOException {

        byte[] imageData = null;

        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int count; /* So byte doc vao buffer */
            while ((count = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, count);
            }
            imageData = baos.toByteArray();

            is.close();
        }

        return imageData;
    }
    public static ArrayList<String> ListPath = new ArrayList<>();
    public static void listFile(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files.length == 0){
            ListPath.add(folder.getCanonicalPath());
            System.out.println(folder.getCanonicalPath());
        }
        for (File f: files){
            if (f.isFile()){
                ListPath.add(f.getCanonicalPath());
                System.out.println(f.getCanonicalPath());
            }else {
                listFile(f.getPath());
            }
        }
    }
}


