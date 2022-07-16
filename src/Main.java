import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;

public class Main {
    public static String mainPath = "/home/aleksandr/Games";

    public static void main(String[] args) {
        StringBuilder logger = new StringBuilder();
        for (String dir: new String[] {
                "src", "src/main", "src/test",
                "res", "res/drawables", "res/vectors", "res/icons",
                "savegames", "temp"}) {
            logger.append(createDir(dir));
            logger.append('\n');
        }
        logger.append(createFile("src/main", "Main.java"));
        logger.append('\n');
        logger.append(createFile("src/main", "Utils.java"));
        logger.append('\n');
        logger.append(createFile("temp", "temp.txt"));
        logger.append('\n');
        writeLog(logger);
    }

    public static String createDir(String dirSubPath) {
        File dir = new File(mainPath + "/" + dirSubPath);
        String logTime = java.time.LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        if (dir.mkdir())
            return logTime + ": " + "Каталог " + dirSubPath + " создан";
        else return logTime + ": " + "Каталог " + dirSubPath + " не был создан";
    }

    public static String createFile(String dirSubPath, String fileName) {
        File file = new File(mainPath + "/" + dirSubPath, fileName);
        String logTime = java.time.LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        try {
            if (file.createNewFile())
                return logTime + ": " + "Файл " + dirSubPath + "/" + fileName + " создан";
            else return logTime + ": " + "Файл " + dirSubPath + "/" + fileName + " не был создан";
        } catch (IOException ex) {
            return logTime + ": " + "Файл " + dirSubPath + "/" + fileName + " не был создан. Отработал catch";
        }
    }

    public static void writeLog(StringBuilder logger) {
        try (FileWriter writer = new FileWriter(mainPath + "/temp/temp.txt", false)) {
            writer.write(logger.toString());
            writer.append('\n');
            writer.append("log is finished");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
