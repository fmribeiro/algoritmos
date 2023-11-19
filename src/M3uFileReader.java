import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class M3uFileReader {

    public static void main(String[] args) throws IOException {
        //tv_channels_0423320_plus (4).m3u
        File file = new File("/home/fmribeiro/Downloads/tv_channels_1188677_plus.m3u");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")));
        String nowStrr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        try (FileWriter fileWriter = new FileWriter("/home/fmribeiro/Downloads/canais_filmes_" + nowStrr + ".m3u", false)) {
            String newLine = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("group-title=\"Canais")) {
                    fileWriter.write(line + newLine);
                    fileWriter.write(bufferedReader.readLine() + newLine);
                }
                if (line.contains("group-title=\"Filmes")) {
                    fileWriter.write(line + newLine);
                    fileWriter.write(bufferedReader.readLine() + newLine);
                }
//                if (line.contains("group-title=\"SERIES")) {
//                    fileWriter.write(line + newLine);
//                    fileWriter.write(bufferedReader.readLine() + newLine);
//                }
            }
        }
    }
}
