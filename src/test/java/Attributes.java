import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Attributes {
    private String URL;
    private String ExcelPath;
    private String CertificationPath;

    public Attributes() {
        Properties props = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("config.properties"))) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.URL = (String)props.getProperty("loginURL");
        this.ExcelPath = (String)props.getProperty("excelPath");
        this.CertificationPath = (String)props.getProperty("certificateFilePath");
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getExcelPath() {
        return ExcelPath;
    }

    public void setExcelPath(String ExcelPath) {
        this.ExcelPath = ExcelPath;
    }

    public String getCertificationPath() {
        return CertificationPath;
    }

    public void setCertificationPath(String CertificationPath) {
        this.CertificationPath = CertificationPath;
    }
}
