import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Day6 {
    public static void main(String[] args) {
        String test = "3,4,3,1,2";
        String data = "4,1,1,1,5,1,3,1,5,3,4,3,3,1,3,3,1,5,3,2,4,4,3,4,1,4,2,2,1,3,5,1,1,3,2,5,1,1,4,2,5,4,3,2,5,3,3,4,5,4,3,5,4,2,5,5,2,2,2,3,5,5,4,2,1,1,5,1,4,3,2,2,1,2,1,5,3,3,3,5,1,5,4,2,2,2,1,4,2,5,2,3,3,2,3,4,4,1,4,4,3,1,1,1,1,1,4,4,5,4,2,5,1,5,4,4,5,2,3,5,4,1,4,5,2,1,1,2,5,4,5,5,1,1,1,1,1,4,5,3,1,3,4,3,3,1,5,4,2,1,4,4,4,1,1,3,1,3,5,3,1,4,5,3,5,1,1,2,2,4,4,1,4,1,3,1,1,3,1,3,3,5,4,2,1,1,2,1,2,3,3,5,4,1,1,2,1,2,5,3,1,5,4,3,1,5,2,3,4,4,3,1,1,1,2,1,1,2,1,5,4,2,2,1,4,3,1,1,1,1,3,1,5,2,4,1,3,2,3,4,3,4,2,1,2,1,2,4,2,1,5,2,2,5,5,1,1,2,3,1,1,1,3,5,1,3,5,1,3,3,2,4,5,5,3,1,4,1,5,2,4,5,5,5,2,4,2,2,5,2,4,1,3,2,1,1,4,4,1,5";

        try {
            int NUM_OF_DAYS = 256;
            /*
            int fishes = 0;
            int fileId = 0;

            ByteArrayOutputStream dtlSt = new ByteArrayOutputStream();
            File tempFolder = new File("data");
            File outputFile = new File(tempFolder, "" + fileId);
            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                for (String workData : test.split(",")) {
                    if (workData.isBlank()) continue;
                    int days = Integer.parseInt(workData);
                    outputStream.write((byte)(NUM_OF_DAYS - days));
                }
            }

            Instant start = Instant.now();
            byte[] dtl = new byte[32768];

            while (true) {
                FileInputStream ios = new FileInputStream(new File(tempFolder, "" + fileId));
                int read = 0;
                fileId++;
                outputFile = new File(tempFolder, "" + fileId);
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
                        fishes++;
                    }
                    outputStream.flush();
                }

                if(!haveRead) break;
                outputStream.close();
                ios.close();
            }
            System.out.println(fishes);
            Instant end = Instant.now();
            System.out.println(Duration.between(start, end));
            */

            ExecutorService es = Executors.newFixedThreadPool(6);
            List<Future<BigInteger>> resultList = new ArrayList<>();
            Instant start = Instant.now();

            int jobId = 0;

            Map<Integer, FishCore> jobs = new HashMap<>();

            for (String workData : data.split(",")) {
                if (workData.isBlank()) continue;
                int days = Integer.parseInt(workData);
                if (!jobs.containsKey(days)) {
                    jobs.put(days, new FishCore("data/j" + jobId, (byte)(NUM_OF_DAYS - days)));
                    jobId++;
                }
                jobs.get(days).incFishes();
            }

            resultList = es.invokeAll(jobs.values());

            es.awaitTermination(5, TimeUnit.SECONDS);

            BigInteger fishes = BigInteger.ZERO;
            for (int i = 0; i < resultList.size(); i++) {
                Future<BigInteger> result = resultList.get(i);
                BigInteger number = null;
                try {
                    number = result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                fishes = fishes.add(number);
            }
            System.out.println(fishes);

            es.shutdown();

            Instant end = Instant.now();
            System.out.println(Duration.between(start, end));

            /*
            for (String workData : test.split(",")) {
                for (int i=0; i < 256; i++) {
                    String children = "";
                    String newWorkData = "";
                    for (String f : workData.split(",")) {
                        if (f.isBlank()) continue;
                        int fish = Integer.parseInt(f);
                        if (fish == 0) {
                            children += "8,";
                            fish = 6;
                        } else {
                            fish--;
                        }
                        newWorkData += fish + ",";
                    }
                    workData = newWorkData + children;
                }
                fishes += workData.length() / 2;
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
