import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

public class FishCore implements Callable<BigInteger> {
    private final File tempFolder;
    private final int startValue;
    private BigInteger fishStartCount = BigInteger.ZERO;

    public FishCore(String folder, byte startValue) throws Exception {
        tempFolder = new File(folder);
        tempFolder.mkdirs();
        File outputFile = new File(tempFolder, "0");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(startValue);
        outputStream.flush();
        outputStream.close();
        this.startValue = startValue;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger fishes = BigInteger.ZERO;
        Instant start = Instant.now();
        byte[] dtl = new byte[32768];
        int fileId = 0;

        while (true) {
            FileInputStream ios = new FileInputStream(new File(tempFolder, "" + fileId));
            int read = 0;
            fileId++;
            File outputFile = new File(tempFolder, "" + fileId);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            boolean haveRead = false;
            while ((read = ios.read(dtl)) != -1) {
                haveRead = true;
                for (int j = 0; j < read; j++) {
                    for (int i = dtl[j] & 0xff; i > 0; i -= 7) {
                        if(i - 9 > 0) {
                            outputStream.write((byte) (i - 9));
                        } else {
                            outputStream.write((byte) 0);
                        }
                    }
                    fishes = fishes.add(BigInteger.ONE);
                }
                outputStream.flush();
            }

            if(!haveRead) break;
            outputStream.close();
            ios.close();
        }

        Instant end = Instant.now();
        System.out.println(this.startValue + " = " + fishes + " in " + Duration.between(start, end));

        return fishes.multiply(fishStartCount);
    }

    public void incFishes() {
        this.fishStartCount = this.fishStartCount.add(BigInteger.ONE);
    }
}
