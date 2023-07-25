package comp3350.highschoolhub.business;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.highschoolhub.application.Main;
import comp3350.highschoolhub.presentation.Messages;

public class CopyDatabase {

    public static void copyDatabaseToDevice(Activity activity) {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = activity.getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = activity.getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory, activity);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(activity, "Unable to access application data: " + ioe.getMessage());
        }
        finally {
            new AccessUsers();
            new AccessHighSchools();
            new AccessRequests();
        }
    }

    private static void copyAssetsToDirectory(String[] assets, File directory, Activity activity) throws IOException {
        AssetManager assetManager = activity.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
